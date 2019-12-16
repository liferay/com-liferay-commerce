import ClayIcon from '@clayui/icon';
import ClayLink from '@clayui/link';
import classNames from 'classnames';
import PropTypes from 'prop-types';
import React, {useState, useEffect} from 'react';
import TableContext from '../../DatasetDisplayContext.es';
import { OPEN_SIDE_PANEL } from '../../../../utilities/eventsDefinitions.es';
import { getOpenedSidePanel } from '../../../../utilities/sidePanels.es';

function submit(action, method = 'get', form) {
	if (!form.current) {
		return;
	}

	form.current.action = action;
	form.current.method = method;
	form.current.submit();
}

function getQueryString(ids) {
	return `?ids=${ids.join(',')}`;
}

function getRichPayload(payload, ids) {
	const richPayload = {
		...payload,
		options: {
			...payload.options,
			url: payload.options.baseUrl + getQueryString(ids)
		}
	};
	return richPayload;
}

function BulkActions(props) {
	const [currentSidePanelActionPayload, setCurrentSidePanelActionPayload] = useState(null);
	
	function handleActionClick(actionDefinition, formRef, loadData, sidePanelId) {
		if(actionDefinition.sidePanelCompatible) {
			const sidePanelActionPayload = {
				id: sidePanelId,
				options: {
					baseUrl: actionDefinition.url,
					onAfterSubmit: () => loadData(),
					slug: actionDefinition.slug || null,
				}
			}
			
			Liferay.fire(
				OPEN_SIDE_PANEL,
				getRichPayload(sidePanelActionPayload, props.selectedItemsId)
			);

			setCurrentSidePanelActionPayload(sidePanelActionPayload)
		} else {
			submit(
				actionDefinition.url,
				actionDefinition.method || 'get',
				formRef
			)
		}
	}

	useEffect(
		() => {
			if(!currentSidePanelActionPayload) {
				return;
			}

			const currentOpenedSidePanel = getOpenedSidePanel();

			if(
				currentOpenedSidePanel &&
				(currentOpenedSidePanel.id === currentSidePanelActionPayload.id) &&
				(currentOpenedSidePanel.url.indexOf(currentSidePanelActionPayload.options.baseUrl) > -1)
			) {
				Liferay.fire(
					OPEN_SIDE_PANEL,
					getRichPayload(currentSidePanelActionPayload, props.selectedItemsId)
				);
			}

		},
		// eslint-disable-next-line react-hooks/exhaustive-deps
		[
			props.selectedItemsId
		]
	)

	return (
		<TableContext.Consumer>
			{({formRef, loadData, sidePanelId}) => (
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
									onClick={() => handleActionClick(actionDefinition, formRef, loadData, sidePanelId)}
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
			icon: PropTypes.string.isRequired,
			label: PropTypes.string.isRequired,
			method: PropTypes.string,
			sidePanelCompatible: PropTypes.bool,
			url: PropTypes.string.isRequired,
		})
	),
	selectedItemsId: PropTypes.array.isRequired,
	totalItemsCount: PropTypes.number.isRequired
};

export default BulkActions;
