import React, { useState, useEffect } from 'react';
import ClayChart from '@clayui/charts';

export default function HistoryChart() {
	const [loading, setLoading] = useState(true);

	function chartInit() {
		setLoading(false);
	}

	return <ClayChart
		data={{
			columns: [
				['2019', 30, 200, 100, 400, 150, 250, 50, 100, 250],
				['2018', 100, 30, 200, 320, 50, 150, 230, 80, 150],
			],
			type: 'line',
			order: null
		}}
		axis={{
			x: {
				type: 'category',
				categories: ['Jan', 'Feb', 'Mar', 'Apr', 'May', 'Jun', 'Jul', 'Aug', 'Sep']
			}
		}}
		grid={{
			x: {
				show: false,
				// lines: COLUMNS[0].map((c, i) => ({value: i}))
			}
		}}
		line={{
			classes: ['bb-line-past', 'bb-line-present']
		}}
		color={{
			pattern: ['#4B9BFF']
		}}
		point={{
			show: false
		}}
		legend={{
			show: false
		}}
		oninit={chartInit}
	/>;
}
