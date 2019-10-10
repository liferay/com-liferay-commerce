import ClayButton from '@clayui/button';
import React from 'react';

import getAppContext from './Context.es';
import FilterResume from './filters/Resume.es';

const ActiveFiltersBar = props => {
	const {actions, state} = getAppContext();

	const filtersActive = state.filters.reduce(
		(acc, filter) => (filter.value ? acc.concat(filter.slug) : acc),
		[]
	);

	return filtersActive.length ? (
		<nav className="subnav-tbar subnav-tbar-primary subnav-tbar-light p-3 border-top">
			<ul className="tbar-nav">
				<li className="p-0 tbar-item tbar-item-expand">
					<div className="tbar-section">
						{filtersActive.map(slug => {
							const filter = state.filters.reduce(
								(found, filter) =>
									found ||
									(filter.slug === slug ? filter : null),
								null
							);

							if (!filter) {
								throw new Error(`Filter "${slug}" not found.`);
							}

							return (
								<FilterResume
									disabled={props.disabled}
									key={filter.slug}
									{...filter}
								/>
							);
						})}
					</div>
				</li>
				<li className="tbar-item">
					<div className="tbar-section">
						<ClayButton
							className=" tbar-link btn-sm"
							disabled={props.disabled}
							displayType="link"
							onClick={actions.resetFiltersValue}
						>
							{Liferay.Language.get('reset-filters')}
						</ClayButton>
					</div>
				</li>
			</ul>
		</nav>
	) : null;
};

export default ActiveFiltersBar;
