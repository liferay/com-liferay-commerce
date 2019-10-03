import ClayTable from '@clayui/table';
import React, {useRef} from 'react';

import EmptyResultMessage from './EmptyResultMessage.es';
import Checkbox from './cells/Checkbox.es';
import Default from './cells/Default.es';
import Dropdown from './cells/Dropdown.es';
import ImageText from './cells/ImageText.es';
import Link from './cells/Link.es';
import Price from './cells/Price.es';
import TableContext from './TableContext.es';

function TableCell(props) {
	const {template, ...otherProps} = props
	const Template = getCustomTemplate(template);
	return (
		<ClayTable.Cell>
			<Template {...otherProps}/>
		</ClayTable.Cell>
	)
}

const idToCellTemplateMapping = {
	checkbox: Checkbox,
	commerceTableCellImageName: ImageText,
	commerceTablePrice: Price,
	default: Default,
	dropdown: Dropdown,
	imageTitle: ImageText,
	link: Link,
	price: Price,
}

function getCustomTemplate(id, customTemplates = {}) {
	const templates = { 
		...idToCellTemplateMapping,
		...customTemplates
	}

	return templates[id] || templates.default;
}

function areAllElementsSelected(selectedItemsId, allItems) {
	const selectedItemsString = selectedItemsId.sort().join(',');
	const allItemsString = allItems.map(el => el.id).sort().join(',');

	return selectedItemsString === allItemsString
}

function Table(props) {
	const formRef = useRef(null);
	const showActionItems = !!(props.items.find(el => el.actionItems));
	const allElementsSelected = areAllElementsSelected(props.selectedItemsId, props.items);

	return (
		<TableContext.Provider formRef={formRef}>
			<form ref={formRef}>
				<ClayTable borderless>
					<ClayTable.Head >
						<ClayTable.Row>
							{props.selectable && (
								<ClayTable.Cell 
									headingCell 
								>
									{props.items.length ? (
										<Checkbox 
											checked={allElementsSelected}
											indeterminate={props.selectedItemsId.length && !allElementsSelected}
											onSelect={props.onSelect}
											value={null}
										/>
									) : null}
								</ClayTable.Cell>
							)}
							{props.schema.fields.map((field) => (
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
								<ClayTable.Cell 
									headingCell 
								/>
							)}
						</ClayTable.Row>
					</ClayTable.Head>
					<ClayTable.Body>
						{
							props.items.map((item) => (
								<ClayTable.Row key={item.id}>
									{props.selectable && (
										<TableCell 
											checked={!!props.selectedItemsId.find(el => el === item.id)}
											onSelect={props.onSelect}
											template="checkbox"
											value={item.id}
										/>
									)}
									{
										props.schema.fields.map((field) => {
											const fieldName = field.fieldName;
											const { [fieldName]: value, ...otherProps } = item;
											return (
												<TableCell 
													data={otherProps}
													key={field.fieldName}
													template={field.contentRenderer}
													value={value} 
												/>
											)
										})
									}
									{showActionItems && (
										<TableCell 
											template="dropdown"
											value={item.actionItems}
										/>
									)}
								</ClayTable.Row>
							))
						}
					</ClayTable.Body>
				</ClayTable>
				{ !props.items.length && <EmptyResultMessage />}
			</form>
		</TableContext.Provider>
	)
}

export default Table;