import filtersReducer, {
	initialState as filtersInitialState
} from './filters.es';

import appReducer, {initialState as appInitialState} from './app.es';

import {combineReducers} from 'redux';

const combinedReducers = combineReducers({
	filters: filtersReducer,
	app: appReducer
});

// export interface StoreProps {
// 	filters: FiltersReducerProps,
// 	app: AppReducerProps
// }

export const initialState = {
	filters: filtersInitialState,
	app: appInitialState
};

export default combinedReducers;
