import React, { useState, useContext } from 'react';
import Icon from '@clayui/icon';
import ClayDropDown from '@clayui/drop-down';
import ClayPanel from '@clayui/panel';
import { renderFilter } from './utils';
import FilterProps from './Filter/definitions';

import getAppContext from './Context';

const FiltersDropdown: React.FunctionComponent = (props) => {
    const [ active, setActive ] = useState(false);
    const { state, actions } = getAppContext();
    const [ visibleFilters, setVisibleFilter ] = useState(state.filters)

    function updateVisibleFilters(query: string) {
        const queryLowercase = query.toLowerCase();
        const results = state.filters.filter(
            filter => {
                return filter.slug.toLowerCase().includes(queryLowercase) || filter.label.toLowerCase().includes(queryLowercase)
            }
        )
        return setVisibleFilter(results)
    }

    return state.filters.length ? (
        <ClayDropDown
            trigger={
                <a aria-expanded="false" aria-haspopup="true" className="dropdown-toggle nav-link navbar-breakpoint-d-block" data-toggle="dropdown" href="#1" role="button">
                    <span className="navbar-text-truncate">Filters</span>
                    <Icon symbol="caret-bottom" />
                </a>
            }
            active={active}
            onActiveChange={setActive}
        >
            <ClayDropDown.Search 
                onChange={(e) => updateVisibleFilters(e.target.value)}
                defaultValue={''}
            />
            <ClayDropDown.ItemList>
                {visibleFilters.map((item: FilterProps, i: number) => (
                    <ClayPanel
                        className="mb-0"
                        collapsable
                        displayTitle={item.label}
                        displayType="primary"
                        showCollapseIcon={true}
                        key={i}
                    >
                        <ClayPanel.Body className="filter-body">
                            {renderFilter(item)}
                        </ClayPanel.Body>
                    </ClayPanel>
                ))}
            </ClayDropDown.ItemList>
        </ClayDropDown>
    ) : null
}

export default FiltersDropdown