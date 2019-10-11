import React from 'react';

import {launcher} from '../utilities/index.es';
import modalLauncher from './entry.es';

const props = {
	actions: [
		{
			definition: 'save'
		}
	],
	closeOnSubmit: true,
	id: 'zeasc',
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
			onClick={() => window.dispatchEvent(new Event('zeasc-open'))}
		>
			Open modal
		</button>
	),
	'modal-trigger-root',
	'modal-trigger-root',
	props
);
