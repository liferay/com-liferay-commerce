'use strict';

import template from './AccountsTableItem.soy';
import Component from 'metal-component';
import Soy, {Config} from 'metal-soy';

import '../autocomplete_item/AutocompleteItem.es';

class AccountsTableItem extends Component {

	_handleItemClick() {
		this.emit('selectAccount', {
			accountId: this.accountId,
			name: this.name,
			thumbnail: this.thumbnail
		});
	}

}

Soy.register(AccountsTableItem, template);

AccountsTableItem.STATE = {
	accountId: Config.string(),
	name: Config.string(),
	thumbnail: Config.string(),
	query: Config.string()
};

export {AccountsTableItem};
export default AccountsTableItem;