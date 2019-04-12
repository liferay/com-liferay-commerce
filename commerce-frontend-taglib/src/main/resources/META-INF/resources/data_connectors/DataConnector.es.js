
import {
    getFetchParams,
    serializeParams,
    createFilterObj
} from './connectorsUtils.es';

import { 
    subscribe,
    notify,
    createConnectorId,
    getRelatedSelectedValuesById
} from './orchestratingUtils.es';

export default class DataConnector {

    constructor(settings = null, component = null) {
        this.initializeProperties();
        if (settings) {
            this.applySettings(settings)
        }
        this.component = component;
        subscribe(this)
    }

    applySettings(settings) {
        Object.entries(settings).forEach(
            ([key, value]) => { this[key] = value }
        )
    }

    setFilter(field, value, operations) {
        if(this.filters && value) {
            this.filters = this.filters.reduce(
                (acc, filter) => [
                    ...acc,
                    // if field already exists, it should update the existing filter
                    // otherwise it should add it to filters array
                    filter.field === field 
                        ? createFilterObj(field, value, operations)
                        : el
                ],
                []
            )
        }

        if(this.filters && !value) {
            this.filters = this.filters.filter((el) => el.field !== field)
        }

        if(!this.filters && value) {
            this.filters = [ createFilterObj(field, value, operations) ]
        }

        return this.filters
    }

    updateSettings(newSettings) {
        Object.keys(newSettings).forEach(
            (key) => {
                if(
                    this[key] instanceof Object &&
                    newSettings[key] instanceof Object
                ){
                    this[key] = Object.assign(
                        {},
                        this[key],
                        newSettings[key]
                    )
                } else {
                    this[key] = newSettings[key]
                }
            }
        )
    }

    setFilters(filters = []) {
        filters.forEach(
            filter => {
                this.setFilter(filter)
            }
        );
    }
    
    searchForRelatedConnectorsValues() {
        return getRelatedSelectedValuesById(this.id);
    }

    initializeProperties() {
        this.id = createConnectorId();
        this.pagination = {
            pageSize: 20,
            page: 1
        };
        this.filters = null;
        this.inputRef = null;
        /*
            {
                name: Config.string(),
                value: Config.oneOfType(
                    [
                        Config.string(),
                        Config.number()
                    ]
                ),
                operator: Config.oneOf(
                    'eq',
                    'neq',
                    'isnull',
                    'isnotnull',
                    'lt',
                    'lte',
                    'gt',
                    'gte',
                    'startswith',
                    'doesnotstartwith',
                    'endswith',
                    'doesnotendwith',
                    'contains',
                    'doesnotcontain',
                    'isempty',
                    'isnotempty'
                )
            }
        */
        this.sorting = null;
        /*
            {
                key: // string,
                order: oneOf(
                    'asc',
                    'desc'
                ) 
            }
        */
    }

    getFetchDataObj() {
        return Object.assign(
            {},
            this.filters ? { filters: this.filters } : null,
            this.pagination ? { pagination: this.pagination } : null,
            this.sorting ? { filters: this.filters } : null,
        );
    }

    performRequest(crudOperation) {
        const data = this.getFetchDataObj();

        const mappedData = !!this.on.mapParameters && this.on.mapParameters(data, this.component);

        const serializedData = typeof mappedData === 'string' ? 
            mappedData : 
            serializeParams(mappedData || data);
        
        const fetchSettings = getFetchParams(
            crudOperation,
            this.remote[crudOperation],
            serializedData
        );

        return fetch.apply(null, fetchSettings)
            .then(response => response.json())
            .then(response => this.on.parseResponse 
                ? this.on.parseResponse(response)
                : response
            )
            .then(jsonResponse => {
                const func = this.on[crudOperation];
                if (func) {
                    return func(jsonResponse, this.component)
                }
                return jsonResponse
            })
            .catch(
                (err) => {
                    if (this.on.error) {
                        return this.on.error(err, crudOperation, serializedData)
                    }
                    throw new Error(err)
                }
            )
    }
    
    read() {
        return this.performRequest('read')
    }
    
    update() {
        return this.performRequest('update')
    }
    
    create() {
        return this.performRequest('create')
    }
    
    delete() {
        return this.performRequest('delete')
    }

    notify() {
        return this.on.notification && this.on.notification() 
    }
}