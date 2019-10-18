import PropTypes from 'prop-types';
import React from 'react';

function SummaryItemDividerVariant() {
	return (
		<div className="col-12">
			<hr />
		</div>
	);
}

const baseItemDefaultProps = {
	label: PropTypes.string,
	value: PropTypes.string
};

function SummaryItemBase(props) {
	return (
		<>
			<div className="col-6 col-md-9">
				<p className="m-0">{props.label}</p>
			</div>
			<div className="col-6 col-md-3">
				<p className="m-0">{props.value}</p>
			</div>
		</>
	);
}

SummaryItemBase.propTypes = baseItemDefaultProps;

function SummaryItemBigVariant(props) {
	return (
		<>
			<div className="col-6 col-md-9">
				<h3 className="my-2">{props.label}</h3>
			</div>
			<div className="col-6 col-md-3">
				<h3 className="my-2">{props.value}</h3>
			</div>
		</>
	);
}

SummaryItemBigVariant.propTypes = baseItemDefaultProps;

function SummaryItemDangerVariant(props) {
	return (
		<>
			<div className="col-6 col-md-9 text-danger">
				<p className="m-0">{props.label}</p>
			</div>
			<div className="col-6 col-md-3 text-danger">
				<p className="m-0">{props.value}</p>
			</div>
		</>
	);
}

SummaryItemDangerVariant.propTypes = baseItemDefaultProps;

function SummaryItem(props) {
	const {style, ...itemProps} = props;

	let ItemVariant;

	switch (style) {
		case 'big':
			ItemVariant = SummaryItemBigVariant;
			break;
		case 'divider':
			ItemVariant = SummaryItemDividerVariant;
			break;
		case 'danger':
			ItemVariant = SummaryItemDangerVariant;
			break;
		default:
			ItemVariant = SummaryItemBase;
			break;
	}

	return <ItemVariant {...itemProps} />;
}

SummaryItem.propTypes = {
	style: PropTypes.string
};

function Summary(props) {
	return (
		<div className="row summary-table text-right">
			{props.items.map((item, i) => (
				<SummaryItem key={i} {...item} />
			))}
		</div>
	);
}

Summary.propTypes = {
	items: PropTypes.array.isRequired
};

Summary.defaultProps = {
	items: []
};

export default Summary;
