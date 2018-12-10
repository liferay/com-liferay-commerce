import {Config} from 'metal-state';
import Component from 'metal-component';
import Soy from 'metal-soy';

import templates from './AddToCart.soy';

class AddToCart extends Component {

	created() {
		Liferay.on(this.cpDefinitionId + 'CPInstance:change', this._instanceChange.bind(this));
	}

	/**
     * Makes an ajax request to submit the data.
     * @param {Event} event
     * @protected
     */

	_addToCart() {
		var instance = this;

		var _optionsJSON = '[]';
		var _quantity = this.quantity;

		var productContent = this._getProductContent();

		if (productContent) {
			_optionsJSON = JSON.stringify(productContent.getFormValues());

			if (this.cpInstanceId == '0') {
				this.cpInstanceId = productContent.getCPInstanceId();
			}
		}

		var quantityNode = document.querySelector('#' + this.taglibQuantityInputId);

		if (quantityNode) {
			_quantity = quantityNode.value;
		}

		fetch(
			this.cartAPI + '/' + this.commerceOrderId,
			{
				body: JSON.stringify(
					{
						cartItem: {
							optionsJSON: _optionsJSON,
							quantity: _quantity,
							productId: this.cpInstanceId
						}
					}
				),
				headers: new Headers({'Content-Type': 'application/json'}),
				method: 'POST'
			}
		)
			.then(response => response.json())
			.then(
				jsonresponse => {
					var messages = jsonresponse.messages;

					if (jsonresponse.success) {
						Liferay.fire('commerce:productAddedToCart', jsonresponse);

						if (messages) {
							messages.forEach(
								function(message) {
									instance._showNotification(message, 'success');
								}
							);
						}
					}
					else if (messages) {
						messages.forEach(
							function(message) {
								instance._showNotification(message, 'danger');
							}
						);
					}
				}
			);
	}

	_getProductContent() {
		return Liferay.component(this.productContentId);
	}

	_handleClick() {
		var instance = this;

		var productContent = this._getProductContent();

		if (productContent) {
			productContent.validateProduct(
				function(hasError) {
					if (!hasError) {
						instance._addToCart();
					}
				}
			);
		}
		else {
			this._addToCart();
		}
	}

	_instanceChange(event) {
		if (event.cpInstanceExist) {
			this.cpInstanceId = event.cpInstanceId;
		}
	}

	_showNotification(message, type) {
		AUI().use(
			'liferay-notification',
			() => {
				new Liferay.Notification(
					{
						closeable: true,
						delay: {
							hide: 5000,
							show: 0
						},
						duration: 500,
						message: message,
						render: true,
						title: '',
						type: type
					}
				);
			}
		);
	}
}

/**
 * State definition.
 * @ignore
 * @static
 * @type {!Object}
 */

AddToCart.STATE = {

	/**
	 * CPDefinitionId.
	 * @instance
	 * @memberof AddToCart
	 * @type {?number}
	 * @default undefined
	 */

	cpDefinitionId: Config.string(),

	/**
	 * CPInstanceId.
	 * @instance
	 * @memberof AddToCart
	 * @type {?number}
	 * @default undefined
	 */

	cpInstanceId: Config.string(),

	/**
	 * CSS classes to be applied to the element.
	 * @instance
	 * @memberof AddToCart
	 * @type {?string}
	 * @default undefined
	 */

	elementClasses: Config.string(),

	/**
	 * Id of the html input to get the quantity.
	 * @instance
	 * @memberof AddToCart
	 * @type {?string}
	 * @default undefined
	 */

	taglibQuantityInputId: Config.string(),

	/**
	 * Default quantity to add to cart.
	 * @instance
	 * @memberof AddToCart
	 * @type {?string}
	 * @default undefined
	 */

	quantity: Config.string(),

	/**
	 * Component id.
	 * @instance
	 * @memberof AddToCart
	 * @type {String}
	 */

	id: Config.string().required(),

	/**
	 * Text to display inside the add to cart button.
	 * @instance
	 * @memberof AddToCart
	 * @type {String}
	 */

	label: Config.string().required(),

	/**
	 * Portlet's namespace
	 * @instance
	 * @memberof AddToCart
	 * @type {String}
	 */

	portletNamespace: Config.string().required(),

	/**
	 * Product content id
	 * @instance
	 * @memberof AddToCart
	 * @type {String}
	 */

	productContentId: Config.string(),

	/**
	 * Uri to add a cart item.
	 * @instance
	 * @memberof AddToCart
	 * @type {String}
	 */

	cartAPI: Config.string().required(),

	/**
     * CommerceOrderId.
     * @instance
     * @memberof AddToCart
     * @type {number}
     */

	commerceOrderId: Config.string().required()
};

// Register component

Soy.register(AddToCart, templates);

export default AddToCart;