import React, { useState } from 'react';

import ClayDropDown from '@clayui/drop-down';
import ClayIcon from '@clayui/icon';
import ClayButton from '@clayui/button';
import { renderFilter, getPrettifiedValue } from '../utils';
import ClayLabel from '@clayui/label';
import getAppContext from '../Context';
import FilterProps from './definitions';


const Resume: React.FunctionComponent<any> = (props) => {
    const {state, actions} = getAppContext();
    const [open, setOpen] = useState(false);
    const prettifiedValue = ['checkbox', 'radio', 'select'].includes(props.type) ? getPrettifiedValue(props) : props.value
    console.log(prettifiedValue);

    return (
        <ClayLabel
            closeButtonProps={{
                onClick: () => actions.updateFilterValue(props.slug, null)
            }}
            className='component-label tbar-label mr-2'
        >
            <div className="d-flex">
                <div className="label-section">
                    {props.label} : {prettifiedValue}
                </div>
                <ClayDropDown
                    trigger={
                        <span className="label-item ml-1">
                            <button className="btn close" type="button">
                                <ClayIcon symbol="caret-bottom" />
                            </button>
                        </span>
                    }
                    active={open}
                    onActiveChange={setOpen}
                >
                    <ClayDropDown.ItemList>
                        <div className="p-3">
                            {renderFilter(props, 'edit')}
                        </div>
                    </ClayDropDown.ItemList>
                </ClayDropDown>
            </div>
        </ClayLabel>
    )
}

export default Resume