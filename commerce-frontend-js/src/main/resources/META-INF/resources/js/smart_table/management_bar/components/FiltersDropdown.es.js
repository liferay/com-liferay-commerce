import ClayDropDown from '@clayui/drop-down';
import Icon from '@clayui/icon';
import ClayPanel from '@clayui/panel';
import React, {useState, useEffect} from 'react';

import {renderFilter} from '../utils/index.es';
import getAppContext from './Context.es';

const FiltersDropdown = () => {
	const [active, setActive] = useState(false);
	const [query, setQuery] = useState('');
	const {state} = getAppContext();
	const [visibleFilters, setVisibleFilter] = useState(
		state.filters.filter(filter => !filter.value)
	);

	useEffect(() => {
		const results = state.filters.filter(filter => {
			switch (true) {
				case !!filter.value:
					return false;
				case query &&
					!(
						filter.slug.toLowerCase().includes(query) ||
						filter.label.toLowerCase().includes(query)
					):
					return false;
				default:
					return true;
			}
		});

		return setVisibleFilter(results);
	}, [state.filters, query]);

	return state.filters.length ? (
		<ClayDropDown
			active={active}
			onActiveChange={setActive}
			trigger={
				<button
					aria-expanded="false"
					aria-haspopup="true"
					className="btn btn-unstyled dropdown-toggle p-2 text-secondary"
					data-toggle="dropdown"
				>
					<span className="navbar-text-truncate">
						{Liferay.Language.get('add-filters')}
					</span>
					{active ? (
						<Icon className="ml-2" symbol="caret-top" />
					) : (
						<Icon className="ml-2" symbol="caret-bottom" />
					)}
				</button>
			}
		>
			{visibleFilters.length ? (
				<>
					<ClayDropDown.Search
						onChange={e => setQuery(e.target.value)}
						value={query}
					/>
					<ClayDropDown.ItemList>
						{visibleFilters.map(item => (
							<ClayPanel
								className="mb-0"
								collapsable
								displayTitle={item.label}
								key={item.slug}
								showCollapseIcon={true}
							>
								<ClayPanel.Body className="filter-body">
									{renderFilter(item, 'add')}
								</ClayPanel.Body>
							</ClayPanel>
						))}
					</ClayDropDown.ItemList>
				</>
			) : (
				<div className="px-3 py-2 text-muted">
					{Liferay.Language.get('no-filters-available')}
				</div>
			)}
		</ClayDropDown>
	) : null;
};

export default FiltersDropdown;
