import React from 'react';

import FilterProps, {filterTypeToComponentMap, CheckboxesFilterProps, MultiFilterProps, DateFilterProps, DateTimeFilterProps, DateFormat, DateTimeFormat} from '../Filter/definitions';

export const renderFilter = (item: FilterProps, panelType): any => {
    const Filter: React.FunctionComponent<any> = filterTypeToComponentMap[item.type];
    return <Filter {...item} panelType={panelType} />
}

export const prettifyCheckboxValue = (value, items): string => {
    const prettifiedValue = value 
        ? value.map((v) => {
            return items.reduce((found: string | null, item) => found || (item.value === v ? item.label : null), null)
        }).join(', ') 
        : '';
    return prettifiedValue;
}

export const prettifySelectValue = (value, items): string => {
    const prettifiedValue = value 
        ? items.reduce((found: string | null, item) => found || (item.value === value ? item.label : null), null)
        : '';
    return prettifiedValue;
}

export const prettifyDateValue = (value?: DateFormat | Date): string => {
    if (!value) {
        return ''
    }
    
    const date = value instanceof Date 
        ? value
        : new Date(
            value.year,
            value.month,
            value.day
        );

    return date.toLocaleDateString();
}

export const prettifyDateTimeValue = (value?: DateTimeFormat): string => {
    if (!value) {
        return ''
    }
    
    const date = value instanceof Date 
        ? value
        : new Date(
            value.year,
            value.month,
            value.day,
            value.hours,
            value.minutes,
            value.seconds
        );
        
    return date.toLocaleDateString() + ' ' + date.toLocaleTimeString();
}

export const prettifyFilterValue = (props: FilterProps) => {

    switch (props.type) {
        case 'checkbox':
            return prettifyCheckboxValue(props.value, props.items);
        case 'radio':
        case 'select':
            return prettifySelectValue(props.value, props.items);
        case 'date':
            return prettifyDateValue(props.value);
        case 'date-time':
            return prettifyDateTimeValue(props.value);
        // case 'date-range':
        // case 'date-time-range':
        //     return prettifySelectValue(props);
        default:
            return props.value;
    }
}