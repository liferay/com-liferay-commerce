import React from 'react';

import FilterResume from './Filter/Resume.es';
import FilterProps from './Filter/definitions';

import getAppContext, { ContextProps } from './Context.es';
import ClayButton from '@clayui/button';


const ActiveFiltersBar = () => {

    const {state, actions} = getAppContext();

    const filtersActive = state.filters.reduce(
        (acc, filter) => !!filter.value ? acc.concat(filter.slug) : acc,
        []
    )

    return filtersActive.length ? (
        <nav className="tbar tbar-inline-md-down subnav-tbar subnav-tbar-primary subnav-tbar-light pt-3 pb-3 border-top">
            <div className="container-fluid container-fluid-max-xl">
                <ul className="tbar-nav">
                    <li className="tbar-item tbar-item-expand">
                        <div className="tbar-section">
                            {filtersActive.map((slug, i) => {
                                const filter = state.filters.reduce(
                                    (found, filter) => found || (filter.slug === slug ? filter : null),
                                    null
                                );
                                
                                if(!filter) {
                                    throw new Error(`Filter "${slug}" not found.`);
                                }
                                
                                return (<FilterResume key={i} {...filter} />)
                            })}
                        </div>
                    </li>
                    <li className="tbar-item">
                        <div className="tbar-section">
                            <ClayButton
                                displayType="link"
                                className=" tbar-link btn-sm"
                                onClick={actions.resetFiltersValue}
                            >
                                Reset filters
                            </ClayButton>
                        </div>
                    </li>
                </ul>
            </div>
        </nav>
    ) : null
}

export default ActiveFiltersBar