import React, {createContext, useContext, useReducer} from 'react';

import {actions} from '../actions/index.es';
import applyMiddleware from '../middleware/index.es';
import reducer, {initialState} from '../reducers/index.es';

export const StoreContext = createContext(null);

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
	const [state, dispatch] = useReducer(reducer, {
		...initialState,
		...stateProps
	});

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

export function useAppState() {
	return useContext(StoreContext);
}

export default useAppState;
