import ClayIcon from '@clayui/icon';
import ClayLink from '@clayui/link';
import classNames from 'classnames';
import React from 'react';
import TableContext from '../../table/TableContext.es';

function submit(action, form) {
    console.log(action,form)
    debugger;
}

function BulkActions(props) {
    return (
        <TableContext.Consumer>
            {value => (
                <nav 
                    className="management-bar-primary navbar navbar-expand-md pb-2 pt-2 subnav-tbar"
                >
                    <div className="container-fluid container-fluid-max-xl py-1">
                        <ul className="navbar-nav">
                            <li className="nav-item">
                                <span className="text-truncate">
                                    {props.selectedItemsId.length} {Liferay.Language.get('of')} {props.totalItemsCount} {Liferay.Language.get('items-selected')}
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
                            </li>
                        </ul>
                        <div className="bulk-actions">
                            {props.bulkActions.map(
                                (actionDefinition, i) => (
                                    <button 
                                        className={classNames('btn btn-monospaced btn-link', i > 0 && 'ml-1')} 
                                        key={actionDefinition.label}
                                        onClick={() => submit(actionDefinition.action, value)}
                                    >
                                        <ClayIcon symbol={actionDefinition.icon} />
                                    </button>
                                )
                            )}
                        </div>
                    </div>
                </nav>
            )}
        </TableContext.Consumer>
    )
}

export default BulkActions;
