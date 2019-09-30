import ClayButton from '@clayui/button';
import ClayIcon, {ClayIconSpriteContext} from '@clayui/icon';
import classNames from 'classnames';
import PropTypes from 'prop-types';
import React from 'react';

import Summary from '../summary/Summary.es';
import ManagementTableBar from '../table_toolbar/TableToolbar.es';
import Pagination from './pagination/index.es';
import Table from './table/index.es';

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

	const content = (
		<>
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
			<Table
				className={!props.tableName ? 'mt-4' : ''}
				items={props.items}
				schema={props.schema}
			/>
		</>
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
						cardCssClass="mt-4"
						title={props.tableName}
					>
						{content}
					</WrappingCard>
				) : content}

				{props.showPagination && (
					<Pagination 

					/>
				)}

				{props.summaryName ? (
					<WrappingCard 
						cardCssClass="mt-4" 
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
	// currentPage: PropTypes.number,
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
	// selectable: PropTypes.bool,
	spritemap: PropTypes.string.isRequired,
	summaryItems: PropTypes.array,
	summaryName: PropTypes.string,
	tableName: PropTypes.string,
	wrapperCssClasses: PropTypes.string,
	// totalItems: PropTypes.number.isRequired,
};

Wrapper.defaultProps = {
	currentPage: 1,
};

export default Wrapper;
