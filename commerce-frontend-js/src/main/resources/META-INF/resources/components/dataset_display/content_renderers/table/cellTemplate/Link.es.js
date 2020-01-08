import ClayLink from '@clayui/link';
import React from 'react';
import DefaultContent from './Default.es';


function Link(props) {
	return (
		<ClayLink href={props.value.href}>
			<DefaultContent {...props} />
		</ClayLink>
	);
}

export default Link;
