import filtersReducer, {
	initialState as filtersInitialState,
	ReducerProps as FiltersReducerProps
} from './filters';

import appReducer, {
	initialState as appInitialState,
	ReducerProps as AppReducerProps
}  from './app';

import { combineReducers } from 'redux';

const combinedReducers = combineReducers({
	filters: filtersReducer,
	app: appReducer,
});

export interface StoreProps {
	filters: FiltersReducerProps,
	app: AppReducerProps
}

export const initialState = {
	filters: filtersInitialState,
	app: appInitialState,
}

export default combinedReducers;