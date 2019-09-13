import Component from 'metal-component';
import {Config} from 'metal-state';
import Soy from 'metal-soy';
import {globalEval} from 'metal-dom';

import templates from './CPDefinitionOptionValueDetail.soy';

/**
 * CPDefinitionOptionValueDetail
 *
 */

class CPDefinitionOptionValueDetail extends Component {

	constructor(opt_config, opt_parentElement) {
		super(opt_config, opt_parentElement);

		this.on('cpDefinitionOptionValueRelIdChanged', this._handleCPDefinitionOptionValueChange);
	}

	rendered() {
		this.loadOptionValueDetail(this.cpDefinitionOptionValueRelId);
	}

	loadOptionValueDetail(cpDefinitionOptionValueRelId) {
		var instance = this;

		let optionValueDetail = this.element.querySelector('.option-value-detail');

		var url = new URL(this.cpDefinitionOptionValueRelURL);

		url.searchParams.append(this.namespace + 'cpDefinitionId', this.cpDefinitionId);
		url.searchParams.append(this.namespace + 'cpDefinitionOptionValueRelId', cpDefinitionOptionValueRelId);
		url.searchParams.set('p_auth', Liferay.authToken);

		fetch(
			url,
			{
				credentials: 'include',
				method: 'GET'
			}
		).then(
			response => response.text()
		).then(
			(text) => {
				optionValueDetail.innerHTML = text;

				globalEval.runScriptsInElement(optionValueDetail);

				var name = optionValueDetail.querySelector('#' + instance.namespace + 'optionValueName');

				if (name) {
					name.addEventListener(
						'keyup',
						(event) => {
							var target = event.target;

							instance.emit('nameChange', target.value);
						}
					);
				}
			}
		);
	}

	_handleCPDefinitionOptionValueChange(event) {
		this.cpDefinitionId = event.cpDefinitionId;
		this.cpDefinitionOptionRelId = event.newVal;

		this._updateCPDefinitionId();

		this.loadOptionValueDetail(event.newVal);
	}

	_handleSaveOptionValue() {
		var instance = this;

		AUI().use(
			'aui-base',
			'aui-form-validator',
			'liferay-form',
			(A) => {
				var hasErrors = false;

				let form = instance.element.querySelector('.option-value-detail form');

				var liferayForm = Liferay.Form.get(form.getAttribute('id'));

				if (liferayForm) {
					var validator = liferayForm.formValidator;

					if (A.instanceOf(validator, A.FormValidator)) {
						validator.validate();

						hasErrors = validator.hasErrors();

						if (hasErrors) {
							validator.focusInvalidField();
						}
					}
				}

				if (!hasErrors) {
					instance._saveOptionValue();
				}
			}
		);
	}

	_handleDeleteOptionValue() {
		if (confirm(Liferay.Language.get('are-you-sure-you-want-to-delete-the-selected-option'))) {
			this._deleteOptionValue();
		}
	}

	_deleteOptionValue() {
		let form = this.element.querySelector('.option-value-detail form');

		form.querySelector('[name=' + this.namespace + 'cmd]').value = 'delete';

		var formData = new FormData(form);

		formData.set('p_auth', Liferay.authToken);

		fetch(
			form.action,
			{
				body: formData,
				credentials: 'include',
				method: 'POST'
			}
		).then(
			response => response.json()
		).then(
			(jsonResponse) => {
				this.emit('optionValueDeleted', jsonResponse);
			}
		);
	}

	_saveOptionValue() {
		let form = this.element.querySelector('.option-value-detail form');

		form.querySelector('[name=' + this.namespace + 'cpDefinitionOptionRelId]').value = this.cpDefinitionOptionRelId;

		var formData = new FormData(form);

		formData.set('p_auth', Liferay.authToken);

		fetch(
			form.action,
			{
				body: formData,
				credentials: 'include',
				method: 'POST'
			}
		).then(
			response => response.json()
		).then(
			(jsonResponse) => {
				this.emit('optionValueSaved', jsonResponse);
			}
		);
	}

	_updateCPDefinitionId() {
		let instance = this;

		var url = new URL(window.location.href);

		var cpDefinitionId = url.searchParams.get(instance.namespace + 'cpDefinitionId');

		if (instance.cpDefinitionId && (cpDefinitionId != instance.cpDefinitionId)) {
			var cpDefinitionOptionValueRelURL = new URL(instance.cpDefinitionOptionValueRelURL);

			cpDefinitionOptionValueRelURL.searchParams.set(instance.namespace + 'cpDefinitionId', instance.cpDefinitionId);

			instance.cpDefinitionOptionValueRelURL = cpDefinitionOptionValueRelURL.href;

			url.searchParams.set(instance.namespace + 'cpDefinitionId', instance.cpDefinitionId);

			window.history.pushState({}, '', url);
		}
	}
}

/**
 * State definition.
 * @type {!Object}
 * @static
 */

CPDefinitionOptionValueDetail.STATE = {
	cpDefinitionId: Config.string(),
	cpDefinitionOptionRelId: Config.string().required(),
	cpDefinitionOptionValueRelId: Config.string().required(),
	cpDefinitionOptionValueRelURL: Config.string().required(),
	namespace: Config.string().required(),
	pathThemeImages: Config.string().required()
};

// Register component

Soy.register(CPDefinitionOptionValueDetail, templates);

export default CPDefinitionOptionValueDetail;