import React, {createContext, useContext, useReducer} from 'react';

import {actions as appActions} from './actions/app.es';
import {actions as filtersActions} from './actions/filters.es';
import applyMiddleware from './middleware/index.es';
import reducers, {initialState} from './reducers/index.es';

const actions = { ...filtersActions, ...appActions };

export const StoreContext = createContext({
	actions: serializeActions(actions, applyMiddleware(e => e)),
	state: initialState
});

export function serializeActions(actions, dispatch) {
	return Object.keys(actions).reduce(
		(curriedActions, actionName) => ({
			...curriedActions,
			[actionName]: actions[actionName](dispatch)
		}),
		{}
	);
}

export function StoreProvider({children, ...stateProps}) {
	const [state, dispatch] = useReducer(reducers, stateProps);

	const serializedActions = serializeActions(
		actions,
		applyMiddleware(dispatch)
	);

	return (
		<StoreContext.Provider value={{actions: serializedActions, state}}>
			{children}
		</StoreContext.Provider>
	);
}

export const StoreConsumer = StoreContext.Consumer;

// eslint-disable-next-line react-hooks/rules-of-hooks
const getAppContext = () => useContext(StoreContext);

export default getAppContext;
