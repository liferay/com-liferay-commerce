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

		return this._stage = 1;
	}

	_handleFormSubmit(e) {
		e.preventDefault();

		const isFormValid = e.target.checkValidity();

		if (isFormValid) {
			this._addAddress(e);
		}
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

	_handleNextButton(e) {
		e.preventDefault();

		this._isFirstFormValid = this.refs.modal.refs.firstForm.checkValidity();

		if (this._isFirstFormValid) {
			return this._stage = 2;
		}

		return false;
	}

	_handleSecondDotClick(e) {
		return this._handleNextButton(e);
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

	_handleTypeChange(evt) {
		return this._addressType = evt.target.value;
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
				this._formData.referent && this._formData.referent.length
			);

		this._isSecondFormValid = isSecondFormValid;

		return this._isFirstFormValid && this._isSecondFormValid;
	}

};

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
	).internal().value('shipping'),
	_countries: Config.array(
		Config.shapeOf(
			{
				id: Config.number().required(),
				name: Config.string().required()
			}
		)
	).value([]),
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
			telephone: null
		}
	),
	_isFirstFormValid: Config.bool().value(false),
	_isLoading: Config.bool().internal().value(false),
	_isSecondFormValid: Config.bool().value(false),
	_isVisible: Config.bool().internal().value(false),
	_regions: Config.array(
		Config.shapeOf(
			{
				id: Config.number().required(),
				name: Config.string().required()
			}
		)
	).value([]),
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