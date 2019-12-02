import React, { useState, useEffect } from 'react';
import ChartWrapper from '../ChartWrapper.es';
import { loadData } from './utils.es';

export default function ForecastChart({ commerceAccountId }) {
	const [loading, setLoading] = useState(true);
	const [chartData, setChartData] = useState({});
	const [accountId, setAccountId] = useState(commerceAccountId);

	Liferay.on('accountSelected', ({ accountId }) => setAccountId(accountId));

	function updateData() {
		const APIUrl = `/o/headless-commerce-machine-learning/v1.0/accountCategoryForecasts/by-monthlyRevenue?accountIds=${accountId}&pageSize=100`;

		startLoading();
		loadData(APIUrl).then(setChartData);
	}

	function stopLoading() {
		setLoading(!chartData.data);
	}

	function startLoading() {
		setLoading(true);
	}

	useEffect(updateData, [accountId]);
	useEffect(stopLoading, [chartData]);

	return (!accountId) ?
		<p>{Liferay.Language.get('no-account-selected')}</p> :
		<ChartWrapper data={chartData} loading={loading} />
}
