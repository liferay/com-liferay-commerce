'use strict';

import template from './AddAddressModal.soy';
import Component from 'metal-component';
import Soy, {Config} from 'metal-soy';

import 'clay-modal';

import '../input_utils/CommerceInputText';

class AddAddressModal extends Component {


	rendered() {
		this._fetchCountries();
	}

	_handleFirstDotClick() {
		return this.stage = 1;
	}

	_handleSecondDotClick() {
		return this.stage = 2;
	}

	_handleNextButton(e) {
		e.preventDefault();

		if (this.noErrors) {
			return this.stage = 2;
		}
	}

	_handleCloseModal(e) {
		e.preventDefault();
		this._isVisible = false;
	}

	syncQuery() {
		this._isLoading = true;
		return this._debouncedFetchUser();
	}

	_handleFormSubmit(evt) {
		evt.preventDefault();
		if (this.query.match(EMAIL_REGEX)) {
			this.addedUsers = [
				...this.addedUsers,
				{
					email: this.query
				}
			];
			this.query = '';
			return true;
		}
		return false;
	}

	_handleInputBox(evt) {
		console.log(evt.target);
		if (evt.keyCode === 8 && !this.query.length) {
			this.addedUsers = this.addedUsers.slice(0, -1);
			return false;
		}
		return this.query = evt.target.value;
	}

	_fetchCountries() {
		return fetch(
			this.countriesAPI,
			{
				method: 'POST',
				body: {
					commerceCountryId: countryId,
					active: true
				}
			}
		)
			.then(
				response => response.json()
			)
			.then(
				response => {
					console.log(response);
					this._isLoading = false;
					return this.users = response.users;
				}
			);
	}

	_fetchShipingCountries() {
		return fetch(
			this.shippingCountriesAPI + themeDisplay.getScopeGroupId(),
			{
				method: 'GET'
			}
		)
			.then(
				response => response.json()
			)
			.then(
				response => {
					console.log(response);
					this._isLoading = false;
					return this.users = response.users;
				}
			);
	}

	_fetchRegions(countryId) {
		return fetch(
			this.regionsAPI + countryId,
			{
				method: 'GET'
			}
		)
			.then(
				response => response.json()
			)
			.then(
				response => {
					console.log(response);
					this._isLoading = false;
					return this.users = response.users;
				}
			);
	}

	_handleAddAddress(e) {
		e.preventDefault();

		return this.emit(
			'AddAddressModalSave',
			data
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
	stage: Config.number(
		Config.oneOf(
			[
				1,
				2
			]
		)
	).value(1),
	formData: Config.shapeOf(
		{
			address: Config.string(),
			city: Config.string(),
			state: Config.string(),
			zipCode: Config.string(),
			country: Config.string(),
			referent: Config.string(),
			email: Config.string(),
			telephone: Config.string()
		}
	).value(
		{
			address: '',
			city: '',
			state: '',
			zipCode: '',
			country: '',
			referent: '',
			email: '',
			telephone: ''
		}
	),
	shippingCountriesAPI: Config.string().required(),
	billingCountriesAPI: Config.string().required(),
	regionsAPI: Config.string().required(),
	countries: Config.array().value([]),
	regions: Config.array().value([]),
	spritemap: Config.string(),
	_isVisible: Config.bool().internal().value(false),
	_isLoading: Config.bool().internal().value(false)
};

export {AddAddressModal};
export default AddAddressModal;
