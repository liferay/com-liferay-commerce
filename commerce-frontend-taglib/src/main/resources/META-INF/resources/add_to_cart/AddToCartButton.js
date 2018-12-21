import template from "./AddToCartButton.soy.js";
import Component from "metal-component";
import Soy, { Config } from "metal-soy";

class AddToCartButton extends Component {
	updateQuantity(quantity) {
		this.quantity = quantity;
	}

	handleBtnClick(e) {
		if (!this.editMode && this.element === e.target) {
			this.editMode = true;
		}
	}

	handleSubmitClick() {
		this.editMode = false;
		this.emit("submitQuantity", this.productId, this.quantity);
	}

	rendered() {
		if (this.element.querySelector("input"))
			this.element.querySelector("input").focus();
		else if (this.element.querySelector("select"))
			this.element.querySelector("select").focus();
	}
}

Soy.register(AddToCartButton, template);

AddToCartButton.STATE = {
	productId: Config.oneOfType([
		Config.string(),
		Config.number()
	]).required(),
	editMode: Config.bool().value(false),
	quantity: Config.number().value(0),
	settings: Config.shapeOf({
		minQuantity: Config.number(),
		maxQuantity: Config.number(),
		multipleQuantities: Config.number(),
		allowedOptions: Config.array(Config.number()),
	}).value({})
};

export { AddToCartButton };
export default AddToCartButton;