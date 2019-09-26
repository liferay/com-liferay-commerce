const applyMiddleware = dispatch => action => {
	dispatch(action);
};

export default applyMiddleware;
