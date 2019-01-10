'use strict';

import template from './UserListItem.soy';
import Component from 'metal-component';
import Soy, {Config} from 'metal-soy';

import '../autocomplete_item/AutocompleteItem.es';
import '../add_to_tick_item/AddToTickItem.es';


const EMAIL_REGEX = /^(([^<>()\[\]\\.,;:\s@"]+(\.[^<>()\[\]\\.,;:\s@"]+)*)|(".+"))@((\[[0-9]{1,3}\.[0-9]{1,3}\.[0-9]{1,3}\.[0-9]{1,3}])|(([a-zA-Z\-0-9]+\.)+[a-zA-Z]{2,}))$/;

class UserListItem extends Component {

	attached() {
		return this._updateStatus();
	}

	willUpdate() {
		return this._updateStatus();
	}

	syncAddedUsers() {
		return this._invited = this.addedUsers.reduce((hasBeenInvited, user) => hasBeenInvited || user.email === this.email, false);
	}

	_updateStatus() {
		if (this.userId) {
			return this._status = 'valid';
		}
		if (this.email.indexOf('@') < 0) {
			return this._status = 'user-not-found';
		}
		if (!EMAIL_REGEX.test(this.email)) {
			return this._status = 'email-not-valid';
		}
		return this._status = 'valid';
	}

	_handleToggleInvitation(e) {
		e.preventDefault();

		return this.emit(
			'toggleInvitation',
			Object.assign(
				{
					email: this.email
				},
				this.userId ? {
					userId: this.userId,
					name: this.name,
					thumbnail: this.thumbnail
				} : {}
			)
		);
	}

};

Soy.register(UserListItem, template);

UserListItem.STATE = {
	userId: Config.oneOfType(
		[
			Config.string(),
			Config.number()
		]
	),
	thumbnail: Config.string(),
	name: Config.string(),
	email: Config.string().required(),
	addedUsers: Config.array(
		Config.shapeOf(
			{
				email: Config.string()
			}
		)
	).value(
		[]
	),
	query: Config.string(),
	_invited: Config.bool().value(false),
	_status: Config.string().value('valid')
};

export {UserListItem};
export default UserListItem;