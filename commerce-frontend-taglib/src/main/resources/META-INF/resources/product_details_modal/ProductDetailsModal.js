'use strict';

import template from './ProductDetailsModal.soy.js';
import Component from 'metal-component';
import Soy, { Config } from 'metal-soy';

import 'clay-modal';

class ProductDetailsModal extends Component {

    created() {
        setTimeout(() => {
            this._isVisible = !this._isVisible
        }, 2000);
    }


    _handleCloseModal(evt) {
        evt.preventDefault();
        this.refs.modal.show();
    }

};

Soy.register(ProductDetailsModal, template);

ProductDetailsModal.STATE = {
    availability: Config.string().oneOf([
        'inStock',
        'available',
        'notAvailable'
    ]).value('inStock'),
    pictureUrl: Config.string().required(),
    sku: Config.string().required(),
    name: Config.string().required(),
    categories: Config.array(
        Config.shapeOf(
            {
                name: Config.string().required(),
                link: Config.string().required()
            }
        )
    ).value([]),
    settings: Config.shapeOf(
        {
            minQuantity: Config.number()
        }
    ).value(),
    spritemap: Config.string(),
    description: Config.string(),
    detailsLink: Config.string(),
    addToOrderLink: Config.string(),
    _isVisible: Config.bool().value(false)
}

export { ProductDetailsModal };
export default ProductDetailsModal;