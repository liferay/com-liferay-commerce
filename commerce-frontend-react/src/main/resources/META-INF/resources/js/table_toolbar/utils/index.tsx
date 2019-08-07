import React from 'react';

import FilterProps, {filterTypeToComponentMap} from '../Filter/definitions';

export const renderFilter = (item: FilterProps): any => {
    const Filter: React.FunctionComponent<any> = filterTypeToComponentMap[item.type];
    return <Filter {...item} />
}