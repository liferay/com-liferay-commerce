'use strict';

import template from './AccountSelector.soy';
import Component from 'metal-component';
import Soy, {Config} from 'metal-soy';

import 'clay-icon';
import './OrdersTable.es';
import './AccountsTable.es';

class AccountSelector extends Component {

	toggleAccountSelector() {
		if (this.openingState === 'closed') {
			this._openModal();
		}

		if (this.openingState === 'open') {
			this._closeModal();
		}

		return this.openingState;
	}

	_openModal() {
		if (!this.currentAccount && !this.accounts) {
			this.currentView = 'accounts';
			this._fetchAccounts();
		}

		if (this.currentAccount && !this.orders) {
			this.currentView = 'orders';
			this._fetchOrders();
		}

		this.openingState = 'opening';

		return setTimeout(
			() => {
				this.openingState = 'open';
			},
			200
		);
	}

	_closeModal() {
		this.openingState = 'closing';

		return setTimeout(
			() => {
				this.openingState = 'closed';
			},
			200
		);
	}

	_handleChangeSelectedView(view) {
		if (!this.accounts && view === 'accounts') {
			this._fetchAccounts();
		}

		this.currentView = view;

		return this.currentView;
	}

	_handleAccountSelected(selectedAccount) {
		if (this.currentAccount) {
			if (selectedAccount.accountId === this.currentAccount.accountId) {
				this.currentView = 'orders';
				return this.currentView;
			}

			this.orders = null;
		}

		this.currentAccount = selectedAccount;

		let formData = new FormData();

		formData.append('accountId', this.currentAccount.accountId);

		fetch(
			this.accountsAPI + 'set-current-account/' + themeDisplay.getScopeGroupId(),
			{
				body: formData,
				method: 'POST'
			}
		).then(
			() => {
				this.currentOrder = null;
				this.emit('accountSelected', this.currentAccount);
				Liferay.fire('accountSelected', this.currentAccount);
			}
		);

		this.currentView = 'orders';
		return this._fetchOrders();
	}

	_handleGetAccounts(query = '') {
		this._fetchAccounts(query);
	}

	_handleOrderSelected(selectedOrder) {
		this.currentOrder = selectedOrder;
	}
	
	_handleGetOrders(query = '') {
		return this._fetchOrders(query);
	}

	_fetchAccounts(query = '') {
		return fetch(
			this.accountsAPI + '/search-accounts/' + themeDisplay.getScopeGroupId() + '?page=1&pageSize=10&q=' + query,
			{
				method: 'GET'
			}
		)
			.then(
				response => response.json()
			)
			.then(
				response => {
					this.accounts = response.accounts;
					return this.accounts;
				}
			);
	}

	_fetchOrders(query = '') {
		return fetch(
			this.accountsAPI + '/search-accounts/' + themeDisplay.getScopeGroupId() + '/' + this.currentAccount.accountId + '/orders?page=1&pageSize=10&q=' + query,
			{
				method: 'GET'
			}
		)
			.then(
				response => response.json()
			)
			.then(
				response => {
					this.orders = response.orders;
					return this.orders;
				}
			);
		}
	}

}

Soy.register(AccountSelector, template);

AccountSelector.STATE = {
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
	accountsAPI: Config.string().required(),
	createNewAccountLink: Config.string(),
	createNewOrderLink: Config.string(),
	currentAccount: Config.object(),
	currentOrder: Config.object(),
	currentView: Config.oneOf(
		[
			'accounts',
			'orders'
		]
	)
		.value('accounts'),
	openingState: Config.oneOf(
		[
			'closed',
			'open',
			'closing',
			'opening'
		]
	)
		.value('closed'),
	orders: Config.arrayOf(
		Config.shapeOf(
			{
				addOrderLink: Config.string(),
				id: Config.oneOfType(
					[
						Config.string(),
						Config.number()
					]
				).required(),
				lastEdit: Config.string(),
				status: Config.string()
			}
		)
	),
	spritemap: Config.string().required(),
	viewAllAccountsLink: Config.string().required(),
	viewAllOrdersLink: Config.string().required()
};

export {AccountSelector};
export default AccountSelector;