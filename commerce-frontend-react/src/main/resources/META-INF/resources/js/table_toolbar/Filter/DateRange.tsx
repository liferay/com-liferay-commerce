import React from 'react';
import ClayDatePicker from '@clayui/date-picker';

import { DateRangeFilterProps } from './definitions';

const DateRangeFilter: React.FunctionComponent<DateRangeFilterProps> = (props: DateRangeFilterProps) => {
    return (
        <div>
            <h6>From</h6>
            <ClayDatePicker />
            <h6>To</h6>
            <ClayDatePicker />
        </div>
    )
}

export default DateRangeFilter;