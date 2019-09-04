import React from 'react';

import modalLauncher from './entry.es';
import {launcher} from '../utilities/entry';

const props = {
	actions: [
		{
			definition: 'save',
		},
	],
	spritemap: './icons.svg',
	id: 'zeasc',
	title: 'Title',
	url: 'http://localhost:9000/form.html',
	submitLabel: 'Create',
	showCancel: true,
	size: 'full-scfeen',
	closeOnSubmit: true
};

// declare global {
//     interface Window { modal: any; }
// }

const modal = modalLauncher('modal', 'modal-root', props);

const modalTrigger = launcher(
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
