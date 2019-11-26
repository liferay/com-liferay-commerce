import React from 'react';

function Price(props) {
	return props.data.promoPrice ? (
		<span className="price-container">
			<b className="text-danger">{props.data.promoPrice}</b>
			<span className="ml-2 text-secondary">
				<del>{props.value}</del>
			</span>
		</span>
	) : (
		<span className="price-container">
			<b>{props.value}</b>
		</span>
	);
}

export default Price;
