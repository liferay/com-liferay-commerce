import React, {useState} from 'react'
import ClayForm from '@clayui/form';
import getAppContext from '../Context';

import { MultiFilterProps } from './definitions';

import ClayButton from '@clayui/button';

const RadioFilter: React.FunctionComponent<MultiFilterProps> = (props: MultiFilterProps) => {
    const { actions } = getAppContext();
    const [ value, setValue ] = useState(props.value);

    return (
        <>
            <ClayForm.RadioGroup
                selectedValue={value || ''}
                onSelectedValueChange={setValue}
            >
                {props.items.map(item => (
                    <ClayForm.Radio key={item.value} label={item.label} value={item.value} />
                ))}
            </ClayForm.RadioGroup>
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

export default RadioFilter