'use strict';
import template from './CartProduct.soy';
import Component from 'metal-component';
import Soy, {Config} from 'metal-soy';

import './Loader.es';
import '../price/Price.es';
import '../quantity_selector/QuantitySelector.es';

class CartProduct extends Component {

	cancelDeletion() {
		this.emit('cancelItemDeletion', this.id);
	}

	deleteItem() {
		this.emit('deleteItem', this.id);
	}

	updateQuantity(quantity) {
		this.emit('submitQuantity', this.id, quantity);
	}

}

Soy.register(CartProduct, template);

CartProduct.STATE = {
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
	isCollapsed: Config.bool().value(false),
	isDeleteDisabled: Config.bool().value(false),
	isDeleting: Config.bool().value(false),
	isUpdating: Config.bool().value(false),
	quantity: Config.number().value(0),
	settings: Config.object().value(
		{}
	)
};

export {CartProduct};
export default CartProduct;