'use strict';

import template from './OrganizationInputItem.soy';
import Component from 'metal-component';
import Soy, {Config} from 'metal-soy';

class OrganizationInputItem extends Component {

	_handleRemoveItem(e) {
		e.preventDefault();

		return this.emit(
			'removeItem',
			{
				id: this.id
			}
		);
	}
}

Soy.register(OrganizationInputItem, template);

OrganizationInputItem.STATE = {
	id: Config.oneOfType(
		[
			Config.number(),
			Config.string()
		]
	).required(),
	name: Config.string().required(),
	spritemap: Config.string()
};

export {OrganizationInputItem};
export default OrganizationInputItem;