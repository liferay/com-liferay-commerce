'use strict';

import { debounce } from 'debounce';

import template from './OrdersTable.soy.js';
import Component from 'metal-component';
import Soy, {Config} from 'metal-soy';

import './AutocompleteItem';

class OrdersTable extends Component {

	created() {
		this._getOrders = debounce(this._getOrders, 500);
	}

	_handleBackButtonClick() {
		return this.emit('changeSelectedView', 'accounts')
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
		return this.emit('getOrders', this.filterString)
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
				id: Config.string(),
				lastEdit: Config.string(),
				status: Config.string(),
				addOrderLink: Config.string()
			}
		)
	),
	createNewOrderLink: Config.string(),
	viewAllOrdersLink: Config.string(),
};

export {OrdersTable};
export default OrdersTable;