'use strict';

import template from './CartProduct.soy';
import Component from 'metal-component';
import Soy, {Config} from 'metal-soy';

import './Loader.es';
import '../price/Price.es';
import '../quantity_selector/QuantitySelector.es';

class CartProduct extends Component {
	_handleUpdateQuantity(quantity) {
		return this.emit('submitQuantity', this.id, quantity);
	}

	_handleDeleteItem() {
		return this.emit('deleteItem', this.id);
	}

	_handleCancelDeletion() {
		return this.emit('cancelItemDeletion', this.id);
	}

}

Soy.register(CartProduct, template);

CartProduct.STATE = {
	collapsed: Config.bool().value(false),
	deleteDisabled: Config.bool().value(false),
	deleting: Config.bool().value(false),
	errorMessages: Config.array().value(
		[]
	),
	id: Config.oneOfType(
		[
			Config.string(),
			Config.number()
		]
	),
	inputChanged: Config.bool().value(false),
	quantity: Config.number().value(0),
	settings: Config.object().value(
		{}
	),
	updating: Config.bool().value(false)
};

export {CartProduct};
export default CartProduct;