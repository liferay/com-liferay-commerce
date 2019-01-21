'use strict';

import template from './AccountSelector.soy';
import Component from 'metal-component';
import Soy, {Config} from 'metal-soy';

import './OrdersTable.es';
import './AccountsTable.es';

class AccountSelector extends Component {

	toggleAccountSelector() {
		if (this.openingState === 'closed') {
			if (!this.currentAccount && !this.accounts) {
				this.currentView = 'accounts';
				this.fetchAccounts();
			}
			if (this.currentAccount && !this.orders) {
				this.currentView = 'orders';
				this.fetchOrders();
			}
			return this.openingState = 'open';
		}

		if (this.openingState === 'open') {
			this.openingState = 'closing';
			return setTimeout(
				() => {
					this.openingState = 'closed';
				},
				200
			);
		}
	}

	handleChangeSelectedView(view) {
		if (!this.accounts && view === 'accounts') {
			this.fetchAccounts();
		}
		return this.currentView = view;
	}

	handleAccountSelected(selectedAccount) {
		if (this.currentAccount) {
			if (selectedAccount.accountId === this.currentAccount.accountId) {
				return this.currentView = 'orders';
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
		return this.fetchOrders();
	}

	handleGetAccounts(query = '') {
		this.fetchAccounts(query);
	}

	handleOrderSelected(selectedOrder) {
		this.currentOrder = selectedOrder;
		return this.toggleAccountSelector();
	}

	handleGetOrders(query = '') {
		this.fetchOrders(query);
	}

	fetchAccounts(query = '') {
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
					return this.accounts = response.accounts;
				}
			);
	}

	fetchOrders(query = '') {
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
					return this.orders = response.orders;
				}
			);
	}
}

Soy.register(AccountSelector, template);

AccountSelector.STATE = {
	accountsAPI: Config.string().required(),
	openingState: Config.oneOf(
		[
			'closed',
			'open',
			'closing'
		]
	)
		.value('closed'),
	currentView: Config.oneOf(
		[
			'accounts',
			'orders'
		]
	)
		.value('accounts'),
	currentAccount: Config.object(),
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
	currentOrder: Config.object(),
	orders: Config.arrayOf(
		Config.shapeOf(
			{
				id: Config.oneOfType(
					[
						Config.string(),
						Config.number()
					]
				).required(),
				lastEdit: Config.string(),
				status: Config.string(),
				addOrderLink: Config.string()
			}
		)
	),
	viewAllAccountsLink: Config.string().required(),
	createNewAccountLink: Config.string(),
	viewAllOrdersLink: Config.string().required(),
	createNewOrderLink: Config.string(),
	spritemap: Config.string().required()
};

export {AccountSelector};
export default AccountSelector;
