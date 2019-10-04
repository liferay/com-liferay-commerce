import {ClayIconSpriteContext} from '@clayui/icon';
import classNames from 'classnames';
import PropTypes from 'prop-types';
import React, { useState, useRef } from 'react';

import Summary from '../summary/Summary.es';
import ManagementBar from './management_bar/index.es';
import Pagination from './pagination/index.es';
import Table from './table/Table.es';
import SmartTableContext from './SmartTableContext.es';

function WrappingCard(props) {
	return (
		<div className={classNames('card', props.className)}>
			{props.title && (
				<div className="card-header">{props.title}</div>
			)}
			<div className={classNames('card-body', props.bodyCssClass)}>
				{props.children}
			</div>
		</div>
	);
}

// function getApiEndpoint(dataSetAPI, pageSize, currentPage = 1, filters = []) {
// 	const formattedFilters = Object.keys(filters)
// 		.map(filter => {
// 			return `${encodeURIComponent(filter)}=${encodeURIComponent(
// 				filters[el]
// 			)}`;
// 		})
// 		.join('&');

// 	const endpoint = `${dataSetAPI}&pageSize=${pageSize}&page=${currentPage}&p_auth=${Liferay.authToken}&${formattedFilters}`;

// 	return endpoint;
// }

function SmartTable(props) {
	const [selectedItemsId, setselectedItemsId] = useState([]);

	const formRef = useRef(null)

	const selectItems = (checked, val = null) => {
		if(!val) {
			if(checked) {
				setselectedItemsId(props.items.map(el => el.id))
			} else {
				setselectedItemsId([])
			}
		} else {
			if(checked) {
				setselectedItemsId(selectedItemsId.concat(val))
			} else {
				setselectedItemsId(selectedItemsId.filter(el => el !== val))
			}
		}
	}

	const managementBar = (
		<ManagementBar 
			actionButton={{
				icon: 'plus',
				label: 'Add',
				onClick: () => {}
			}}
			bulkActions={props.bulkActions}
			filters={props.filters}
			onFilterChange={() => {}}
			selectAllItems={() => selectItems(true)}
			selectedItemsId={selectedItemsId}
			totalItemsCount={props.items.length}
		/>
	)
	const table = (
		<Table
			items={props.items}
			onSelect={selectItems}
			schema={props.schema}
			selectable={props.selectable}
			selectedItemsId={selectedItemsId}
		/>
	)

	return (
		<SmartTableContext.Provider value={{formRef}}>
			<ClayIconSpriteContext.Provider value={props.spritemap}>
				<div
					className={classNames(
						'commerce-table-wrapper',
						props.wrapperCssClasses
					)}
				>
					{props.tableName ? (
						<WrappingCard 
							bodyCssClass="p-0"
							title={props.tableName}
						>
							{managementBar}
							{table}
						</WrappingCard>
					) : (
						<>
							<WrappingCard bodyCssClass="p-0">
								{managementBar}
							</WrappingCard>
							<WrappingCard bodyCssClass="p-0" className="mt-4">
								{table}
							</WrappingCard>
						</>
					)}

					{props.showPagination && (
						<Pagination 
							currentPage={props.currentPage}
							pageSize={props.pageSize}
							paginationEntries={props.paginationEntries}
							paginationSelectedEntry={props.paginationSelectedEntry}
							totalItems={props.totalItems}
						/>
					)}

					{props.summaryName ? (
						<WrappingCard 
							className="mt-4" 
							title={props.summaryName}
						>
							<Summary items={props.summaryItems} />
						</WrappingCard>
					) : (
						<Summary items={props.summaryItems} />
					)}
				</div>
			</ClayIconSpriteContext.Provider>
		</SmartTableContext.Provider>
	);
}

SmartTable.propTypes = {
	bulkActions: PropTypes.array,
	currentPage: PropTypes.number.isRequired,
	// dataProviderKey: PropTypes.string.isRequired,
	// dataSetAPI: PropTypes.string.isRequired,
	// disableAJAX: PropTypes.bool,
	filters: PropTypes.array,
	id: PropTypes.string.isRequired,
	items: PropTypes.array.isRequired,
	pageSize: PropTypes.number.isRequired,
	paginationEntries: PropTypes.array.isRequired,
	paginationSelectedEntry: PropTypes.number.isRequired,
	schema: PropTypes.object.isRequired,
	selectable: PropTypes.bool,
	showPagination: PropTypes.bool,
	spritemap: PropTypes.string.isRequired,
	summaryItems: PropTypes.array,
	summaryName: PropTypes.string,
	tableName: PropTypes.string,
	totalItems: PropTypes.number,
	wrapperCssClasses: PropTypes.string,
};

SmartTable.defaultProps = {
	currentPage: 1,
};

export default SmartTable;
