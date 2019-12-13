import {actionsDefinition} from '../actions/index.es';

export const initialState = {
	actionButton: null,
	filters: [],
	inputSearch: {
		placeholder: Liferay.Language.get('search-item'),
		value: ''
	},
	onFiltersChange: null
};

const reducer = (state = initialState, action) => {
	switch (action.type) {
		case actionsDefinition.UPDATE_FILTER_VALUE:
			return {
				...state,
				filters: state.filters.map(el => ({
					...el,
					value:
						action.payload.id === el.id
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
		default:
			return state;
	}
};

export default reducer;
