'use strict';

import debounce from 'metal-debounce';

import template from './AccountsTable.soy';
import Component from 'metal-component';
import Soy, {Config} from 'metal-soy';

import './AccountsTableItem.es';

class AccountsTable extends Component {

	created() {
		this._handleFilterChange = debounce(this._handleFilterChange.bind(this), 500);
	}

	handleSelectAccount(accountData) {
		this.emit('accountSelected', accountData);
	}

	_getAccounts() {
		return this.emit('getAccounts', this.filterString);
	}

	_handleFilterChange(evt) {
		this.filterString = evt.target.value;

		return this._getAccounts();
	}

	_handleSubmitFilter(evt) {
		evt.preventDefault();

		return this._getAccounts();
	}

}

Soy.register(AccountsTable, template);

AccountsTable.STATE = {
	accounts: Config.arrayOf(
		Config.shapeOf(
			{
				accountId: Config.oneOfType(
					[
						Config.string(),
						Config.number()
					]
				).required(),
				name: Config.string(),
				thumbnail: Config.string()
			}
		)
	),
	createNewOrderLink: Config.string(),
	currentAccount: Config.shapeOf(
		{
			accountId: Config.oneOfType(
				[
					Config.string(),
					Config.number()
				]
			).required(),
			name: Config.string(),
			thumbnail: Config.string()
		}
	),
	filterString: Config.string().value('').internal()
};

export {AccountsTable};
export default AccountsTable;