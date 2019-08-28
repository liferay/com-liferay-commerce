import FilterProps from './Filter/definitions';
import launcher from './entry.es';

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
		// value: '2018-05-05',
		operator: 'eq'
	}
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

window.tableToolbar = launcher('tableToolbar', 'table-toolbar', props);
