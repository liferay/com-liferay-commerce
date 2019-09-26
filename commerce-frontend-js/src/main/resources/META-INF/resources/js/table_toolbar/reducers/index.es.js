import {combineReducers} from 'redux';

import appReducer, {initialState as appInitialState} from './app.es';
import filtersReducer, {
	initialState as filtersInitialState
} from './filters.es';

const combinedReducers = combineReducers({
	app: appReducer,
	filters: filtersReducer
});

export const initialState = {
	app: appInitialState,
	filters: filtersInitialState
};

export default combinedReducers;
