import template from './AddToCartButton.soy';
import Component from 'metal-component';
import Soy, {Config} from 'metal-soy';

import '../quantity_selector/QuantitySelector.es';

class AddToCartButton extends Component {
	updateQuantity(quantity) {
		this.quantity = quantity;
	}

	submitQuantity(quantity) {
		this.quantity = quantity;
		this.handleSubmitClick();
	}

	handleBtnClick(e) {
		if (!this.editMode && this.element === e.target) {
			this.editMode = true;
		}
	}

	handleSubmitClick() {
		this.editMode = false;
		this.emit('submitQuantity', this.productId, this.quantity);
	}

	rendered() {
		if (this.element.querySelector('input')) {
			this.element.querySelector('input').focus();
		}
		else if (this.element.querySelector('select')) {
			this.element.querySelector('select').focus();
		}
	}
}

Soy.register(AddToCartButton, template);

AddToCartButton.STATE = {
	editMode: Config.bool().value(false),
	productId: Config.oneOfType(
		[
			Config.string(),
			Config.number()
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