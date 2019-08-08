import React from 'react';
import ClayDatePicker from '@clayui/date-picker';

import { DateRangeFilterProps } from './definitions';

interface IProps extends DateRangeFilterProps {
    panelType?: 'add' | 'edit'
}

const DateRangeFilter: React.FunctionComponent<IProps> = (props: IProps) => {
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