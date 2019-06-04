'use strict';

import { debounce } from 'metal-debounce';

import DataConnector from '../data_connectors/DataConnector.es';
import BaseDatalist from './BaseDatalist.es';

import { defaultEnhancedComponentState } from '../data_connectors/connectorsUtils.es';
import { Config } from 'metal-state';

class Datalist extends BaseDatalist {

    created(){
        super.created();
        if(this.connectorSettings) {
            this.initializeConnector(this.connectorSettings)
        }
        this._getNewData = debounce(this._getNewData.bind(this), 200);
    }

    _getNewData() {
        if(this.connector) {
            this.connector.setFilter('keyword', this.query, 'contains');
            this.connector.read()
        }
    }

    initializeConnector(connectorSettings) {
        const settings = {
            remote: connectorSettings.remote,
            on: Object.assign(
                {},
                {
                    read: (el) => {
                        this.data = el.map(
                            (el) => { 
                                return {
                                    label: el[this.labelField],
                                    value: el[this.valueField]
                                }
                            }
                        )
                    }
                },
                connectorSettings.on || {}
            )
        }
        this.connector = new DataConnector(settings, this)
    }

    rendered(){
        this.connector.inputRef = this.refs.input;
        if(this.changeToBeNotified && this.connector) {
            this.connector.notify();
            this.changeToBeNotified = false;
            this.emit('selected')
        }
    }

    detached(){
        this.connector = null;
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

    syncQuery(query) {
        super.syncQuery(query);
        this._getNewData();
    }

    syncStringifiedSelected() {
        this.changeToBeNotified = true;
    }

    syncSelected(selected) {
        this.stringifiedSelected = selected ? JSON.stringify(selected) : '';
    }

}

Datalist.STATE = Object.assign(
    {},
    {
        valueField: Config.string().value('value'),
        labelField: Config.string().value('label'),
        dependsOn: Config.shapeOf(
            {
                id: Config.string(),
                condition: Config.oneOf(
                    [
                        'not null'
                    ]
                )
            }
        )
    },
    Datalist.STATE,
    defaultEnhancedComponentState
)

export {Datalist};
export default Datalist;
