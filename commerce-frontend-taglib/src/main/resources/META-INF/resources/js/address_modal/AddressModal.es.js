'use strict';

import template from './AddressModal.soy';
import Component from 'metal-component';
import Soy, {Config} from 'metal-soy';

import 'clay-modal';

import '../input_utils/CommerceInputText';

class AddressModal extends Component {

	attached() {
		return this._fetchCountries();
	}

	sync_formData() {
		return this._validateForms();
	}

	_handleFirstDotClick(e) {
		e.preventDefault();
		this._stage = 1;
		return this._stage;
	}

	_handleSecondDotClick(e) {
		return this._handleNextButton(e);
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

			const country = this._countries.filter((country) => country.id == value);

			if (country.length === 1) {
				this._isBillingAllowed = country[0].billingAllowed;
				this._isShippingAllowed = country[0].shippingAllowed;

				this._fetchRegions();
			}
			else {
				this._regions = [];
			}
		}
		else if (e.target.name === 'addressType') {
			this._formData = Object.assign(
				{},
				this._formData,
				{
					addressType: value
				}
			);
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

	_handleInputBox(evt) {
		this._formData = Object.assign(
			{},
			this._formData,
			{
				[evt.target.name]: evt.target.value
			}
		);
		return evt.target.value;
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

	fetchExistingAddress(id) {
		fetch(
			'/o/commerce-ui/address/' + id + '?p_auth=' + Liferay.authToken,
			{
				method: 'GET'
			}
		).then(
			response => response.json()
		).then(
			(jsonResponse) => {
				const data = JSON.parse(jsonResponse);

				this._formData = Object.assign(
					{},
					this._formData,
					{
						address: data.street1,
						city: data.city,
						country: data.commerceCountryId,
						addressType: data.type,
						id: id,
						region: data.commerceRegionId,
						referent: data.name,
						telephone: data.phoneNumber,
						zipCode: data.zip
					}
				);

				this._fetchRegions();
			}
		);
	}

	_fetchCountries() {
		return fetch(
			this.countriesAPI,
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
			this.regionsAPI + this._formData.country + '?p_auth=' + Liferay.authToken,
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
		if (this._firstFormValid && this._secondFormValid) {
			this._addAddress(e);
		}
		return e;
	}

	_addAddress(e) {
		return this.emit('addressModalSave', this._formData);
	}

	resetForm() {
		this._formData = {
			address: null,
			addressType: 2,
			city: null,
			country: null,
			id: null,
			referent: null,
			region: null,
			telephone: null,
			zipCode: null
		};

		this._stage = 1;
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

Soy.register(AddressModal, template);

AddressModal.STATE = {
	countriesAPI: Config.string().required(),
	regionsAPI: Config.string().required(),
	spritemap: Config.string(),
	_isBillingAllowed: Config.bool().value(true),
	_isShippingAllowed: Config.bool().value(true),
	_countries: Config.array(
		Config.shapeOf(
			{
				id: Config.number().required(),
				billingAllowed: Config.bool().required(),
				name: Config.string().required(),
				shippingAllowed: Config.bool().required()
			}
		)
	).value([]),
	_firstFormValid: Config.bool().value(false),
	_formData: Config.shapeOf(
		{
			address: Config.string(),
			addressType: Config.oneOfType(
				[
					Config.string(),
					Config.number()
				]
			),
			city: Config.string(),
			country: Config.oneOfType(
				[
					Config.string(),
					Config.number()
				]
			),
			id: Config.oneOfType(
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
			addressType: 2,
			city: null,
			country: null,
			id: null,
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

export {AddressModal};
export default AddressModal;