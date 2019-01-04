'use strict';

import template from './UserInvitationInputItem.soy.js';
import Component from 'metal-component';
import Soy, {Config} from 'metal-soy';

class UserInvitation extends Component {
	_handleRemoveItem() {
		return this.emit(
			'removeItem',
			{
				email: this.email
			}
		);
	}
};

Soy.register(UserInvitation, template);

UserInvitation.STATE = {
	email: Config.string().required(),
	name: Config.string(),
	spritemap: Config.string(),
	thumbnail: Config.string()
};

export {UserInvitation};
export default UserInvitation;