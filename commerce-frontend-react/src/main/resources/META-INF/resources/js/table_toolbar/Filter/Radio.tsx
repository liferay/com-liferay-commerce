import React, {useState} from 'react'
import ClayForm from '@clayui/form';
import getAppContext from '../Context';

import { MultiFilterProps } from './definitions';

import ClayButton from '@clayui/button';

interface IProps extends MultiFilterProps {
    panelType?: 'add' | 'edit'
}

const RadioFilter: React.FunctionComponent<IProps> = (props: IProps) => {
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
                    disabled={value === props.value}
                >
                    {props.panelType === 'edit' ? 'Edit filter' : 'Add filter'}
                </ClayButton>
            </div>
        </>
    )
}

export default RadioFilter