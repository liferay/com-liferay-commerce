import 'clay-table';
import 'clay-pagination-bar';
import {Config} from 'metal-state';
import Component from 'metal-component';
import Soy from 'metal-soy';

import template from './CommerceTable.soy';

import './CommerceTableExtensions.es';
import './ModalLinkCellTemplate.es';

class CommerceTable extends Component {

	_getApiURL() {
		let url = this.dataSetAPI;

		url += `&pageSize=${this.pageSize}`;
		url += `&page=${this.currentPage}`;
		url += `&p_auth=${Liferay.authToken}`;

		url += '&' + Object.keys(this.filters).map(
			el => {
				return encodeURIComponent(el) + '=' + encodeURIComponent(this.filters[el]);
			}).join('&');

		return url;
	}

	_handleItemsPerPageClicked(event) {
		if (this.disableAJAX) {
			return;
		}

		event.preventDefault();

		if (this.pageSize == event.data.item.label) {
			return;
		}

		this.pageSize = event.data.item.label;
		this.currentPage = 1;
		this.paginationSelectedEntry = this.paginationEntries.map((x) => x.label).indexOf(this.pageSize);

		this._loadData();
	}

	/**
	 * Toggles the selection of an item and adds or removes it from selected items
	 * list.
	 * @param {!Event} event
	 * @private
	 */
	_handleItemToggled(event) {
	}

	_handlePageClicked(event) {
		if (this.disableAJAX) {
			return;
		}

		let newPage = parseInt(event.data.page, 10);

		if (this.currentPage == newPage) {
			return;
		}

		this.currentPage = newPage;

		this._loadData();
	}

	_loadData() {
		fetch(
			this._getApiURL(),
			{
				method: 'GET'
			}
		)
			.then(response => response.json())
			.then(
				updatedItems => {
					this.items = updatedItems;
				}
			)
			.catch(
				console.error
			);
	}

}

Soy.register(CommerceTable, template);

CommerceTable.STATE = {
	currentPage: Config.number().required(),
	dataProviderKey: Config.string().required(),
	dataSetAPI: Config.string().required(),
	disableAJAX: Config.bool(),
	filters: Config.object(),
	id: Config.string(),
	items: Config.array().required(),
	pageSize: Config.number().required(),
	paginationBaseHref: Config.string(),
	paginationEntries: Config.array().required(),
	paginationSelectedEntry: Config.number().required(),
	schema: Config.object().required(),
	selectable: Config.bool(),
	spritemap: Config.string().required(),
	tableName: Config.string().required(),
	totalItems: Config.number().required()
};

export {CommerceTable};
export default CommerceTable;