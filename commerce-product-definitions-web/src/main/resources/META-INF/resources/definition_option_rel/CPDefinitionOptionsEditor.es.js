import './CPDefinitionOptionDetail.es';
import './CPDefinitionOptionList.es';
import './CPDefinitionOptionValuesEditor.es';
import './CPDefinitionOptionValueDetail.es';
import './CPDefinitionOptionValueList.es';

import Component from 'metal-component';
import {Config} from 'metal-state';
import Soy from 'metal-soy';

import templates from './CPDefinitionOptionsEditor.soy';

/**
 * CPDefinitionOptionsEditor
 *
 */

class CPDefinitionOptionsEditor extends Component {

	created() {
		this.updateState();
		this._handleKeyUpForModal = this._handleKeyUpForModal.bind(this);
	}

	_handleAddOption() {
		var instance = this;

		AUI().use(
			'liferay-item-selector-dialog',
			(A) => {
				var itemSelectorDialog = new A.LiferayItemSelectorDialog(
					{
						eventName: 'productOptionsSelectItem',
						on: {
							selectedItemChange: function(event) {
								var selectedItems = event.newVal;

								var formData = new FormData();

								formData.append(instance.namespace + 'cmd', 'add_multiple');
								formData.append(instance.namespace + 'cpDefinitionId', instance.cpDefinitionId);
								formData.append(instance.namespace + 'cpOptionIds', selectedItems);
								formData.append('p_auth', Liferay.authToken);

								fetch(
									instance.editProductDefinitionOptionRelURL,
									{
										body: formData,
										credentials: 'include',
										method: 'POST'
									}
								).then(
									response => response.json()
								).then(
									(jsonResponse) => {
										instance.cpDefinitionId = jsonResponse.cpDefinitionId.toString();

										instance.updateState();
									}
								);
							}
						},
						title: Liferay.Language.get('select-options-to-add'),
						url: instance.optionsItemSelectorURL
					}
				);

				itemSelectorDialog.open();
			}
		);
	}

	updateState() {
		this._updateCPDefinitionId();

		var url = new URL(this.cpDefinitionOptionsURL);

		url.searchParams.set('p_auth', window.Liferay.authToken);

		fetch(
			url,
			{
				credentials: 'include',
				method: 'GET'
			}
		).then(
			response => response.json()
		).then(
			(jsonResponse) => {
				this._cpDefinitionOptions = jsonResponse;

				if ((this._cpDefinitionOptions && this._cpDefinitionOptions.length > 0)) {
					if (!this._currentOption || this._currentOption == null) {
						this._currentOption = this._cpDefinitionOptions[0].cpDefinitionOptionRelId;
					}
				}
			}
		);
	}

	_handleOptionSelected(cpDefinitionOptionRelId) {
		this._showAddNewOption = false;
		this._currentOption = cpDefinitionOptionRelId;
	}

	_handleOptionSaved(event) {
		this.cpDefinitionId = event.cpDefinitionId.toString();

		this._currentOption = event.cpDefinitionOptionRelId;

		this.updateState();

		if (event.success) {
			this._showNotification(this.successMessage, 'success');
		}
		else {
			this._showNotification(event.message, 'danger');
		}
	}

	_handleOptionDeleted(event) {
		this.cpDefinitionId = event.cpDefinitionId.toString();

		this._currentOption = null;

		this.updateState();

		if (event.success) {
			this._showNotification(this.successMessage, 'success');
		}
		else {
			this._showNotification(event.message, 'danger');
		}
	}

	_handleCancelEditing(event) {
		this.cpDefinitionId = event.cpDefinitionId.toString();

		this._currentOption = null;

		this.updateState();
	}

	_handleKeyUpForModal(evt) {
		if (evt.code === 'Escape') {
			this._handleCloseValueEditor();
		}
	}

	_handleEditValues(cpOptionId) {
		this._currentOption = cpOptionId;
		this._showValues = true;

		document.addEventListener('keyup', this._handleKeyUpForModal);
	}

	_handleCloseValueEditor() {
		this.updateState();

		this._showValues = false;

		document.removeEventListener('keyup', this._handleKeyUpForModal);
	}

	_showNotification(message, type) {
		AUI().use(
			'liferay-notification',
			() => {
				new Liferay.Notification(
					{
						closeable: true,
						delay: {
							hide: 5000,
							show: 0
						},
						duration: 500,
						message: message,
						render: true,
						title: '',
						type: type
					}
				);
			}
		);
	}

	_updateCPDefinitionId() {
		let instance = this;

		var url = new URL(window.location.href);

		var urlCPDefinitionId = url.searchParams.get(instance.namespace + 'cpDefinitionId');

		if (urlCPDefinitionId != instance.cpDefinitionId) {
			var cpDefinitionId = Math.max(urlCPDefinitionId, instance.cpDefinitionId || 0);

			instance.cpDefinitionId = cpDefinitionId.toString();

			var cpDefinitionOptionsURL = new URL(instance.cpDefinitionOptionsURL);

			cpDefinitionOptionsURL.searchParams.set(instance.namespace + 'cpDefinitionId', cpDefinitionId);

			instance.cpDefinitionOptionsURL = cpDefinitionOptionsURL.href;

			var cpDefinitionOptionValueRelURL = new URL(instance.cpDefinitionOptionValueRelURL);

			cpDefinitionOptionValueRelURL.searchParams.set(instance.namespace + 'cpDefinitionId', cpDefinitionId);

			instance.cpDefinitionOptionValueRelURL = cpDefinitionOptionValueRelURL.href;

			var cpDefinitionOptionValueRelsURL = new URL(instance.cpDefinitionOptionValueRelsURL);

			cpDefinitionOptionValueRelsURL.searchParams.set(instance.namespace + 'cpDefinitionId', cpDefinitionId);

			instance.cpDefinitionOptionValueRelsURL = cpDefinitionOptionValueRelsURL.href;

			if (urlCPDefinitionId != cpDefinitionId) {
				url.searchParams.set(instance.namespace + 'cpDefinitionId', cpDefinitionId);

				window.history.pushState({}, '', url);
			}
		}
	}
}

/**
 * State definition.
 * @type {!Object}
 * @static
 */

CPDefinitionOptionsEditor.STATE = {
	cpDefinitionId: Config.string().required(),
	cpDefinitionOptionsURL: Config.string().required(),
	cpDefinitionOptionValueRelsURL: Config.string().required(),
	cpDefinitionOptionValueRelURL: Config.string().required(),
	editProductDefinitionOptionRelURL: Config.string().required(),
	namespace: Config.string().required(),
	optionsItemSelectorURL: Config.string().required(),
	optionURL: Config.string().required(),
	pathThemeImages: Config.string().required(),
	successMessage: Config.string().required(),
	_cpDefinitionOptions: Config.array().value([])
};

// Register component

Soy.register(CPDefinitionOptionsEditor, templates);

export default CPDefinitionOptionsEditor;