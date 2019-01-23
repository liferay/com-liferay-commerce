'use strict';

import template from './CompareCheckbox.soy';
import Component from 'metal-component';
import Soy, {Config} from 'metal-soy';

class CompareCheckbox extends Component {
	attached() {
		Liferay.on(
			'compareIsAvailable',
			() => {
				return this.isCompareAvailable = true;
			}
		);
		Liferay.on(
			'compareIsUnavailable',
			() => {
				return this.isCompareAvailable = false;
			}
		);
		Liferay.on(
			'productRemovedFromCompare',
			(data) => {
				if (data.id === this.productId) {
					this.isInCompare = false;
				}
			}
		);
	}

	_handleCompareCheckbox(evt) {
		evt.preventDefault();

		this.isInCompare = !this.isInCompare;

		return Liferay.fire('toggleProductToCompare', {
			id: this.productId,
			thumbnail: this.pictureUrl
		});
	}
}

Soy.register(CompareCheckbox, template);

CompareCheckbox.STATE = {
	isCheckboxVisible: Config.bool(),
	isCompareAvailable: Config.bool(),
	isInCompare: Config.bool(),
	isLabelVisible: Config.bool(),
	pictureUrl: Config.string().required(),
	productId: Config.oneOfType(
		[
			Config.number(),
			Config.string()
		]
	).required()
};

export {CompareCheckbox};
export default CompareCheckbox;