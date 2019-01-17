'use strict';

import template from './OrganizationListItem.soy';
import Component from 'metal-component';
import Soy, {Config} from 'metal-soy';

import '../add_to_tick_item/AddToTickItem.es';
import '../autocomplete_item/AutocompleteItem.es';

class OrganizationListItem extends Component {

	syncSelectedOrganizations() {
		return this._selected = this.selectedOrganizations.reduce(
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

Soy.register(OrganizationListItem, template);

OrganizationListItem.STATE = {
	id: Config.oneOfType(
		[
			Config.string(),
			Config.number()
		]
	),
	name: Config.string().required(),
	selectedOrganizations: Config.array(
		Config.shapeOf(
			{
				id: Config.oneOfType(
					[
						Config.string(),
						Config.number()
					]
				),
				name: Config.string()
			}
		)
	).value(
		[]
	),
	colorId: Config.number(),
	query: Config.string(),
	_selected: Config.bool().value(false)
};

export {OrganizationListItem};
export default OrganizationListItem;