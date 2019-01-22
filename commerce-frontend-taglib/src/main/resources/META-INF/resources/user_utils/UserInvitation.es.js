
'use strict';

import {debounce} from 'metal-debounce';

import template from './UserInvitation.soy';
import Component from 'metal-component';
import Soy, {Config} from 'metal-soy';

import 'clay-modal';

import './UserListItem.es';
import './UserInputItem.es';

const EMAIL_REGEX = /^(([^<>()\[\]\\.,;:\s@"]+(\.[^<>()\[\]\\.,;:\s@"]+)*)|(".+"))@((\[[0-9]{1,3}\.[0-9]{1,3}\.[0-9]{1,3}\.[0-9]{1,3}])|(([a-zA-Z\-0-9]+\.)+[a-zA-Z]{2,}))$/;

class UserInvitation extends Component {

	attached() {
		this._debouncedFetchUsers = debounce(this._fetchUsers.bind(this), 300);
	}

	created() {
		this._fetchUsers();
	}

	shouldUpdate(changes) {
		if (changes.events) {
			return false;
		}

		return true;
	}

	testAddedUsers(e) {
		const contentWrapper = this.element.querySelector('.autocomplete-input__content');

		this.element.querySelector('.autocomplete-input__box').focus();

		contentWrapper.scrollTo(0, contentWrapper.offsetHeight);

		return this.emit('updateUsers', this.addedUsers);
	}

	testQuery() {
		this._isLoading = true;

		return this._debouncedFetchUsers();
	}

	_handleFormSubmit(evt) {
		evt.preventDefault();

		if (this.query.match(EMAIL_REGEX)) {
			this.addedUsers = [
				...this.addedUsers,
				{
					email: this.query
				}
			];

			this.query = '';

			this.testAddedUsers();
			this.testQuery();

			return true;
		}

		return false;
	}

	_handleInputBox(evt) {
		evt.preventDefault();

		if (evt.keyCode === 8 && !this.query.length) {
			this.addedUsers = this.addedUsers.slice(0, -1);

			this.testAddedUsers();

			return false;
		}

		this.query = evt.target.value;

		this.testQuery();

		return true;
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
			hasUserAlreadyBeenAdded ?
				this.addedUsers.filter((user) => user.email !== userToBeToggled.email) :
				[...this.addedUsers, userToBeToggled];

		this.testAddedUsers();

		return this.addedUsers;
	}

	_fetchUsers() {
		return fetch(
			this.usersAPI + '?q=' + this.query,
			{
				method: 'GET'
			}
		)
			.then(
				response => response.json()
			)
			.then(
				response => {
					this._isLoading = false;

					this.users = response.users;

					return this.users;
				}
			);
	}
};

Soy.register(UserInvitation, template);

const USER_SCHEMA = Config.shapeOf(
	{
		email: Config.string().required(),
		name: Config.string().required(),
		thumbnail: Config.string().required(),
		userId: Config.oneOfType(
			[
				Config.number(),
				Config.string()
			]
		).required()
	}
);

UserInvitation.STATE = {
	addedUsers: Config.array(USER_SCHEMA).value([]),
	query: Config.string().value(''),
	spritemap: Config.string(),
	users: Config.array(USER_SCHEMA).value([]),
	usersAPI: Config.string().value(''),
	_isLoading: Config.bool().internal().value(false)
};

export {UserInvitation};
export default UserInvitation;