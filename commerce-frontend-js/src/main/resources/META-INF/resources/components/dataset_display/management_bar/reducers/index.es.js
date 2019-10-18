import {actionsDefinition} from '../actions/index.es';

export const initialState = {
	actionButton: null,
	filters: [],
	inputSearch: {
		placeholder: Liferay.Language.get('search-item'),
		value: ''
	},
	onFilterChange: null
};

const reducer = (state = initialState, action) => {
	switch (action.type) {
		case actionsDefinition.UPDATE_FILTER_VALUE:
			return {
				...state,
				filters: state.filters.map(el => ({
					...el,
					value:
						action.payload.slug === el.slug
							? action.payload.value
							: el.value
				}))
			};
		case actionsDefinition.RESET_FILTERS_VALUE:
			return {
				...state,
				filters: state.filters.map(el => ({
					...el,
					value: null
				}))
			};
		case actionsDefinition.UPDATE_INPUT_SEARCH_VALUE:
			return {
				...state,
				inputSearch: {
					...state.inputSearch,
					value: action.payload
				}
			};
		default:
			return state;
	}
};

export default reducer;
