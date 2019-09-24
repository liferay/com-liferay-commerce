import React, {useState} from 'react';
import classNames from 'classnames';
import { NumberFilterProps } from './definitions';
import getAppContext, { ContextProps } from '../Context.es';

import ClayButton from '@clayui/button';

// interface IProps extends NumberFilterProps {
//     panelType?: 'add' | 'edit'
// }

const NumberFilter = (props) => {

    const { actions } = getAppContext();
    const [ value, setValue ] = useState(props.value);

    return (
        <div className="form-group">
            <div className="input-group">
                <div className={classNames(
                        "input-group-item",
                        {
                            'input-group-prepend': props.inputText
                        }
                    )}>
                    <input 
                        aria-label="Amount (to the nearest dollar)" 
                        className="form-control" 
                        type="number" 
                        min={props.min} 
                        max={props.max} 
                        value={value || ''}
                        onChange={(e) => setValue(e.target.value)}
                    />
                </div>
                {props.inputText && (
                    <div className="input-group-append input-group-item input-group-item-shrink">
                        <span className="input-group-text">{props.inputText}</span>
                    </div>
                )}
            </div>
            <div className="mt-2">
                <ClayButton
                    className="btn-sm"
                    onClick={() => actions.updateFilterValue(props.slug, value)}
                    disabled={value === props.value}
                >
                    {props.panelType === 'edit' ? 'Edit filter' : 'Add filter'}
                </ClayButton>
            </div>
        </div>
    )
}

export default NumberFilter;