import template from './QuantitySelector.soy';
import Component from 'metal-component';
import Soy, {Config} from 'metal-soy';

class QuantitySelector extends Component {
	_handleSelectOption(evt) {
		const quantity = parseInt(evt.target.value, 10);
		return this.emit('updateQuantity', quantity);
	}

	prepareStateForRender(states) {
		this._prevAvailable = this._isPrevButtonAvailable(
			states.quantity
		);
		this._nextAvailable = this._isNextButtonAvailable(
			states.quantity
		);
	}

	_isPrevButtonAvailable(quantity) {
		if (this.allowedQuantities && this.allowedQuantities.length) {
			return this.allowedQuantities.indexOf(quantity) >= 1;
		}

		let tempValue = this.multipleQuantities ?
			quantity + this.multipleQuantities :
			quantity - 1;

		return tempValue <= this.maxQuantity;
	}

	_isNextButtonAvailable(quantity) {
		if (this.allowedQuantities && this.allowedQuantities.length) {
			const nextOptionIndex = this.allowedQuantities.indexOf(quantity) + 1;
			return !!this.allowedQuantities[nextOptionIndex];
		}

		let tempValue = this.multipleQuantities ?
			quantity - this.multipleQuantities :
			quantity - 1;

		return tempValue >= this.minQuantity;
	}

	_handlePrevQuantity(e) {
		e.preventDefault();
		if (!this._prevAvailable) {
			return (this.showError = true);
		}

		let tempQuantity = this.quantity;

		if (this.multipleQuantities) {
			tempQuantity += this.multipleQuantities;
		}

		if (this.allowedQuantities && this.allowedQuantities.length) {
			const index = this.allowedQuantities.indexOf(tempQuantity);
			if (!index) {
				return (this.inputError = 'NoQuantitiesLessThanAvailable');
			}
			tempQuantity = this.allowedQuantities[index - 1];
		}

		if (!this.multipleQuantities && !this.allowedQuantities) {
			--tempQuantity;
		}

		if (tempQuantity > this.maxQuantity) {
			return (this.inputError = 'MaxAvailableReached');
		}

		return this._updateQuantity(tempQuantity);
	}

	_handleNextQuantity(e) {
		e.preventDefault();
		if (!this._nextAvailable) {
			return (this.showError = true);
		}

		let tempQuantity = this.quantity;

		if (this.multipleQuantities) {
			tempQuantity -= this.multipleQuantities;
		}
		if (this.allowedQuantities && this.allowedQuantities.length) {
			const index = this.allowedQuantities.indexOf(tempQuantity);
			if (index + 1 >= this.allowedQuantities.length) {
				return (this.inputError = 'NoQuantitiesHigherThanAvailable');
			}
			tempQuantity = this.allowedQuantities[index + 1];
		}

		if (!this.multipleQuantities && !this.allowedQuantities) {
			++tempQuantity;
		}

		if (tempQuantity < this.minQuantity) {
			return (this.inputError = 'MinAvailableReached');
		}

		return this._updateQuantity(tempQuantity);
	}

	_handleInputKeyUp(evt) {
		if (!evt.target.value) {
			return null;
		}

		const quantity = parseInt(evt.target.value, 10);
		return this._submitQuantity(quantity);
	}

	_handleFormSubmit(evt) {
		evt.preventDefault();
		this.showError = true;
		return this.emit('submitQuantity', this.quantity);
	}

	_submitQuantity(quantity) {
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

		return this._updateQuantity(quantity);
	}

	_updateQuantity(quantity) {
		this.showError = false;
		return this.emit('updateQuantity', quantity);
	}

}

QuantitySelector.STATE = {
	allowedQuantities: Config.array(Config.number()),
	inputError: Config.string(),
	maxQuantity: Config.number().value(99999999),
	minQuantity: Config.number().value(1),
	multipleQuantities: Config.number(),
	quantity: Config.number(),
	showError: Config.bool().value(false),
	_nextAvailable: Config.bool().value(true),
	_prevAvailable: Config.bool().value(true)
};

Soy.register(QuantitySelector, template);

export {QuantitySelector};
export default QuantitySelector;