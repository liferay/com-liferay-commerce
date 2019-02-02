'use strict';

import {debounce} from 'metal-debounce';

import template from './AddOrganizationsModal.soy';
import Component from 'metal-component';
import Soy, {Config} from 'metal-soy';

import 'clay-modal';

import './OrganizationInputItem.es';
import './OrganizationListItem.es';

class AddOrganizationModal extends Component {

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

	attached() {
		return this._fetchOrganizations();
	}

	close() {
		return this._isVisible = false;
	}

	created() {
		this._debouncedFetchOrganizations = debounce(this._fetchOrganizations.bind(this), 300);
	}

	open() {
		return this._isVisible = true;
	}

	syncAddedOrganizations() {
		const contentWrapper = this.element.querySelector('.autocomplete-input__content');

		this.element.querySelector('.autocomplete-input__box').focus();
		return contentWrapper.scrollTo(0, contentWrapper.offsetHeight);
	}

	_handleCloseModal(e) {
		e.preventDefault();
		this._modalVisible = false;
	}

	syncQuery() {
		this._loading = true;
		return this._debouncedFetchOrganizations();
	}

	_handleFormSubmit(e) {
		e.preventDefault();

		if (this.organizations.length) {
			this._toggleItem(this.organizations[0]);

			this.query = '';
		}

		return e;
	}

	_handleInputBox(e) {
		if (e.keyCode === 8 && !this.query.length) {
			this.selectedOrganizations = this.selectedOrganizations.slice(0, -1);

			return false;
		}
		this.query = e.target.value;
		return this.query;
	}

	_toggleItem(organizationToBeToggled) {
		if (!organizationToBeToggled.id) {
			this.query = '';
		}

		const hasOrganizationAlreadyBeenAdded = this.selectedOrganizations.reduce(
			(alreadyAdded, organization) => alreadyAdded || organization.id === organizationToBeToggled.id,
			false
		);

		this.selectedOrganizations = hasOrganizationAlreadyBeenAdded ?
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
					this._loading = false;
					this.organizations = this.addColorToOrganizations(response.organizations);
					return this.organizations;
				}
			);
	}

	addColorToOrganizations(organizations) {
		return organizations.map(
			organization => Object.assign(
				{
					colorId: Math.floor(Math.random() * 6) + 1
				},
				organization,
			)
		);
	}

	_addOrganizations() {
		if (!this.selectedOrganizations.length) {
			return false;
		}

		return this.emit(
			'addOrganization',
			this.selectedOrganizations
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

Soy.register(AddOrganizationModal, template);

const ORGANIZATION_SCHEMA = Config.shapeOf(
	{
		colorId: Config.number(),
		id: Config.oneOfType(
			[
				Config.number(),
				Config.string()
			]
		).required(),
		name: Config.string().required()
	}
);

AddOrganizationModal.STATE = {
	organizations: Config.array(ORGANIZATION_SCHEMA).value([]),
	organizationsAPI: Config.string().value(''),
	query: Config.string().value(''),
	selectedOrganizations: Config.array(ORGANIZATION_SCHEMA).value([]),
	spritemap: Config.string(),
	_loading: Config.bool().internal().value(false),
	_modalVisible: Config.bool().internal().value(false)
};

export {AddOrganizationModal};
export default AddOrganizationModal;