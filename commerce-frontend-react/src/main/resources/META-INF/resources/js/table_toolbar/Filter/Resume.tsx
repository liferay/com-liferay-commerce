import React, { useState } from 'react';

import ClayDropDown from '@clayui/drop-down';
import ClayIcon from '@clayui/icon';
import { renderFilter, getPrettifiedValue } from '../utils';
import ClayLabel from '@clayui/label';
import getAppContext, { ContextProps } from '../Context';


const Resume: React.FunctionComponent<any> = (props) => {
    const { actions } : ContextProps = getAppContext();
    const [open, setOpen] = useState(false);
    const prettifiedValue = ['checkbox', 'radio', 'select'].includes(props.type) 
        ? getPrettifiedValue(props) 
        : props.value;

    return (
        <ClayLabel
            closeButtonProps={{
                onClick: () => actions.updateFilterValue(props.slug, null)
            }}
            className='component-label tbar-label mr-2'
        >
            <div className="d-flex">
                <div className="label-section mr-2">
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