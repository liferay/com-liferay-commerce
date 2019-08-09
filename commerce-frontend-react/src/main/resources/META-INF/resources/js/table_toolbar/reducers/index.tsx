import filtersReducer from './filters';
import appReducer from './app';
import { combineReducers } from 'redux';

const combinedReducers = combineReducers({
	filters: filtersReducer,
	app: appReducer,
});

export default combinedReducers;