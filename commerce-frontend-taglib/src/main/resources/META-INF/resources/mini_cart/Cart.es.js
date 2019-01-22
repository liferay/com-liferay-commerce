import 'clay-icon';

import debounce from 'metal-debounce';
import Component from 'metal-component';
import Soy, {Config} from 'metal-soy';

import template from './Cart.soy';

import './CartProduct.es';
import './Summary.es';

class Cart extends Component {

	toggleCart() {
		return this.cartId && (this.isOpen = !this.isOpen);
	}

	attached() {
		window.Liferay.on(
			'updateCart',
			(evt) => {
				const updateCartData = evt.details[0];
				this.products = updateCartData.products;
				this.summary = updateCartData.summary;
				this.isLoading = false;
				this.pendingOperations = [];
				this.productsCount = updateCartData.products.length;
				return true;
			}
		);
		return this.cartId && this.getProducts();
	}

	syncPendingOperations(pendingOperations) {
		this.isLoading = !!pendingOperations.length;
	}

	normalizeProducts(rawProducts) {
		const normalizedProducts = rawProducts.map(
			productData => {
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

	updateProductQuantity(productId, quantity) {
		this.addPendingOperation(productId);
		this.setProductProperties(
			productId,
			{
				isDeleteDisabled: true,
				quantity: quantity,
				isUpdating: true
			}
		);
		return this.getProductProperty(productId, 'sendUpdateRequest')();
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

	deleteProduct(productId) {
		this.setProductProperties(
			productId,
			{
				isDeleteDisabled: true,
				inputChanged: false,
				isUpdating: false,
				isCollapsed: true
			}
		);
		return this.productsCount = this.productsCount - 1;
	}

	setProductProperties(productId, newProperties) {
		return this.products = this.products.map(product =>
			product.id === productId ?
				Object.assign({}, product, newProperties) :
				product
		);
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

	subtractProducts(orArray, subArray) {
		const result = subArray.reduce(
			(arrayToBeFiltered, elToRemove) => {
				return arrayToBeFiltered.filter((elToCheck) => elToCheck.id !== elToRemove.id);
			},
			orArray
		);
		return !subArray.length && result;
	}

	handleDeleteItem(productId) {
		const isDeleteDisabled = this.getProductProperty(
			productId,
			'isDeleteDisabled'
		);

		if (isDeleteDisabled) {
			return false;
		}

		this.setProductProperties(
			productId, {
				isDeleteDisabled: true,
				isDeleting: true
			}
		);

		return setTimeout(() => {
			const isDeleting = this.getProductProperty(productId, 'isDeleting');
			if (isDeleting) {
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

	handleCancelItemDeletion(productId) {
		this.setProductProperties(
			productId,
			{
				isDeleting: false,
				isDeleteDisabled: false
			}
		);
		return this.removePendingOperation();
	}

	addPendingOperation(productId) {
		if (!this.pendingOperations.includes(productId)) {
			return (this.pendingOperations = [...this.pendingOperations, productId]);
		}
		return false;
	}

	removePendingOperation(productId) {
		return (this.pendingOperations = this.pendingOperations.filter(
			el => el !== productId
		));
	}

	sendUpdateRequest(productId) {
		return fetch(
			this.cartAPI + '/cart-item/' + productId,
			{
				body: JSON.stringify(
					{
						cartItem: {
							quantity: this.getProductProperty(productId, 'quantity')
						}
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
						const updatedPrice = jsonresponse.products.reduce(
							(acc, el) => (el.id === productId ? el.price : acc), null);
						this.removePendingOperation(productId);
						this.setProductProperties(productId, {
							isDeleteDisabled: false,
							isUpdating: false,
							price: updatedPrice
						});
						return this.summary = jsonresponse.summary;
					}
					else if (jsonresponse.errorMessages) {
						this.setProductProperties(productId, {
							isDeleteDisabled: false,
							isUpdating: false,
							errorMessages: jsonresponse.errorMessages
						});
						this.removePendingOperation(productId);
					}
					else {
						var validatorErrors = jsonresponse.validatorErrors;

						if (validatorErrors) {
							this.setProductProperties(productId, {
								isDeleteDisabled: false,
								isUpdating: false,
								errorMessages: validatorErrors.map((item) => item.message)
							});
						}
						else {
							this.setProductProperties(productId, {
								isDeleteDisabled: false,
								isUpdating: false,
								errorMessages: jsonresponse.error
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
					this.productsCount = this.products.length;
					return !!(this.products && this.summary);
				}
			)
			.catch(
				err => {
					return console.log(err);
				}
			);
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
							isDeleteDisabled: false,
							isUpdating: false,
							errorMessages: jsonresponse.errorMessages
						});
						this.removePendingOperation(productId);
					}
					else {
						this.setProductProperties(productId, {
							isDeleteDisabled: false,
							isUpdating: false,
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
	isDeleting: false,
	isDeleteDisabled: false,
	isCollapsed: false,
	isUpdating: false
};

Cart.STATE = {

	spritemap: Config.string().required(),

	cartAPI: Config.string().required(),

	isOpen: Config.bool().value(true),

	isDisable: Config.bool().value(false),

	cartId: Config.oneOfType(
		[
			Config.number(),
			Config.string()
		]
	),

	products: {
		setter: 'normalizeProducts',
		value: null
	},

	productsCount: Config.number().value(0),

	summary: Config.shapeOf(
		{
			checkoutUrl: Config.string().value(''),
			subtotal: Config.string().value(''),
			total: Config.string().value(''),
			discount: Config.string().value(''),
			itemsQuantity: Config.number().value(0)
		}
	),

	isLoading: Config.bool().value(false),

	pendingOperations: Config.array().value(
		[]
	),

	detailsUrl: Config.string().required(),

	checkoutUrl: Config.string().required()
};

export {Cart};
export default Cart;