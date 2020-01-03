import React, { useState, useContext } from 'react';
import ClayDropDown from '@clayui/drop-down';
import { ClayButtonWithIcon } from '@clayui/button';
import PropTypes from 'prop-types';

import DatasetDisplayContext from '../../DatasetDisplayContext.es'

import { OPEN_MODAL } from '../../../../utilities/eventsDefinitions.es';
import { datasetDisplaySupportModalId } from '../../DatasetDisplay.es';

function CreationMenu(props) {
	const [active, setActive] = useState(false);
	const datasetContext = useContext(DatasetDisplayContext);

	function executeAction(i) {
		const clickedItem = props.items[i];

		switch (clickedItem.type) {
			case 'modal':
				Liferay.fire(OPEN_MODAL, {
					id: datasetDisplaySupportModalId,
					onClose: datasetContext.loadData,
					url: clickedItem.url,
				})
				break;
			case 'inline':
				break;
			default:
				window.location.href = clickedItem.href;
				break;
		}
	}

	return (
		<ul className="navbar-nav">
			<li className="nav-item">
				{props.items.length ? (
					<ClayDropDown
						active={active}
						onActiveChange={setActive}
						trigger={<ClayButtonWithIcon symbol="plus" />}
					>
						<ClayDropDown.ItemList>
							{props.items.map((item, i) => (
								<ClayDropDown.Item href={item.href || '#'} key={i} onClick={(e) => {
									e.preventDefault();
									setActive(false);
									executeAction(i);
								}}>
									{item.label}
								</ClayDropDown.Item>
							))}
						</ClayDropDown.ItemList>
					</ClayDropDown>
				) : (
					<ClayButtonWithIcon onClick={() => executeAction(0)} symbol="plus" />
				)}
			</li>
		</ul>
	);
};

CreationMenu.propTypes = {
	items: PropTypes.arrayOf(PropTypes.oneOfType([
		PropTypes.shape({
			href: PropTypes.string.isRequired,
			label: PropTypes.string.isRequired,
		}),
		PropTypes.shape({
			label: PropTypes.string.isRequired,
			type: PropTypes.oneOf(['modal']).isRequired,
			url: PropTypes.string.isRequired,
		}),
		PropTypes.shape({
			apiUrl: PropTypes.string.isRequired,
			editableFields: PropTypes.arrayOf(PropTypes.string).isRequired,
			label: PropTypes.string.isRequired,
			type: PropTypes.oneOf(['inline']).isRequired,
		})
	])).isRequired
}

export default CreationMenu;
