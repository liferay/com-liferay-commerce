import ClayButton from '@clayui/button';
import React from 'react';

import getAppContext from './Context.es';
import FilterResume from './Filter/Resume.es';

const ActiveFiltersBar = () => {
	const {actions, state} = getAppContext();

	const filtersActive = state.filters.reduce(
		(acc, filter) => (filter.value ? acc.concat(filter.slug) : acc),
		[]
	);

	return filtersActive.length ? (
		<nav className="tbar tbar-inline-md-down subnav-tbar subnav-tbar-primary subnav-tbar-light pt-3 pb-3 border-top">
			<div className="container-fluid container-fluid-max-xl">
				<ul className="tbar-nav">
					<li className="tbar-item tbar-item-expand">
						<div className="tbar-section">
							{filtersActive.map((slug, i) => {
								const filter = state.filters.reduce(
									(found, filter) =>
										found ||
										(filter.slug === slug ? filter : null),
									null
								);

								if (!filter) {
									throw new Error(
										`Filter "${slug}" not found.`
									);
								}

								return <FilterResume key={i} {...filter} />;
							})}
						</div>
					</li>
					<li className="tbar-item">
						<div className="tbar-section">
							<ClayButton
								className=" tbar-link btn-sm"
								displayType="link"
								onClick={actions.resetFiltersValue}
							>
								{Liferay.Language.get('reset-filters')}
							</ClayButton>
						</div>
					</li>
				</ul>
			</div>
		</nav>
	) : null;
};

export default ActiveFiltersBar;
