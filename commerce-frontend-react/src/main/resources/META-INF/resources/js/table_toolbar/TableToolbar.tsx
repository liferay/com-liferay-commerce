import React, { useState, FunctionComponent } from 'react';
import Icon from '@clayui/icon';
import ClayDropDown from '@clayui/drop-down';
import Button from '@clayui/button';
import ClayPanel from '@clayui/panel';

function FiltersDropdown(props) {
    const [ active, setActive ] = useState(false);
    
    return (
        <ClayDropDown
            trigger={
                <a aria-expanded="false" aria-haspopup="true" className="dropdown-toggle nav-link navbar-breakpoint-d-block" data-toggle="dropdown" href="#1" role="button">
                    <span className="navbar-text-truncate">Filters</span>
                    <Icon spritemap={props.spritemap} symbol="caret-bottom" />
                </a>
            }
            active={active}
            onActiveChange={setActive}
        >
            <ClayDropDown.ItemList>
                    {props.filtersDefinitions.map((item, i) => (
                        <ClayPanel
                            className="mb-0"
                            collapsable
                            displayTitle="Title"
                            displayType="primary"
                            showCollapseIcon={true}
                            spritemap={props.spritemap}
                        >
                            <ClayPanel.Body>
                                {'Body!'}
                            </ClayPanel.Body>
                        </ClayPanel>
                    ))}
            </ClayDropDown.ItemList>
        </ClayDropDown>
    )
}

const filtersDefinitions = [
    {
        slug: 'shipping-date',
        type: 'date', //'select' // 'input' // 'checkbox' // 'radio' // 'number'
        label: 'shipping-date',
        formData: {
            from: {
                label: 'from',
            },
            to: {
                label: 'to',
            }
        }
    },
    {
        slug: 'shipping-date',
        type: 'date',
        label: 'shipping-date',
        formData: {
            from: {
                label: 'from',
            },
            to: {
                label: 'to',
            }
        }
    }
]

const TableToolbar: FunctionComponent = (props) => {
    return (
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
                    <FiltersDropdown spritemap={props.spritemap} filtersDefinitions={filtersDefinitions}/>
                </ul>
                <div className="navbar-form navbar-form-autofit navbar-overlay navbar-overlay-sm-down">
                    <div className="container-fluid container-fluid-max-xl">
                        <form role="search">
                            <div className="input-group">
                                <div className="input-group-item">
                                    <input className="form-control input-group-inset input-group-inset-after" placeholder="Search for..." type="text" />
                                    <span className="input-group-inset-item input-group-inset-item-after">
                                        <button className="btn btn-unstyled" type="submit">
                                            <Icon spritemap={props.spritemap} symbol="search" />
                                        </button>
                                        <button className="btn btn-unstyled d-none" type="button">
                                            <Icon spritemap={props.spritemap} symbol="times" />
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
                            <Icon spritemap={props.spritemap} symbol="search" />
                        </a>
                    </li>
                    <li className="nav-item navbar-breakpoint-down-d-none">
                        <a className="nav-link nav-link-monospaced" href="#1" role="button">
                            <Icon spritemap={props.spritemap} symbol="view" />
                        </a>
                    </li>
                    <li className="nav-item navbar-breakpoint-down-d-none">
                        <a className="nav-link nav-link-monospaced" href="#1" role="button">
                            <Icon spritemap={props.spritemap} symbol="table" />
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
                                    <Icon spritemap={props.spritemap} symbol="ellipsis-v" />
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
    )
}

export default TableToolbar;