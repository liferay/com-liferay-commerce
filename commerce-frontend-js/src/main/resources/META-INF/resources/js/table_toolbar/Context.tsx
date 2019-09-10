import React, { createContext, useReducer, useContext } from 'react';
import applyMiddleware from './middleware/index';
import reducers, { StoreProps, initialState } from './reducers';

import { 
    actions as filtersActions
} from './actions/filters';

import { 
    actions as appActions
} from './actions/app';

const actions = Object.assign(
	{},
	filtersActions,
	appActions
);

export const StoreContext = createContext({
	state: initialState,
	actions: serializeActions(
		actions,
		applyMiddleware(e => e)
	)
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

type IProps = StoreProps & {
	children
}

export function StoreProvider({children, ...stateProps}: IProps) {
	const [state, dispatch] = useReducer(
		reducers,
		stateProps
	);

	const serializedActions = serializeActions(
		actions,
		applyMiddleware(dispatch)
	);

	return (
		<StoreContext.Provider value={{ state, actions: serializedActions }}>
			{children}
		</StoreContext.Provider>
	);
}

export const StoreConsumer = StoreContext.Consumer;

const getAppContext = () => useContext(StoreContext);

export interface ContextProps {
	state: StoreProps,
	actions: {
		[s: string]: any 
	}
}

export default getAppContext;