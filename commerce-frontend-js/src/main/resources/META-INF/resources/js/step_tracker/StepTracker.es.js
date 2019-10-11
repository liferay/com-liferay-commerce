import classnames from 'classnames';
import PropTypes from 'prop-types';
import React from 'react';

const stateToCssClassesMap = {
	active: 'text-primary',
	completed: 'text-success',
	inactive: 'text-muted'
};

function mapStateToCssClass(state) {
	return stateToCssClassesMap[state];
}

function Step(props) {
	return (
		<div
			className={classnames(
				`step`,
				mapStateToCssClass(props.state || 'inactive')
			)}
		>
			<span className="step-label">{props.label}</span>
		</div>
	);
}

Step.propTypes = {
	label: PropTypes.string.isRequired,
	state: PropTypes.oneOf(['completed', 'active', 'inactive'])
};

function StepTracker(props) {
	return (
		<div className="step-tracker rounded">
			{props.steps.map(step => (
				<Step key={step.id} {...step} />
			))}
		</div>
	);
}

StepTracker.propTypes = {
	steps: PropTypes.array.isRequired
};

export default StepTracker;
