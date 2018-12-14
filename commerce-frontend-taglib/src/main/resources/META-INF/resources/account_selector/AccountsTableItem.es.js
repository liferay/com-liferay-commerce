'use strict';

import template from './AccountsTableItem.soy';
import Component from 'metal-component';
import Soy, {Config} from 'metal-soy';

import './AutocompleteItem.es';

class AccountsTableItem extends Component {

	_handleItemClick() {
		this.emit('selectAccount', {
			id: this.id,
			name: this.name,
			thumbnail: this.thumbnail
		});
	}

}

Soy.register(AccountsTableItem, template);

AccountsTableItem.STATE = {
	id: Config.oneOfType(
		[
			Config.string(),
			Config.number()
		]
	),
	name: Config.string(),
	thumbnail: Config.string(),
	query: Config.string()
};

export {AccountsTableItem};
export default AccountsTableItem;