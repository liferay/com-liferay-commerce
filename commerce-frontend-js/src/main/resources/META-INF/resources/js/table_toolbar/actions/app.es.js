export const actionsDefinition = {
	GET_DATA_FULFILLED: 'getDataFullFilled',
	GET_DATA_PENDING: 'getDataPending',
	GET_DATA_REJECTED: 'getDataRejected',
	SET_ERROR: 'setError',
	SET_LOADING: 'setLoading',
	UPDATE_SEARCH_VALUE: 'updateSearchValue'
};

const updateSearchValue = dispatch => value => {
	return dispatch({
		payload: value,
		type: actionsDefinition.UPDATE_SEARCH_VALUE,
	});
};

const setLoading = dispatch => value => {
	return dispatch({
		payload: value,
		type: actionsDefinition.SET_LOADING,
	});
};

const setError = dispatch => value => {
	return dispatch({
		payload: value,
		type: actionsDefinition.SET_ERROR,
	});
};

const getQueryData = filter => ({
	field: filter.slug,
	operator: filter.operator,
	value: filter.value,
});

const serializeFilter = filter =>
	`${filter.field} ${filter.operator} ${filter.value}`;

const getData = dispatch => (url, filters) => {
	const activeFilters = filters.filter(filter => filter.value);

	const prettifiedFilters = activeFilters.map(getQueryData);
	const serializedFilters = prettifiedFilters.map(serializeFilter);
	const query = serializedFilters.join(' and ');

	dispatch({
		type: actionsDefinition.GET_DATA_PENDING
	});

	return fetch(url + (query && `?filter=${query}`), {
		headers: new Headers({
			'Content-Type': 'application/json'
		}),
		method: 'GET'
	})
		.then(() => {
			dispatch({
				type: actionsDefinition.GET_DATA_FULFILLED
			});
		})
		.catch(err =>
			dispatch({
				payload: err,
				type: actionsDefinition.GET_DATA_REJECTED,
			})
		);
};

export const actions = {
	getData,
	setError,
	setLoading,
	updateSearchValue
};

export default actions;
