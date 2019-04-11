import template from './AddToCartButton.soy';
import Component from 'metal-component';
import Soy, {Config} from 'metal-soy';

import '../quantity_selector/QuantitySelector.es';

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

class AddToCartButton extends Component {

	created() {
		this.initialQuantity = this.quantity;

		window.Liferay.on('accountSelected', this._handleAccountChange, this);
	}

	detached() {
		window.Liferay.detach('accountSelected', this._handleAccountChange, this);
	}

	willReceiveState(changes) {
		if (changes.editMode) {
			setTimeout(() => selectInput(this.element), 100);
		}
	}

	_updateQuantity(quantity) {
		this.quantity = quantity;
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
	}

	_handleBtnFocus(e) {
		this._handleBtnClick(e);
	}

	_handleAccountChange(e) {
		this.accountId = e.accountId;
	}

	_handleBtnFocusin() {
		clearTimeout(this.closingTimeout);
	}

	_handleBtnFocusout(e) {
		this.closingTimeout = setTimeout(
			() => this._disableEditMode(),
			100
		);
	}

	_handleSubmitClick() {
		const formData = new FormData();

		formData.append('commerceAccountId', this.accountId);
		formData.append('groupId', themeDisplay.getScopeGroupId());
		formData.append('languageId', themeDisplay.getLanguageId());
		formData.append('productId', this.productId);
		formData.append('quantity', this.quantity);
		formData.append('options', this.options);

		if (this.orderId) {
			formData.append('orderId', this.orderId);
		}

		fetch(
			this.cartAPI,
			{
				body: formData,
				method: 'POST'
			}
		)
			.then(response => response.json())
			.then(
				(jsonresponse) => {
					if (jsonresponse.success) {
						Liferay.fire('updateCart', jsonresponse);

						this.editMode = false;
						this.initialQuantity = this.quantity;
						this.element.parentElement.closest('[tabindex="0"]').focus();
						this.emit('submitQuantity', this.productId, this.quantity);
					}
					else if (jsonresponse.errorMessages) {
						this._showNotification(jsonresponse.errorMessages[0], 'danger');
					}
					else {
						var validatorErrors = jsonresponse.validatorErrors;

						if (validatorErrors) {
							validatorErrors.forEach(
								validatorError => {
									this._showNotification(validatorError.message, 'danger');
								}
							);
						}
						else {
							this._showNotification(jsonresponse.error, 'danger');
						}
					}
				}
			);
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

Soy.register(AddToCartButton, template);

AddToCartButton.STATE = {
	accountId: Config.oneOfType(
		[
			Config.number(),
			Config.string()
		]
	),
	cartAPI: Config.string().required(),
	disabled: Config.bool().value(false),
	editMode: Config.bool().value(false),
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