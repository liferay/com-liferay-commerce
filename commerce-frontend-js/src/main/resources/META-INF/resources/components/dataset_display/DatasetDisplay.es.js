import {ClayIconSpriteContext} from '@clayui/icon';
import classNames from 'classnames';
import PropTypes from 'prop-types';
import React, {useState, useRef, useEffect} from 'react';

import DatasetDisplayContext from './DatasetDisplayContext.es';
import ManagementBar from './management_bar/index.es';
import Pagination from './pagination/index.es';
import Table from './table/Table.es';

import {createOdataFilterStrings} from '../../utilities/odata.es';
import {showNotification} from '../../utilities/index.es'

function loadData(apiUrl, filters) {
	const filterString = createOdataFilterStrings(filters);
	const authString = `p_auth=${window.Liferay.authToken}`;
	const url = `${apiUrl}&${authString}&${filterString}`

	return fetch(url)
		.then(response => response.json())
}

function DatasetDisplay(props) {
	const [selectedItemsId, setselectedItemsId] = useState([]);
	const [filters, updateFilters] = useState(props.filters);
	const [items, updateItems] = useState(props.items)
	const formRef = useRef(null);

	useEffect(() => {
		loadData(props.apiUrl, filters.filter(e => !!e.value))
			.then((dataSetData) => {
				if(dataSetData instanceof Array) {
					return updateItems(dataSetData)
				}

				updateItems(dataSetData.items)
				//TODO: updatingPagination and filters
			})
			.catch((e) => {
				console.error(e)
				showNotification(
					Liferay.Language.get('unexpected-error'),
					'danger'
				)
			})

	}, [filters, props.apiUrl])

	const selectItems = (checked, val) => {
		if (val === 'table-head-selector') {
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

	const managementBar = (
		<ManagementBar
			actionButton={{
				icon: 'plus',
				label: 'Add',
				onClick: () => {}
			}}
			bulkActions={props.bulkActions}
			filters={filters}
			onFiltersChange={updateFilters}
			selectAllItems={() => selectItems(true)}
			selectedItemsId={selectedItemsId}
			totalItemsCount={props.items.length}
		/>
	);

	const table = (
		<Table
			items={items}
			onSelect={selectItems}
			schema={props.schema}
			selectable={props.bulkActions && !!props.bulkActions.length}
			selectedItemsId={selectedItemsId}
		/>
	);

	return (
		<DatasetDisplayContext.Provider
			value={{
				formRef,
				loadData,
				sidePanelId: props.sidePanelId,
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
	apiUrl: PropTypes.string.isRequired,
	bulkActions: PropTypes.array,
	currentPage: PropTypes.number.isRequired,
	filters: PropTypes.array,
	id: PropTypes.string.isRequired,
	items: PropTypes.array.isRequired,
	managementBarWrapperCssClasses: PropTypes.string,
	pageSize: PropTypes.number.isRequired,
	paginationEntries: PropTypes.array.isRequired,
	paginationSelectedEntry: PropTypes.number.isRequired,
	schema: PropTypes.object.isRequired,
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
