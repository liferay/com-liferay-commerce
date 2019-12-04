import React from 'react';

import {launcher} from '../../utilities/index.es';
import {OPEN_MODAL} from '../../utilities/eventsDefinitions.es';
import modalLauncher from './entry.es';

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
	url: 'http://localhost:9000/form.html'
};

modalLauncher('modal', 'modal-root', props);

launcher(
	() => (
		<button
			className="btn btn-primary"
			onClick={() => Liferay.fire(OPEN_MODAL, {id: 'test-modal-id'})}
		>
			Open modal
		</button>
	),
	'modal-trigger-root',
	'modal-trigger-root'
);
