import React, { FunctionComponent } from 'react';
import Icon from '@clayui/icon';
import FiltersDropdown from './FiltersDropdown';
import ClayButton from '@clayui/button';
import getAppContext from './Context';

const NavBar: FunctionComponent<any> = (props) => {
    const { actions } = getAppContext();

    function handlePlusButtonClick(e) {
        Promise.resolve(props.plusButton.onClick(e))
            .then(actions.resetFiltersValue)
    }

    return (
        <nav className="management-bar management-bar-light navbar navbar-expand-md">
            <div className="container-fluid container-fluid-max-xl">
                <ul className="navbar-nav">
                    <FiltersDropdown />
                </ul>
                <div className="navbar-form navbar-form-autofit navbar-overlay navbar-overlay-sm-down">
                    <div className="d-inline">
                        <form role="search">
                            <div className="input-group">
                                <div className="input-group-item">
                                    <input className="form-control input-group-inset input-group-inset-after" placeholder="Search for..." type="text" />
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
                    {props.plusButton && (
                        <li className="nav-item">
                            <ClayButton 
                                displayType="primary"
                                monospaced
                                onClick={handlePlusButtonClick}
                            >
                                <Icon symbol="plus" />
                            </ClayButton>
                        </li>
                    )}
                </ul>
            </div>
        </nav>
    )
}
export default NavBar;