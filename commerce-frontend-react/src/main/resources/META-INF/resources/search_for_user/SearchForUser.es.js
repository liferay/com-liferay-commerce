import React from 'react';

import Button from '@clayui/button';

export default function SearchForUser(props) {
	console.log(`In: ${JSON.stringify(props)}`)
	return (
		<div aria-labelledby="theDropdownToggleId" class="dropdown-menu">
			<form>
				<div class="dropdown-section">
				<div class="input-group input-group-sm">
					<div class="input-group-item">
					<input
						class="form-control input-group-inset input-group-inset-after"
						placeholder="Search for..."
						type="text"
					/>
					<span class="input-group-inset-item input-group-inset-item-after">
						<button class="btn btn-unstyled" type="button">
						<svg
							class="lexicon-icon lexicon-icon-search"
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
				<div class="inline-scroller">
				<div class="dropdown-subheader" role="presentation">
					My actions
				</div>
				<ul class="list-unstyled">
					<li>
					<a class="dropdown-item" href="#1">
						D Structure
					</a>
					</li>
					<li>
					<a class="dropdown-item" href="#1">
						F Structure
					</a>
					</li>
				</ul>
				<div class="dropdown-subheader" role="presentation">
					Assign by role
				</div>
				<ul class="list-unstyled">
					<li>
					<a class="dropdown-item" href="#1">
						D Structure
					</a>
					</li>
					<li>
					<a class="dropdown-item" href="#1">
						F Structure
					</a>
					</li>
					<li>
					<a class="disabled dropdown-item" href="#1" tabindex="-1">
						H Structure
					</a>
					</li>
					<li>
					<a class="dropdown-item" href="#1">
						J Structure
					</a>
					</li>
					<li>
					<a class="dropdown-item" href="#1">
						L Structure
					</a>
					</li>
				</ul>
				</div>
			</form>
			<div class="dropdown-caption">Showing 7 of 203 Users</div>
			<div class="dropdown-section">
				<button class="btn btn-block btn-secondary">More</button>
			</div>
		</div>
	)
}