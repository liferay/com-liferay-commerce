import React, { useState, useEffect } from 'react';
import ChartWrapper from '../ChartWrapper.es';
import { loadData } from '../utils/index.es';

export default function ForecastChart({ accountIdParamName, APIBaseUrl, commerceAccountId, noAccountErrorMessage, noDataErrorMessage }) {
	const [loading, setLoading] = useState(true);
	const [chartData, setChartData] = useState({});
	const [accountId, setAccountId] = useState(commerceAccountId);

	Liferay.on('accountSelected', ({ accountId }) => setAccountId(accountId));

	function updateData() {
		const APIUrl = `${APIBaseUrl}?${accountIdParamName}=${accountId}&pageSize=200`;

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
		<p>{noAccountErrorMessage}</p> :
		<ChartWrapper data={chartData} loading={loading} noDataErrorMessage={noDataErrorMessage} />
}
