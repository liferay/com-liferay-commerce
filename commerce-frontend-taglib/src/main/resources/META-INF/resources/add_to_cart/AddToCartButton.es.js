import template from './AddToCartButton.soy';
import Component from 'metal-component';
import Soy, {Config} from 'metal-soy';

import '../quantity_selector/QuantitySelector.es';

let notificationDidShow = false;

function showNotification(message, type) {
	!notificationDidShow && AUI().use(
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

	notificationDidShow = true;

	setTimeout(() => {
		notificationDidShow = false;
	}, 500);
}

function resetInputQuantity() {
	setTimeout(
		() => {
			this.inputQuantity = (this.settings.allowedQuantities && this.settings.allowedQuantities.length) ?
				this.settings.allowedQuantities[0] : this.settings.minQuantity;
		}, 500);
}

function doFocusOut() {
	const parentElement = this.element.parentElement;
	const tabbableElement = !!parentElement &&
        !!parentElement.closest && parentElement.closest('[tabindex="0"]');

	if (tabbableElement) {
		parentElement.focus();
	}
	else if (parentElement) {
		// IE compatibility
		parentElement.parentElement.focus();
	}
}

function doSubmit() {
	const formData = new FormData();

	formData.append('commerceAccountId', this.accountId);
	formData.append('groupId', themeDisplay.getScopeGroupId());
	formData.append('productId', this.productId);
	formData.append('languageId', themeDisplay.getLanguageId());
	formData.append('quantity', this.inputQuantity);
	formData.append('options', this.options);

	if (this.orderId) {
		formData.append('orderId', this.orderId);
	}

	return fetch(
		this.cartAPI + `?p_auth=${window.Liferay.authToken}`,
		{
			body: formData,
			method: 'POST'
		}
	).then(
		response => response.json()
	).then(
		jsonresponse => {
			if (jsonresponse.success) {
				if (jsonresponse.products) {
					Liferay.fire(
						'refreshCartUsingData',
						{
							detailsUrl: jsonresponse.detailsUrl,
							orderId: jsonresponse.orderId,
							products: jsonresponse.products,
							summary: jsonresponse.summary,
							valid: jsonresponse.valid
						}
					);
				}

				if (this.orderId !== jsonresponse.orderId) {
					Liferay.fire(
						'orderChanged',
						{
							orderId: jsonresponse.orderId
						}
					);
					this.orderId = jsonresponse.orderId;
				}
				
				this._animateMarker(this.quantity);
				this.quantity = this.inputQuantity;
			}
			else if (jsonresponse.errorMessages) {
				showNotification(jsonresponse.errorMessages[0], 'danger');
			}
			else {
				const validatorErrors = jsonresponse.validatorErrors;

				if (validatorErrors) {
					validatorErrors.forEach(
						validatorError => {
							showNotification(validatorError.message, 'danger');
						}
					);
				}
				else {
					showNotification(jsonresponse.error, 'danger');
				}
			}
		}
	).catch(
		weShouldHandleErrors => {
		}
	);
}

class AddToCartButton extends Component {

	created() {
		this.quantity = this.quantity;
		resetInputQuantity.call(this);
		this._handleMarkerAnimation = this._handleMarkerAnimation.bind(this);
	}

	_animateMarker(prevQuantity) {
		if(prevQuantity === 0) {
			this.updatingTransition = 'adding';
		} else {
			this.updatingTransition = 'incrementing';
		}
		
		this.refs.marker.addEventListener('animationend', this._handleMarkerAnimation, this);
	}
	
	_handleMarkerAnimation() {
		this.updatingTransition = null
		this.refs.marker.removeEventListener('animationend', this._handleMarkerAnimation, this);
	}

	attached() {
		window.Liferay.on('accountSelected', this._handleAccountChange, this);
		window.Liferay.on('productRemovedFromCart', this._handleCartProductRemoval, this);
	}

	detached() {
		window.Liferay.detach('accountSelected', this._handleAccountChange, this);
		window.Liferay.detach('productRemovedFromCart', this._handleCartProductRemoval, this);
	}

	_updateQuantity(quantity) {
		this.inputQuantity = quantity;
	}

	_submitQuantity(quantity) {
		this._updateQuantity(quantity);
		this._handleSubmitClick();
	}

	_handleAccountChange(e) {
		this.accountId = e.accountId;
		this.orderId = null;
		//NOT CORRECT!!!
		this.quantity = 0;
		resetInputQuantity.call(this);
	}

<<<<<<< HEAD
	_handleCartProductRemoval(e) {
		if (e.productId === this.productId) {
			this.quantity = 0;
			resetInputQuantity.call(this);
		}
	}

	_handleBtnFocus(e) {
		this._handleBtnClick(e);
	}

	_handleBtnFocusin() {
		clearTimeout(this.closingTimeout);
	}
=======
	_handleSubmitClick() {
		if (!this.accountId) {
			const message = Liferay.Language.get('no-account-selected');
			const type = 'danger';
>>>>>>> COMMERCE-2491 add to cart button and quantity restyled and refactored

			showNotification(message, type);
		}

		if (this.disabled) {
			return null;
		}

		resetInputQuantity.call(this);

		doSubmit.call(this)
			.then(() => doFocusOut.call(this));
	}
}

Soy.register(AddToCartButton, template);

AddToCartButton.STATE = {
	accountId: Config.oneOfType(
		[
			Config.number(),
			Config.string()
		]
	),
	buttonStyle: Config.oneOf(
		[
			'block',
			'inline'
		]
	).value('inline'),
	cartAPI: Config.string().required(),
	disabled: Config.bool().value(false),
	inputQuantity: Config.number(),
	options: Config.string().value('[]'),
	orderId: Config.oneOfType(
		[
			Config.number(),
			Config.string()
		]
	),
	quantity: Config.number(),
	productId: Config.oneOfType(
		[
			Config.number(),
			Config.string()
		]
	).required(),
	settings: Config.shapeOf(
		{
			allowedQuantity: Config.array(Config.number()),
			maxQuantity: Config.number(),
			minQuantity: Config.number(),
			multipleQuantity: Config.number()
		}
	).value({}),
	updatingTransition: Config.oneOf(
		[
			'adding',
			'incrementing'
		]
	),
};

export {AddToCartButton};
export default AddToCartButton;
