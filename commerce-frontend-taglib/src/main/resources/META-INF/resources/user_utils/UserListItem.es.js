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
		this._invited = this.addedUsers.reduce((hasBeenInvited, user) => hasBeenInvited || user.email === this.email, false);
	}

	_updateStatus() {
		if (this.userId) {
			this._status = 'valid';
		}
 		else if (this.email.indexOf('@') < 0) {
			this._status = 'user-not-found';
		}
 		else if (!EMAIL_REGEX.test(this.email)) {
			this._status = 'email-not-valid';
		}
 		else {
			this._status = 'valid';
		}
		return this._status;
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
					name: this.name,
					thumbnail: this.thumbnail,
					userId: this.userId
				} : {}
			)
		);
	}
}

Soy.register(UserListItem, template);

UserListItem.STATE = {
	_invited: Config.bool().value(false),
	_status: Config.string().value('valid'),
	addedUsers: Config.array(
		Config.shapeOf(
			{
				email: Config.string()
			}
		)
	).value(
		[]
	),
	email: Config.string().required(),
	name: Config.string(),
	query: Config.string(),
	thumbnail: Config.string(),
	userId: Config.oneOfType(
		[
			Config.string(),
			Config.number()
		]
	)
};

export {UserListItem};
export default UserListItem;