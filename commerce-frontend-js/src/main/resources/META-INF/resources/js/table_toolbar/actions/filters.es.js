export const actionsDefinition = {
    UPDATE_FILTER_VALUE: 'updateFilterValue',
    RESET_FILTERS_VALUE: 'resetFiltersValue',
}

const updateFilterValue = dispatch => (slug, value = null) => dispatch(
    { 
        type: actionsDefinition.UPDATE_FILTER_VALUE,
        payload: {
            slug,
            value,
        }
    }
)

const resetFiltersValue = dispatch => () => dispatch(
    { 
        type: actionsDefinition.RESET_FILTERS_VALUE,
    }
)

export const actions = {
    updateFilterValue,
    resetFiltersValue,
}

export default actions;
