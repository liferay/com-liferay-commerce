'use strict';

import template from './CompareCheckbox.soy';
import Component from 'metal-component';
import Soy, {Config} from 'metal-soy';

class CompareCheckbox extends Component {
	attached() {
		Liferay.on(
			'compareIsAvailable',
			() => {
				this.compareAvailable = true;
				return this._emitUpdates();
			}
		);
		Liferay.on(
			'compareIsUnavailable',
			() => {
				this.compareAvailable = false;
				return this._emitUpdates();
			}
		);
		Liferay.on(
			'productRemovedFromCompare',
			(data) => {
				if (data.id === this.productId) {
					this.inCompare = false;
				}
				return this._emitUpdates();
			}
		);
	}

	_emitUpdates() {
		this.emit(
			'checkboxCompareUpdated',
			{
				compareAvailable: this.compareAvailable,
				inCompare: this.inCompare
			}
		);
	}

	_handleCompareCheckbox(evt) {
		evt.preventDefault();
		this.inCompare = !this.inCompare;
		return Liferay.fire(
			'toggleProductToCompare',
			{
				id: this.productId,
				thumbnail: this.pictureUrl || null
			}
		);
	}
}

Soy.register(CompareCheckbox, template);

CompareCheckbox.STATE = {
	checkboxVisible: Config.bool(),
	compareAvailable: Config.bool(),
	inCompare: Config.bool(),
	labelVisible: Config.bool(),
	pictureUrl: Config.string(),
	productId: Config.oneOfType(
		[
			Config.number(),
			Config.string()
		]
	).required()
};

export {CompareCheckbox};
export default CompareCheckbox;