import React, {useState} from 'react';
import PropTypes from 'prop-types';
import classNames from 'classnames';
import ClayTable from '@clayui/table';
import { showNotification } from '../utilities';

function BaseTable(props) {
	return (
		<ClayTable>
			Table
		</ClayTable>
	)
}

BaseTable.propTypes = {
	schema: PropTypes.shape({
		fieldName: PropTypes.string.isRequired,
		label: PropTypes.string.isRequired,
		sortable: PropTypes.bool
	}).isRequired,
	cellsVariants: PropTypes.arrayOf({
		fieldName: PropTypes.string,
		variant: PropTypes.oneOf([
			PropTypes.func,
			PropTypes.shape
		])
	}),
	items: PropTypes.array
}

function Pagination(prop) {

}

Pagination.propTypes = {

}

function getApiEndpoint(dataSetAPI, pageSize, currentPage = 1, filters = []) {
	const formattedFilters = Object.keys(filters).map((filter) => {
		return `${encodeURIComponent(filter)}=${encodeURIComponent(filters[el])}` 
	}).join('&');

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
		.catch((e) => {
			showNotification(e.message, danger);
		})
	}

	return (
		<div className={classNames('commerce-table-wrapper', props.wrapperCssClasses)}>
			<Table />
			<Pagination />
			<Summary />
		</div>
	);
}

Table.propTypes = {
	wrapperCssClasses: PropTypes.string,
	currentPage: PropTypes.number,
	dataProviderKey: PropTypes.string.isRequired,
	dataSetAPI: PropTypes.string.isRequired,
	disableAJAX: PropTypes.bool,
	filters: PropTypes.object,
	id: PropTypes.string,
	items: PropTypes.array.isRequired,
	pageSize: PropTypes.number.isRequired,
	paginationBaseHref: PropTypes.string,
	paginationEntries: PropTypes.array.isRequired,
	paginationSelectedEntry: PropTypes.number.isRequired,
	schema: PropTypes.object.isRequired,
	selectable: PropTypes.bool,
	spritemap: PropTypes.string.isRequired,
	tableName: PropTypes.string.isRequired,
	totalItems: PropTypes.number.isRequired,
	wrappedByPanels: PropTypes.bool
}

Table.defaultProps = {
	currentPage: 1,
	wrappedByPanels: false
}

export default Table;