import ClayTable from '@clayui/table';
import PropTypes from 'prop-types';
import React, { useContext } from 'react';
import classNames from 'classnames'

import Checkbox from './cellTemplate/Checkbox.es';
import ClayIcon from '@clayui/icon';
import DatasetDisplayContext from '../DatasetDisplayContext.es'

function TableHeadCell(props) {
	const context = useContext(DatasetDisplayContext);

	const sortingMatch = context.sorting.find((el) => el.fieldName === props.fieldName)

	function _handleSortingCellClick() {
		if(sortingMatch) {
			const updatedSortedElements = context.sorting.map((el) => el.fieldName === props.fieldName ? ({
				...el,
				direction: el.direction === 'ASC' ? 'DESC' : 'ASC'
			}) : el)
			context.updateSorting(updatedSortedElements)
		} else {
			context.updateSorting(context.sorting.concat({
				direction: 'ASC',
				fieldName: props.fieldName
			}))
		}
	}

	return (
		<ClayTable.Cell
			className="table-cell-expand-smaller"
			headingCell
			headingTitle
		>
			{props.sortable ? (
				<a 
					className="inline-item text-truncate-inline" 
					href="#"
					onClick={_handleSortingCellClick}
				>
					{props.label}
					<span className="inline-item inline-item-after sorting-icons-wrapper">
						<ClayIcon
							className={classNames("sorting-icon", (sortingMatch && (sortingMatch.direction === 'ASC')) && 'active')}
							draggable
							symbol={'order-arrow-up'}
							/>
						<ClayIcon
							className={classNames("sorting-icon", (sortingMatch && (sortingMatch.direction === 'DESC')) && 'active')}
							draggable
							symbol={'order-arrow-down'}
						/>
					</span>
				</a>
			) : props.label}
		</ClayTable.Cell>
	)
}

function TableHeadRow(props) {
	return (
		<ClayTable.Head>
			<ClayTable.Row>
				{props.selectable && (
					<ClayTable.Cell headingCell>
						{props.itemsQuantity ? (
							<Checkbox
								checked={
									props.allElementsSelected
								}
								indeterminate={
									props.selectedItemsId
										.length &&
									!props.allElementsSelected
								}
								name={'table-head-selector'}
								onSelect={props.onSelect}
								value={'all-items'}
							/>
						) : null}
					</ClayTable.Cell>
				)}
				{props.schema.fields.map(field => {
					return (
						<TableHeadCell
							fieldName={field.fieldName}
							key={field.fieldName}
							label={field.label}
							sortable={field.sortable}
						/>
					)
				})}
				{props.showActionItems && (
					<ClayTable.Cell headingCell />
				)}
			</ClayTable.Row>
		</ClayTable.Head>
	)
}

TableHeadRow.propTypes = {
	allElementsSelected: PropTypes.bool,
	itemsQuantity: PropTypes.number,
	schema: PropTypes.shape({
		fields: PropTypes.arrayOf(
			PropTypes.shape({
				contentRenderer: PropTypes.string,
				fieldName: PropTypes.string.isRequired,
				label: PropTypes.string.isRequired,
				sortable: PropTypes.bool
			}).isRequired
		)
	}),
	selectedItemsId: PropTypes.arrayOf(
		PropTypes.oneOfType(
			[
				PropTypes.string,
				PropTypes.number
			]
		)
	),
	showActionItems: PropTypes.bool,
	sorting: PropTypes.arrayOf(
		PropTypes.shape({
			direction: PropTypes.oneOf(['ASC', 'DESC']).isRequired,
			fieldName: PropTypes.string.isRequired
		})
	),
};

TableHeadRow.defaultProps = {
	allElementsSelected: false
}

export default TableHeadRow;
