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

	_getOrders() {
		return this.emit('getOrders', this.filterString);
	}

}

Soy.register(OrdersTable, template);

OrdersTable.STATE = {
	accountName: Config.string(),
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
				id: Config.oneOfType(
					[
						Config.string(),
						Config.number()
					]
				),
				lastEdit: Config.string(),
				status: Config.string(),
				addOrderLink: Config.string()
			}
		)
	),
	createNewOrderLink: Config.string(),
	viewAllOrdersLink: Config.string(),
	spritemap: Config.string().required()
};

export {OrdersTable};
export default OrdersTable;