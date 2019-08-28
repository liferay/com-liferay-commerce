// import {ModalProps} from './Modal.es';

import launcher from './entry.es';

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
};

// declare global {
//     interface Window { modal: any; }
// }

window.modal = launcher('modal', 'modal-root', props);

window.modal.open();
