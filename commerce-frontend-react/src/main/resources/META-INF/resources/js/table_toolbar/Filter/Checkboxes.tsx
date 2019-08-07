import React, { useState } from 'react';
import ClayForm from '@clayui/form';
import ClayButton from '@clayui/button';

import { CheckboxesFilterProps } from './definitions';

import getAppContext from '../Context';

const CheckboxesFilter: React.FunctionComponent<CheckboxesFilterProps> = (props: CheckboxesFilterProps) => {

    const { actions } = getAppContext();
    const [value, setValue] = useState(props.value);

    function selectCheckbox(itemValue) {
        if(!value) {
            return setValue([itemValue]);
        }

        if(!value.includes(itemValue)) {
            return setValue(value.concat(itemValue));
        } else {
            if(value.length === 1) {
                return setValue(undefined);
            } else {
                return setValue(value.filter(v => v !== itemValue));
            }
        }
    }

    return (
        <>
            {props.items.map(
                (item, i) => {
                    let checked: boolean = false;

                    if (!!value) {
                        checked = value.reduce((acc: boolean, el) => acc || el === item.value, false)
                    }

                    return (
                        <ClayForm.Checkbox
                            aria-label={item.label}
                            checked={checked}
                            onChange={() => selectCheckbox(item.value)}
                            label={item.label}
                            key={i}
                        />
                    )
                }
            )}
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

export default CheckboxesFilter;
