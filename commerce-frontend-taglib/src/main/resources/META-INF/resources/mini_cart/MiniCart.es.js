import 'clay-icon';

import debounce from 'metal-debounce';
import Component from 'metal-component';
import Soy, {Config} from 'metal-soy';

import template from './MiniCart.soy';

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
				this._loading = false;
				this.pendingOperations = [];
				this.productsQuantity = updateCartData.products.length;
				return true;
			}
		);
		return this.cartId && this.getProducts();
	}

	syncPendingOperations(pendingOperations) {
		this._loading = !!pendingOperations.length;
	}

	normalizeProducts(rawProducts) {
		const normalizedProducts = rawProducts.map(
			productData => {
				return Object.assign(
					{
						sendDeleteRequest: debounce(
							() => this.sendDeleteRequest(productData.id),
							500
						),
						sendUpdateRequest: debounce(
							() => this.sendUpdateRequest(productData.id),
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

	updateProductQuantity(productId, quantity) {
		this.addPendingOperation(productId);
		this.setProductProperties(
			productId,
			{
				deleteDisabled: true,
				updating: true,
				quantity: quantity
			}
		);
		return this.cartId && this.getProducts();
	}

	_handleSubmitQuantity(productId, quantity) {
		this.setProductProperties(
			productId,
			{
				inputChanged: false
			}
		);
		return this.updateProductQuantity(productId, quantity);
	}

	deleteProduct(productId) {
		this.setProductProperties(
			productId,
			{
				inputChanged: false,
				collapsed: true,
				deleteDisabled: true,
				updating: false
			}
		);
		this.productsQuantity = this.productsQuantity - 1;
		return this.productsQuantity;
	}

	setProductProperties(productId, newProperties) {
		this.products = this.products.map(product =>
			product.id === productId ?
				Object.assign(
					{},
					product,
					newProperties
				) : product
		);
		return this.products;
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
						return null;
					}
				}
			},
			false
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

	_handleDeleteItem(productId) {
		const deleteDisabled = this.getProductProperty(
			productId,
			'deleteDisabled'
		);

		if (deleteDisabled) {
			return false;
		}

		this.setProductProperties(
			productId, {
				deleteDisabled: true,
				deleting: true
			}
		);

		return setTimeout(() => {
			const deleting = this.getProductProperty(productId, 'deleting');
			if (deleting) {
				this.setProductProperties(
					productId,
					{
						collapsed: true
					}
				);

				this.getProductProperty(productId, 'sendDeleteRequest')();
			}
		}, 2000);
	}

	_handleCancelItemDeletion(productId) {
		this.setProductProperties(
			productId,
			{
				deleting: false,
				deleteDisabled: false
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
							deleteDisabled: false,
							updating: false,
							price: updatedPrice,
							errorMessages: null
						});

						return this.summary = jsonresponse.summary;
					}

					if (jsonresponse.errorMessages && jsonresponse.errorMessages.length) {
						this.setProductProperties(productId, {
							deleteDisabled: false,
							updating: false,
							errorMessages: jsonresponse.errorMessages
						});
						return this.removePendingOperation(productId);
					}

					var validatorErrors = jsonresponse.validatorErrors;

					if (validatorErrors) {
						this.setProductProperties(productId, {
							deleteDisabled: false,
							updating: false,
							errorMessages: validatorErrors.map((item) => item.message)
						});
					} else {
						this.setProductProperties(productId, {
							deleteDisabled: false,
							updating: false,
							errorMessages: jsonresponse.error
						});
					}
					return this.removePendingOperation(productId);

				}
			)
			.catch(
				err => {
					this.removePendingOperation(productId);
					this.setProductProperties(productId, {
						deleteDisabled: false,
						updating: false
					});

					console.log(err);
				}
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
					this.summary = updatedCart.summary;
					this.productsQuantity = this.products.length;
					return !!(this.products && this.summary);
				}
			)
			.catch(
				err => {
					console.log(err);
				}
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
		)
			.then(response => response.json())
			.then(
				(jsonresponse) => {
					if (jsonresponse.success) {
						this.removePendingOperation(productId);
						this.setProductProperties(
							productId,
							{
								deleteDisabled: false
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
							deleteDisabled: false,
							updating: false,
							errorMessages: jsonresponse.errorMessages
						});
						this.removePendingOperation(productId);
					}
					else {
						this.setProductProperties(productId, {
							deleteDisabled: false,
							updating: false,
							errorMessages: jsonresponse.error
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

}

Soy.register(Cart, template);

const productStateSchema = {
	inputChanged: false,
	deleting: false,
	deleteDisabled: false,
	collapsed: false,
	updating: false
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
	disabled: Config.bool().value(false),
	_loading: Config.bool().internal().value(false),
	_open: Config.bool().internal().value(false),
	pendingOperations: Config.array().value(
		[]
	),
	products: {
		setter: 'normalizeProducts',
		value: null
	},
	productsQuantity: Config.number(),
	spritemap: Config.string().required(),
	summary: Config.shapeOf(
		{
			checkoutUrl: Config.string(),
			discount: Config.string(),
			itemsQuantity: Config.number(),
			subtotal: Config.string(),
			total: Config.string()
		}
	)
};

export {Cart};
export default Cart;