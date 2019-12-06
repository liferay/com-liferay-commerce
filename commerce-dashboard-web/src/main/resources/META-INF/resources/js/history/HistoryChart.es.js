import React from 'react';
import ChartWrapper from '../ChartWrapper.es';

export default function HistoryChart({ accountIdParamName, APIBaseUrl, commerceAccountId, noAccountErrorMessage, noDataErrorMessage }) {
	const chartData = {
		data: {
			columns: [
				['2019', 30, 200, 100, 400, 150, 250, 50, 100, 250],
				['2018', 100, 30, 200, 320, 50, 150, 230, 80, 150],
			],
			type: 'line',
			order: null
		},
		axis: {
			x: {
				type: 'category',
				categories: ['Jan', 'Feb', 'Mar', 'Apr', 'May', 'Jun', 'Jul', 'Aug', 'Sep']
			}
		},
		grid: {
			x: {
				show: false,
				// lines: COLUMNS[0].map((c, i) => ({value: i}))
			}
		},
		line: {
			classes: ['bb-line-past', 'bb-line-present']
		},
		color: {
			pattern: ['#4B9BFF']
		},
		point: {
			show: false
		},
		legend: {
			show: false
		},
	}

	return <ChartWrapper data={chartData} noDataErrorMessage={noDataErrorMessage} />;
}
