import 'clay-icon';

import debounce from 'metal-debounce';
import Component from 'metal-component';
import Soy, {Config} from 'metal-soy';

import template from './MiniCart.soy';

import './CartProduct.es';
import './Summary.es';

class Cart extends Component {

	created() {
		this._handleClickOutside = this._handleClickOutside.bind(this);
	}

	attached() {
		window.Liferay.on(
			'updateCart',
			(evt) => {
				const updateCartData = evt.details[0];
				this.products = updateCartData.products;
				this.summary = updateCartData.summary;
				this._loading = false;
				this.pendingOperations = [];
				return true;
			}
		);
		return this.cartId && this._getProducts();
	}

	open() {
		this._open = true;
		this.element.addEventListener('transitionend', () => {
			window.addEventListener('click', this._handleClickOutside);
		});
		return this._open;
	}

	close() {
		this._open = false;
		this.element.addEventListener('transitionend', () => {
			window.removeEventListener('click', this._handleClickOutside);
		});
		return this._open;
	}

	toggle() {
		return this._open ? this.close() : this.open();
	}

	syncPendingOperations(pendingOperations) {
		this._loading = !!pendingOperations.length;
	}

	syncProducts() {
		this.productsQuantity = this.products.reduce(
			(quantity, product) => {
				return product.collapsed ? quantity : quantity + 1
			},
			0
		);
	}

	_handleClickOutside(e) {
		const topBar = document.querySelector('.minium-frame__topbar');
		if (
			topBar.contains(e.target) && !this.element.contains(e.target)
		) {
			this.close();
		}
	}

	_handleSubmitQuantity(productId, quantity) {
		this._setProductProperties(
			productId,
			{
				inputChanged: false
			}
		);
		return this._updateProductQuantity(productId, quantity);
	}

	_handleDeleteItem(productId) {
		const deleteDisabled = this._getProductProperty(
			productId,
			'deleteDisabled'
		);

		if (deleteDisabled) {
			return false;
		}

		this._setProductProperties(
			productId,
			{
				deleteDisabled: true,
				deleting: true
			}
		);

		return setTimeout(
			() => {
				const deleting = this._getProductProperty(productId, 'deleting');
				if (deleting) {
					this._setProductProperties(
						productId,
						{
							collapsed: true
						}
					);
					this._getProductProperty(productId, 'sendDeleteRequest')();
				}
			},
			2000
		);
	}

	_handleCancelItemDeletion(productId) {
		this._setProductProperties(
			productId,
			{
				deleteDisabled: false,
				deleting: false
			}
		);
		return this._removePendingOperation();
	}

	_handleProductUpdate(productId, products) {
		const updatedPrice = products.reduce(
			(acc, el) => {
				return el.id === productId ? el.price : acc;
			},
			null
		);
		this._removePendingOperation(productId);
		return this._setProductProperties(
			productId,
			{
				deleteDisabled: false,
				errorMessages: null,
				price: updatedPrice,
				updating: false
			}
		);
	}

	_handleResponseErrors(productId, res) {
		const errorMessages = !!res.errorMessages ? res.errorMessages : res.validatorErrors.map(item => item.message);
		return this._setProductProperties(
			productId,
			{
				deleteDisabled: false,
				updating: false,
				errorMessages
			}
		);
	}

	_normalizeProducts(rawProducts) {
		const normalizedProducts = rawProducts.map(
			productData => {
				return productData.sendUpdateRequest 
					? Object.assign(
						{
							sendDeleteRequest: debounce(
								() => this._sendDeleteRequest(productData.id),
								500
							),
							sendUpdateRequest: debounce(
								() => this._sendUpdateRequest(productData.id),
								500
							)
						},
						productStateSchema,
						productData
					) 
					: productData;
			}
		);
		return normalizedProducts;
	}

	_updateProductQuantity(productId, quantity) {
		this._addPendingOperation(productId);
		this._setProductProperties(
			productId,
			{
				deleteDisabled: true,
				quantity: quantity,
				updating: true
			}
		);
		return this._getProductProperty(productId, 'sendUpdateRequest')();
	}

	_deleteProduct(productId) {
		return this._setProductProperties(
			productId,
			{
				collapsed: true,
				deleteDisabled: true,
				inputChanged: false,
				updating: false
			}
		);
	}

	_setProductProperties(productId, newProperties) {
		this.products = this.products.map(
			product => {
				return product.id === productId 
					? Object.assign(
						{},
						product,
						newProperties
					)
					: product;
			}
		);
		return this.products;
	}

	_getProductProperty(productId, key) {
		return this.products.reduce(
			(property, product) => {
				return product.id === productId ? product[key] : property;
			},
			null
		);
	}

	_subtractProducts(orArray, subArray) {
		const result = subArray.reduce(
			(arrayToBeFiltered, elToRemove) => {
				return arrayToBeFiltered.filter((elToCheck) => elToCheck.id !== elToRemove.id);
			},
			orArray
		);
		return !subArray.length && result;
	}

	_addPendingOperation(productId) {
		if (!this.pendingOperations.includes(productId)) {
			this.pendingOperations = [...this.pendingOperations, productId];
		}
		return this.pendingOperations;
	}

	_removePendingOperation(productId) {
		this.pendingOperations = this.pendingOperations.filter(
			el => el !== productId
		);
		return this.pendingOperations;
	}

	_sendUpdateRequest(productId) {
		return fetch(
			this.cartAPI + '/cart-item/' + productId + '?groupId=' + themeDisplay.getScopeGroupId() + '&commerceAccountId=' + this.commerceAccountId,
			{
				body: JSON.stringify(
					{
						cartItem: {
							quantity: this._getProductProperty(productId, 'quantity')
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
							(acc, el) => {
								return el.id === productId ? el.price : acc;
							},
							null
						);
						this._removePendingOperation(productId);
						this._setProductProperties(
							productId,
							{
								deleteDisabled: false,
								errorMessages: null,
								price: updatedPrice,
								updating: false
							}
						);
						this.summary = jsonresponse.summary;
						return this.summary;
					}

					if (jsonresponse.errorMessages && jsonresponse.errorMessages.length) {
						this._setProductProperties(
							productId,
							{
								deleteDisabled: false,
								errorMessages: jsonresponse.errorMessages,
								updating: false
							}
						);
						return this._removePendingOperation(productId);
					}

					var validatorErrors = jsonresponse.validatorErrors;

					this._setProductProperties(
						productId,
						{
							deleteDisabled: false,
							errorMessages: validatorErrors ?
								validatorErrors.map(item => item.message) :
								jsonresponse.error,
							updating: false
						}
					);

					return this._removePendingOperation(productId);

				}
			)
			.catch(
				err => {
					this._removePendingOperation(productId);
					this._setProductProperties(
						productId,
						{
							deleteDisabled: false,
							updating: false
						}
					);
				}
			);
	}

	_getProducts() {
		return fetch(
			this.cartAPI + '/' + this.cartId + '?groupId=' + themeDisplay.getScopeGroupId() + '&commerceAccountId=' + this.commerceAccountId,
			{
				method: 'GET'
			}
		)
		.then(response => response.json())
		.then(
			updatedCart => {
				this.products = updatedCart.products;
				this.summary = updatedCart.summary;
				return !!(this.products && this.summary);
			}
		)
		.catch(
			err => {
				return err;
			}
		);
	}

	_sendDeleteRequest(productId) {
		this._addPendingOperation(productId);

		return fetch(
			this.cartAPI + '/cart-item/' + productId + '?groupId=' + themeDisplay.getScopeGroupId() + '&commerceAccountId=' + this.commerceAccountId,
			{
				method: 'DELETE'
			}
		)
			.then(response => response.json())
			.then(
				(jsonresponse) => {
					if (jsonresponse.success) {
						this._removePendingOperation(productId);
						this._setProductProperties(
							productId,
							{
								deleteDisabled: false
							}
						);

						this.summary = jsonresponse.summary;

						const productsToBeRemoved = this._subtractProducts(this.products, jsonresponse.products);
						productsToBeRemoved.forEach(
							product => {
								this._deleteProduct(product.id);
							}
						);
					}

					this._setProductProperties(
						productId,
						{
							deleteDisabled: false,
							errorMessages: jsonresponse.errorMessages || jsonresponse.error,
							updating: false
						}
					);

					this._removePendingOperation(productId);
				}
			)
			.catch(
				err => {
					this._removePendingOperation(productId);
				}
			);
	}
}

Soy.register(Cart, template);

const productStateSchema = {
	collapsed: false,
	deleteDisabled: false,
	deleting: false,
	inputChanged: false,
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
	commerceAccountId: Config.oneOfType(
		[
			Config.number(),
			Config.string()
		]
	),
	detailsUrl: Config.string().required(),
	disabled: Config.bool().value(false),
	pendingOperations: Config.array().value(
		[]
	),
	products: {
		setter: '_normalizeProducts',
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
	),
	_loading: Config.bool().internal().value(false),
	_open: Config.bool().internal().value(false)
};

export {Cart};
export default Cart;