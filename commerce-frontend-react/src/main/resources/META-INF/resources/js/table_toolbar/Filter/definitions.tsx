import React from 'react';
import TextFilter from './Text';
import NumberFilter from './Number';
import DateRangeFilter from './DateRange';
import DateFilter from './Date';
import RadioFilter from './Radio';
import SelectFilter from './Select';
import CheckboxesFilter from './Checkboxes';

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
    type: 'date',
    inputText?: string,
    value?: string
}

export interface DateFilterProps extends BaseFilterProps {
    type: 'text',
    value?: string
}

export interface NumberFilterProps extends BaseFilterProps {
    type: 'number',
    min?: string | number,
    max?: string | number,
    inputText?: string,
    value?: string | number
}

export interface DateRangeFilterProps extends BaseFilterProps {
    type: 'date-range'
    value?: string | number
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
    DateFilterProps | 
    NumberFilterProps | 
    DateRangeFilterProps | 
    CheckboxesFilterProps | 
    MultiFilterProps

export default FilterProps;

export const filterTypeToComponentMap = {
    'text' : TextFilter,
    'number' : NumberFilter,
    'date-range' : DateRangeFilter,
    'date' : DateFilter,
    'radio' : RadioFilter,
    'select' : SelectFilter,
    'checkbox' : CheckboxesFilter
}