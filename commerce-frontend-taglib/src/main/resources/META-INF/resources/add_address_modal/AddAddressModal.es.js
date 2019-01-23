'use strict';

import template from './AddAddressModal.soy';
import Component from 'metal-component';
import Soy, {Config} from 'metal-soy';

import 'clay-modal';

import '../input_utils/CommerceInputText';

class AddAddressModal extends Component {


	attached() {
		this._fetchCountries();
	}

	sync_addressType() {
		return this._fetchCountries();
	}

	sync_formData() {
		return this._validateForms();
	}

	_handleFirstDotClick(e) {
		e.preventDefault();
		return this._stage = 1;
	}

	_handleSecondDotClick(e) {
		return this._handleNextButton(e);
	}

	_handleTypeChange(evt) {
		return this.addressType = evt.target.value;
	}

	_handleNextButton(e) {
		e.preventDefault();
		this._isFirstFormValid = this.refs.modal.refs.firstForm.checkValidity();
		if (this._isFirstFormValid) {
			return this._stage = 2;
		}
		return false;
	}

	_handleCloseModal(e) {
		e.preventDefault();
		this._isVisible = false;
	}

	_handleSelectBox(evt) {
		const value = evt.target.value;
		if (evt.target.name === 'commerceCountry') {
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

	_validateForms(form) {
		const isFirstFormValid =
			!!(
				this._formData.address && this._formData.address.length &&
				this._formData.city && this._formData.city.length &&
				this._formData.zipCode && this._formData.zipCode.length &&
				this._formData.country && this._formData.country.length &&
				this._formData.region && this._formData.region.length
			);
		this._isFirstFormValid = isFirstFormValid;

		const isSecondFormValid =
			!!(
				this._formData.referent && this._formData.referent.length &&
				this._formData.email && this._formData.email.length &&
				this._formData.telephone && this._formData.telephone.length
			);
		this._isSecondFormValid = isSecondFormValid;

		return this._isFirstFormValid && this._isSecondFormValid;
	}

	_fetchCountries() {
		return fetch(
			(this.addressType === 'shipping' ? this.shippingCountriesAPI : this.billingCountriesAPI) +
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

	_handleFormSubmit(e) {
		e.preventDefault();
		const isFormValid = e.target.checkValidity();
		if (isFormValid) {
			this._addAddress(e);
		}
	}

	_addAddress(e) {
		return this.emit(
			'addAddressModalSave',
			this._formData
		);
	}

	toggle() {
		return this._isVisible = !this._isVisible;
	}

	open() {
		return this._isVisible = true;
	}

	close() {
		return this._isVisible = false;
	}
};

Soy.register(AddAddressModal, template);

AddAddressModal.STATE = {
	_stage: Config.number(
		Config.oneOf(
			[
				1,
				2
			]
		)
	).value(1),
	addressType: Config.oneOf(
		[
			'billing',
			'shipping'
		]
	).internal(),
	_isFirstFormValid: Config.bool().value(false),
	_isSecondFormValid: Config.bool().value(false),
	_formData: Config.shapeOf(
		{
			address: Config.string(),
			city: Config.string(),
			zipCode: Config.string(),
			country: Config.oneOfType(
				[
					Config.string(),
					Config.number()
				]
			),
			region: Config.oneOfType(
				[
					Config.string(),
					Config.number()
				]
			),
			referent: Config.string(),
			email: Config.string(),
			telephone: Config.string()
		}
	).value(
		{
			address: null,
			city: null,
			region: null,
			zipCode: null,
			country: null,
			referent: null,
			email: null,
			telephone: null
		}
	),
	shippingCountriesAPI: Config.string().required(),
	billingCountriesAPI: Config.string().required(),
	regionsAPI: Config.string().required(),
	_countries: Config.array(
		Config.shapeOf(
			{
				id: Config.number().required(),
				name: Config.string().required()
			}
		)
	).value([]),
	_regions: Config.array(
		Config.shapeOf(
			{
				id: Config.number().required(),
				name: Config.string().required()
			}
		)
	).value([]),
	spritemap: Config.string(),
	_isVisible: Config.bool().internal().value(false),
	_isLoading: Config.bool().internal().value(false)
};

export {AddAddressModal};
export default AddAddressModal;
