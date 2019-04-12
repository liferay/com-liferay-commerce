import { Config } from 'metal-soy';

export function createFilterObj(field, value = null, operations = 'contains') {
    return {
        field,
        value,
        operations
    }
}

export const defaultEnhancedComponentState = {
	connector: Config.any(),
	smart: Config.bool().value(true),
	selected: Config.any(),
	data: Config.any(),
    connectorSettings: Config.shapeOf(
        {
            remote: Config.object(),
            on: Config.object()
        }
    )
}

export function compose(...fns) {
	return (...args) => fns.reduceRight((params, f) => Array.isArray(params) ? f(...params) : f(params), args)
}

export function pipe(...fns) {
	return (...args) => fns.reduce((params, f) => Array.isArray(params) ? f(...params) : f(params), args);
}

export function isPromise(p) {
    return !!p && p instanceof Promise;
}

export function makeId(length) {
    const charsList = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789";
    const id = new Array(5).fill('').reduce(
        (acc) => {
            return acc + charsList.charAt(Math.floor(Math.random() * charsList.length))
        },
        ''
    )
    return id
}

export function createNewId(length, idsList = []) {
    let newId = null
    do {
        newId = makeId(length)
    } while ( idsList.includes(newId) );
    return newId
}

export function wrapIntoPromise(action) {
	return (...params) => {
		const result = action.apply(null, params);
		return isPromise(result) 
			? result
			: new Promise(
                (resolve, _) => resolve(result))
	}
}

export const crudMethodsMapping = new Map(
    [
        ['read', 'get'],
        ['create', 'post'],
        ['update', 'put'],
        ['delete', 'delete']
    ]
);

export function getFetchParams(crudOperation, remoteOption, serializedData) {
    const method = remoteOption.method || crudMethodsMapping.get(crudOperation);

    const baseUrl = remoteOption.url || remoteOption;

    const url = method === 'get'
        ? baseUrl + '?' + serializedData
        : baseUrl

    const body = method !== 'get' ? serializedData : null;

    const credential = remoteOption.credential;

    return [
        url,
        Object.assign(
            {},
            body || {},
            credential || {}
        )
    ]
}

export function formatRemoteKey(remoteKey, remoteValue){
    const remoteDefaultValue = {
        method: crudMethodsMapping.get(remoteKey),
        credential: 'include'
    };

    if(typeof remoteValue === 'string'){
        remoteDefaultValue.url = remoteValue
    } else {
        remoteDefaultValue.url = remoteValue.url
        remoteDefaultValue.method = remoteValue.method || remoteDefaultValue.method
        remoteDefaultValue.credential = remoteValue.credential || remoteDefaultValue.credential
    }

    return remoteDefaultValue;
};

export function formatSetting(settingName, settingValue) {
    switch(settingName) {
        case 'remote':
            const remoteKeys = Object.keys(settingValue);
            const formattedRemote = remoteKeys.reduce(
                (acc, remoteKey) => {
                    const remoteValue = settingValue[remoteKey]
                    return Object.assign(
                        {},
                        acc,
                        {
                            [remoteKey]: formatRemoteKey(remoteKey, remoteValue)
                        }			
                    )	
                },
                {}
            )
            return [settingName, formattedRemote];
        default:
            return [settingName, settingValue]
    }
}

export function isSettingValid(settingName) {
    switch(settingName) {
        case 'componentType':
        case 'remote':
        case 'on':
        return true;
    default:
        throw new Error(`Setting "${settingName}" not supported`)
    }
}

export function serializeParams(params, type = 'json') {
    let serializedParams = null;
    switch(type) {
        case 'json':
            serializedParams = JSON.stringify(params);
            break;
        default: 
            throw new Error(`Serialization type "${dataKey}" not supported`);
    }
    return serializedParams
}