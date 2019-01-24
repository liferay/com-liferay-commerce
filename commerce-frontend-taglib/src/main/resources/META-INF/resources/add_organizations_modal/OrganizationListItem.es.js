'use strict';

import template from './OrganizationListItem.soy';
import Component from 'metal-component';
import Soy, {Config} from 'metal-soy';

import '../add_to_tick_item/AddToTickItem.es';
import '../autocomplete_item/AutocompleteItem.es';

class OrganizationListItem extends Component {

	syncSelectedOrganizations() {
		this._selected = this.selectedOrganizations.reduce(
			(hasItemBeenSelected, item) =>
				hasItemBeenSelected || item.id === this.id,
			false
		);

		return this._selected;
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
	_selected: Config.bool().value(false),
	colorId: Config.number(),
	id: Config.oneOfType(
		[
			Config.number(),
			Config.string()
		]
	),
	name: Config.string().required(),
	query: Config.string(),
	selectedOrganizations: Config.array(
		Config.shapeOf(
			{
				id: Config.oneOfType(
					[
						Config.number(),
						Config.string()
					]
				),
				name: Config.string()
			}
		)
	).value(
		[]
	)
};

export {OrganizationListItem};
export default OrganizationListItem;