import 'clay-icon';

import debounce from 'metal-debounce';
import Component from 'metal-component';
import Soy, {Config} from 'metal-soy';

import template from './Cart.soy';

import './CartProduct.es';
import './Summary.es';

class Cart extends Component {

	addPendingOperation(productId) {
		if (!this.pendingOperations.includes(productId)) {
			return (this.pendingOperations = [...this.pendingOperations, productId]);
		}

		return false;
	}

	attached() {
		window.Liferay.on(
			'updateCart',
			(evt) => {
				const updateCartData = evt.details[0];

				this.products = updateCartData.products;
				this.productsCount = updateCartData.products.length;
				this.summary = updateCartData.summary;

				this.isLoading = false;
				this.pendingOperations = [];

				return true;
			}
		);

		return this.cartId && this.getProducts();
	}

	deleteProduct(productId) {
		this.setProductProperties(
			productId,
			{
				inputChanged: false,
				isCollapsed: true,
				isDeleteDisabled: true,
				isUpdating: false
			}
		);

		return this.productsCount = this.productsCount - 1;
	}

	getProductProperty(productId, key) {
		return this.products.reduce(
			(property, product) => {
				if (property) {
					return property;
				}
				if (product.id === productId) {
					try {
						return product[key];
					}
					catch (error) {
						console.warn(`Property ${key} not found!`);

						return undefined;
					}
				}
			},
			false
		);
	}

	getProducts() {
		return fetch(
			this.cartAPI + '/' + this.cartId,
			{
				method: 'GET'
			}
		)
			.then(response => response.json())
			.then(
				updatedCart => {
					this.products = updatedCart.products;
					this.productsCount = updatedCart.products.length;
					this.summary = updatedCart.summary;

					return !!(this.products && this.summary);
				}
			)
			.catch(
				err => {
					return console.log(err);
				}
			);
	}

	handleCancelItemDeletion(productId) {
		this.setProductProperties(
			productId,
			{
				isDeleteDisabled: false,
				isDeleting: false
			}
		);

		return this.removePendingOperation();
	}

	handleDeleteItem(productId) {
		const deleteDisabled = this.getProductProperty(
			productId,
			'isDeleteDisabled'
		);

		if (deleteDisabled) {
			return false;
		}

		this.setProductProperties(
			productId, {
				isDeleteDisabled: true,
				isDeleting: true
			}
		);

		return setTimeout(() => {
			const deleting = this.getProductProperty(productId, 'isDeleting');

			if (deleting) {
				this.setProductProperties(
					productId,
					{
						isCollapsed: true
					}
				);

				this.getProductProperty(productId, 'sendDeleteRequest')();
			}
		}, 2000);
	}

	handleSubmitQuantity(productId, quantity) {
		this.setProductProperties(
			productId,
			{
				inputChanged: false
			}
		);

		return this.updateProductQuantity(productId, quantity);
	}

	normalizeProducts(rawProducts) {
		const normalizedProducts = rawProducts.map(
			(productData) => {
				return Object.assign(
					{
						sendUpdateRequest: debounce(
							() => this.sendUpdateRequest(productData.id),
							500
						),
						sendDeleteRequest: debounce(
							() => this.sendDeleteRequest(productData.id),
							500
						)
					},
					productStateSchema,
					productData
				);
			}
		);

		return normalizedProducts;
	}

	removePendingOperation(productId) {
		return (this.pendingOperations = this.pendingOperations.filter(
			el => el !== productId
		));
	}

	sendDeleteRequest(productId) {
		this.addPendingOperation(productId);

		return fetch(
			this.cartAPI + '/cart-item/' + productId,
			{
				method: 'DELETE'
			}
		)
			.then(response => response.json())
			.then(
				(jsonresponse) => {
					if (jsonresponse.success) {
						this.removePendingOperation(productId);
						this.setProductProperties(
							productId,
							{
								isDeleteDisabled: false
							}
						);

						this.summary = jsonresponse.summary;

						const productsToBeRemoved = this.subtractProducts(this.products, jsonresponse.products);

						productsToBeRemoved.forEach(element => {
							this.deleteProduct(element.id);
						});
					}
					else if (jsonresponse.errorMessages) {
						this.setProductProperties(productId, {
							errorMessages: jsonresponse.errorMessages,
							isDeleteDisabled: false,
							isUpdating: false
						});

						this.removePendingOperation(productId);
					}
					else {
						this.setProductProperties(productId, {
							errorMessages: jsonresponse.error,
							isDeleteDisabled: false,
							isUpdating: false
						});

						this.removePendingOperation(productId);
					}
				}
			)
			.catch(
				err => {
					this.removePendingOperation(productId);

					console.log(err);
				}
			);
	}

	sendUpdateRequest(productId) {
		return fetch(
			this.cartAPI + '/cart-item/' + productId,
			{
				body: JSON.stringify(
					{
						quantity: this.getProductProperty(productId, 'quantity')
					}
				),
				headers: new Headers({'Content-Type': 'application/json'}),
				method: 'PUT'
			}
		)
			.then(response => response.json())
			.then(
				(jsonresponse) => {
					if (jsonresponse.success) {
						this.removePendingOperation(productId);

						const updatedPrice = jsonresponse.products.reduce(
							(acc, el) => (el.id === productId ? el.price : acc),
							null);

						this.setProductProperties(productId, {
							isDeleteDisabled: false,
							isUpdating: false,
							price: updatedPrice
						});

						return this.summary = jsonresponse.summary;
					}
					else if (jsonresponse.errorMessages) {
						this.setProductProperties(productId, {
							errorMessages: jsonresponse.errorMessages,
							isDeleteDisabled: false,
							isUpdating: false
						});

						this.removePendingOperation(productId);
					}
					else {
						var validatorErrors = jsonresponse.validatorErrors;

						if (validatorErrors) {
							this.setProductProperties(productId, {
								errorMessages: validatorErrors.map((item) => item.message),
								isDeleteDisabled: false,
								isUpdating: false
							});
						}
						else {
							this.setProductProperties(productId, {
								errorMessages: jsonresponse.error,
								isDeleteDisabled: false,
								isUpdating: false
							});
						}

						this.removePendingOperation(productId);
					}
				}
			)
			.catch(
				err => {
					this.removePendingOperation(productId);
					this.setProductProperties(productId, {
						isDeleteDisabled: false,
						isUpdating: false
					});

					console.log(err);
				}
			);
	}

	setProductProperties(productId, newProperties) {
		return this.products = this.products.map(product =>
			product.id === productId ?
				Object.assign({}, product, newProperties) :
				product
		);
	}

	subtractProducts(orArray, subArray) {
		const result = subArray.reduce(
			(arrayToBeFiltered, elToRemove) => {
				return arrayToBeFiltered.filter((elToCheck) => elToCheck.id !== elToRemove.id);
			},
			orArray
		);

		return !subArray.length && result;
	}

	syncPendingOperations(pendingOperations) {
		this.isLoading = !!pendingOperations.length;
	}

	toggleCart() {
		if (this.isOpen = !this.isOpen) {
			return true;
		}

		return false;
	}

	updateProductQuantity(productId, quantity) {
		this.addPendingOperation(productId);
		this.setProductProperties(
			productId,
			{
				isDeleteDisabled: true,
				isUpdating: true,
				quantity: quantity
			}
		);

		return this.getProductProperty(productId, 'sendUpdateRequest')();
	}

}

Soy.register(Cart, template);

const productStateSchema = {
	inputChanged: false,
	isCollapsed: false,
	isDeleteDisabled: false,
	isDeleting: false,
	isUpdating: false
};

Cart.STATE = {
	cartAPI: Config.string().required(),
	cartId: Config.oneOfType(
		[
			Config.number(),
			Config.string()
		]
	),
	checkoutUrl: Config.string().required(),
	detailsUrl: Config.string().required(),
	isDisable: Config.bool().value(false),
	isLoading: Config.bool().value(false),
	isOpen: Config.bool().value(true),
	pendingOperations: Config.array().value(
		[]
	),
	productsCount: Config.number().value(0),
	spritemap: Config.string().required(),
	summary: Config.shapeOf(
		{
			checkoutUrl: Config.string().value(''),
			subtotal: Config.string().value(''),
			total: Config.string().value(''),
			discount: Config.string().value(''),
			itemsQuantity: Config.number().value(0)
		}
	)
};

export {Cart};
export default Cart;