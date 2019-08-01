'use strict';

import template from './ProductDetailsModal.soy.js';
import Component from 'metal-component';
import Soy, {Config} from 'metal-soy';

import 'clay-modal';

class ProductDetailsModal extends Component {

	created() {
		setTimeout(
			() => {
				this._isVisible = !this._isVisible;
			},
			2000
		);
	}

	_handleCloseModal(evt) {
		evt.preventDefault();
		return this.refs.modal.show();
	}

}

Soy.register(ProductDetailsModal, template);

ProductDetailsModal.STATE = {
	addToOrderLink: Config.string(),
	availability: Config.string()
		.oneOf(
			[
				'available',
				'inStock',
				'notAvailable'
			]
		)
		.value('inStock'),
	categories: Config.array(
		Config.shapeOf(
			{
				link: Config.string().required(),
				name: Config.string().required()
			}
		)
	)
		.value([]),
	description: Config.string(),
	detailsLink: Config.string(),
	name: Config.string().required(),
	pictureUrl: Config.string().required(),
	settings: Config.shapeOf(
		{
			minQuantity: Config.number()
		}
	)
		.value(),
	sku: Config.string().required(),
	spritemap: Config.string(),
	_modalVisible: Config.bool().value(false)
};

export {ProductDetailsModal};
export default ProductDetailsModal;