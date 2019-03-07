import template from "./AddToCartButton.soy.js";
import Component from "metal-component";
import Soy from "metal-soy";

import "./QuantitySelector.js";

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
	productId: {
		value: null
	},
	editMode: {
		value: false
	},
	quantity: {
		value: 0
	},
	settings: {
		value: {}
	}
};

export { AddToCartButton };
export default AddToCartButton;