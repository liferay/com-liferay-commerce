import ClayIcon from '@clayui/icon';
import ClayLink from '@clayui/link';
import classNames from 'classnames';
import PropTypes from 'prop-types';
import React from 'react';
import TableContext from '../../DatasetDisplayContext.es';

function submit(action, method = 'get', form) {
	if (!form.current) {
		return;
	}

	form.current.action = action;
	form.current.method = method;
	form.current.submit();
}

function BulkActions(props) {
	return (
		<TableContext.Consumer>
			{({formRef}) => (
				<nav className="management-bar-primary navbar navbar-expand-md pb-2 pt-2 subnav-tbar">
					<div className="container-fluid container-fluid-max-xl py-1">
						<ul className="navbar-nav">
							<li className="nav-item">
								<span className="text-truncate">
									{props.selectedItemsId.length}{' '}
									{Liferay.Language.get('of')}{' '}
									{props.totalItemsCount}{' '}
									{Liferay.Language.get('items-selected')}
								</span>
								<ClayLink
									className="ml-3"
									href="#"
									onClick={e => {
										e.preventDefault();
										props.selectAllItems();
									}}
								>
									{Liferay.Language.get('select-all')}
								</ClayLink>
							</li>
						</ul>
						<div className="bulk-actions">
							{props.bulkActions.map((actionDefinition, i) => (
								<button
									className={classNames(
										'btn btn-monospaced btn-link',
										i > 0 && 'ml-1'
									)}
									key={actionDefinition.label}
									onClick={() =>
										submit(
											actionDefinition.action,
											actionDefinition.method,
											formRef
										)
									}
								>
									<ClayIcon symbol={actionDefinition.icon} />
								</button>
							))}
						</div>
					</div>
				</nav>
			)}
		</TableContext.Consumer>
	);
}

BulkActions.propTypes = {
	bulkActions: PropTypes.arrayOf(
		PropTypes.shape({
			action: PropTypes.string.isRequired,
			icon: PropTypes.string.isRequired,
			label: PropTypes.string.isRequired,
			method: PropTypes.string
		})
	),
	selectedItemsId: PropTypes.array.isRequired,
	totalItemsCount: PropTypes.number.isRequired
};

export default BulkActions;
