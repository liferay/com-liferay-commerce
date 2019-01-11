'use strict';

import template from './UserInvitationModal.soy';
import Component from 'metal-component';
import Soy, {Config} from 'metal-soy';

import 'clay-modal';

import '../user_utils/UserInvitation.es';

class UserInvitationModal extends Component {

	syncUsers() {
		const contentWrapper = this.element.querySelector('.autocomplete-input__content');
		this.element.querySelector('.autocomplete-input__box').focus();
		contentWrapper.scrollTo(0, contentWrapper.offsetHeight);
	}

	_updateUsers(users) {
		console.log('update triggered!', users);
		return this.users = users
	}

	_handleCloseModal(e) {
		e.preventDefault();
		return this._isVisible = false;
	}

	_sendInvitations() {
		if (!this.users.length) {
			return false;
		};
		return this.emit('userInvitationSave', this.users);
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
	_isVisible: Config.bool().internal().value(false),
	_isLoading: Config.bool().internal().value(false)
};

export {UserInvitationModal};
export default UserInvitationModal;
