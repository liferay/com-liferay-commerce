import launcher from './entry.es';
import './step-tracker.scss';

window.AddOrCreate = launcher('stepTracker', 'step-tracker', {
	steps: [
		{
			slug: 'received',
			label: 'Received',
			state: 'completed',
		},
		{
			slug: 'confirmed',
			label: 'Confirmed',
			state: 'active',
		},
		{
			slug: 'trasmitted',
			label: 'Trasmitted',
			state: 'inactive',
		},
		{
			slug: 'shipped',
			label: 'Shipped',
			state: 'inactive',
		},
		{
			slug: 'completed',
			label: 'Completed',
			state: 'inactive',
		},
	],
});
