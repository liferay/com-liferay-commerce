'use strict';

import template from './AutocompleteItem.soy';
import Component from 'metal-component';
import Soy, {Config} from 'metal-soy';

class AutocompleteItem extends Component {

	syncQuery() {
		this.processQuery();
	}

	syncText() {
		this.processQuery();
	}

	processQuery() {
		const regex = new RegExp(`(.*?)(${this.query})(.*)`, 'gmi');
		const results = regex.exec(this.text);

		if (!results) {
			return this.reinitializeTextGroups();
		}

		return this.updateHighlightedText(results);
	}

	reinitializeTextGroups() {
		this.firstGroup = this.text;
		this.secondGroup = null;
		this.thirdGroup = null;
		return false;
	}

	updateHighlightedText(results) {
		this.firstGroup = results[1] || null;
		this.secondGroup = results[2] || null;
		this.thirdGroup = results[3] || null;
		return true;
	}

}

Soy.register(AutocompleteItem, template);

AutocompleteItem.STATE = {
	text: Config.any(),
	query: Config.string(),
	firstGroup: Config.string().internal(),
	secondGroup: Config.string().internal(),
	thirdGroup: Config.string().internal()
};

export {AutocompleteItem};
export default AutocompleteItem;