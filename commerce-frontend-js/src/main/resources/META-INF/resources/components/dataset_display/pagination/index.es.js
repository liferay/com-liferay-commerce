import {ClayPaginationWithBasicItems} from '@clayui/pagination';
import ClayPaginationBar from '@clayui/pagination-bar';
import ClayButton from '@clayui/button';
import ClayIcon from '@clayui/icon';
import PropTypes from 'prop-types';
import React from 'react';

function Pagination(props) {
	const perPage =
		props.paginationEntries[props.paginationSelectedEntry].label;

	return (
		<ClayPaginationBar>
			<ClayPaginationBar.DropDown
				items={props.paginationEntries}
				trigger={
					<ClayButton displayType="unstyled">
						{perPage} {Liferay.Language.get('items-per-page')}
						<ClayIcon symbol="caret-double-l" />
					</ClayButton>
				}
			/>

			<ClayPaginationBar.Results />

			<ClayPaginationWithBasicItems
				activePage={props.activePage}
				onPageChange={props.onPageChange}
				totalPages={props.totalItems}
			/>
		</ClayPaginationBar>
	);
}

Pagination.propTypes = {
	onDeltaChange: PropTypes.func.isRequired,
	onEntryChange: PropTypes.func.isRequired,
	onPageChange: PropTypes.func.isRequired,
	paginationEntries: PropTypes.arrayOf(
		PropTypes.shape({
			label: PropTypes.oneOfType([
				PropTypes.string.isRequired,
				PropTypes.number.isRequired
			]),
			onClick: PropTypes.func
		}).isRequired
	),
	paginationSelectedEntry: PropTypes.number.isRequired,
	totalItems: PropTypes.number.isRequired
};

export default Pagination;
