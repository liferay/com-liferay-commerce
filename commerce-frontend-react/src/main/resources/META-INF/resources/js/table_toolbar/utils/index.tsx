import React from 'react';

import FilterProps, {filterTypeToComponentMap, CheckboxesFilterProps, MultiFilterProps} from '../Filter/definitions';

export const renderFilter = (item: FilterProps, panelType): any => {
    const Filter: React.FunctionComponent<any> = filterTypeToComponentMap[item.type];
    return <Filter {...item} panelType={panelType} />
}

export const getCheckboxesFilterPrettifiedValue = ({value, items}: CheckboxesFilterProps ): string | null => {
    const prettifiedValue = value 
        ? value.map((v) => {
            return items.reduce((found: string | null, item) => found || (item.value === v ? item.label : null), null)
        }).join(', ') 
        : null;
    return prettifiedValue;
}

export const getMultiFilterPrettifiedValue = ({value, items}: MultiFilterProps ): string | null => {
    const prettifiedValue = value 
        ? items.reduce((found: string | null, item) => found || (item.value === value ? item.label : null), null)
        : null;
    return prettifiedValue;
}

export const getPrettifiedValue = (props: MultiFilterProps | CheckboxesFilterProps) => {
    switch (props.type) {
        case 'checkbox':
            return getCheckboxesFilterPrettifiedValue(props);
        default:
            return getMultiFilterPrettifiedValue(props);
    }
}