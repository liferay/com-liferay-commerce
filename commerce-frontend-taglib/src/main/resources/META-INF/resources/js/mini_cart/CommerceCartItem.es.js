'use strict';

import template from './CommerceCartItem.soy';
import Component from 'metal-component';
import Soy, {Config} from 'metal-soy';

import 'clay-icon';
import './Loader.es';
import '../price/Price.es';
import '../quantity_selector/QuantitySelector.es';

class CommerceCartItem extends Component {
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

Soy.register(CommerceCartItem, template);

CommerceCartItem.STATE = {
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
	options: Config.array(
		Config.shapeOf(
			{
				key: Config.string(),
				value: Config.oneOfType(
					[
						Config.string(),
						Config.number()
					]
				)
			}
		)
	),
	inputChanged: Config.bool().value(false),
	quantity: Config.number().value(0),
	settings: Config.object().value(
		{}
	),
	spritemap: Config.string().required(),
	updating: Config.bool().value(false)
};

export {CommerceCartItem};
export default CommerceCartItem;