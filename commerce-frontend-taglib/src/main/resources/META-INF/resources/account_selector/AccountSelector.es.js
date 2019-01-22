'use strict';

import template from './AccountSelector.soy';
import Component from 'metal-component';
import Soy, {Config} from 'metal-soy';

import 'clay-icon';
import './OrdersTable.es';
import './AccountsTable.es';

class AccountSelector extends Component {

	fetchAccounts(query = '') {
		return fetch(
			this.accountsAPI + '/search-accounts/' + themeDisplay.getScopeGroupId() + '?groupId=' + themeDisplay.getScopeGroupId() + '&page=1&pageSize=10&q=' + query,
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
			this.accountsAPI + '/search-accounts/' + themeDisplay.getScopeGroupId() + '/' + this.currentAccount.accountId + '/orders?groupId=' + themeDisplay.getScopeGroupId() + '&page=1&pageSize=10&q=' + query,
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

	handleChangeSelectedView(view) {
		if (!this.accounts && view === 'accounts') {
			this.fetchAccounts();
		}

		return this.currentView = view;
	}

	handleGetAccounts(query = '') {
		this.fetchAccounts(query);
	}

	handleGetOrders(query = '') {
		this.fetchOrders(query);
	}

	handleOrderSelected(selectedOrder) {
		this.currentOrder = selectedOrder;

		return this.toggleAccountSelector();
	}

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

}

Soy.register(AccountSelector, template);

AccountSelector.STATE = {
	accountsAPI: Config.string().required(),
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
	createNewAccountLink: Config.string(),
	createNewOrderLink: Config.string(),
	currentAccount: Config.object(),
	currentOrder: Config.object(),
	currentView: Config.oneOf(
		[
			'accounts',
			'orders'
		]
	).value('accounts'),
	openingState: Config.oneOf(
		[
			'closed',
			'open',
			'closing'
		]
	).value('closed'),
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
	spritemap: Config.string().required(),
	viewAllAccountsLink: Config.string().required(),
	viewAllOrdersLink: Config.string().required()
};

export {AccountSelector};
export default AccountSelector;