import React, {useState} from 'react'
import { ClayRadio, ClayRadioGroup} from '@clayui/form';
import getAppContext, { ContextProps } from '../Context';

import { MultiFilterProps } from './definitions';

import ClayButton from '@clayui/button';

interface IProps extends MultiFilterProps {
    panelType?: 'add' | 'edit'
}

const RadioFilter: React.FunctionComponent<IProps> = (props: IProps) => {
    const { actions } : ContextProps  = getAppContext();
    const [ value, setValue ] = useState(props.value);

    return (
        <>
            <ClayRadioGroup
                selectedValue={value || ''}
                onSelectedValueChange={setValue}
            >
                {props.items.map(item => (
                    <ClayRadio key={item.value} label={item.label} value={item.value} />
                ))}
            </ClayRadioGroup>
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

export default RadioFilter