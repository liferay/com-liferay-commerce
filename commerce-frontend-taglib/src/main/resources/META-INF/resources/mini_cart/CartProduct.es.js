'use strict';

import template from './CartProduct.soy';
import Component from 'metal-component';
import Soy, {Config} from 'metal-soy';

import './Loader.es';
import './Price.es';
import '../quantity_selector/QuantitySelector.es';

class CartProduct extends Component {
	updateQuantity(quantity) {
		this.emit('submitQuantity', this.id, quantity);
	}

	deleteItem() {
		this.emit('deleteItem', this.id);
	}

	cancelDeletion() {
		this.emit('cancelItemDeletion', this.id);
	}
}

Soy.register(CartProduct, template);

CartProduct.STATE = {

	id: Config.oneOfType(
		[
			Config.string(),
			Config.number()
		]
	),

	inputChanged: Config.bool().value(false),

	isDeleting: Config.bool().value(false),

	isDeleteDisabled: Config.bool().value(false),

	isCollapsed: Config.bool().value(false),

	isUpdating: Config.bool().value(false),

	quantity: Config.number().value(0),

	errorMessages: Config.array().value(
		[]
	),

	settings: Config.object().value(
		{}
	)

};

export {CartProduct};
export default CartProduct;