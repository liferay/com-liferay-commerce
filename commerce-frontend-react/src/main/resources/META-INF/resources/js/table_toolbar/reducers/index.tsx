import filtersReducer from './filters';
import { combineReducers } from 'redux';

const combinedReducers = combineReducers({
	filters: filtersReducer,
});

export default combinedReducers;