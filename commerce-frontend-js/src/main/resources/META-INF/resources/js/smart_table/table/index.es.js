import ClayTable from '@clayui/table';
import React from 'react';

import Default from './cells/Default.es';
import Dropdown from './cells/Dropdown.es';
import ImageText from './cells/ImageText.es';
import Link from './cells/Link.es';
import Price from './cells/Price.es';

function TableCell(props) {
	return (
		<ClayTable.Cell>
			<props.template {...props}/>
		</ClayTable.Cell>
	)
}

const idToCellMapping = {
	commerceTableCellImageName: ImageText,
	commerceTablePrice: Price,
	default: Default,
	link: Link,
}

function getCustomTemplate(id, customTemplates = {}) {
	const templates = { 
		...idToCellMapping,
		...customTemplates
	}

	return templates[id] || templates.default;
}

function Table(props) {
	const showActionItems = !!(props.items.find(el => el.actionItems));

	return (
		<ClayTable borderless className={props.className}>
			<ClayTable.Head >
				<ClayTable.Row>
					{props.schema.fields.map((field, i) => (
						<ClayTable.Cell 
							className={i === 0 ? 'table-cell-expand table-cell-minw-300' : 'table-cell-expand-smaller'}
							headingCell 
							headingTitle key={field.fieldName}
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
				{props.items.map((item, itemIndex) => (
					<ClayTable.Row key={itemIndex}>
						{
							props.schema.fields.map((field) => {
								const fieldName = field.fieldName;
								const { [fieldName]: value, ...otherProps } = item;
								return (
									<TableCell 
										data={otherProps}
										key={field.fieldName}
										template={getCustomTemplate(field.contentRenderer, props.customTemplates)}
										value={value} 
									/>
								)
							})
						}
						{showActionItems && (
							<TableCell 
								template={Dropdown}
								value={item.actionItems}
							/>
						)}
					</ClayTable.Row>
				))}
			</ClayTable.Body>
		</ClayTable>
	)
}

export default Table;