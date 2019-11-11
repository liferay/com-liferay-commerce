import {
    actionDefinition
} from '../actions/folder.es'

export const initialState = {
    content: null,
    compatibilities: null,
    loading: null
}

export default function reducer(state = initialState, action) {
    switch (action.type) {
        case actionDefinition.GET_FOLDER_FULFILLED:
            return {
                ...state,
                content: action.payload.data.content,
                compatibilities: action.payload.data.compatibilities,
                loading: false
            }
        case actionDefinition.GET_FOLDER_PENDING:
            return {
                ...state,
                loading: true
            }
        default:
            return state
    }
};