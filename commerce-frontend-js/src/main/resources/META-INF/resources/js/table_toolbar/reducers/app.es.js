import {actionsDefinition} from '../actions/app.es';

// export interface ReducerProps {
//   spritemap?: string,
//   plusButton?: {
//     onClick: FunctionConstructor,
//     resetFiltersAfterClickAction: boolean
//   },
//   queryEndpoint?: string,
//   inputSearch: {
//     name: string,
//     value?: string
//   }
// }

export const initialState = {
	inputSearch: {
		name: 'main-search'
	}
};

const reducer = (state = initialState, action) => {
	switch (action.type) {
		case actionsDefinition.UPDATE_SEARCH_VALUE:
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
