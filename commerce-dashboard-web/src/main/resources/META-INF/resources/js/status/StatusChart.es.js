import React, { useState, useEffect } from 'react';
import ClayChart from '@clayui/charts';

export default function StatusChart({ commerceAccountId }) {
	const [accountId, setAccountId] = useState(commerceAccountId);

	Liferay.on('accountSelected', ({ accountId }) => setAccountId(accountId));

	// useEffect(updateData, [accountId]);

	return <ClayChart
		data={{
			x: 'x',
			columns: [
				['x', 'Draft', 'Pending', 'Approved', 'Placed', 'Delivered'],
				["quantity", 80000, 50000, 60000, 20000, 35000],
			],
			type: 'bar',
		}}
		axis={{
			x: {
				type: 'category'
			},
			y: {
				tick: {
					count: 5
				}
			}
		}}
		grid={{
			x: {
				show: false,
			}
		}}
		legend={{
			show: false
		}}
	/>;
}
