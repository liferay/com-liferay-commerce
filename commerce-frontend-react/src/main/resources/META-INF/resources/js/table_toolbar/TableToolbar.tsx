import React, {useEffect, FunctionComponent } from 'react';
import Icon, {ClayIconSpriteContext} from '@clayui/icon';
import FiltersDropdown from './FiltersDropdown';
import getAppContext from './Context';
import ClayButton from '@clayui/button';
import ActiveFiltersBar from './ActiveFiltersBar';

const TableToolbar: FunctionComponent<any> = () => {
    const {state, actions} = getAppContext();

    return (
        <>
            <nav className="management-bar management-bar-light navbar navbar-expand-md">
                <div className="container-fluid container-fluid-max-xl">
                    <ul className="navbar-nav">
                        {/* <li className="nav-item">
                            <div className="custom-control custom-checkbox">
                                <label>
                                    <input className="custom-control-input" type="checkbox" />
                                    <span className="custom-control-label"></span>
                                </label>
                            </div>
                        </li> */}
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
                        {/* <li className="nav-item navbar-breakpoint-d-none">
                            <a className="nav-link nav-link-monospaced" href="#1" role="button">
                                <Icon symbol="search" />
                            </a>
                        </li>
                        <li className="nav-item navbar-breakpoint-down-d-none">
                            <a className="nav-link nav-link-monospaced" href="#1" role="button">
                                <Icon symbol="view" />
                            </a>
                        </li>
                        <li className="nav-item navbar-breakpoint-down-d-none">
                            <a className="nav-link nav-link-monospaced" href="#1" role="button">
                                <Icon symbol="table" />
                            </a>
                        </li> */}
                        <li className="nav-item">
                            <ClayButton 
                                displayType="primary"
                                monospaced
                            >
                                <Icon symbol="plus" />
                            </ClayButton>
                            {/* <a className="btn btn-primary nav-btn nav-btn-monospaced navbar-breakpoint-down-d-none" href="#1">
                                <svg className="lexicon-icon lexicon-icon-plus" focusable="false" role="presentation">
                                    <use href="/images/icons/icons.svg#plus"></use>
                                </svg>
                            </a> */}
                        </li>
                    </ul>
                </div>
            </nav>
            <ActiveFiltersBar />
        </>
    )
}

export default TableToolbar;