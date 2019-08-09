interface AppStateProps {
  spritemap: string,
}

const reducer = (
  state: AppStateProps | undefined, action) => {
    switch (action.type) {
      default:
        return state;
    }
};

export default reducer;