'use strict';

import {debounce} from 'metal-debounce';

import template from './UserInvitationModal.soy';
import Component from 'metal-component';
import Soy, {Config} from 'metal-soy';

import 'clay-modal';

import '../user_utils/UserListItem.es';
import '../user_utils/UserInputItem.es';

const EMAIL_REGEX = /^(([^<>()\[\]\\.,;:\s@"]+(\.[^<>()\[\]\\.,;:\s@"]+)*)|(".+"))@((\[[0-9]{1,3}\.[0-9]{1,3}\.[0-9]{1,3}\.[0-9]{1,3}])|(([a-zA-Z\-0-9]+\.)+[a-zA-Z]{2,}))$/;

class UserInvitationModal extends Component {

	created() {
		this._debouncedFetchUser = debounce(this._fetchUsers.bind(this), 300);
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

	_handleCloseModal(e) {
		e.preventDefault();
		this._isVisible = false;
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
			];
			this.query = '';
			return true;
		}
		return false;
	}

	_handleInputBox(evt) {
		if (evt.keyCode === 8 && !this.query.length) {
			this.addedUsers = this.addedUsers.slice(0, -1);
			return false;
		}
		return this.query = evt.target.value;
	}

	_handleInputName(evt) {
		return this.accountName = evt.target.value;
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
					return this.users = response.users;
				}
			);
	}

	_sendInvitations() {
		if (!this.addedUsers.length) {
			return false;
		};

		return this.emit(
			'inviteUserToAccount',
			this.addedUsers
		);
	}

	toggle() {
		return this._isVisible = !this._isVisible;
	}

	open() {
		return this._isVisible = true;
	}

	close() {
		return this._isVisible = false;
	}
};

Soy.register(UserInvitationModal, template);

const USER_SCHEMA = Config.shapeOf(
	{
		userId: Config.oneOfType(
			[
				Config.string(),
				Config.number()
			]
		).required(),
		thumbnail: Config.string().required(),
		name: Config.string().required(),
		email: Config.string().required()
	}
);

UserInvitationModal.STATE = {
	usersAPI: Config.string().value(''),
	query: Config.string().value(''),
	spritemap: Config.string(),
	users: Config.array(USER_SCHEMA).value([]),
	addedUsers: Config.array(USER_SCHEMA).value([]),
	_isVisible: Config.bool().internal().value(false),
	_isLoading: Config.bool().internal().value(false)
};

export {UserInvitationModal};
export default UserInvitationModal;
