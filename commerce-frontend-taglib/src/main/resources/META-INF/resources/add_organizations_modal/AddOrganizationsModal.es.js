'use strict';

import {debounce} from 'metal-debounce';

import template from './AddOrganizationsModal.soy';
import Component from 'metal-component';
import Soy, {Config} from 'metal-soy';

import 'clay-modal';

import './OrganizationInputItem.es';
import './OrganizationListItem.es';

class AddOrganizationModal extends Component {

	created() {
		this._debouncedFetchOrganizations = debounce(this._fetchOrganizations.bind(this), 300);
	}

	attached() {
		this._fetchOrganizations();
	}

	syncAddedOrganizations() {
		const contentWrapper = this.element.querySelector('.autocomplete-input__content');
		this.element.querySelector('.autocomplete-input__box').focus();
		contentWrapper.scrollTo(0, contentWrapper.offsetHeight);
		return true;
	}

	_handleCloseModal(e) {
		e.preventDefault();
		this._isVisible = false;
	}

	syncQuery() {
		this._isLoading = true;
		return this._debouncedFetchOrganizations();
	}

	_handleFormSubmit(evt) {
		evt.preventDefault();

		if (this.organizations.length) {
			this._toggleItem(this.organizations[0]);
			this.query = '';
			return true;
		}
		return false;
	}

	_handleInputBox(evt) {
		if (evt.keyCode === 8 && !this.query.length) {
			this.selectedOrganizations = this.selectedOrganizations.slice(0, -1);
			return false;
		}
		return this.query = evt.target.value;
	}

	_toggleItem(organizationToBeToggled) {
		if (!organizationToBeToggled.id) {
			this.query = '';
		}

		const hasOrganizationAlreadyBeenAdded = this.selectedOrganizations.reduce(
			(alreadyAdded, organization) => alreadyAdded || organization.id === organizationToBeToggled.id,
			false
		);

		this.selectedOrganizations =
			hasOrganizationAlreadyBeenAdded ?
				this.selectedOrganizations.filter((organization) => organization.id !== organizationToBeToggled.id) :
				[...this.selectedOrganizations, organizationToBeToggled];

		return this.selectedOrganizations;
	}

	_fetchOrganizations() {
		return fetch(
			this.organizationsAPI + '?q=' + this.query,
			{
				method: 'GET'
			}
		)
			.then(
				response => response.json()
			)
			.then(
				response => {
					this._isLoading = false;

					return this.organizations = this.addColorToOrganizations(response.organizations);
				}
			);
	}

	addColorToOrganizations(organizations) {
		return organizations.map(organization => {
			return Object.assign(
				{
					colorId: Math.floor(Math.random() * 6) + 1
				},
				organization,
			);
		});
	}

	_addOrganizations() {
		if (!this.selectedOrganizations.length) {
			return false;
		};

		return this.emit(
			'addOrganization',
			this.selectedOrganizations
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

Soy.register(AddOrganizationModal, template);

const ORGANIZATION_SCHEMA = Config.shapeOf(
	{
		id: Config.oneOfType(
			[
				Config.number(),
				Config.string()
			]
		).required(),
		name: Config.string().required(),
		colorId: Config.number()
	}
);

AddOrganizationModal.STATE = {
	organizationsAPI: Config.string().value(''),
	query: Config.string().value(''),
	spritemap: Config.string(),
	organizations: Config.array(ORGANIZATION_SCHEMA).value([]),
	selectedOrganizations: Config.array(ORGANIZATION_SCHEMA).value([]),
	_isVisible: Config.bool().internal().value(false),
	_isLoading: Config.bool().internal().value(false)
};

export {AddOrganizationModal};
export default AddOrganizationModal;
