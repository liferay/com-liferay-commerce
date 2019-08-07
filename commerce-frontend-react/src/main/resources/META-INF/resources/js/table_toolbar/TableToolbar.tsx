import React, {useEffect, FunctionComponent } from 'react';
import Icon, {ClayIconSpriteContext} from '@clayui/icon';
import FiltersDropdown from './FiltersDropdown';
import getAppContext from './Context';
import ActiveFiltersBar from './ActiveFiltersBar';

const TableToolbar: FunctionComponent<any> = () => {
    const {state, actions} = getAppContext();

    return (
        <>
            <nav className="management-bar management-bar-light navbar navbar-expand-md">
                <div className="container-fluid container-fluid-max-xl">
                    <ul className="navbar-nav">
                        <li className="nav-item">
                            <div className="custom-control custom-checkbox">
                                <label>
                                    <input className="custom-control-input" type="checkbox" />
                                    <span className="custom-control-label"></span>
                                </label>
                            </div>
                        </li>
                        <FiltersDropdown />
                    </ul>
                    <div className="navbar-form navbar-form-autofit navbar-overlay navbar-overlay-sm-down">
                        <div className="container-fluid container-fluid-max-xl">
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
                        <li className="nav-item navbar-breakpoint-d-none">
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
                        </li>
                        <li className="nav-item">
                            <button className="btn btn-secondary clay-site-open-overlay-up nav-btn" type="button">
                                Open Overlay
                            </button>
                        </li>
                    </ul>
                    <div className="navbar-overlay navbar-overlay-up">
                        <div className="container-fluid container-fluid-max-xl">
                            <ul className="navbar-nav">
                                <li className="nav-item">
                                    <div className="custom-control custom-checkbox">
                                        <label>
                                            <input className="custom-control-input" type="checkbox" />
                                            <span className="custom-control-label"></span>
                                        </label>
                                    </div>
                                </li>
                                <li className="dropdown nav-item nav-item-shrink">
                                    <span className="navbar-text">3 of 25</span>
                                </li>
                                <li className="nav-item">
                                    <button className="btn btn-link nav-btn" type="button">
                                        Select All
                                    </button>
                                </li>
                            </ul>
                            <ul className="navbar-nav">
                                <li className="dropdown nav-item">
                                    <a aria-expanded="false" aria-haspopup="true" className="dropdown-toggle nav-link nav-link-monospaced" data-toggle="dropdown" href="#1" role="button">
                                        <Icon symbol="ellipsis-v" />
                                    </a>
                                    <ul className="dropdown-menu dropdown-menu-right">
                                        <li><a className="dropdown-item" href="#1" role="button">Delete</a></li>
                                        <li><a className="dropdown-item" href="#1" role="button">Copy</a></li>
                                        <li><a className="dropdown-item" href="#1" role="button">Info</a></li>
                                    </ul>
                                </li>
                                <li className="nav-item">
                                    <button className="btn btn-secondary clay-site-close-overlay-up nav-btn" type="button">
                                        Close Overlay
                                    </button>
                                </li>
                            </ul>
                        </div>
                    </div>
                </div>
            </nav>
            <ActiveFiltersBar />
        </>
    )
}

export default TableToolbar;