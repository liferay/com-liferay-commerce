'use strict';

import {debounce} from 'metal-debounce';

import template from './AddAccountModal.soy';
import Component from 'metal-component';
import Soy, {Config} from 'metal-soy';

import 'clay-modal';

import '../user_utils/UserListItem.es';
import '../user_utils/UserInputItem.es';

const EMAIL_REGEX = /^(([^<>()\[\]\\.,;:\s@"]+(\.[^<>()\[\]\\.,;:\s@"]+)*)|(".+"))@((\[[0-9]{1,3}\.[0-9]{1,3}\.[0-9]{1,3}\.[0-9]{1,3}])|(([a-zA-Z\-0-9]+\.)+[a-zA-Z]{2,}))$/;

class AddAccountModal extends Component {

	attached() {
		this._fetchUsers();
	}

	close() {
		return this._isVisible = false;
	}

	created() {
		this._debouncedFetchUser = debounce(this._fetchUsers.bind(this), 300);
	}

	open() {
		return this._isVisible = true;
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

	toggle() {
		return this._isVisible = !this._isVisible;
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

	_handleCloseModal(e) {
		e.preventDefault();

		this._isVisible = false;
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

	_sendInvitations() {
		if (!this.addedUsers.length) {
			return false;
		};

		const data = {
			accountName: this.accountName,
			administratorsEmail: this.addedUsers
		};

		return this.emit(
			'AddAccountModalSave',
			data
		);
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

};

Soy.register(AddAccountModal, template);

const USER_SCHEMA = Config.shapeOf(
	{
		email: Config.string().required(),
		name: Config.string().required(),
		thumbnail: Config.string().required(),
		userId: Config.oneOfType(
			[
				Config.string(),
				Config.number()
			]
		).required()
	}
);

AddAccountModal.STATE = {
	accountName: Config.string().value(''),
	addedUsers: Config.array(USER_SCHEMA).value([]),
	query: Config.string().value(''),
	spritemap: Config.string(),
	users: Config.array(USER_SCHEMA).value([]),
	usersAPI: Config.string().value(''),
	_isLoading: Config.bool().internal().value(false),
	_isVisible: Config.bool().internal().value(false)
};

export {AddAccountModal};
export default AddAccountModal;