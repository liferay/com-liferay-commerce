import React, { createContext, useReducer, useContext } from 'react';
import applyMiddleware from './middleware/index';
import reducers from './reducers/index';

import { 
    initialState as initialFiltersState
} from './reducers/filters';

import { 
    actions as filtersActions
} from './actions/filters';


export const StoreContext = createContext<Partial<any>>({});;

export function initializeActions(actions, dispatch) {
	return Object.keys(actions).reduce(
		(curriedActions, actionName) => ({
			...curriedActions,
			[actionName]: actions[actionName](dispatch)
		}),
		{}
	);
}

export function StoreProvider(props) {
	const [state, dispatch] = useReducer(
		reducers,
		{
			filters: props.filters || initialFiltersState,
			app: props.app
		}
	);

	const actions = initializeActions(
		Object.assign({}, filtersActions),
		applyMiddleware(dispatch)
	);

	return (
		<StoreContext.Provider value={{ state, actions }}>
			{props.children}
		</StoreContext.Provider>
	);
}

export const StoreConsumer = StoreContext.Consumer;

const getAppContext = () => useContext(StoreContext);

export default getAppContext;