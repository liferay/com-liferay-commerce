'use strict';

import template from './AccountSelector.soy';
import Component from 'metal-component';
import Soy, {Config} from 'metal-soy';

import 'clay-icon';

import './OrdersTable.es';
import './AccountsTable.es';

class AccountSelector extends Component {

	created() {
		this._handleClickOutside = this._handleClickOutside.bind(this);
		this._refreshOrderState = this._refreshOrderState.bind(this);
	}

	attached() {
		window.Liferay.on('orderChanged', this._refreshOrderState, this);
	}

	detached() {
		window.Liferay.detach('orderChanged', this._refreshOrderState, this);
	}

	_refreshOrderState({orderId}) {
		this._getOrders()
			.then(orders => {
				this.orders = orders;
				if (orderId) {
					this.currentOrder = this.orders.reduce((found, order) => found || (order.id == orderId ? order : null), null);
					this.currentView = 'orders';
				}
			});
	}

	_handleClickOutside(e) {
		if (!document.querySelector('#account-manager-curtain').contains(e.target)) {
			this._closeModal();
		}
	}

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
			this._getOrders()
				.then(orders => {
					this.orders = orders;
				});
		}

		this.openingState = 'opening';

		return setTimeout(
			() => {
				this.openingState = 'open';
				window.addEventListener('click', this._handleClickOutside);
			},
			200
		);
	}

	_closeModal() {
		this.openingState = 'closing';

		return setTimeout(
			() => {
				this.openingState = 'closed';
				window.removeEventListener('click', this._handleClickOutside);
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
			this.accountsAPI + 'set-current-account?groupId=' + themeDisplay.getScopeGroupId(),
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
		return this._getOrders()
			.then(orders => {
				this.orders = orders;
			});
	}

	_handleGetAccounts(query = '') {
		this._fetchAccounts(query);
	}

	_handleOrderSelected(selectedOrder) {
		this.currentOrder = selectedOrder;
		return this.toggleAccountSelector();
	}

	_handleGetOrders(query = '') {
		return this._getOrders(query)
			.then(orders => {
				this.orders = orders;
			});
	}

	_fetchAccounts(query = '') {
		return fetch(
			this.accountsAPI + 'search-accounts?groupId=' + themeDisplay.getScopeGroupId() + '&page=1&pageSize=10&q=' + query,
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

	_getOrders(query = '') {
		return fetch(
			this.accountsAPI + 'search-accounts/' + this.currentAccount.accountId + '/orders?groupId=' + themeDisplay.getScopeGroupId() + '&page=1&pageSize=10&q=' + query,
			{
				method: 'GET'
			}
		)
			.then(
				response => response.json()
			)
			.then(
				response => response.orders
			);
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