import React, { useState, useEffect } from 'react';
import Icon from '@clayui/icon';
import ClayDropDown from '@clayui/drop-down';
import ClayPanel from '@clayui/panel';
import { renderFilter } from './utils';
import FilterProps from './Filter/definitions';

import getAppContext from './Context';

const FiltersDropdown: React.FunctionComponent = (props) => {
    const [ active, setActive ] = useState(false);
    const [ query, setQuery ] = useState('');
    const { state, actions } = getAppContext();
    const [ visibleFilters, setVisibleFilter ] = useState(state.filters.filter(filter => !filter.value))

    useEffect(() => {
        const results = state.filters.filter(
            filter => {
                switch (true) {
                    case !!filter.value:
                        return false
                    case query && !(
                            filter.slug.toLowerCase().includes(query) ||
                            filter.label.toLowerCase().includes(query)
                        ):
                        return false
                    default:
                        return true
                }
            }
        )
        return setVisibleFilter(results)
    }, [ state.filters, query ])

    return state.filters.length ? (
        <ClayDropDown
            trigger={
                <a aria-expanded="false" aria-haspopup="true" className="dropdown-toggle nav-link navbar-breakpoint-d-block" data-toggle="dropdown" href="#1" role="button">
                    <span className="navbar-text-truncate">Add filters</span>
                    <Icon symbol="caret-bottom" />
                </a>
            }
            active={active}
            onActiveChange={setActive}
        >
            <ClayDropDown.Search 
                onChange={(e) => setQuery(e.target.value.toLowerCase() || null)}
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
                        key={item.slug}
                    >
                        <ClayPanel.Body className="filter-body">
                            {renderFilter(item, 'add')}
                        </ClayPanel.Body>
                    </ClayPanel>
                ))}
            </ClayDropDown.ItemList>
        </ClayDropDown>
    ) : null
}

export default FiltersDropdown