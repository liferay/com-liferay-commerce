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
		return contentWrapper.scrollTo(0, contentWrapper.offsetHeight);
	}

	_handleCloseModal(e) {
		e.preventDefault();
		this._modalVisible = false;
		return e;
	}

	syncQuery() {
		this._loading = true;
		return this._debouncedFetchUser();
	}

	_handleFormSubmit(evt) {
		evt.preventDefault();
		let result = false;
		if (this.query.match(EMAIL_REGEX)) {
			this.addedUsers = [
				...this.addedUsers,
				{
					email: this.query
				}
			];

			this.query = '';
			result = true;
		}
		return result;
	}

	_handleInputBox(evt) {
		if (evt.keyCode === 8 && !this.query.length) {
			this.addedUsers = this.addedUsers.slice(0, -1);
		}
		else {
			this.query = evt.target.value;
		}
	}
	
	_handleInputName(evt) {
		this.accountName = evt.target.value;
		return evt;
	}
	_toggleInvitation(userToBeToggled) {
		if (!userToBeToggled.id) {
			this.query = '';
		}

		const userAlreadyAdded = this.addedUsers.reduce(
			(alreadyAdded, user) => alreadyAdded || user.email === userToBeToggled.email,
			false
		);

		this.addedUsers = userAlreadyAdded ?
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
					this._loading = false;
					this.users = response.users;
					return this.users;
				}
			);
	}

	_handleCreateAccount() {
		if (!this.addedUsers.length || !this.accountName) {
			return false;
		}

		const data = {
			accountName: this.accountName,
			administratorsEmail: this.addedUsers
		};

		return this.emit(
			'AddAccountModalSave',
			data
		);
	}

	toggle() {
		this._modalVisible = !this._modalVisible;
		return this._modalVisible;
	}

	open() {
		this._modalVisible = true;
		return this._modalVisible;
	}

	close() {
		this._modalVisible = false;
		return this._modalVisible;
	}
}

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
	_loading: Config.bool().internal().value(false),
	_modalVisible: Config.bool().internal().value(false)
};

export {AddAccountModal};
export default AddAccountModal;