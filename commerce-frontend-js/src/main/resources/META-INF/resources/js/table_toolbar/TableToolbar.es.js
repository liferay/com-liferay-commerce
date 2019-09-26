import {ClayIconSpriteContext} from '@clayui/icon';
import React from 'react';

import ActiveFiltersBar from './ActiveFiltersBar.es';
import {StoreProvider} from './Context.es';
import NavBar from './NavBar.es';

const TableToolbar = props => {
	const {filters, ...otherProps} = props;

	return (
		<StoreProvider app={otherProps} filters={filters}>
			<ClayIconSpriteContext.Provider value={props.spritemap}>
				<NavBar />
				<ActiveFiltersBar />
			</ClayIconSpriteContext.Provider>
		</StoreProvider>
	);
};

export default TableToolbar;
