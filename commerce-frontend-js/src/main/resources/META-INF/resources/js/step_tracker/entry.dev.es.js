import launcher from './entry.es';
import './_step-tracker.scss';

window.AddOrCreate = launcher('step_tracker', 'step-tracker', {
	steps: [
		{
			id: 'received',
			label: 'Received',
			state: 'completed',
		},
		{
			id: 'confirmed',
			label: 'Confirmed',
			state: 'active',
		},
		{
			id: 'trasmitted',
			label: 'Trasmitted',
			state: 'inactive',
		},
		{
			id: 'shipped',
			label: 'Shipped',
			state: 'inactive',
		},
		{
			id: 'completed',
			label: 'Completed',
			state: 'inactive',
		},
	],
});
