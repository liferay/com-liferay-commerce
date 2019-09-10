import React from 'react';
import classnames from 'classnames';

const stateToCssClassesMap = {
	"completed": "text-success",
	"active": "text-primary",
	"inactive": "text-muted"
}

function mapStateToCssClass(state) {
	return stateToCssClassesMap[state]
}

function Step(props) {
	return (
		<div className={classnames(`step`, mapStateToCssClass(props.state || 'inactive'))}>
			<span className="step-label">
				{props.label}
			</span>
		</div>
	)
}

function StepTracker(props) {
	return (
		<div className="step-tracker rounded">
			{props.steps.map(step => <Step key={step.id} label={step.label} state={step.state} />)}
		</div>
	);
}

export default StepTracker;