'use strict';

import template from './ProductCard.soy';
import Component from 'metal-component';
import Soy, {Config} from 'metal-soy';

function liferayNavigation(url) {
	if (Liferay.SPA) {
		Liferay.SPA.app.navigate(url);
	}
	else {
		window.location.href = url;
	}
}

class ProductCard extends Component {

	_handleCardKeypress(e) {
		if (e.key === 'Enter' && e.target === this.element) {
			liferayNavigation(this.element.dataset.href);
		}

		if (['A', 'a'].includes(e.key)) {
			e.preventDefault();
			let next = this.element.closest('.minium-product-tiles__item');
			if (e.target !== this.element) {
				next = e.shiftKey ? next.previousElementSibling : next.nextElementSibling;
			}
			if (next) {
				setTimeout(() => next.querySelector('.commerce-button').focus(), 100);
			}
		}
	}

	_handleCheckboxCompareUpdate(newCompareState) {
		this.compareState = newCompareState;
		return this.compareState;
	}

	_handleRemoveProduct() {
		const formData = new FormData();

		formData.append(this.compareContentNamespace + 'cpDefinitionId', this.productId);
		formData.append(this.compareContentNamespace + this.productId + 'Compare', false);

		return fetch(
			this.editCompareProductActionURL,
			{
				body: formData,
				credentials: 'include',
				method: 'post'
			}
		)
			.then(
				() => {
					liferayNavigation(window.location.href);
					return Liferay.SPA;
				}
			);
	}
}

Soy.register(ProductCard, template);

ProductCard.STATE = {
	accountId: Config.oneOfType(
		[
			Config.string(),
			Config.number()
		]
	).value(null),
	availability: Config.string()
		.oneOf(
			[
				'inStock',
				'available',
				'notAvailable'
			]
		)
		.value('inStock'),
	cartAPI: Config.string(),
	categories: Config.array(
		Config.shapeOf(
			{
				link: Config.string().required(),
				name: Config.string().required()
			}
		)
	),
	compareContentNamespace: Config.string(),
	compareState: Config.shapeOf(
		{
			compareAvailable: Config.bool(),
			inCompare: Config.bool()
		}
	).value(
		{
			checkboxVisible: true,
			compareAvailable: true,
			inCompare: false
		}
	),
	deleteButtonVisible: Config.bool(),
	description: Config.string(),
	detailsLink: Config.string(),
	editCompareProductActionURL: Config.string(),
	minQuantity: Config.number(),
	name: Config.string().required(),
	orderId: Config.oneOfType(
		[
			Config.string(),
			Config.number()
		]
	),
	pictureUrl: Config.string(),
	price: Config.shapeOf(
		{
			formattedPrice: Config.string().required(),
			formattedPromoPrice: Config.string()
		}
	),
	productId: Config.oneOfType(
		[
			Config.string(),
			Config.number()
		]
	).required(),
	settings: Config.shapeOf(
		{
			allowedOptions: Config.array(Config.number()),
			maxQuantity: Config.number(),
			minQuantity: Config.number(),
			multipleQuantity: Config.number()
		}
	).value({}),
	sku: Config.string(),
	skuId: Config.oneOfType(
		[
			Config.string(),
			Config.number()
		]
	),
	spritemap: Config.string()

};

export {ProductCard};
export default ProductCard;