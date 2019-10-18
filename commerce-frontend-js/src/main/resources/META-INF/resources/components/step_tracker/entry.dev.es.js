import launcher from './entry.es';

import '../../styles/main.scss';

launcher('step_tracker', 'step-tracker', {
	spritemap: './assets/icons.svg',
	steps: [
		{
			id: 'received',
			label: 'Received',
			state: 'completed'
		},
		{
			id: 'confirmed',
			label: 'Confirmed',
			state: 'active'
		},
		{
			id: 'trasmitted',
			label: 'Trasmitted',
			state: 'inactive'
		},
		{
			id: 'shipped',
			label: 'Shipped',
			state: 'inactive'
		},
		{
			id: 'completed',
			label: 'Completed',
			state: 'inactive'
		}
	]
});
