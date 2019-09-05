import launcher from './entry.es';

window.AddOrCreate = launcher('addOrCreate', 'add-or-create', {
	onSubmit: console.log,
	onSearch: value => Array.from({length: Math.random() * 4}, (_, i) => ({
		id: i,
		label: `Suggestion ${value} ${i}`,
	})),
});
