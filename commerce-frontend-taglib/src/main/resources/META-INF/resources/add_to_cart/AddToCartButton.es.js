import template from './AddToCartButton.soy';
import Component from 'metal-component';
import Soy, {Config} from 'metal-soy';

import '../quantity_selector/QuantitySelector.es';

class AddToCartButton extends Component {

	created() {
		if (!this.accountId) {
			this.disabled = true;
		}
	}

	handleBtnClick(e) {
		if (!this.editMode && this.element === e.target && !this.disabled) {
			this.editMode = true;
		}
	}

	handleSubmitClick() {
		const formData = new FormData();

		formData.append('accountId', this.accountId);
		formData.append('groupId', themeDisplay.getScopeGroupId());
		formData.append('options', '[]');
		formData.append('productId', this.productId);
		formData.append('quantity', this.quantity);

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
						this.emit('submitQuantity', this.productId, this.quantity);
					}
					else if (jsonresponse.errorMessages) {
						this._showNotification(jsonresponse.errorMessages[0], 'danger');
					}
					else {
						var validatorErrors = jsonresponse.validatorErrors;

						if (validatorErrors) {
							validatorErrors.forEach(
								function(validatorError) {
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

	rendered() {
		if (this.element.querySelector('input')) {
			this.element.querySelector('input').focus();
		}
		else if (this.element.querySelector('select')) {
			this.element.querySelector('select').focus();
		}
	}

	submitQuantity(quantity) {
		this.quantity = quantity;

		this.handleSubmitClick();
	}

	updateQuantity(quantity) {
		this.quantity = quantity;
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
			allowedOptions: Config.array(Config.number()),
			maxQuantity: Config.number(),
			minQuantity: Config.number(),
			multipleQuantities: Config.number()
		}
	).value({})
};

export {AddToCartButton};
export default AddToCartButton;