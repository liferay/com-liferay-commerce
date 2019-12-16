import ClayTable from '@clayui/table';
import PropTypes from 'prop-types';
import React, { useState } from 'react';

import DatasetDisplayContext from '../DatasetDisplayContext.es';
import EmptyResultMessage from './EmptyResultMessage.es';
import TableContext from './TableContext.es';
import Checkbox from './cellTemplate/Checkbox.es';
import Comment from './cellTemplate/Comment.es';
import { getCustomCellTemplate } from '../utils/tableCellRenderer.es';
import ClayIcon from '@clayui/icon';

function TableCell(props) {
	const { template, ...otherProps } = props;
	const Template = getCustomCellTemplate(template);

	return (
		<ClayTable.Cell>
			<Template {...otherProps} />
			{props.comment && <Comment>{props.comment}</Comment>}
		</ClayTable.Cell>
	);
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

	const [sidePanelProps, setSidePanelProps] = useState({});

	return props.items.length ? (
		<DatasetDisplayContext.Consumer>
			{({ formRef }) => (
				<TableContext.Provider
					value={{
						setSidePanelProps,
						sidePanelProps
					}}
				>
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
													name={'table-head-selector'}
													onSelect={props.onSelect}
													value={'all-items'}
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
											{field.sortable ? (
												<a className="inline-item text-truncate-inline" href="#">
													{field.label}
													<span className="inline-item inline-item-after">
														<ClayIcon
															draggable
															symbol="order-arrow-up"
														/>
													</span>
												</a>
											) : field.label}
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
											const comment = otherProps.comments
												? otherProps.comments[
														field.fieldName
												  ]
												: null;
											return (
												<TableCell
													comment={comment}
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
										{showActionItems ? (
											item.actionItems ? (
												<TableCell
													template="dropdown"
													value={item.actionItems}
												/>
											) : (
												<ClayTable.Cell />
											)
										) : null}
									</ClayTable.Row>
								))}
							</ClayTable.Body>
						</ClayTable>
					</form>
				</TableContext.Provider>
			)}
		</DatasetDisplayContext.Consumer>
	) : (
		<EmptyResultMessage />
	);
}

Table.propTypes = {
	items: PropTypes.arrayOf(
		PropTypes.shape({
			id: PropTypes.oneOfType([PropTypes.string, PropTypes.number])
				.isRequired
		})
	),
	schema: PropTypes.shape({
		fields: PropTypes.array.isRequired
	}).isRequired
};

Table.defaultProps = {
	items: []
};

export default Table;
