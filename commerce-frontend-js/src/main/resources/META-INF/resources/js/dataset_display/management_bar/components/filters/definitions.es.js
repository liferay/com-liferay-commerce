/*
export interface DateFormat {
	year: number,
	month: number,
	day: number
}

export interface DateTimeFormat {
	year: number,
	month: number,
	day: number,
	hours: number,
	minutes: number,
	seconds: number
}

export interface BaseFilterProps extends React.HTMLAttributes<HTMLDivElement>{
	slug: string,
	label: string,
	operator:
		'eq'
		| 'neq'
		| 'isnull'
		| 'isnotnull'
		| 'lt'
		| 'lte'
		| 'gt'
		| 'gte'
		| 'startswith'
		| 'doesnotstartwith'
		| 'endswith'
		| 'doesnotendwith'
		| 'contains'
		| 'doesnotcontain'
		| 'isempty'
		| 'isnotempty'
}

export interface TextFilterProps extends BaseFilterProps {
	type: 'text',
	inputText?: string,
	value?: string
}

export interface DateFilterProps extends BaseFilterProps {
	type: 'date',
	value?: DateFormat
}

export interface DateTimeFilterProps extends BaseFilterProps {
	type: 'date-time',
	value?: DateTimeFormat
}

export interface NumberFilterProps extends BaseFilterProps {
	type: 'number',
	min?: string | number,
	max?: string | number,
	inputText?: string,
	value?: string | number
}

export interface DateRangeFilterProps extends BaseFilterProps {
	type: 'date-time-range'
	value?: string
}

export interface CheckboxesFilterProps extends BaseFilterProps {
	type: 'checkbox',
	items: Array<{
		label: string,
		value: number | string,
	}>,
	value?: Array<number | string>
}

export interface MultiFilterProps extends BaseFilterProps {
	type: 'select' | 'radio',
	items: Array<{
		label: string,
		value: number | string,
	}>,
	value?: number | string
}

export type FilterProps =
	TextFilterProps |
	DateTimeFilterProps |
	NumberFilterProps |
	DateFilterProps |
	DateTimeFilterProps |
	CheckboxesFilterProps |
	MultiFilterProps

export default FilterProps;
*/
