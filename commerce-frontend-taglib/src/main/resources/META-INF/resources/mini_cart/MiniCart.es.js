import 'clay-icon';

import debounce from 'metal-debounce';
import Component from 'metal-component';
import Soy, {Config} from 'metal-soy';

import template from './MiniCart.soy';

import './CommerceCartItem.es';
import './Summary.es';

const COMMERCE_TOPBAR_CLASS = 'commerce-topbar',
	OPEN_CART_CLASS = 'cart-open';

function notifyProductRemoval(productId = '') {
	Liferay.fire(
		'productRemovedFromCart',
		{productId}
	);
}

class Cart extends Component {

	created() {
		this._topbar = window.document.querySelector(`.${COMMERCE_TOPBAR_CLASS}`);
		this._handleClickOutside = this._handleClickOutside.bind(this);
		this._refreshCartUsingData = this._refreshCartUsingData.bind(this);
		this.reset = this.reset.bind(this);
		this._setAndRefreshOrder = this._setAndRefreshOrder.bind(this);
	}

	_handleClickOutside(e) {
		const topBar = this._topbar || document.body;
		if (
			topBar.contains(e.target) && !this.element.contains(e.target)
		) {
			this.close();
		}
	}

	_handleToggleCart() {
		if (this.disabled || !this.orderId) {
			return null;
		}
		return this._open ? this.close() : this.open();
	}

	open() {
		this._open = true;
		this._topbar && this._topbar.classList.add(OPEN_CART_CLASS);
		this.element.addEventListener('transitionend', () => {
			window.addEventListener('click', this._handleClickOutside);
		});
		return this._open;
	}

	close() {
		this._open = false;
		this._topbar && this._topbar.classList.remove(OPEN_CART_CLASS);
		this.element.addEventListener(
			'transitionend',
			() => {
				window.removeEventListener('click', this._handleClickOutside);
			}
		);
		return this._open;
	}

	_refreshCartUsingData(evt) {
		try {
			this.orderId = evt.orderId;
			this.products = evt.products;
			this.summary = evt.summary;
			this.valid = evt.valid;
			this.detailsUrl = evt.detailsUrl || null;
			this._loading = false;
			this.pendingOperations = [];
			return true;
		}
		catch (error) {
			return false;
		}
	}

	_setAndRefreshOrder(orderId) {
		this.orderId = orderId;
		return this.refresh();
	}

	attached() {
		window.Liferay.on(
			'refreshCartUsingData',
			this._refreshCartUsingData,
			this
		);

		window.Liferay.on(
			'accountSelected',
			this.reset,
			this
		);

		window.Liferay.on(
			'orderSelected',
			this._setAndRefreshOrder,
			this
		);

		this._getData();
	}

	detached() {
		super.detached();
		window.Liferay.detach(
			'refreshCartUsingData',
			this._refreshCartUsingData,
			this
		);

		window.Liferay.detach(
			'accountSelected',
			this.reset,
			this
		);

		window.Liferay.detach(
			'orderSelected',
			this._setAndRefreshOrder,
			this
		);
	}

	_getData() {
		return this.orderId && this._getProducts();
	}

	refresh() {
		return this._getData();
	}

	reset() {
		this.productsQuantity = null;
		this.orderId = null;
		this.products = null;
		this.summary = null;
		this.valid = true;
		if (this._open === true) {
			this.close();
		}
	}

	syncPendingOperations(pendingOperations) {
		this._loading = !!pendingOperations.length;
	}

	normalizeProducts(rawProducts) {
		if (!rawProducts) {
			return null;
		}
		const normalizedProducts = rawProducts.map(
			productData => {
				return Object.assign(
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
				);
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

	_handleSubmitQuantity(productId, quantity) {
		this._setProductProperties(
			productId,
			{
				inputChanged: false
			}
		);
		return this._updateProductQuantity(productId, quantity);
	}

	_removeProductsFromCart(products = []) {
		products.length && products.forEach(product => {
			const {
				id: orderProductId,
				cpinstanceId: catalogProductId
			} = product;

			notifyProductRemoval(catalogProductId.toString());

			this._setProductProperties(
				orderProductId,
				{
					collapsed: true,
					deleteDisabled: true,
					inputChanged: false,
					updating: false
				}
			);
		});
	}

	_setProductProperties(productId, newProperties) {
		this.products = this.products.map(
			product => {
				return product.id === productId ? Object.assign(
					{},
					product,
					newProperties
				) :
					product;
			}
		);
		return this.products;
	}

	_getProductProperty(productId, key) {
		return this.products.reduce(
			(property, product) => product.id === productId ? product[key] : property,
			null
		);
	}

	_subtractProducts(orArray, subArray) {
		return new Promise(resolve => {
			const result = subArray.reduce(
				(arrayToBeFiltered, elToRemove) => {
					return arrayToBeFiltered.filter((elToCheck) => elToCheck.id !== elToRemove.id);
				},
				orArray
			);

			return resolve(result);
		});
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

	_addPendingOperation(productId) {
		if (!this.pendingOperations.indexOf(productId) > -1) {
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
			`${this.cartAPI}/cart-item/${productId}?commerceAccountId=${this.commerceAccountId}&
				groupId=${themeDisplay.getScopeGroupId()}&p_auth=${Liferay.authToken}&
				quantity=${this._getProductProperty(productId, 'quantity')}`,
			{
				headers: new Headers({'Content-Type': 'application/json',
					'Accept': 'application/json'}),
				method: 'PUT'
			}
		)
			.then(response => response.json())
			.then(
				(jsonresponse) => {
					if (jsonresponse.success) {
						this._handleProductUpdate(productId, jsonresponse.products);
						this.summary = jsonresponse.summary;
						return this.summary;
					}

					this._handleResponseErrors(productId, jsonresponse);
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
		const errorMessages = res.errorMessages ? res.errorMessages : res.validatorErrors.map(item => item.message);
		return this._setProductProperties(
			productId,
			{
				deleteDisabled: false,
				errorMessages,
				updating: false
			}
		);
	}

	_getProducts() {
		return fetch(
			`${this.cartAPI}/${this.orderId}?commerceAccountId=${this.commerceAccountId}&
				groupId=${themeDisplay.getScopeGroupId()}&p_auth=${Liferay.authToken}`,
			{
				method: 'GET'
			}
		)
			.then(response => response.json())
			.then(
				updatedCart => {
					this.products = updatedCart.products;
					this.summary = updatedCart.summary;
					this.valid = updatedCart.valid;
					return !!(this.products && this.summary);
				}
			)
			.catch(
				err => {
					return err;
				}
			);
	}

	syncProducts() {
		this.productsQuantity = this.products ?
			this.products.reduce(
				(quantity, product) => {
					return product.collapsed ? quantity : quantity + 1;
				},
				0
			) :
			0;
	}

	_sendDeleteRequest(productId) {
		this._addPendingOperation(productId);

		return fetch(
			`${this.cartAPI}/cart-item/${productId}?commerceAccountId=${this.commerceAccountId}&
				groupId=${themeDisplay.getScopeGroupId()}&p_auth=${Liferay.authToken}`,
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

						this._subtractProducts(this.products, jsonresponse.products)
							.then(
								products => {
									setTimeout(() => this._removeProductsFromCart(products), 50);
								});
					}

					this._handleResponseErrors(productId, jsonresponse);
					return this._removePendingOperation(productId);
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
	orderId: Config.oneOfType(
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
	detailsUrl: Config.string(),
	valid: Config.bool(),
	disabled: Config.bool().value(false),
	pendingOperations: Config.array().value(
		[]
	),

	/**
	 * For each product in the cart,
	 * the related object received from the endpoint
	 * contains 2 ID's:
	 *
	 * @param productId The id of the product relative to the cart.
	 * @param cpinstanceId The id of the product relative to the catalog.
	 */

	products: {
		setter: 'normalizeProducts',
		value: null
	},
	productsQuantity: Config.number().internal().value(0),
	spritemap: Config.string().required(),
	workflowStatus: Config.number(),
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
