export const actionsDefinition = {
	RESET_FILTERS_VALUE: 'resetFiltersValue',
	UPDATE_FILTER_VALUE: 'updateFilterValue',
};

const updateFilterValue = dispatch => (id, value = null) =>
	dispatch({
		payload: {
			id,
			value
		},
		type: actionsDefinition.UPDATE_FILTER_VALUE
	});

const resetFiltersValue = dispatch => () =>
	dispatch({
		type: actionsDefinition.RESET_FILTERS_VALUE
	});

export const actions = {
	resetFiltersValue,
	updateFilterValue,
};

export default actions;
