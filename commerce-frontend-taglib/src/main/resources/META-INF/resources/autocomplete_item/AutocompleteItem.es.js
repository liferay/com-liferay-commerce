'use strict';

import template from './AutocompleteItem.soy';
import Component from 'metal-component';
import Soy, {Config} from 'metal-soy';

class AutocompleteItem extends Component {

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

	syncQuery() {
		this.processQuery();
	}

	syncText() {
		this.processQuery();
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
	firstGroup: Config.string().internal(),
	query: Config.string(),
	secondGroup: Config.string().internal(),
	text: Config.any(),
	thirdGroup: Config.string().internal()
};

export {AutocompleteItem};
export default AutocompleteItem;