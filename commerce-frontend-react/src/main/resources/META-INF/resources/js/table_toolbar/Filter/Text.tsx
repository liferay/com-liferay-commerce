import React, { useState } from 'react';
import classNames from 'classnames';
import { TextFilterProps } from './definitions';
import getAppContext from '../Context';

import ClayButton from '@clayui/button';

const TextFilter: React.FunctionComponent<TextFilterProps> = (props: TextFilterProps) => {
    const { actions } = getAppContext();
    const [ value, setValue ] = useState(props.value);

    return (
        <div className="form-group">
            <div className="input-group">
                <div className={
                    classNames(
                        "input-group-item",
                        {
                            'input-group-prepend': props.inputText
                        }
                    )
                }>
                    <input 
                        aria-label={props.label}
                        className="form-control" 
                        type="text" 
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
                >
                    Add filter
                </ClayButton>
            </div>
        </div>
    )
}

export default TextFilter