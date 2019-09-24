import React, { FunctionComponent, useState, useEffect } from 'react';
import Icon from '@clayui/icon';
import FiltersDropdown from './FiltersDropdown.es';
import ClayButton from '@clayui/button';
import getAppContext from './Context.es';


const NavBar = () => {
    const { state, actions } = getAppContext();
    const [ inputSearchValue, setInputSearchValue ] = useState(state.app.inputSearch.value);
    const [ initialized, setInitialized ] = useState(false);

    function handlePlusButtonClick(e) {
        Promise.resolve(
            state.app.plusButton && 
            state.app.plusButton.onClick(e)
        )
            .then(() => {
                if(
                    state.app.plusButton && 
                    state.app.plusButton.resetFiltersAfterClickAction
                ) {
                    actions.resetFiltersValue();
                }
            })
    }

    useEffect(
        () => {
            if(!initialized) {
                return setInitialized(true)
            }
            actions.getData(
                state.app.queryEndpoint,
                state.filters.concat({
                    type: "text",
                    slug: state.app.inputSearch.name,
                    value: state.app.inputSearch.value,
                    operator: "contains",
                    label: ''
                })
            );
        },
        [
            ...state.filters.map((filter) => filter.value),
            state.app.inputSearch.value
        ]
    )

    return (
        <nav className="management-bar management-bar-light navbar navbar-expand-md">
            <div className="container-fluid container-fluid-max-xl">
                <ul className="navbar-nav">
                    <FiltersDropdown />
                </ul>
                <div className="navbar-form navbar-form-autofit navbar-overlay navbar-overlay-sm-down">
                    <div className="d-inline">
                        <form role="search" onSubmit={
                            (e) => {
                                e.preventDefault();
                                actions.updateSearchValue(inputSearchValue)
                            }
                        }>
                            <div className="input-group">
                                <div className="input-group-item">
                                    <input 
                                        className="form-control input-group-inset input-group-inset-after" 
                                        placeholder="Search for..." 
                                        type="text" 
                                        value={inputSearchValue || ''}
                                        onChange={(e) => setInputSearchValue(e.target.value)}
                                    />
                                    <span className="input-group-inset-item input-group-inset-item-after">
                                        <button className="btn btn-unstyled" type="submit">
                                            <Icon symbol="search" />
                                        </button>
                                        <button className="btn btn-unstyled d-none" type="button">
                                            <Icon symbol="times" />
                                        </button>
                                    </span>
                                </div>
                            </div>
                        </form>
                    </div>
                </div>
                <ul className="navbar-nav">
                    {
                        state.app.plusButton && (
                            <li className="nav-item">
                                <ClayButton 
                                    displayType="primary"
                                    monospaced
                                    onClick={handlePlusButtonClick}
                                >
                                    <Icon symbol="plus" />
                                </ClayButton>
                            </li>
                        )
                    }
                </ul>
            </div>
        </nav>
    )
}
export default NavBar;