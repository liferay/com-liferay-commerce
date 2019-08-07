import React, { useState } from 'react';

import ClayDropDown from '@clayui/drop-down';
import ClayIcon from '@clayui/icon';
import ClayButton from '@clayui/button';
import { renderFilter } from '../utils';
import ClayLabel from '@clayui/label';
import getAppContext from '../Context';
import FilterProps from './definitions';


const Resume: React.FunctionComponent<FilterProps> = (props: FilterProps) => {
    const {state, actions} = getAppContext();
    const [open, setOpen] = useState(false);

    // let valueToBeDisplayed: string | number | string[] | number[] = props.value;
    
    // if(valueToBeDisplayed instanceof Array) {
    //     valueToBeDisplayed = props.value.map(optionValue => {
    //         const label = props;
    //     })
    // }

    return (
        <ClayLabel
            closeButtonProps={{
                onClick: () => actions.updateFilterValue(props.slug, null)
            }}
            className='component-label tbar-label mr-2'
        >
            <div className="d-flex">
                <div className="label-section">
                    {props.label} : {props.value}
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
                            {renderFilter(props)}
                        </div>
                    </ClayDropDown.ItemList>
                </ClayDropDown>
            </div>
        </ClayLabel>
    )
}

export default Resume