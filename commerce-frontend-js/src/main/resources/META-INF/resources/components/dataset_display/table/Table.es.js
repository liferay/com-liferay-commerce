import ClayTable from '@clayui/table';
import PropTypes from 'prop-types';
import React, {useState} from 'react';

import Modal from '../../modal/Modal.es';
import DatasetDisplayContext from '../DatasetDisplayContext.es';
import EmptyResultMessage from './EmptyResultMessage.es';
import TableContext from './TableContext.es';
import Checkbox from './cells/Checkbox.es';
import Default from './cells/Default.es';
import Dropdown from './cells/Dropdown.es';
import ImageText from './cells/ImageText.es';
import Link from './cells/Link.es';
import ModalLink from './cells/ModalLink.es';
import Price from './cells/Price.es';
import SidePanelLink from './cells/SidePanelLink.es';

function TableCell(props) {
	const {template, ...otherProps} = props;
	const Template = getCustomTemplate(template);
	return (
		<ClayTable.Cell>
			<Template {...otherProps} />
		</ClayTable.Cell>
	);
}

const idToCellTemplateMapping = {
	checkbox: Checkbox,
	commerceTableCellImageName: ImageText,
	commerceTablePrice: Price,
	default: Default,
	dropdown: Dropdown,
	imageTitle: ImageText,
	link: Link,
	modalLink: ModalLink,
	price: Price,
	sidePanelLink: SidePanelLink
};

function getCustomTemplate(id, customTemplates = {}) {
	const templates = {
		...idToCellTemplateMapping,
		...customTemplates
	};

	return templates[id] || templates.default;
}

function areAllElementsSelected(selectedItemsId, allItems) {
	const selectedItemsString = selectedItemsId.sort().join(',');
	const allItemsString = allItems
		.map(el => el.id)
		.sort()
		.join(',');

	return selectedItemsString === allItemsString;
}

function Table(props) {
	const showActionItems = !!props.items.find(el => el.actionItems);
	const allElementsSelected = areAllElementsSelected(
		props.selectedItemsId,
		props.items
	);
	const containsModal = props.schema.fields.find(
		field => field.contentRenderer === 'modalLink'
	);

	const [modalProps, setModalProps] = useState({});
	const [sidePanelProps, setSidePanelProps] = useState({});

	return (
		<DatasetDisplayContext.Consumer>
			{({formRef, loadData}) => (
				<TableContext.Provider
					value={{
						modalProps,
						setModalProps,
						setSidePanelProps,
						sidePanelProps
					}}
				>
					{containsModal && (
						<Modal onSubmit={loadData} {...modalProps} />
					)}
					<form ref={formRef}>
						<ClayTable borderless>
							<ClayTable.Head>
								<ClayTable.Row>
									{props.selectable && (
										<ClayTable.Cell headingCell>
											{props.items.length ? (
												<Checkbox
													checked={
														allElementsSelected
													}
													indeterminate={
														props.selectedItemsId
															.length &&
														!allElementsSelected
													}
													onSelect={props.onSelect}
													value={null}
												/>
											) : null}
										</ClayTable.Cell>
									)}
									{props.schema.fields.map(field => (
										<ClayTable.Cell
											className="table-cell-expand-smaller"
											headingCell
											headingTitle
											key={field.fieldName}
										>
											{field.label}
										</ClayTable.Cell>
									))}
									{showActionItems && (
										<ClayTable.Cell headingCell />
									)}
								</ClayTable.Row>
							</ClayTable.Head>
							<ClayTable.Body>
								{props.items.map(item => (
									<ClayTable.Row key={item.id}>
										{props.selectable && (
											<TableCell
												checked={
													!!props.selectedItemsId.find(
														el => el === item.id
													)
												}
												name="selectedIds"
												onSelect={props.onSelect}
												template="checkbox"
												value={item.id}
											/>
										)}
										{props.schema.fields.map(field => {
											const fieldName = field.fieldName;
											const {
												[fieldName]: value,
												...otherProps
											} = item;
											return (
												<TableCell
													data={otherProps}
													fieldName={field.fieldName}
													key={field.fieldName}
													template={
														field.contentRenderer
													}
													value={value}
												/>
											);
										})}
										{showActionItems && (
											<TableCell
												template="dropdown"
												value={item.actionItems}
											/>
										)}
									</ClayTable.Row>
								))}
							</ClayTable.Body>
						</ClayTable>
						{!props.items.length && <EmptyResultMessage />}
					</form>
				</TableContext.Provider>
			)}
		</DatasetDisplayContext.Consumer>
	);
}

Table.propTypes = {
	items: PropTypes.array.isRequired,
	schema: PropTypes.shape({
		fields: PropTypes.array.isRequired
	}).isRequired
};

export default Table;
