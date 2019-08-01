import template from './QuantitySelector.soy';
import Component from 'metal-component';
import Soy, {Config} from 'metal-soy';

class QuantitySelector extends Component {

	attached() {
		if (!this.quantity) {
			this.quantity = this.allowedQuantities ? this.allowedQuantities[0] : this.minQuantity;
			this._updateQuantity(this.quantity);
		}
		return !!this.quantity;
	}

	syncQuantity() {
		this.checkButtonsAvailability(this.quantity);
	}

	prepareStateForRender(state) {
		this.checkButtonsAvailability(state.quantity);
	}

	_handleSelectOption(e) {
		const quantity = parseInt(e.target.value, 10);
		return this.emit('updateQuantity', quantity);
	}

	checkButtonsAvailability(quantity) {
		this._prevAvailable = this._isPrevButtonAvailable(quantity);
		this._nextAvailable = this._isNextButtonAvailable(quantity);
	}

	_isPrevButtonAvailable(quantity) {
		let tempValue = this.multipleQuantity ?
			quantity - this.multipleQuantity :
			quantity - 1;

		return tempValue >= this.minQuantity;
	}

	_isNextButtonAvailable(quantity) {
		let tempValue = this.multipleQuantity ?
			quantity + this.multipleQuantity :
			quantity + 1;

		return tempValue <= this.maxQuantity;
	}

	_handlePrevQuantityButtonPressed(e) {
		e.preventDefault();
		if (!this._prevAvailable) {
			this.showError = true;
			return false;
		}

		let quantity = this.quantity;

		if (this.multipleQuantity) {
			quantity -= this.multipleQuantity;
		}
		else {
			quantity -= 1;
		}

		if (quantity < this.minQuantity) {
			this.inputError = 'MaxAvailableReached';
			return false;
		}

		return this._updateQuantity(quantity);
	}

	_handleNextQuantityButtonPressed(e) {
		e.preventDefault();
		if (!this._nextAvailable) {
			this.showError = true;
			return false;
		}

		let quantity = this.quantity;

		if (this.multipleQuantity) {
			quantity += this.multipleQuantity;
		}
		else {
			quantity += 1;
		}

		if (quantity > this.maxQuantity) {
			this.inputError = 'MaxAvailableReached';
			return false;
		}

		return this._updateQuantity(quantity);
	}

	_handleArrowKeys(e) {
		if (e.keyCode == 38) {
			return this._handleNextQuantityButtonPressed(e);
		}
		if (e.keyCode == 40) {
			return this._handlePrevQuantityButtonPressed(e);
		}
		return e;
	}

	_handleInputKeyUp(e) {
		if (!e.target.value) {
			return null;
		}
		const quantity = parseInt(
			e.target.value,
			10
		);
		return this._submitQuantity(quantity);
	}

	_handleFormSubmit(e) {
		e.preventDefault();
		this.showError = true;
		return this.emit('submitQuantity', this.quantity);
	}

	_submitQuantity(quantity) {
		if (this.multipleQuantity) {
			if (quantity % this.multipleQuantity) {
				return (this.inputError = 'NotMultipleThan');
			}
		}

		if (quantity < this.minQuantity) {
			return (this.inputError = 'MinAvailableReached');
		}

		if (quantity > this.maxQuantity) {
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
	disabled: Config.bool().value(false),
	inputError: Config.string(),
	maxQuantity: Config.number().value(99999999),
	minQuantity: Config.number().value(1),
	multipleQuantity: Config.number(),
	quantity: Config.number(),
	showError: Config.bool().value(false),
	_nextAvailable: Config.bool().value(true),
	_prevAvailable: Config.bool().value(true)
};

Soy.register(QuantitySelector, template);

export {QuantitySelector};
export default QuantitySelector;