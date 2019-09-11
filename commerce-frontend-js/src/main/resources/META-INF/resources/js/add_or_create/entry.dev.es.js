import launcher from './entry.es';

import './_add-or-create.scss';

window.AddOrCreate = launcher('addOrCreate', 'add-or-create', {
	onSubmit: console.log,
	onSearch: value => Array.from({length: Math.random() * 4}, (_, i) => ({
		id: i,
		label: `Suggestion ${value} ${i}`,
	})),
});
