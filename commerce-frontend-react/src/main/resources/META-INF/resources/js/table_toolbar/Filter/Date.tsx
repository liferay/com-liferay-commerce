import React, { useState} from 'react';
import ClayDatePicker from '@clayui/date-picker';
import ClayButton from '@clayui/button';

import getAppContext, { ContextProps } from '../Context';
import { DateFilterProps } from './definitions'

interface IProps extends DateFilterProps {
    panelType?: 'add' | 'edit'
}

const DateFilter: React.FunctionComponent<IProps> = (props: IProps) => {
    const {actions} : ContextProps = getAppContext();
    const [ value, setValue ] = useState<string | Date>(props.value || '');

    return (
        <>
            <ClayDatePicker 
                onValueChange={setValue}
                value={''}
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