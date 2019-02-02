'use strict';

import template from './UserRolesModal.soy';
import Component from 'metal-component';
import Soy, {Config} from 'metal-soy';

import 'clay-modal';

import './RoleInputItem.es';
import './RoleListItem.es';

class UserRolesModal extends Component {

	close() {
		return this._isVisible = false;
	}

	open() {
		return this._isVisible = true;
	}

	syncQuery(e) {
		return this._filterRoles();
	}

	syncSelectedRoles() {
		const contentWrapper = this.element.querySelector('.autocomplete-input__content');

		this.element.querySelector('.autocomplete-input__box').focus();
		return contentWrapper.scrollTo(0, contentWrapper.offsetHeight);
	}

	_handleCloseModal(e) {
		e.preventDefault();
		this._modalVisible = false;
	}

	syncQuery() {
		return this._filterRoles();
	}

	_handleFormSubmit(evt) {
		evt.preventDefault();
		let result = false;

		if (this.filteredRoles.length) {
			this._toggleItem(this.filteredRoles[0]);

			this.query = '';
			result = true;
		}

		return result;
	}

	_handleInputBox(evt) {
		if (evt.keyCode === 8 && !this.query.length) {
			this.selectedRoles = this.selectedRoles.slice(0, -1);
		}
		else {
			this.query = evt.target.value;
		}
		return evt;
	}

	_toggleItem(item) {
		if (!item.id) {
			this.query = '';
		}

		const hasRoleAlreadyBeenAdded = this.selectedRoles.reduce(
			(alreadyAdded, role) => alreadyAdded || role.id === item.id,
			false
		);

		this.selectedRoles = hasRoleAlreadyBeenAdded ?
			this.selectedRoles.filter((role) => role.id !== item.id) :
			[...this.selectedRoles, item];

		return this.selectedRoles;
	}

	_filterRoles() {
		this.filteredRoles = this.roles.filter(role => role.name.toLowerCase().indexOf(this.query.toLowerCase()) > -1);
		return this.filteredRoles;
	}

	_updateRoles() {
		if (this.selectedRoles.length) {
			this.emit(
				'updateRoles',
				this.selectedRoles
			);
		}
		return this.selectedRoles;
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
	filteredRoles: Config.array(ROLE_SCHEMA).value([]),
	query: Config.string().value(''),
	roles: Config.array(ROLE_SCHEMA).value([]),
	selectedRoles: Config.array(ROLE_SCHEMA).value([]),
	spritemap: Config.string(),
	_modalVisible: Config.bool().internal().value(false)
};

export {UserRolesModal};
export default UserRolesModal;