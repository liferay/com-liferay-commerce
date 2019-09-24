import FilterProps from "../Filter/definitions";

export const actionsDefinition = {
    GET_DATA_PENDING: 'getDataPending',
    GET_DATA_FULFILLED: 'getDataFullFilled',
    GET_DATA_REJECTED: 'getDataRejected',
    UPDATE_SEARCH_VALUE: 'updateSearchValue',
    SET_LOADING: 'setLoading',
    SET_ERROR: 'setError',
};

const updateSearchValue = dispatch => (value) => {
    return dispatch(
        {
            type: actionsDefinition.UPDATE_SEARCH_VALUE,
            payload: value,
        }
    )
};

const setLoading = dispatch => (value) => {
    return dispatch(
        {
            type: actionsDefinition.SET_LOADING,
            payload: value,
        }
    )
};

const setError = dispatch => (value) => {
    return dispatch(
        {
            type: actionsDefinition.SET_ERROR,
            payload: value,
        }
    )
};

const getQueryData = filter => ({
    field: filter.slug,
    value: filter.value,
    operator: filter.operator,
});

const serializeFilter = filter => `${filter.field} ${filter.operator} ${filter.value}`;

const getData = dispatch => (url, filters) => {
    const activeFilters = filters.filter(filter => filter.value);

    const prettifiedFilters = activeFilters.map(getQueryData);
    const serializedFilters = prettifiedFilters.map(serializeFilter);
    const query = serializedFilters.join(' and ');
    
    dispatch(
        {
            type: actionsDefinition.GET_DATA_PENDING,
        }
    );

    return fetch(
        url + (query && `?filter=${query}`),
        {
            method: 'GET',
            headers: new Headers({
                'Content-Type': 'application/json',
            }),
        }
    )
        .then(
            () => {
                dispatch({
                    type: actionsDefinition.GET_DATA_FULFILLED,
                });
            }
        )
        .catch(
            (err) => dispatch({
                type: actionsDefinition.GET_DATA_REJECTED,
                payload: err,
            })
        )
};

export const actions = {
    getData,
    updateSearchValue,
    setLoading,
    setError,
};

export default actions;
