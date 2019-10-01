import ClayLink from '@clayui/link';
import React from 'react';

function BulkActions(props) {
	return (
		<nav className="tbar tbar-inline-md-down subnav-tbar subnav-tbar-primary">
            <div className="container-fluid container-fluid-max-xl">
                <ul className="tbar-nav tbar-nav-wrap">
                    <li className="tbar-item tbar-item-expand">
                        <div className="tbar-section">
                            <span className="component-text text-truncate-inline">
                                <span className="text-truncate">
                                    {props.selectedItemsCount} {Liferay.Language.get('of')} {props.totalItemsCount} {Liferay.Language.get('items-selected')}
                                </span>
                                <ClayLink
                                    className="ml-3"
                                    href="#"
                                    onClick={
                                        (e) =>{
                                            e.preventDefault()
                                            props.selectAllItems()
                                        }
                                    }
                                >
                                    {Liferay.Language.get('select-all')}
                                </ClayLink>
                            </span>
                        </div>
                    </li>
                </ul>
            </div>
        </nav>
	);
}

export default BulkActions;
