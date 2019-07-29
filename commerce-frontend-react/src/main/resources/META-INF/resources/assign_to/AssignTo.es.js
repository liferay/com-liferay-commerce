import React, { Fragment } from 'react';
import ReactDOM from 'react-dom';
import ClayAlert from '@clayui/alert';

export function SearchForUser(props) {
	return (
		<Fragment>
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
							<use href="/images/icons/icons.svg#search" />
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
					<a className="disabled dropdown-item" href="#1" tabindex="-1">
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
		</Fragment>
	)
}

export default function(componentId, id, props) {
	const portletFrame = window.document.getElementById(id);
	let instance = null;
	ReactDOM.render(
		<SearchForUser
			ref={component => {
				instance = component;
			}}
			showFilters={false}
			{...props}
		/>,
		portletFrame
	);
	if (window.Liferay && window.Liferay.component) {
		window.Liferay.component(componentId, instance);
	}
	return instance;
}