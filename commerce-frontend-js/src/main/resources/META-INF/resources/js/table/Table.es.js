import {ClayIconSpriteContext} from '@clayui/icon';
import ClayTable from '@clayui/table';
import classNames from 'classnames';
import PropTypes from 'prop-types';
import React, {useState} from 'react';

import Summary from '../summary/Summary.es';
import ManagementTableBar from '../table_toolbar/TableToolbar.es';
import {showNotification} from '../utilities/index.es';
import Pagination from './pagination/index.es';

function WrappingCard(props) {
	return (
		<div className={classNames('card', props.cardCssClass)}>
			<div className="card-header">{props.title}</div>
			<div className={classNames('card-body', props.bodyCssClass)}>
				{props.children}
			</div>
		</div>
	);
}

function BaseTable(props) {
	return <ClayTable>Table</ClayTable>;
}

// BaseTable.propTypes = {
// 	schema: PropTypes.shape({
// 		fieldName: PropTypes.string.isRequired,
// 		label: PropTypes.string.isRequired,
// 		sortable: PropTypes.bool
// 	}).isRequired,
// 	cellsVariants: PropTypes.arrayOf({
// 		fieldName: PropTypes.string,
// 		variant: PropTypes.oneOf([PropTypes.func, PropTypes.shape])
// 	}),
// 	items: PropTypes.array
// };

function getApiEndpoint(dataSetAPI, pageSize, currentPage = 1, filters = []) {
	const formattedFilters = Object.keys(filters)
		.map(filter => {
			return `${encodeURIComponent(filter)}=${encodeURIComponent(
				filters[el]
			)}`;
		})
		.join('&');

	const endpoint = `${dataSetAPI}&pageSize=${pageSize}&page=${currentPage}&p_auth=${Liferay.authToken}&${formattedFilters}`;

	return endpoint;
}

function Table(props) {
	const [items, updateItems] = useState(props.items);
	const [summary, updateSummary] = useState(props.summary);

	function loadData() {
		fetch(
			getApiEndpoint(props.dataSetAPI, pageSize, currentPage, filters),
			{
				method: 'GET'
			}
		)
			.then(response => response.json())
			.then(jsonResponse => {
				updateSummary(jsonResponse.summary);
				updateItems(jsonResponse.items);
			})
			.catch(e => {
				showNotification(e.message, danger);
			});
	}

	return (
		<ClayIconSpriteContext.Provider value={props.spritemap}>
			<div
				className={classNames(
					'commerce-table-wrapper',
					props.wrapperCssClasses
				)}
			>
				{/* <ManagementTableBar />
				<Table /> */}
				<Pagination />
				{props.summaryName ? (
					<WrappingCard cardCssClass="mt-4" title={props.summaryName}>
						<Summary items={props.summaryItems} />
					</WrappingCard>
				) : (
					<Summary items={props.summaryItems} />
				)}
			</div>
		</ClayIconSpriteContext.Provider>
	);
}

Table.propTypes = {
	wrapperCssClasses: PropTypes.string,
	// currentPage: PropTypes.number,
	// dataProviderKey: PropTypes.string.isRequired,
	// dataSetAPI: PropTypes.string.isRequired,
	// disableAJAX: PropTypes.bool,
	// filters: PropTypes.object,
	// id: PropTypes.string,
	// items: PropTypes.array.isRequired,
	// pageSize: PropTypes.number.isRequired,
	// paginationBaseHref: PropTypes.string,
	// paginationEntries: PropTypes.array.isRequired,
	// paginationSelectedEntry: PropTypes.number.isRequired,
	// schema: PropTypes.object.isRequired,
	// selectable: PropTypes.bool,

	spritemap: PropTypes.string.isRequired,
	summaryName: PropTypes.string,
	summaryItems: PropTypes.array
	// tableName: PropTypes.string.isRequired,
	// totalItems: PropTypes.number.isRequired,
};

Table.defaultProps = {
	currentPage: 1,
	wrappedByPanels: false
};

export default Table;
