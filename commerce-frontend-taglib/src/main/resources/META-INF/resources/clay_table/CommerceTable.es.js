import 'clay-table';
import 'clay-pagination-bar';
import Component from 'metal-component';
import Soy from 'metal-soy';

import template from './CommerceTable.soy';

class CommerceTable extends Component {

	/**
	 * Toggles the selection of an item and adds or removes it from selected items
	 * list.
	 * @param {!Event} event
	 * @private
	 */
	_handleItemToggled(event) {

	}

	_handleItemsPerPageClicked(event) {
		console.log(event);
	}

	_handlePageClicked(event) {
		console.log(event);
	}

}

Soy.register(CommerceTable, template);

CommerceTable.STATE = {

};

export {CommerceTable};
export default CommerceTable;