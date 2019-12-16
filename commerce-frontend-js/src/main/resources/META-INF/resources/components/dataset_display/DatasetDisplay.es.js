import {ClayIconSpriteContext} from '@clayui/icon';
import {ClayPaginationBarWithBasicItems} from '@clayui/pagination-bar';
import classNames from 'classnames';
import PropTypes from 'prop-types';
import React, {useState, useRef, useEffect} from 'react';

import DatasetDisplayContext from './DatasetDisplayContext.es';
import ManagementBar from './management_bar/index.es';
import Table from './table/Table.es';

import {createOdataFilterStrings} from '../../utilities/odata.es';
import {showNotification} from '../../utilities/index.es';
import {formatFilters} from '../../utilities/filters.es';

function loadData(apiUrl, filters, delta, page = 1) {
	const filterString = createOdataFilterStrings(filters);
	const authString = `p_auth=${window.Liferay.authToken}`;
	const pagination = `pageSize=${delta}&page=${page}`
	const url = `${apiUrl}&${authString}&${pagination}&${filterString}`

	return fetch(url)
		.then(response => response.json())
}

function DatasetDisplay(props) {
	const [selectedItemsId, setselectedItemsId] = useState([]);
	const [filters, updateFilters] = useState(formatFilters(props.filters));
	const [items, updateItems] = useState(props.items)
	const [pageNumber, setPageNumber] = useState(props.pagination.initialPageNumber || 1);
	const [delta, setDelta] = useState(props.pagination.initialDelta || props.pagination.deltas[0].label);
	const [totalItems, setTotalItems] = useState(props.pagination.initialTotalItems);
	const formRef = useRef(null);

	function updateDataset(dataSetData) {
		if(dataSetData instanceof Array) {
			return updateItems(dataSetData)
		}
		setTotalItems(dataSetData)
		updateItems(dataSetData.items)
	}
	
	function getData(apiUrl, filters, delta, pageNumber, showSuccessNotification = false) {
		return loadData(apiUrl, filters, delta, pageNumber)
			.then(updateDataset)
			.then(() => {
				if(showSuccessNotification) {
					showNotification(
						Liferay.Language.get('table-data-updated'),
						'success'
					)
				}
			})
			.catch((e) => {
				console.error(e)
				showNotification(
					Liferay.Language.get('unexpected-error'),
					'danger'
				)
			})
	};

	useEffect(() => {
		getData(props.apiUrl, filters.filter(e => !!e.value), delta, pageNumber)
	// eslint-disable-next-line react-hooks/exhaustive-deps
	}, [props.apiUrl, filters, delta, pageNumber])

	const selectItems = (val, checked) => {
		if (val === 'all-items') {
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

	return (
		<DatasetDisplayContext.Provider
			value={{
				formRef,
				loadData: () => getData(props.apiUrl, filters.filter(e => !!e.value), delta, pageNumber, true),
				sidePanelId: props.sidePanelId,
			}}
		>
			<ClayIconSpriteContext.Provider value={props.spritemap}>
				<div className={classNames('smart-table-wrapper', props.wrapperCssClasses)}>
					{ props.showManagementBar ? (
						<div className={classNames(props.managementBarWrapperCssClasses)}>
							<ManagementBar
								actionButton={{
									icon: 'plus',
									label: 'Add',
									onClick: () => {}
								}}
								bulkActions={props.bulkActions}
								filters={filters}
								onFiltersChange={updateFilters}
								selectAllItems={() => selectItems('all-items', true)}
								selectedItemsId={selectedItemsId}
								totalItemsCount={props.items.length}
							/>
						</div>
					) : null}

					<div className={classNames(props.tableWrapperCssClasses)}>
						<Table
							items={items}
							onSelect={selectItems}
							schema={props.schema}
							selectable={props.bulkActions && !!props.bulkActions.length}
							selectedItemsId={selectedItemsId}
						/>
					</div>
				</div>
				{props.showPagination && props.pagination && items && items.length ? (
					<div className={classNames(props.paginationWrapperCssClasses)}>
						<ClayPaginationBarWithBasicItems
							activeDelta={delta}
							activePage={pageNumber}
							deltas={props.deltas}
							ellipsisBuffer={3}
							onDeltaChange={(deltaVal) => {
								setPageNumber(1);
								setDelta(deltaVal);
							}}
							onPageChange={setPageNumber}
							totalItems={totalItems}
						/>
					</div>
				) : null}
			</ClayIconSpriteContext.Provider>
		</DatasetDisplayContext.Provider>
	);
}

DatasetDisplay.propTypes = {
	apiUrl: PropTypes.string.isRequired,
	bulkActions: PropTypes.array,
	filters: PropTypes.array,
	id: PropTypes.string.isRequired,
	items: PropTypes.array.isRequired,
	managementBarWrapperCssClasses: PropTypes.string,
	pagination: PropTypes.shape({
		deltas: PropTypes.arrayOf(
			PropTypes.shape({
				href: PropTypes.string,
				label: PropTypes.number.isRequired,
			}).isRequired,
		),
		initialDelta: PropTypes.number.isRequired,
		initialPageNumber: PropTypes.number,
		initialTotalItems: PropTypes.number.isRequired,
	}),
	schema: PropTypes.object.isRequired,
	showManagementBar: PropTypes.bool,
	showPagination: PropTypes.bool,
	sidePanelId: PropTypes.string,
	spritemap: PropTypes.string.isRequired,
	tableTitle: PropTypes.string,
	tableWrapperCssClasses: PropTypes.string,
	wrapTableIntoCard: PropTypes.bool,
	wrapperCssClasses: PropTypes.string
};

DatasetDisplay.defaultProps = {
	showManagementBar: true,
	showPagination: true,
};

export default DatasetDisplay;
