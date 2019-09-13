'use strict';

import template from './AccountSelector.soy.js';
import Component from 'metal-component';
import Soy, { Config } from 'metal-soy';

import './OrdersTable';
import './AccountsTable';

class AccountSelector extends Component {

	toggleAccountSelector() {
		if ( this.openingState === 'closed' ) {
			if ( !this.currentAccount && !this.accounts ) {
				this.currentView = 'accounts';
				this.fetchAccounts()
			}
			if ( this.currentAccount && !this.orders ) {
				this.currentView = 'orders';
				this.fetchOrders()
			}
			return this.openingState = 'open';
		}

		if ( this.openingState === 'open' ) {
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
		if ( !this.accounts && view === 'accounts' ) {
			this.fetchAccounts();
		}
		return this.currentView = view;
	}

	handleAccountSelected(selectedAccount) {
		if (this.currentAccount) {
			if ( selectedAccount.id === this.currentAccount.id ) {
				return this.currentView = 'orders';
			}
			this.orders = null;
		}
		this.currentAccount = selectedAccount;
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
			this.accountsAPI + '/' + _authorize(query),
			{
				method: 'GET'
			}
		)
		.then(
			response => response.json()
		)
		.then(
			accounts => {
				return this.accounts = accounts;
			}
		)
	}

	fetchOrders(query = '') {
		return fetch(
			this.accountsAPI + '/' + this.currentAccount.id + '/orders/' + _authorize(query),
			{
				method: 'GET'
			}
		)
		.then(
			response => response.json()
		)
		.then(
			orders =>
			{
				return this.orders = orders;
			}
		)
	}

	_authorize(query) {
		if (!query.includes('?')) {
			query += '?p_auth=' + Liferay.authToken;
		}
		else if (!query.includes('p_auth=')) {
			query += '&p_auth=' + Liferay.authToken
		}

		return query;
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
				id: Config.oneOfType(
					[
						Config.string(),
						Config.number()
					]
				),
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
				),
				lastEdit: Config.string(),
				status: Config.string(),
				addOrderLink: Config.string()
			}
		)
	),
	viewAllAccountsLink: Config.string().required(),
	createNewAccountLink: Config.string().required(),
	viewAllOrdersLink: Config.string().required(),
	createNewOrderLink: Config.string().required()
}

export {AccountSelector};
export default AccountSelector;