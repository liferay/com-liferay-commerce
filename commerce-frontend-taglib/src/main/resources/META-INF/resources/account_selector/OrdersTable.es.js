'use strict';

import debounce from 'metal-debounce';

import template from './OrdersTable.soy';
import Component from 'metal-component';
import Soy, {Config} from 'metal-soy';

import '../autocomplete_item/AutocompleteItem.es';

class OrdersTable extends Component {

	created() {
		this._getOrders = debounce(this._getOrders, 500);
	}

	_getOrders() {
		return this.emit('getOrders', this.filterString);
	}

	_handleBackButtonClick() {
		return this.emit('changeSelectedView', 'accounts');
	}

	_handleFilterChange(evt) {
		this.filterString = evt.target.value;

		return this._getOrders();
	}

	_handleSubmitFilter(evt) {
		evt.preventDefault();

		return this._getOrders();
	}

}

Soy.register(OrdersTable, template);

OrdersTable.STATE = {
	accountName: Config.string(),
	createNewOrderLink: Config.string(),
	currentOrderId: Config.oneOfType(
		[
			Config.number(),
			Config.string()
		]
	),
	filterString: Config.string().value('').internal(),
	orders: Config.arrayOf(
		Config.shapeOf(
			{
				addOrderLink: Config.string(),
				id: Config.oneOfType(
					[
						Config.number(),
						Config.string()
					]
				).required(),
				lastEdit: Config.string(),
				status: Config.string()
			}
		)
	),
	spritemap: Config.string().required(),
	viewAllOrdersLink: Config.string()
};

export {OrdersTable};
export default OrdersTable;