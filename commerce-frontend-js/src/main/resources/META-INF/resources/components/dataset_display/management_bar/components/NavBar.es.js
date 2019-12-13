import React from 'react';
import getAppContext from './Context.es';
import FiltersDropdown from './FiltersDropdown.es';
import MainSearch from './MainSearch.es';

const NavBar = () => {
	const {state} = getAppContext();
	const mainFilter = state.filters.find(f => f.main);

	return (
		<nav className="management-bar management-bar-light navbar navbar-expand-md">
			<div className="container-fluid container-fluid-max-xl">
				<ul className="navbar-nav">
					<FiltersDropdown />
				</ul>
				<div className="navbar-form navbar-form-autofit navbar-overlay navbar-overlay-sm-down">
					{mainFilter ? <MainSearch /> : null}
				</div>
				{/* <ul className="navbar-nav">
					{state.actionButton && (
						<li className="nav-item">
							{state.actionButton}
						</li>
					)}
				</ul> */}
			</div>
		</nav>
	);
};

export default NavBar;
