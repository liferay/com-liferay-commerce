import {ClayIconSpriteContext} from '@clayui/icon';
import classNames from 'classnames';
import PropTypes from 'prop-types';
import React, {useState, useRef} from 'react';

import DatasetDisplayContext from './DatasetDisplayContext.es';
import ManagementBar from './management_bar/index.es';
import Pagination from './pagination/index.es';
import Table from './table/Table.es';

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

function DatasetDisplay(props) {
	const [selectedItemsId, setselectedItemsId] = useState([]);

	const formRef = useRef(null);

	const selectItems = (checked, val = null) => {
		if (!val) {
			if (checked) {
				setselectedItemsId(props.items.map(el => el.id));
			} else {
				setselectedItemsId([]);
			}
		} else {
			if (checked) {
				setselectedItemsId(selectedItemsId.concat(val));
			} else {
				setselectedItemsId(selectedItemsId.filter(el => el !== val));
			}
		}
	};

	function loadData() {
		return;
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
	);

	const table = (
		<Table
			items={props.items}
			onSelect={selectItems}
			schema={props.schema}
			selectable={props.selectable}
			selectedItemsId={selectedItemsId}
		/>
	);

	return (
		<DatasetDisplayContext.Provider
			value={{
				formRef,
				loadData,
				sidePanelId: props.sidePanelId
			}}
		>
			<ClayIconSpriteContext.Provider value={props.spritemap}>
				<div
					className={classNames(
						'smart-table-wrapper',
						props.wrapperCssClasses
					)}
				>
					<div
						className={classNames(
							props.managementBarWrapperCssClasses
						)}
					>
						{managementBar}
					</div>
					<div className={classNames(props.tableWrapperCssClasses)}>
						{table}
					</div>
				</div>
				<div className={classNames(props.paginationWrapperCssClasses)}>
					{props.showPagination && (
						<Pagination
							currentPage={props.currentPage}
							onDeltaChange={() => {}}
							onEntryChange={() => {}}
							onPageChange={() => {}}
							pageSize={props.pageSize}
							paginationEntries={props.paginationEntries}
							paginationSelectedEntry={
								props.paginationSelectedEntry
							}
							totalItems={props.totalItems}
						/>
					)}
				</div>
			</ClayIconSpriteContext.Provider>
		</DatasetDisplayContext.Provider>
	);
}

DatasetDisplay.propTypes = {
	bulkActions: PropTypes.array,
	currentPage: PropTypes.number.isRequired,
	// dataProviderKey: PropTypes.string.isRequired,
	// dataSetAPI: PropTypes.string.isRequired,
	// disableAJAX: PropTypes.bool,
	filters: PropTypes.array,
	id: PropTypes.string.isRequired,
	items: PropTypes.array.isRequired,
	managementBarWrapperCssClasses: PropTypes.string,
	pageSize: PropTypes.number.isRequired,
	paginationEntries: PropTypes.array.isRequired,
	paginationSelectedEntry: PropTypes.number.isRequired,
	schema: PropTypes.object.isRequired,
	selectable: PropTypes.bool,
	showPagination: PropTypes.bool,
	sidePanelId: PropTypes.string,
	spritemap: PropTypes.string.isRequired,
	tableTitle: PropTypes.string,
	tableWrapperCssClasses: PropTypes.string,
	totalItems: PropTypes.number,
	wrapTableIntoCard: PropTypes.bool,
	wrapperCssClasses: PropTypes.string
};

DatasetDisplay.defaultProps = {
	currentPage: 1,
	showPagination: true
};

export default DatasetDisplay;
