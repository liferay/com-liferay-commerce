import ClayButton from '@clayui/button';
import ClayIcon, {ClayIconSpriteContext} from '@clayui/icon';
import classNames from 'classnames';
import PropTypes from 'prop-types';
import React, { useState } from 'react';

import BulkActions from './bulk_actions/index.es';
import Summary from '../summary/Summary.es';
import ManagementTableBar from '../table_toolbar/TableToolbar.es';
import Pagination from './pagination/index.es';
import Table from './table/index.es';

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

function Wrapper(props) {
	const [selectedItemsId, setselectedItemsId] = useState([]);
	const selectItems = (val, checked) => {
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

	const managementTableBar = (
		<ManagementTableBar 
			actionButton={(
				<ClayButton
					displayType="primary"
					monospaced
					onClick={() => console.log('clicked')}
				>
					<ClayIcon symbol="plus" />
				</ClayButton>
			)}
			filters={props.filters}
			onFilterChange={console.log}
			spritemap={props.spritemap}
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
	const bulkActions = (
		selectedItemsId.length ? (
			<BulkActions 
				selectAllItems={() => selectItems(null, true)}
				selectedItemsCount={selectedItemsId.length}
				totalItemsCount={props.items.length}
			/>
		) : null
	)

	return (
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
						{managementTableBar}
						<div className="border-bottom" />
						{bulkActions}
						{table}
					</WrappingCard>
				) : (
					<>
						<WrappingCard bodyCssClass="p-0">
							{managementTableBar}
						</WrappingCard>
						<WrappingCard bodyCssClass="p-0" className="mt-4">
							{bulkActions}
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
	);
}

Wrapper.propTypes = {
	currentPage: PropTypes.number.isRequired,
	// dataProviderKey: PropTypes.string.isRequired,
	// dataSetAPI: PropTypes.string.isRequired,
	// disableAJAX: PropTypes.bool,
	filters: PropTypes.array,
	// id: PropTypes.string,
	items: PropTypes.array.isRequired,
	pageSize: PropTypes.number.isRequired,
	// paginationBaseHref: PropTypes.string,
	paginationEntries: PropTypes.array.isRequired,
	paginationSelectedEntry: PropTypes.number.isRequired,
	schema: PropTypes.object.isRequired,
	showPagination: PropTypes.bool,
	// selectable: PropTypes.bool,
	spritemap: PropTypes.string.isRequired,
	summaryItems: PropTypes.array,
	summaryName: PropTypes.string,
	tableName: PropTypes.string,
	totalItems: PropTypes.number,
	wrapperCssClasses: PropTypes.string,
	// totalItems: PropTypes.number.isRequired,
};

Wrapper.defaultProps = {
	currentPage: 1,
};

export default Wrapper;
