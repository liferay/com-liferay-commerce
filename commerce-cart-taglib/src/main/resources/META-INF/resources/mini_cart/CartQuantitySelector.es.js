import template from './CartQuantitySelector.soy';
import Component from 'metal-component';
import Soy, {Config} from 'metal-soy';

class CartQuantitySelector extends Component {

	handleSelectOption(evt) {
		const quantity = parseInt(evt.target.value);
		return this.emit('updateQuantity', quantity);
	}

	prepareStateForRender(states) {
		const isPrevAvailable = this.checkWhetherIsPrevButtonAvailable(
			states.quantity
		);
		const isNextAvailable = this.checkWhetherIsNextButtonAvailable(
			states.quantity
		);
		return Object.assign(
			{},
			states,
			{
				isPrevAvailable,
				isNextAvailable
			}
		);
	}

	checkWhetherIsPrevButtonAvailable(quantity) {
		if (this.allowedQuantities && this.allowedQuantities.length) {
			return this.allowedQuantities.indexOf(quantity) >= 1;
		}

		let tempValue = this.multipleQuantity ?
			quantity - this.multipleQuantity :
			quantity - 1;

		return tempValue >= this.minQuantity;
	}

	checkWhetherIsNextButtonAvailable(quantity) {
		if (this.allowedQuantities && this.allowedQuantities.length) {
			const nextOptionIndex = this.allowedQuantities.indexOf(quantity) + 1;
			return !!this.allowedQuantities[nextOptionIndex];
		}

		let tempValue = this.multipleQuantity ?
			quantity + this.multipleQuantity :
			quantity - 1;

		return tempValue <= this.maxQuantity;
	}

	handlePrevQuantity(event) {
		event.preventDefault();

		if (!this.isPrevAvailable) {
			return (this.showError = true);
		}

		let tempQuantity = this.quantity;

		if (this.multipleQuantity) {
			tempQuantity -= this.multipleQuantity;
		}

		if (this.allowedQuantities && this.allowedQuantities.length) {
			const index = this.allowedQuantities.indexOf(tempQuantity);
			if (!index) {
				return (this.inputError = 'NoQuantitiesLessThanAvailable');
			}
			tempQuantity = this.allowedQuantities[index - 1];
		}

		if (!this.multipleQuantity && !this.allowedQuantities) {
			--tempQuantity;
		}

		if (tempQuantity < this.minQuantity) {
			return (this.inputError = 'MinAvailableReached');
		}

		return this.updateQuantity(tempQuantity);
	}

	handleNextQuantity(event) {
		event.preventDefault();

		if (!this.isNextAvailable) {
			return (this.showError = true);
		}

		let tempQuantity = this.quantity;

		if (this.multipleQuantity) {
			tempQuantity += this.multipleQuantity;
		}
		if (this.allowedQuantities && this.allowedQuantities.length) {
			const index = this.allowedQuantities.indexOf(tempQuantity);
			if (index + 1 >= this.allowedQuantities.length) {
				return (this.inputError = 'NoQuantitiesHigherThanAvailable');
			}
			tempQuantity = this.allowedQuantities[index + 1];
		}

		if (!this.multipleQuantity && !this.allowedQuantities) {
			++tempQuantity;
		}

		if (tempQuantity > this.maxQuantity) {
			return (this.inputError = 'MaxAvailableReached');
		}

		return this.updateQuantity(tempQuantity);
	}

	handleInputKeyUp(evt) {
		if (!evt.target.value) {
			return null;
		}

		const quantity = parseInt(evt.target.value);
		return this.submitQuantity(quantity);
	}

	handleFormSubmit(evt) {
		evt.preventDefault();
		return this.showError = true;
	}

	submitQuantity(quantity) {
		if (this.multipleQuantity) {
			if (quantity % this.multipleQuantity) {
				return (this.inputError = 'NotMultipleThan');
			}
		}

		if (quantity < this.minQuantity) {
			return (this.inputError = 'MinAvailableReached');
		}

		if (quantity < this.minQuantity) {
			return (this.inputError = 'MaxAvailableReached');
		}

		return this.updateQuantity(quantity);
	}

	updateQuantity(quantity) {
		this.showError = false;
		return this.emit('updateQuantity', quantity);
	}
}

CartQuantitySelector.STATE = {

	minQuantity: Config.number().value(1),

	maxQuantity: Config.number().value(99999999),

	multipleQuantity: Config.number(),

	allowedQuantities: Config.array(),

	quantity: Config.number().value(0),

	isPrevAvailable: Config.bool().value(true),

	isNextAvailable: Config.bool().value(true),

	inputError: Config.string(),

	showError: Config.bool().value(false)

};

Soy.register(CartQuantitySelector, template);

export {CartQuantitySelector};
export default CartQuantitySelector;