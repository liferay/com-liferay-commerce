'use strict';

import template from './CartProduct.soy';
import Component from 'metal-component';
import Soy from 'metal-soy';

import './Loader.es';
import './Price.es';
import './QuantitySelector.es';

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
	id: {
		value: null
	},
	inputChanged: {
		value: false
	},
	isDeleting: {
		value: false
	},
	isDeleteDisabled: {
		value: false
	},
	isCollapsed: {
		value: false
	},
	isUpdating: {
		value: false
	},
	localization: {
		value: {}
	},
	quantity: {
		value: null
	},
	error: {
		value: null
	},
	settings: {
		value: {}
	}
};

export {CartProduct};
export default CartProduct;