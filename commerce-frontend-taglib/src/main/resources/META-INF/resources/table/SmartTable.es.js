import 'clay-table';
import 'clay-pagination-bar';

import {Config} from 'metal-state';
import Component from 'metal-component';
import Soy from 'metal-soy';

import { debounce } from 'metal-debounce';

import DataConnector from '../data_connectors/DataConnector.es';
import { defaultEnhancedComponentState } from '../data_connectors/connectorsUtils.es';

import template from './SmartTable.soy';

import './SmartTableExtensions.es';
import './ModalLinkCellTemplate.es';

class SmartTable extends Component {

	created(){
		super.created();
        if(this.connectorSettings) {
            this.initializeConnector()
		}
        this.refresh = debounce(this.refresh.bind(this), 200);
	}
	
    syncConnectorSettings(settings) {
        this.connector.updateSettings(settings)
	}
	
	reload(){
		this.currentPage = 1;
		this.refresh();
	}
	
	refresh(){
		this.connector.read();
	}

    initializeConnector(connectorSettings) {
		const onFunctions = Object.assign(
			{},
			{
				read: (response) => { this.items = response }
			},
			connectorSettings.on || {}
		)
        const settings = Object.assign(
			{},
			connectorSettings,
			{
				on: onFunctions
			}
		)
        this.connector = new DataConnector(settings, this)
	}

    syncConnectorSettings(settings) {
        if(!settings) {
            return;
        }
        if(this.connector) {
            this.connector.updateSettings(settings)
        } else {
            this.initializeConnector(settings)
        }
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
		this.paginationSelectedEntry = this.paginationEntries.map((x) => x.label).indexOf(this.pageSize);

	}
	
	syncPageSize(pageSize){
		if(this.connector) {
			this.connector.pagination.pageSize = pageSize;
			this.refresh();
		}
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
		const newPage = parseInt(event.data.page, 10);
		if (this.currentPage == newPage) {
			return;
		}
		this.currentPage = newPage;
	}

	syncCurrentPage(currentPage) {
		if(this.connector) {
			this.connector.pagination.page = currentPage;
			this.refresh();
		}
	}

}

Soy.register(SmartTable, template);

SmartTable.STATE = Object.assign(
	{},
	{
		currentPage: Config.number().required(),
		apiUrl: Config.string().required(),
		disableAJAX: Config.bool(),
		id: Config.string(),
		items: Config.array().required(),
		pageSize: Config.number().required(),
		paginationBaseHref: Config.string(),
		paginationEntries: Config.array().required(),
		paginationSelectedEntry: Config.number().required(),
		schema: Config.object().required(),
		selecSmartTable: Config.bool(),
		spritemap: Config.string().required(),
		tableName: Config.string().required(),
		totalItems: Config.number().required(),
		showPagination: Config.bool().value(true)
	},
	defaultEnhancedComponentState
)

export {SmartTable};
export default SmartTable;