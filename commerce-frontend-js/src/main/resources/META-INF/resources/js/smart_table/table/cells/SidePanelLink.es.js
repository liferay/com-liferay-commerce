import ClayLink from '@clayui/link';
import PropTypes from 'prop-types';
import React from 'react'

import SmartTableContext from '../../SmartTableContext.es';
import TableContext from '../TableContext.es';

function SidePanelLink(props) {
    return (
        <SmartTableContext.Consumer>
            {
                ({loadData}) => (
                    <TableContext.Consumer>
                        {({setSidePanelProps, sidePanelProps}) => (
                            <ClayLink 
                                href="#"
                                onClick={() => {
                                    setSidePanelProps({
                                        ...sidePanelProps,
                                        onSubmit: () => {
                                            loadData();
                                        },
                                        url: props.value.url
                                    })
                                }}
                            >
                                {props.value.label}
                            </ClayLink>
                        )}
                    </TableContext.Consumer>
                )
            }
        </SmartTableContext.Consumer>
    )
}

SidePanelLink.propTypes = {
    value: PropTypes.shape({
        label: PropTypes.oneOfType([
            PropTypes.string,
            PropTypes.number
        ]).isRequired,
        url: PropTypes.string.isRequired,
    }).isRequired
}

export default SidePanelLink;