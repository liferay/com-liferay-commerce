import ClayTable from '@clayui/table';
import PropTypes from 'prop-types';
import React, { useState } from 'react';

import DatasetDisplayContext from '../../DatasetDisplayContext.es';
import TableContext from './TableContext.es';
import Comment from './cellTemplate/Comment.es';
import { getCustomCellTemplate } from '../../utilities/tableCells.es';
import TableHeadRow from './TableHeadRow.es';
import ActionsDropdown from '../common/ActionsDropdown.es';

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

	return (
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
							<TableHeadRow
								allElementsSelected={allElementsSelected}
								itemsQuantity={props.items.length}
								onSelect={props.onSelect}
								schema={props.schema}
								selectable={props.selectable}
								selectedItemsId={props.selectedItemsId}
								showActionItems={showActionItems}
								sorting={props.sorting}
							/>
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
												<ClayTable.Cell>
													<ActionsDropdown
														items={item.actionItems}
													/>
												</ClayTable.Cell >
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
	)
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
	}).isRequired,
	selectedItemsId: PropTypes.array
};

Table.defaultProps = {
	items: []
};

export default Table;
