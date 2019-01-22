'use strict';

import template from './RoleInputItem.soy';
import Component from 'metal-component';
import Soy, {Config} from 'metal-soy';

class RoleInputItem extends Component {

	_handleRemoveItem(evt) {
		evt.preventDefault();

		return this.emit(
			'removeItem',
			{
				id: this.id
			}
		);
	}

};

Soy.register(RoleInputItem, template);

RoleInputItem.STATE = {
	id: Config.oneOfType(
		[
			Config.number(),
			Config.string()
		]
	).required(),
	name: Config.string().required(),
	spritemap: Config.string()
};

export {RoleInputItem};
export default RoleInputItem;