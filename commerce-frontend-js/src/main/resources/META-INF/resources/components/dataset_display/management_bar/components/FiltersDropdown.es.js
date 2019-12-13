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
		state.filters.filter(filter => !(filter.main || filter.invisible))
	);

	useEffect(() => {
		const results = state.filters.filter(filter => {
			switch (true) {
				case !!filter.main:
					return false;
				case !!filter.invisible:
					return false;
				case query &&
					!(
						filter.id.toLowerCase().includes(query) ||
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
				<button className="btn btn-unstyled dropdown-toggle p-2 text-secondary">
					<span className="navbar-text-truncate">
						{Liferay.Language.get('set-filters')}
					</span>
					{active ? (
						<Icon className="ml-2" symbol="caret-top" />
					) : (
						<Icon className="ml-2" symbol="caret-bottom" />
					)}
				</button>
			}
		>
			<ClayDropDown.Search
				onChange={e => setQuery(e.target.value)}
				value={query}
			/>
			{visibleFilters.length ? (
				<ClayDropDown.ItemList>
					{visibleFilters.map(item => (
						<ClayPanel
							className="mb-0"
							collapsable
							displayTitle={item.label}
							key={item.id}
							showCollapseIcon={true}
						>
							<ClayPanel.Body className="filter-body">
								{renderFilter(item, 'add')}
							</ClayPanel.Body>
						</ClayPanel>
					))}
				</ClayDropDown.ItemList>
			) : (
				<div className="dropdown-section text-muted">
					{Liferay.Language.get('no-filters-available')}
				</div>
			)}
		</ClayDropDown>
	) : null;
};

export default FiltersDropdown;
