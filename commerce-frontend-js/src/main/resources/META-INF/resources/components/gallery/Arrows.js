import React from 'react';
import PropTypes from 'prop-types';

export default function Arrows({onNext, onPrev}) {
	return (
		<>
			{onPrev && <div className="arrow prev" onClick={onPrev} />}
			{onNext && <div className="arrow next" onClick={onNext} />}
		</>
	);
}

Arrows.propTypes = {
	onNext: PropTypes.func,
	onPrev: PropTypes.func
};
