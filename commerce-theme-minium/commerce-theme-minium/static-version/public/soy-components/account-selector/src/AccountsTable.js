'use strict';

import { debounce } from 'debounce';

import template from './AccountsTable.soy.js';
import Component from 'metal-component';
import Soy, { Config } from 'metal-soy';

import './AccountsTableItem';

class AccountsTable extends Component {

	created() {
		this._handleFilterChange = debounce(this._handleFilterChange, 500);
	}

	handleSelectAccount(accountData) {
		this.emit('accountSelected', accountData)
	}

	_handleFilterChange(evt) {
		this.filterString = evt.target.value;
		return this._getAccounts();
	}

	_handleSubmitFilter(evt) {
		evt.preventDefault();
		return this._getAccounts();
	}

	_getAccounts() {
		return this.emit('getAccounts', this.filterString)
	}
}

Soy.register(AccountsTable, template);

AccountsTable.STATE = {
	currentAccount: Config.shapeOf(
		{
			id: Config.string(),
			name: Config.string(),
			thumbnail: Config.string()
		}
	),
	filterString: Config.string().value('').internal(),
	accounts: Config.arrayOf(
		Config.shapeOf(
			{
				id: Config.string(),
				name: Config.string(),
				thumbnail: Config.string()
			}
		)
	),
	createNewOrderLink: Config.string()
};

export {AccountsTable};
export default AccountsTable;