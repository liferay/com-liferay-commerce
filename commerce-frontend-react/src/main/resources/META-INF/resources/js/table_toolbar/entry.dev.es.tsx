import FilterProps from './Filter/definitions';
import tableToolbarLauncher from './entry.es';

const filters: FilterProps[] = [
	{
		type: "text",
		label: "Text test",
		operator: 'contains',
		slug: 'text-test',
		value: 'Test input'
	},
	{
		type: 'select',
		items: [
			{
				label: 'First option',
				value: 'first-option'
			},
			{
				label: 'Second option',
				value: 'second-option'
			}
		],
		label: 'Select test',
		operator: "eq",
		slug: 'select-test',
		value: 'second-option'
	},
	{
		type: 'radio',
		items: [
			{
				label: 'First option',
				value: 'first-option'
			},
			{
				label: 'Second option',
				value: 'second-option'
			}
		],
		label: 'Radio test',
		operator: "eq",
		slug: 'radio-test',
		// value: 'first-option'
	},
	{
		type: 'checkbox',
		items: [
			{
				label: 'First option',
				value: 'first-option'
			},
			{
				label: 'Second option',
				value: 'second-option'
			},
			{
				label: 'Third option',
				value: 'third-option'
			}
		],
		label: 'Checkbox test',
		operator: "contains",
		slug: 'checkbox-test',
		value: ['first-option', 'third-option']
	},
	{
		type: 'number',
		slug: 'number-test',
		label: 'Number test',
		operator: 'eq',
		inputText: '$',
		value: 123,
		min: 20,
		max: 200
	},
	{
		type: 'date',
		slug: 'date-test',
		label: 'Date test',
		operator: 'eq',
		value: {
			day: 1,
			month: 2,
			year: 1
		}
	},
	// {
	// 	type: 'date-time',
	// 	slug: 'date-time-test',
	// 	label: 'Date Time test',
	// 	operator: 'eq',
	// 	value: {
	// 		day: 1,
	// 		month: 10,
	// 		year: 2010,
	// 		hours: 20,
	// 		minutes: 0,
	// 		seconds: 10
	// 	}
	// }
]

const props = {
	filters: filters,
	spritemap: './icons.svg',
	plusButton: {
		onClick: (e) => console.log(e),
		resetFiltersAfterClickAction: true
	},
	queryEndpoint: '/toolbar-test',
	inputSearch: {
		name: 'main-search'
	}
}

declare global {
    interface Window { tableToolbar: any; }
}

window.tableToolbar = tableToolbarLauncher('tableToolbar', 'table-toolbar', props);
