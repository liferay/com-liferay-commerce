'use strict';

import template from './ProductCard.soy.js';
import Component from 'metal-component';
import Soy, {Config} from 'metal-soy';

class ProductCard extends Component {};

Soy.register(ProductCard, template);

ProductCard.STATE = {
	availability: Config.string().oneOf([
		'inStock',
		'available',
		'notAvailable'
	]).value('inStock'),
	sku: Config.string().required(),
	pictureUrl: Config.string(),
	name: Config.string().required(),
	categories: Config.array(
		Config.shapeOf({
			name: Config.string().required(),
			link: Config.string().required()
		})
	),
	price: Config.shapeOf({
		formattedPrice: Config.string().required(),
		formattedPromoPrice: Config.string()
	}),
	description: Config.string(),
	spritemap: Config.string(),
	detailsLink: Config.string(),
	minQuantity: Config.number()
};

export {ProductCard};
export default ProductCard;