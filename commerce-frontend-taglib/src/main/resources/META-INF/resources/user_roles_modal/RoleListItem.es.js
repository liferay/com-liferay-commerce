'use strict';

import template from './RoleListItem.soy';
import Component from 'metal-component';
import Soy, {Config} from 'metal-soy';

import '../autocomplete_item/AutocompleteItem.es';

class RoleListItem extends Component {

	syncSelectedRoles() {
		return this._selected = this.selectedRoles.reduce(
			(hasItemBeenSelected, item) => 
				hasItemBeenSelected || item.id === this.id, false
		);
	}

	_handleToggleItem(e) {
		e.preventDefault();

		return this.emit(
			'toggleItem',
			{
				id: this.id,
				name: this.name
			}
		);
	}

};

Soy.register(RoleListItem, template);

RoleListItem.STATE = {
	id: Config.oneOfType(
		[
			Config.string(),
			Config.number()
		]
	),
	name: Config.string(),
	selectedRoles: Config.array(
		Config.shapeOf(
			{
				id: Config.oneOfType(
					[
						Config.string(),
						Config.number()
					]
				),
				name: Config.string(),
			}
		)
	).value(
		[]
	),
	query: Config.string(),
	_selected: Config.bool().value(false),
};

export {RoleListItem};
export default RoleListItem;