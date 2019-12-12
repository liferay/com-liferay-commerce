export const actionsDefinition = {
	RESET_FILTERS_VALUE: 'resetFiltersValue',
	UPDATE_FILTER_VALUE: 'updateFilterValue',
	UPDATE_INPUT_SEARCH_VALUE: 'updateInputSearchValue'
};

const updateFilterValue = dispatch => (slug, value = null) =>
	dispatch({
		payload: {
			slug,
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
