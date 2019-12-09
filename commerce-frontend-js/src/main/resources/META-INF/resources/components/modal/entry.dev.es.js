import React from 'react';

import {launcher} from '../../utilities/index.es';
import {OPEN_MODAL} from '../../utilities/eventsDefinitions.es';
import modalLauncher from './entry.es';

import './_modal.scss';
import '../../styles/main.scss';

const props = {
	actions: [
		{
			definition: 'save'
		}
	],
	closeOnSubmit: true,
	id: 'test-modal-id',
	showCancel: true,
	size: 'full-screen',
	spritemap: './assets/icons.svg',
	submitLabel: 'Create',
	title: 'Title',
	url: 'http://localhost:9000/modal-content.html'
};

modalLauncher('modal', 'modal-root-id', props);

launcher(
	() => (
		<button
			className="btn btn-primary"
			onClick={() => Liferay.fire(OPEN_MODAL, {id: 'test-modal-id'})}
		>
			Open modal
		</button>
	),
	'modal-trigger',
	'modal-trigger-root-id'
);
