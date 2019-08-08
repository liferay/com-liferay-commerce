import React, { useState} from 'react';
import ClayDatePicker from '@clayui/date-picker';
import ClayButton from '@clayui/button';

import getAppContext from '../Context';
import { DateFilterProps } from './definitions'

interface IProps extends DateFilterProps {
    panelType?: 'add' | 'edit'
}

const DateFilter: React.FunctionComponent<IProps> = (props: IProps) => {
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
                    disabled={value === props.value}
                >
                    {props.panelType === 'edit' ? 'Edit filter' : 'Add filter'}
                </ClayButton>
            </div>
        </>
    )
}

export default DateFilter;