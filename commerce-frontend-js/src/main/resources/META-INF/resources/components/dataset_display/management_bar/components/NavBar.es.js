import Icon from '@clayui/icon';
import React from 'react';

import getAppContext from './Context.es';
import FiltersDropdown from './FiltersDropdown.es';

const NavBar = () => {
	const {actions, state} = getAppContext();

	return (
		<nav className="management-bar management-bar-light navbar navbar-expand-md">
			<div className="container-fluid container-fluid-max-xl">
				<ul className="navbar-nav">
					<FiltersDropdown />
				</ul>
				<div className="navbar-form navbar-form-autofit navbar-overlay navbar-overlay-sm-down">
					<div className="d-inline">
						<form
							onSubmit={e => {
								e.preventDefault();
								state.onFilterChange();
							}}
							role="search"
						>
							<div className="input-group">
								<div className="input-group-item">
									<input
										className="form-control input-group-inset input-group-inset-after"
										onChange={e =>
											actions.updateInputSearchValue(
												e.target.value
											)
										}
										placeholder={Liferay.Language.get(
											'search-for'
										)}
										type="text"
										value={state.inputSearch.value || ''}
									/>
									<span className="input-group-inset-item input-group-inset-item-after">
										<button
											className="btn btn-unstyled"
											type="submit"
										>
											<Icon symbol="search" />
										</button>
										<button
											className="btn btn-unstyled d-none"
											type="button"
										>
											<Icon symbol="times" />
										</button>
									</span>
								</div>
							</div>
						</form>
					</div>
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
