import { actionsDefinition } from '../actions/filters'
import FilterProps from '../Filter/definitions';

export type ReducerProps = Array<FilterProps>

export const initialState: ReducerProps = [];

const reducer = (
  state: ReducerProps = initialState, action) => {
    switch (action.type) {
      case actionsDefinition.UPDATE_FILTER_VALUE:
        return state.map(
          el => ({
            ...el,
            value: action.payload.slug === el.slug 
              ? action.payload.value
              : el.value
          })
        );
      case actionsDefinition.RESET_FILTERS_VALUE:
        return state.map(
          el => ({
            ...el,
            value: null
          })
        );
      default:
        return state;
    }
};

export default reducer;