import tableToolbarLauncher from './entry.es';

export const filters = [
	{
		label: 'Text test',
		operator: 'contains',
		slug: 'text-test',
		type: 'text',
		value: 'Test input'
	},
	// {
	// 	items: [
	// 		{
	// 			label: 'First option',
	// 			value: 'first-option'
	// 		},
	// 		{
	// 			label: 'Second option',
	// 			value: 'second-option'
	// 		}
	// 	],
	// 	label: 'Select test',
	// 	operator: 'eq',
	// 	slug: 'select-test',
	// 	type: 'select',
	// 	value: 'second-option'
	// },
	// {
	// 	items: [
	// 		{
	// 			label: 'First option',
	// 			value: 'first-option'
	// 		},
	// 		{
	// 			label: 'Second option',
	// 			value: 'second-option'
	// 		}
	// 	],
	// 	label: 'Radio test',
	// 	operator: 'eq',
	// 	slug: 'radio-test',
	// 	type: 'radio'
	// },
	// {
	// 	items: [
	// 		{
	// 			label: 'First option',
	// 			value: 'first-option'
	// 		},
	// 		{
	// 			label: 'Second option',
	// 			value: 'second-option'
	// 		},
	// 		{
	// 			label: 'Third option',
	// 			value: 'third-option'
	// 		}
	// 	],
	// 	label: 'Checkbox test',
	// 	operator: 'contains',
	// 	slug: 'checkbox-test',
	// 	type: 'checkbox',
	// 	value: ['first-option', 'third-option']
	// },
	// {
	// 	inputText: '$',
	// 	label: 'Number test',
	// 	max: 200,
	// 	min: 20,
	// 	operator: 'eq',
	// 	slug: 'number-test',
	// 	type: 'number',
	// 	value: 123,
	// },
	// {
	// 	label: 'Date test',
	// 	operator: 'eq',
	// 	slug: 'date-test',
	// 	type: 'date',
	// 	value: {
	// 		day: 1,
	// 		month: 2,
	// 		year: 1
	// 	}
	// }
];

const props = {
	filters,
	inputSearch: {
		name: 'main-search'
	},
	plusButton: {
		// eslint-disable-next-line no-console
		onClick: e => console.log(e),
		resetFiltersAfterClickAction: true
	},
	queryEndpoint: '/toolbar-test',
	spritemap: './icons.svg',
};

window.tableToolbar = tableToolbarLauncher(
	'tableToolbar',
	'table-toolbar',
	props
);
