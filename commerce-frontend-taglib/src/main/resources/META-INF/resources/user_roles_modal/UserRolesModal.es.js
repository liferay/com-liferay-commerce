'use strict';

import {debounce} from 'metal-debounce';

import template from './UserRolesModal.soy';
import Component from 'metal-component';
import Soy, {Config} from 'metal-soy';

import 'clay-modal';

import './RoleInputItem.es';
import './RoleListItem.es';

class UserRolesModal extends Component {

	syncSelectedRoles() {
		const contentWrapper = this.element.querySelector('.autocomplete-input__content');
		this.element.querySelector('.autocomplete-input__box').focus();
		contentWrapper.scrollTo(0, contentWrapper.offsetHeight);
		return true;
	}

	_handleCloseModal(e) {
		e.preventDefault();
		this._isVisible = false;
	}

	syncQuery(e) {
		return this._filterRoles();
	}

	_handleFormSubmit(evt) {
		evt.preventDefault();

		if (this.filteredRoles.length) {
			this._toggleItem(this.filteredRoles[0]);
			this.query = '';
			return true;
		}
		return false;
	}

	_handleCheckBox(e) {
		console.log(e);
	}

	_handleInputBox(evt) {
		if (evt.keyCode === 8 && !this.query.length) {
			this.selectedRoles = this.selectedRoles.slice(0, -1);
			return false;
		}
		return this.query = evt.target.value;
	}

	_toggleItem(item) {
		if (!item.id) {
			this.query = '';
		}

		const hasRoleAlreadyBeenAdded = this.selectedRoles.reduce(
			(alreadyAdded, role) => alreadyAdded || role.id === item.id,
			false
		);

		this.selectedRoles =
			hasRoleAlreadyBeenAdded ?
				this.selectedRoles.filter((role) => role.id !== item.id) :
				[...this.selectedRoles, item];

		return this.selectedRoles;
	}

	_filterRoles() {
		return this.filteredRoles = this.roles.filter(role => role.name.toLowerCase().indexOf(this.query.toLowerCase()) > -1);
	}

	_updateRoles() {
		if (!this.selectedRoles.length) {
			return false;
		};

		console.log(this.selectedRoles);

		return this.emit(
			'updateRoles',
			this.selectedRoles
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

Soy.register(UserRolesModal, template);


const ROLE_SCHEMA = Config.shapeOf(
	{
		id: Config.oneOfType(
			[
				Config.number(),
				Config.string()
			]
		).required(),
		name: Config.string().required()
	}
);

UserRolesModal.STATE = {
	query: Config.string().value(''),
	spritemap: Config.string(),
	roles: Config.array(ROLE_SCHEMA).value([]),
	filteredRoles: Config.array(ROLE_SCHEMA).value([]),
	selectedRoles: Config.array(ROLE_SCHEMA).value([]),
	_isVisible: Config.bool().internal().value(false)
};

export {UserRolesModal};
export default UserRolesModal;
