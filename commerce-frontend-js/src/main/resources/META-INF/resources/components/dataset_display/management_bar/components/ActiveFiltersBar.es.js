import ClayButton from '@clayui/button';
import React from 'react';

import getAppContext from './Context.es';
import FilterResume from './filters/Resume.es';

function ActiveFiltersBar(props) {
	const {actions, state} = getAppContext();

	const filtersActive = state.filters.reduce(
		(acc, filter) => ((filter.value && !filter.invisible && !filter.main) ? acc.concat(filter.id) : acc),
		[]
	);

	return filtersActive.length ? (
		<nav className="subnav-tbar subnav-tbar-primary subnav-tbar-light p-3 border-top mb-0">
			<ul className="tbar-nav">
				<li className="p-0 tbar-item tbar-item-expand">
					<div className="tbar-section">
						{filtersActive.map(id => {
							const filter = state.filters.reduce(
								(found, filter) =>
									found ||
									(filter.id === id ? filter : null),
								null
							);

							if (!filter) {
								throw new Error(`Filter "${id}" not found.`);
							}

							return (
								<FilterResume
									disabled={props.disabled}
									key={filter.id}
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
