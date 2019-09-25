import template from './AddToCartButton.soy';
import Component from 'metal-component';
import Soy, {Config} from 'metal-soy';

import '../quantity_selector/QuantitySelector.es';

let notificationDidShow = false;

const selectInput = (element) => {
	const inputBox = element.querySelector('input');
	const selectBox = element.querySelector('select');

	if (inputBox) {
		inputBox.focus();
		inputBox.select();
	}
 else if (selectBox) {
		selectBox.focus();
	}
};

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

	this.editMode = false;
}

function doSubmit() {
	const formData = new FormData();

	formData.append('commerceAccountId', this.accountId);
	formData.append('groupId', themeDisplay.getScopeGroupId());
	formData.append('productId', this.productId);
	formData.append('languageId', themeDisplay.getLanguageId());
	formData.append('quantity', this.quantity);
	formData.append('options', this.options);
	formData.append('p_auth', Liferay.authToken);

	if (this.orderId) {
		formData.append('orderId', this.orderId);
	}

	return fetch(
		this.cartAPI,
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
							summary: jsonresponse.summary
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

				this.initialQuantity = this.quantity;
				this.hasQuantityChanged = true;
				this.emit('submitQuantity', this.productId, this.quantity);
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
		this.initialQuantity = this.quantity;
		resetInputQuantity.call(this);
		this.hasQuantityChanged = false;

		window.Liferay.on('accountSelected', this._handleAccountChange, this);
	}

	detached() {
		window.Liferay.detach('accountSelected', this._handleAccountChange, this);
	}

	willReceiveState(changes) {
		if (changes.editMode) {
			setTimeout(
				() => selectInput(this.element),
				100
			);
		}
	}

	_updateQuantity(quantity) {
		this.inputQuantity = quantity;
	}

	_submitQuantity(quantity) {
		this._updateQuantity(quantity);
		this._handleSubmitClick();
	}

	_enableEditMode() {
		this.editMode = true;
	}

	_disableEditMode() {
		this.editMode = false;
		this.quantity = this.initialQuantity;
		this.hasQuantityChanged = false;
		doFocusOut.call(this);
	}

	_handleBtnClick(e) {
		if (
			!this.editMode &&
            this.element === e.target &&
            !this.disabled &&
            !!this.accountId
		) {
			this._enableEditMode();
		}
 else if (!this.accountId) {
			const message = Liferay.Language.get('no-account-selected');
			const type = 'danger';

			showNotification(message, type);
		}
	}

	_handleAccountChange(e) {
		this.accountId = e.accountId;
		this.orderId = null;
		this.quantity = 0;
		resetInputQuantity.call(this);
	}

	_handleBtnFocus(e) {
		this._handleBtnClick(e);
	}

	_handleBtnFocusin() {
		clearTimeout(this.closingTimeout);
	}

	_handleBtnFocusout(e) {
		this.closingTimeout = setTimeout(
			() => this._disableEditMode(),
			(this.hasQuantityChanged ? 1000 : 100)
		);
	}

	_handleSubmitClick() {
		if (this.disabled) {
			return null;
		}

		this.quantity = this.inputQuantity;
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
	buttonVariant: Config.oneOf(
		[
			'compact',
			'inline'
		]
	).value('inline'),
	cartAPI: Config.string().required(),
	disabled: Config.bool().value(false),
	editMode: Config.bool().value(false),
	hasQuantityChanged: Config.bool().value(false),
	inputQuantity: Config.number(),
	options: Config.string().value('[]'),
	orderId: Config.oneOfType(
		[
			Config.number(),
			Config.string()
		]
	),
	productId: Config.oneOfType(
		[
			Config.number(),
			Config.string()
		]
	).required(),
	quantity: Config.number().value(0),
	settings: Config.shapeOf(
		{
			allowedQuantity: Config.array(Config.number()),
			maxQuantity: Config.number(),
			minQuantity: Config.number(),
			multipleQuantity: Config.number()
		}
	).value({})
};

export {AddToCartButton};
export default AddToCartButton;
