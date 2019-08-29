import React, {useState} from 'react';
import ClayDatePicker from '@clayui/date-picker';

import { DateRangeFilterProps } from './definitions';

interface IProps extends DateRangeFilterProps {
    panelType?: 'add' | 'edit'
}

const DateRangeFilter: React.FunctionComponent<IProps> = (props: IProps) => {

    const [ value, setValue ] = useState<string | Date>('');
    return (
        <div>
            <h6>From</h6>
            <ClayDatePicker 
                onValueChange={setValue}
                value={''}
             />
            <h6>To</h6>
            <ClayDatePicker 
                onValueChange={setValue}
                value={''}
            />
        </div>
    )
}

export default DateRangeFilter;