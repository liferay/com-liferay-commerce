
'use strict';

import { createNewId } from './connectorsUtils.es'

export const collection = new Map();

export function getConnectorById(connectorId) {
    return collection.get(connectorId)
}

export function getRelatedSelectedValuesById(connectorId, included) {
    const connectors = getRelatedConnectorsById(connectorId, included);
    
    return connectors.reduce(
        (acc, connector) => {
            const input = connector.inputRef;
            return !input 
                ? acc 
                : acc.concat(
                    {
                    name: input.name,
                    value: input.value ? JSON.parse(input.value) : null
                    }
                )
        },
        []
    )
}

export function createConnectorId(length = 5) {
    const ids = Array.from(collection.keys());
    return createNewId(length, ids);
}

export function getRelatedConnectorsById(connectorId, included = false) {
    let relatedConnectors = new Map(collection);
    if(!included) {
        relatedConnectors.delete(connectorId)
    }

    return relatedConnectors.size ? Array.from(relatedConnectors.values()) : null;
}

export function notify(triggererId, included = false) {
    const connectors = getRelatedConnectorsById(triggererId, included);
    return connectors.map((connector) => connector.respond(triggererId))
}

export function subscribe(connector) {
    const { id } = connector 
    collection.set(id, connector)
}

export function getStore(){
    return collection;
}

const dataConnectorOrchestrator = {
    collection,
    getConnectorById,
    getRelatedConnectorsById,
    notify,
    getRelatedSelectedValuesById,
    subscribe,
    getStore
}

window.dataConnectorOrchestrator = dataConnectorOrchestrator

export default dataConnectorOrchestrator;