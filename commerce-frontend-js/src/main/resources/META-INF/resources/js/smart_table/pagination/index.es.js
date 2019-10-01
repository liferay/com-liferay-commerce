import { ClayPaginationWithBar } from '@clayui/pagination';
import React from 'react';

function Pagination(props) {
	const perPage = props.paginationEntries[props.paginationSelectedEntry].label;
	return (
		<ClayPaginationWithBar
			activeDelta={perPage}
			activePage={props.currentPage}
			deltas={props.paginationEntries}
			ellipsisBuffer={3}
			onDeltaChange={props.onEntryChange}
			onPageChange={props.onPageChange}
			totalItems={props.totalItems}
		/>
	)
}

export default Pagination;
