import React from 'react';
import ChartWrapper from '../ChartWrapper.es';

export default function StatusChart(accountIdParamName, APIBaseUrl, commerceAccountId, noAccountErrorMessage, noDataErrorMessage) {
	const chartData = {
		data: {
			x: 'x',
			columns: [
				['x', 'Draft', 'Pending', 'Approved', 'Placed', 'Delivered'],
				["quantity", 80000, 50000, 60000, 20000, 35000],
			],
			type: 'bar',
		},
		axis: {
			x: {
				type: 'category'
			},
			y: {
				tick: {
					count: 5
				}
			}
		},
		grid: {
			x: {
				show: false,
			}
		},
		legend: {
			show: false
		},
	}

	return <ChartWrapper data={chartData} noDataErrorMessage={noDataErrorMessage} />;
}
