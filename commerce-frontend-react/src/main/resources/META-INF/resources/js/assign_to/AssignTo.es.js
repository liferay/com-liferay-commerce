import React, { Fragment, useState } from 'react';
import Icon, { ClayIconSpriteContext } from '@clayui/icon';
import ClayDropDown, { Align } from '@clayui/drop-down';
import ClayButton from '@clayui/button';

export default function AssignTo(props) {

	const [ active, setActive ] = useState(false)

	return (
		<ClayIconSpriteContext.Provider value={props.spritemap}>
			
			<div className="commerce-header__assign-to d-none d-xl-flex align-items-center px-3 mr-3 border-right">
				<span className="mr-3">Assigned to:</span>
				<ClayDropDown
					trigger={
						<ClayButton displayType="primary">
							Admin
							<span className="inline-item inline-item-after">
								<Icon symbol="share" />
							</span>
						</ClayButton>
					}
					active={active}
					onActiveChange={setActive}
					alignmentPosition={Align.BottomLeft}
				>
					<>
						<form>
							<div className="dropdown-section">
								<div className="input-group input-group-sm">
									<div className="input-group-item">
										<input
											className="form-control input-group-inset input-group-inset-after"
											placeholder={Liferay.Language.get('search-for')}
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
									{Liferay.Language.get('my-actions')}
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
									{Liferay.Language.get('assign-by-role')}
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
							<button className="btn btn-block btn-secondary">{Liferay.Language.get('more')}</button>
						</div>
					</>
				</ClayDropDown>
			</div>
		</ClayIconSpriteContext.Provider>
	)
}