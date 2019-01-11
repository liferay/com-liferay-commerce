'use strict';

import template from './AddAccountModal.soy';
import Component from 'metal-component';
import Soy, {Config} from 'metal-soy';

import 'clay-modal';

import '../user_utils/UserInvitation.es';

class AddAccountModal extends Component {

	_updateAddedUsers(users) {
		console.log('update triggered!', users);
		return this.users = users;
	}

	_handleCloseModal(e) {
		e.preventDefault();
		return this._isVisible = false;
	}

	_addAccounts() {
		if (!this.users.length) {
			return false;
		};
		
		const result = {
			account: 'test',
			users: this.users
		}

		console.log(result);

		return this.emit(
			'addAccount', result
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

Soy.register(AddAccountModal, template);

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

AddAccountModal.STATE = {
	usersAPI: Config.string().value(''),
	query: Config.string().value(''),
	spritemap: Config.string(),
	users: Config.array(USER_SCHEMA).value([]),
	_isVisible: Config.bool().internal().value(false),
	_isLoading: Config.bool().internal().value(false)
};

export {AddAccountModal};
export default AddAccountModal;
