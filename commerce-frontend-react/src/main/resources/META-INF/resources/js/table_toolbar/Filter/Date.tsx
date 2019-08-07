import React, { useState} from 'react';
import ClayDatePicker from '@clayui/date-picker';
import ClayButton from '@clayui/button';

import getAppContext from '../Context';
import { DateFilterProps } from './definitions'

const DateFilter: React.FunctionComponent<DateFilterProps> = (props: DateFilterProps) => {
    const {actions} = getAppContext();
    const [value, setValue] = useState(props.value);

    return (
        <>
            <ClayDatePicker 
                onValueChange={setValue}
                placeholder="YYYY-MM-DD"
                value={value || ''}
            />
            <div className="mt-2">
                <ClayButton
                    className="btn-sm"
                    onClick={() => actions.updateFilterValue(props.slug, value)}
                >
                    Add filter
                </ClayButton>
            </div>
        </>
    )
}

export default DateFilter;