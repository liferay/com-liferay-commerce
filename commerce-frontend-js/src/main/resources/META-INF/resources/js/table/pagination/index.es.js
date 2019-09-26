import ClayButton from '@clayui/button';
import ClayDropDown from '@clayui/drop-down';
import ClayIcon from '@clayui/icon';
import ClayPagination from '@clayui/pagination';
import React, {useState} from 'react';

function Pagination() {
	const initialActivePage = 1;
	const numberOfItems = 20;
	const [active, setActive] = useState(initialActivePage);
	const [activePage, setActivePage] = React.useState(1);
	const [perPage, setPerPage] = React.useState(10);

	const options = [
		{
			label: '5',
			value: 5
		},
		{
			label: '10',
			value: 10
		},
		{
			label: '20',
			value: 20
		},
		{
			label: '30',
			value: 30
		},
		{
			label: '50',
			value: 50
		}
	];

	return (
		<div className="pagination-bar mt-4">
			<ClayDropDown
				active={active}
				className="pagination-items-per-page"
				onActiveChange={newVal => setActive(newVal)}
				trigger={
					<ClayButton displayType="unstyled">
						{`${perPage} entries`}
						<ClayIcon symbol="caret-double-l" />
					</ClayButton>
				}
			>
				<ClayDropDown.ItemList>
					{options.map((item, i) => (
						<ClayDropDown.Item
							key={i}
							onClick={() => setPerPage(item.value)}
						>
							{`${item.label} items`}
						</ClayDropDown.Item>
					))}
				</ClayDropDown.ItemList>
			</ClayDropDown>

			<div className="pagination-results">
				{`Showing ${activePage} to ${Math.ceil(
					numberOfItems / perPage
				)} of ${numberOfItems}`}
			</div>

			<ClayPagination
				activePage={active}
				ellipsisBuffer={1}
				onPageChange={setActive}
				totalPages={8}
			/>
		</div>
	);
}

export default Pagination;
