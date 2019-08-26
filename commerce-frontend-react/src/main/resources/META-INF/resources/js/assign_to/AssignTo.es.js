import React, { Fragment } from 'react';
import Icon, { ClayIconSpriteContext } from '@clayui/icon';

export default function AssignTo(props) {
	return (
		<ClayIconSpriteContext.Provider value={props.spritemap}>
			<div className="commerce-header__assign-to d-none d-xl-flex align-items-center px-3 mr-3 border-right">
				<label htmlFor="assigned-to mr-3 small">Assigned to:</label>
				<div className="btn-group dropdown ml-3" role="group">
					<button
						className="btn btn-secondary dropdown-toggle"
						type="button"
						aria-expanded="false"
						aria-haspopup="true"
						data-toggle="dropdown"
						id="theDropdownToggleId"
					>
						Admin
						<span className="inline-item inline-item-before">
							<Icon symbol="share" />
						</span>
					</button>
					<div
						aria-labelledby="theDropdownToggleId"
						className="dropdown-menu"
					>
						<form>
							<div className="dropdown-section">
								<div className="input-group input-group-sm">
									<div className="input-group-item">
										<input
											className="form-control input-group-inset input-group-inset-after"
											placeholder="Search for..."
											type="text"
										/>
										<span className="input-group-inset-item input-group-inset-item-after">
											<button className="btn btn-unstyled" type="button">
											<svg
												className="lexicon-icon lexicon-icon-search"
												focusable="false"
												role="presentation"
											>
												<use href="/images/icons/icons.svg#search"/>
											</svg>
											</button>
										</span>
									</div>
								</div>
							</div>
						</form>
						<form>
							<div className="inline-scroller">
								<div className="dropdown-subheader" role="presentation">
									My actions
								</div>
								<ul className="list-unstyled">
									<li>
										<a className="dropdown-item" href="#1">
											D Structure
										</a>
									</li>
									<li>
										<a className="dropdown-item" href="#1">
											F Structure
										</a>
									</li>
								</ul>
								<div className="dropdown-subheader" role="presentation">
									Assign by role
								</div>
								<ul className="list-unstyled">
									<li>
										<a className="dropdown-item" href="#1">
											D Structure
										</a>
									</li>
									<li>
										<a className="dropdown-item" href="#1">
											F Structure
										</a>
									</li>
									<li>
										<a className="disabled dropdown-item" href="#1" tabIndex="-1">
											H Structure
										</a>
									</li>
									<li>
										<a className="dropdown-item" href="#1">
											J Structure
										</a>
									</li>
									<li>
										<a className="dropdown-item" href="#1">
											L Structure
										</a>
									</li>
								</ul>
							</div>
						</form>
						<div className="dropdown-caption">Showing 7 of 203 Users</div>
						<div className="dropdown-section">
							<button className="btn btn-block btn-secondary">More</button>
						</div>
					</div>
				</div>
			</div>
		</ClayIconSpriteContext.Provider>
	)
}