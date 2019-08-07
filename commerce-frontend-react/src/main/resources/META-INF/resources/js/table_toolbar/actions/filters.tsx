export const actionsDefinition = {
    UPDATE_FILTER_VALUE: 'updateFilterValue',
    RESET_FILTERS_VALUE: 'resetFiltersValue',
    PERFORM_UPDATE_REQUEST_PENDING: 'performUpdateRequestPending',
    PERFORM_UPDATE_REQUEST_FULFILLED: 'performUpdateRequestFullFilled',
    PERFORM_UPDATE_REQUEST_REJECTED: 'performUpdateRequestRejected'
}

const updateFilterValue = dispatch => (slug: string, value: any = null) => dispatch(
    { 
        type: actionsDefinition.UPDATE_FILTER_VALUE, 
        payload: {
            slug,
            value
        }
    }
)

const resetFiltersValue = dispatch => () => dispatch(
    { 
        type: actionsDefinition.RESET_FILTERS_VALUE
    }
)

const performUpdateRequest = dispatch => (url: string, filters: Object) => {
    
    dispatch(
        {
            type: actionsDefinition.PERFORM_UPDATE_REQUEST_PENDING
        }
    )

    return fetch(
        url,
        {
            method: 'GET',
            headers: new Headers({
                'Content-Type': 'application/json'
            })
        }
    )
        .then(
            () => {
                dispatch({ 
                    type: actionsDefinition.PERFORM_UPDATE_REQUEST_FULFILLED,
                })
            }
        )
        .catch(
            (err) => dispatch({ 
                type: actionsDefinition.PERFORM_UPDATE_REQUEST_REJECTED,
                payload: err 
            })
        )
}

export const actions = {
    updateFilterValue,
    resetFiltersValue,
    performUpdateRequest
}

export default actions;
