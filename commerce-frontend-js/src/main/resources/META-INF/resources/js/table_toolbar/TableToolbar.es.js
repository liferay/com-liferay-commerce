import React from 'react';
import ActiveFiltersBar from './ActiveFiltersBar.es';
import NavBar from './NavBar.es';
import {StoreProvider} from './Context.es';
import {ClayIconSpriteContext} from '@clayui/icon';

const TableToolbar = props => {
	const {filters, ...otherProps} = props;

	return (
		<StoreProvider filters={filters} app={otherProps}>
			<ClayIconSpriteContext.Provider value={props.spritemap}>
				<NavBar />
				<ActiveFiltersBar />
			</ClayIconSpriteContext.Provider>
		</StoreProvider>
	);
};

export default TableToolbar;
