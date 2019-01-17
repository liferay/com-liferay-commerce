import template from './QuantitySelector.soy';
import Component from 'metal-component';
import Soy, {Config} from 'metal-soy';

class QuantitySelector extends Component {
	handleSelectOption(evt) {
		const quantity = parseInt(evt.target.value);
		this.emit('updateQuantity', quantity);
	}

	prepareStateForRender(states) {
		this.isPrevAvailable = this.checkWhetherIsPrevButtonAvailable(
			states.quantity
		);
		this.isNextAvailable = this.checkWhetherIsNextButtonAvailable(
			states.quantity
		);
	}

	checkWhetherIsPrevButtonAvailable(quantity) {
		if (this.allowedOptions && this.allowedOptions.length) {
			return this.allowedOptions.indexOf(quantity) >= 1;
		}

		let tempValue = this.multipleQuantities ?
			quantity - this.multipleQuantities :
			quantity - 1;

		return tempValue >= this.minQuantity;
	}

	checkWhetherIsNextButtonAvailable(quantity) {
		if (this.allowedOptions && this.allowedOptions.length) {
			const nextOptionIndex = this.allowedOptions.indexOf(quantity) + 1;
			return !!this.allowedOptions[nextOptionIndex];
		}

		let tempValue = this.multipleQuantities ?
			quantity + this.multipleQuantities :
			quantity - 1;

		return tempValue <= this.maxQuantity;
	}

	handlePrevQuantity(e) {
		e.preventDefault();
		if (!this.isPrevAvailable) {
			return (this.showError = true);
		}

		let tempQuantity = this.quantity;

		if (this.multipleQuantities) {
			tempQuantity -= this.multipleQuantities;
		}

		if (this.allowedOptions && this.allowedOptions.length) {
			const index = this.allowedOptions.indexOf(tempQuantity);
			if (!index) {
				return (this.inputError = 'NoQuantitiesLessThanAvailable');
			}
			tempQuantity = this.allowedOptions[index - 1];
		}

		if (!this.multipleQuantities && !this.allowedOptions) {
			--tempQuantity;
		}

		if (tempQuantity < this.minQuantity) {
			return (this.inputError = 'MinAvailableReached');
		}

		this.updateQuantity(tempQuantity);
	}

	handleNextQuantity(e) {
		e.preventDefault();
		if (!this.isNextAvailable) {
			return (this.showError = true);
		}

		let tempQuantity = this.quantity;

		if (this.multipleQuantities) {
			tempQuantity += this.multipleQuantities;
		}
		if (this.allowedOptions && this.allowedOptions.length) {
			const index = this.allowedOptions.indexOf(tempQuantity);
			if (index + 1 >= this.allowedOptions.length) {
				return (this.inputError = 'NoQuantitiesHigherThanAvailable');
			}
			tempQuantity = this.allowedOptions[index + 1];
		}

		if (!this.multipleQuantities && !this.allowedOptions) {
			++tempQuantity;
		}

		if (tempQuantity > this.maxQuantity) {
			return (this.inputError = 'MaxAvailableReached');
		}

		this.updateQuantity(tempQuantity);
	}

	handleInputKeyUp(evt) {
		if (!evt.target.value) {
			return null;
		}

		const quantity = parseInt(evt.target.value);
		this.submitQuantity(quantity);
	}

	handleFormSubmit(evt) {
		evt.preventDefault();
		this.showError = true;
		this.emit('submitQuantity', this.quantity);
	}

	submitQuantity(quantity) {
		if (this.multipleQuantities) {
			if (quantity % this.multipleQuantities) {
				return (this.inputError = 'NotMultipleThan');
			}
		}

		if (quantity < this.minQuantity) {
			return (this.inputError = 'MinAvailableReached');
		}

		if (quantity < this.minQuantity) {
			return (this.inputError = 'MaxAvailableReached');
		}

		this.updateQuantity(quantity);
	}

	updateQuantity(quantity) {
		this.showError = false;
		this.emit('updateQuantity', quantity);
	}
}

QuantitySelector.STATE = {
	minQuantity: Config.number().value(1),
	maxQuantity: Config.number().value(99999999),
	multipleQuantities: Config.number(),
	allowedOptions: Config.array(Config.number()),
	quantity: Config.number(),
	isPrevAvailable: Config.bool().value(true),
	isNextAvailable: Config.bool().value(true),
	inputError: Config.string(),
	showError: Config.bool().value(false)
};

Soy.register(QuantitySelector, template);

export {QuantitySelector};
export default QuantitySelector;