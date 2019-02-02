'use strict';

import template from './AddAddressModal.soy';
import Component from 'metal-component';
import Soy, {Config} from 'metal-soy';

import 'clay-modal';

import '../input_utils/CommerceInputText';

class AddAddressModal extends Component {

	attached() {
		return this._fetchCountries();
	}

	close() {
		return this._isVisible = false;
	}

	open() {
		return this._isVisible = true;
	}

	sync_addressType() {
		return this._fetchCountries();
	}

	sync_formData() {
		return this._validateForms();
	}

	toggle() {
		return this._isVisible = !this._isVisible;
	}

	_addAddress(e) {
		return this.emit(
			'addAddressModalSave',
			Object.assign(
				{}, this._formData,
				{addressType: this._addressType})
		);
	}

	_fetchCountries() {
		return fetch(
			(this._addressType === 'shipping' ? this.shippingCountriesAPI : this.billingCountriesAPI) +
				themeDisplay.getScopeGroupId(),
			{
				method: 'GET'
			}
		)
			.then(
				response => response.json()
			)
			.then(
				countries => {
					return this._countries = countries;
				}
			);
	}

	_fetchRegions() {
		return fetch(
			this.regionsAPI + this._formData.country,
			{
				method: 'GET'
			}
		)
			.then(
				response => response.json()
			)
			.then(
				regions => {
					return this._regions = regions;
				}
			);
	}

	_handleCloseModal(e) {
		e.preventDefault();

		this._isVisible = false;
	}

	_handleFirstDotClick(e) {
		e.preventDefault();
		this._stage = 1;
		return this._stage;
	}

	_handleFormSubmit(e) {
		e.preventDefault();

		const isFormValid = e.target.checkValidity();

		if (isFormValid) {
			this._addAddress(e);
		}
	}

	_handleTypeChange(evt) {
		this.addressType = evt.target.value;
		return this.addressType;
	}

	_handleNextButton(e) {
		e.preventDefault();
		this._firstFormValid = this.refs.modal.refs.firstForm.checkValidity();
		if (this._firstFormValid) {
			this._stage = 2;
		}
		return e;
	}

	_handleCloseModal(e) {
		e.preventDefault();
		this._modalVisible = false;
		return e;
	}

	_handleSelectBox(e) {
		const value = e.target.value;
		if (e.target.name === 'commerceCountry') {
			this._formData = Object.assign(
				{},
				this._formData,
				{
					country: value
				}
			);

			this._fetchRegions();
		}
		else {
			this._formData = Object.assign(
				{},
				this._formData,
				{
					region: value
				}
			);
		}

		return value;
	}

	_handleTypeChange(evt) {
		return this._addressType = evt.target.value;
	}

	_validateForms() {
		const firstFormValid = !!(
			this._formData.address && this._formData.address.length &&
			this._formData.city && this._formData.city.length &&
			this._formData.zipCode && this._formData.zipCode.length &&
			this._formData.country && this._formData.country.length &&
			this._formData.region && this._formData.region.length
		);
		this._firstFormValid = firstFormValid;

		const secondFormValid = !!(
			this._formData.referent && this._formData.referent.length &&
			this._formData.telephone && this._formData.telephone.length
		);
		this._secondFormValid = secondFormValid;

		return this._firstFormValid && this._secondFormValid;
	}

	_fetchCountries() {
		return fetch(
			(this.addressType === 'shipping' ?
				this.shippingCountriesAPI :
				this.billingCountriesAPI
			) + themeDisplay.getScopeGroupId(),
			{
				method: 'GET'
			}
		)
			.then(
				response => response.json()
			)
			.then(
				countries => {
					this._countries = countries;
					return this._countries;
				}
			);
	}

	_fetchRegions() {
		return fetch(
			this.regionsAPI + this._formData.country,
			{
				method: 'GET'
			}
		)
			.then(
				response => response.json()
			)
			.then(
				regions => {
					this._regions = regions;
					return this._regions;
				}
			);
	}

	_handleFormSubmit(e) {
		e.preventDefault();
		const formValid = e.target.checkValidity();
		if (formValid) {
			this._addAddress(e);
		}
		return e;
	}

	_addAddress(e) {
		return this.emit(
			'addAddressModalSave',
			Object.assign(
				{},
				this._formData,
				{
					addressType: this.addressType
				}
			)
		);
	}

	toggle() {
		this._modalVisible = !this._modalVisible;
		return this._modalVisible;
	}

	open() {
		this._modalVisible = true;
		return this._modalVisible;
	}

	close() {
		this._modalVisible = false;
		return this._modalVisible;
	}
}

Soy.register(AddAddressModal, template);

AddAddressModal.STATE = {
	billingCountriesAPI: Config.string().required(),
	regionsAPI: Config.string().required(),
	shippingCountriesAPI: Config.string().required(),
	spritemap: Config.string(),
	_addressType: Config.oneOf(
		[
			'billing',
			'shipping'
		]
	).internal(),
	_countries: Config.array(
		Config.shapeOf(
			{
				id: Config.number().required(),
				name: Config.string().required()
			}
		)
	).value([]),
	_firstFormValid: Config.bool().value(false),
	_formData: Config.shapeOf(
		{
			address: Config.string(),
			city: Config.string(),
			country: Config.oneOfType(
				[
					Config.string(),
					Config.number()
				]
			),
			referent: Config.string(),
			region: Config.oneOfType(
				[
					Config.string(),
					Config.number()
				]
			),
			telephone: Config.string(),
			zipCode: Config.string()
		}
	).value(
		{
			address: null,
			city: null,
			country: null,
			referent: null,
			region: null,
			telephone: null,
			zipCode: null
		}
	),
	_modalVisible: Config.bool().internal().value(false),
	_regions: Config.array(
		Config.shapeOf(
			{
				id: Config.number().required(),
				name: Config.string().required()
			}
		)
	).value([]),
	_secondFormValid: Config.bool().value(false),
	_stage: Config.number(
		Config.oneOf(
			[
				1,
				2
			]
		)
	).value(1)
};

export {AddAddressModal};
export default AddAddressModal;