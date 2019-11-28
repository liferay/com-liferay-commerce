import React, { useState, useEffect } from 'react';
import ClayChart from '@clayui/charts';

export default function OverviewChart({ commerceAccountId }) {
	const [accountId, setAccountId] = useState(commerceAccountId);

	Liferay.on('accountSelected', ({ accountId }) => setAccountId(accountId));

	// useEffect(updateData, [accountId]);

	return (<div className="overview-chart-wrapper">
		<div className="donut" style={{ '--perc': 66 }}>
			<div className="chart">
				<svg viewBox="0 0 104 104" xmlns="http://www.w3.org/2000/svg">
					<circle className="track" cx="52" cy="52" r="50" />
					<circle className="filler" cx="52" cy="52" r="50" />
				</svg>
				<div className="text">66%</div>
			</div>
			<div className="label">Label</div>
		</div>
		<div className="donut" style={{ '--perc': 66 }}>
			<div className="chart">
				<svg viewBox="0 0 104 104" xmlns="http://www.w3.org/2000/svg">
					<circle className="track" cx="52" cy="52" r="50" />
					<circle className="filler" cx="52" cy="52" r="50" />
				</svg>
				<div className="text">66%</div>
			</div>
			<div className="label">Label</div>
		</div>
		<div className="donut" style={{ '--perc': 66 }}>
			<div className="chart">
				<svg viewBox="0 0 104 104" xmlns="http://www.w3.org/2000/svg">
					<circle className="track" cx="52" cy="52" r="50" />
					<circle className="filler" cx="52" cy="52" r="50" />
				</svg>
				<div className="text">66%</div>
			</div>
			<div className="label">Label</div>
		</div>
	</div>);
}
