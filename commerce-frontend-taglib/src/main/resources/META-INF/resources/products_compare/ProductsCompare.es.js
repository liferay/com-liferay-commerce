'use strict';

import template from './ProductsCompare.soy';
import Component from 'metal-component';
import Soy, {Config} from 'metal-soy';

import './ProductsCompareItem.es';

class ProductsCompare extends Component {

	created() {
		window.Liferay.on('toggleProductToCompare', this._handleToggleProductToCompare, this);
	}

	detached() {
		window.Liferay.detach('toggleProductToCompare', this._handleToggleProductToCompare, this);
	}

	_handleToggleProductToCompare(data) {
		const toggledProduct = {
			id: data.id,
			thumbnail: data.thumbnail
		};

		const included = this.products.reduce(
			(acc, el) => {
				return acc || el.id === data.id;
			},
			false
		);

		return included ?
			this._handleRemoveProduct(toggledProduct) :
			this._handleAddProduct(toggledProduct);
	}

	_addProduct(product) {
		this.products = this.products.concat(
			{
				id: product.id,
				thumbnail: product.thumbnail,
				visibility: 'hidden'
			}
		);

		return this._updateProductVisibility(product.id, 'visible');
	}

	_removeProduct(product) {
		this._updateProductVisibility(product.id, 'hidden');
		this._toogleRemoteStatus(product.id, false);
		return new Promise(
			(resolve) => {
				return setTimeout(
					() => {
						this.products = this.products.filter(
							(el) => el.id !== product.id
						);
						Liferay.fire('productRemovedFromCompare', product);
						return resolve(this.products);
					},
					500
				);
			}
		);
	}

	_toogleRemoteStatus(id, toogle) {
		const formData = new FormData();

		formData.append(this.portletNamespace + 'cpDefinitionId', id);
		formData.append(this.portletNamespace + id + 'Compare', toogle);
		formData.append('p_auth', Liferay.authToken);

		return fetch(
			this.editCompareProductActionURL,
			{
				body: formData,
				credentials: 'include',
				method: 'post'
			}
		);
	}

	_handleAddProduct(product) {
		return this._addProduct(product)
			.then(
				() => this._toogleRemoteStatus(product.id, true)
			)
			.then(
				() => this._updateCompareGlobalState()
			);
	}

	_handleRemoveProduct(product) {
		return this._removeProduct(product)
			.then(
				() => this._updateCompareGlobalState()
			);
	}

	_updateCompareGlobalState() {
		return this.products.length < this.limit ?
			Liferay.fire('compareIsAvailable') :
			Liferay.fire('compareIsUnavailable');
	}

	_updateProductVisibility(id, toState = 'visible') {
		return new Promise(
			(resolve) => {
				setTimeout(
					() => {
						this.products = this.products.map(
							(el) => {
								return el.id === id ?
									{
										id: el.id,
										thumbnail: el.thumbnail,
										visibility: toState === 'visible' ? 'showing' : 'hiding'
									} :
									el;
							}
						);

						return this.products;
					},
					100
				);
				return setTimeout(
					() => {
						this.products = this.products.map(
							(el) => {
								return el.id === id ?
									{
										id: el.id,
										thumbnail: el.thumbnail,
										visibility: toState
									} :
									el;
							}
						);
						return resolve(this.products);
					},
					400
				);
			}
		);
	}

	_submitCompare() {
		if (Liferay.SPA) {
			Liferay.SPA.app.navigate(this.compareProductsURL);
		}
		else {
			window.location.href = this.compareProductsURL;
		}
	}

}

Soy.register(ProductsCompare, template);

ProductsCompare.STATE = {
	compareProductsURL: Config.string().required(),
	editCompareProductActionURL: Config.string(),
	limit: Config.number().required(),
	portletNamespace: Config.string().required(),
	products: Config.array(
		Config.object()
	).value([]),
	spritemap: Config.string()
};

export {ProductsCompare};
export default ProductsCompare;
