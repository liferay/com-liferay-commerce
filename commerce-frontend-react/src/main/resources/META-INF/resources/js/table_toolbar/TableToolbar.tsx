import React, {FunctionComponent } from 'react';
import ActiveFiltersBar from './ActiveFiltersBar';
import NavBar from './NavBar';
import { StoreProvider } from './Context';
import { ClayIconSpriteContext } from '@clayui/icon';

const TableToolbar: FunctionComponent<any> = (props) => {

    const {filters, ...otherProps} = props;

    return (
        <StoreProvider filters={filters} app={otherProps}>
            <ClayIconSpriteContext.Provider value={props.spritemap}>
                <NavBar/>
                <ActiveFiltersBar />
            </ClayIconSpriteContext.Provider>
        </StoreProvider>
    )
}

export default TableToolbar;