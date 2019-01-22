'use strict';

import template from './UserInputItem.soy';
import Component from 'metal-component';
import Soy, {Config} from 'metal-soy';

class UserInputItem extends Component {

	_handleRemoveItem(evt) {
		evt.preventDefault();

		return this.emit(
			'removeItem',
			{
				email: this.email
			}
		);
	}

};

Soy.register(UserInputItem, template);

UserInputItem.STATE = {
	email: Config.string().required(),
	name: Config.string(),
	spritemap: Config.string(),
	thumbnail: Config.string()
};

export {UserInputItem};
export default UserInputItem;