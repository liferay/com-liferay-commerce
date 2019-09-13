'use strict';

import {debounce} from 'debounce'

import template from './UserInvitation.soy.js';
import Component from 'metal-component';
import Soy, { Config } from 'metal-soy';

import './UserInvitationListItem';
import './UserInvitationInputItem';

const EMAIL_REGEX = /^(([^<>()\[\]\\.,;:\s@"]+(\.[^<>()\[\]\\.,;:\s@"]+)*)|(".+"))@((\[[0-9]{1,3}\.[0-9]{1,3}\.[0-9]{1,3}\.[0-9]{1,3}])|(([a-zA-Z\-0-9]+\.)+[a-zA-Z]{2,}))$/

class UserInvitation extends Component {

	created() {
		this._debouncedFetchUser = debounce(this._fetchUsers, 300);
	}

	attached() {
		this._fetchUsers();
	}

	syncAddedUsers() {
		const contentWrapper = this.element.querySelector('.autocomplete-input__content');
		this.element.querySelector('.autocomplete-input__box').focus();
		contentWrapper.scrollTo(0, contentWrapper.offsetHeight);
		return true;
	}

	syncQuery() {
		this._isLoading = true;
		return this._debouncedFetchUser();
	}

	_handleFormSubmit(evt) {
		evt.preventDefault();
		if (this.query.match(EMAIL_REGEX)) {
			this.addedUsers = [
				...this.addedUsers,
				{
					email: this.query
				}
			]
			this.query = '';
			return true
		}
		return false
	}

	_handleInputBox(evt) {
		if (evt.keyCode === 8 && !this.query.length) {
			this.addedUsers = this.addedUsers.slice(0, -1);
			return false
		}
		return this.query = evt.target.value;
	}

	_toggleInvitation(userToBeToggled) {
		if (!userToBeToggled.id) {
			this.query = '';
		}

		const hasUserAlreadyBeenAdded = this.addedUsers.reduce(
			(alreadyAdded, user) => alreadyAdded || user.email === userToBeToggled.email,
			false
		);

		this.addedUsers =
			hasUserAlreadyBeenAdded
			? this.addedUsers.filter((user) => user.email !== userToBeToggled.email )
			: [ ...this.addedUsers, userToBeToggled ];

		return this.addedUsers;
	}

	_fetchUsers() {
		return fetch(
			this.usersAPI + '/' + _authorize(this.query),
			{
				method: 'GET'
			}
		)
		.then(
			response => response.json()
		)
		.then(
			users => {
				this._isLoading = false;
				return this.users = users;
			}
		);
	}

	_sendInvitations() {
		if (!this.addedUsers.length) {
			return false;
		};
		return fetch(
			this.invitationAPI + '?p_auth=' + Liferay.authToken,
			{
				method: 'POST',
				body: JSON.stringify(this.addedUsers)
			}
		)
		.then(
			response => response.json()
		)
		.then(
			invitation => invitation.state
		);
	}

	_authorize(query) {
		if (!query.includes('?')) {
			query += '?p_auth=' + Liferay.authToken;
		}
		else if (!query.includes('p_auth=')) {
			query += '&p_auth=' + Liferay.authToken
		}

		return query;
	}
};

Soy.register(UserInvitation, template);

const USER_SCHEMA = Config.shapeOf(
	{
		id: Config.oneOfType(
			[
				Config.string(),
				Config.number()
			]
		).required(),
		thumbnail: Config.string().required(),
		name: Config.string().required(),
		email: Config.string().required(),
	}
)

UserInvitation.STATE = {
	usersAPI: Config.string().required(),
	invitationAPI: Config.string().required(),
	query: Config.string().value(''),
	users: Config.array( USER_SCHEMA ).value([]),
	addedUsers: Config.array( USER_SCHEMA ).value([]),
	_isLoading: Config.bool().internal().value(false),
}

export { UserInvitation, EMAIL_REGEX };
export default UserInvitation;