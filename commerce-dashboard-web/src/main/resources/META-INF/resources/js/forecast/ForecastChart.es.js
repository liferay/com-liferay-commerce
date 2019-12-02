import React, { useState, useEffect } from 'react';
import ChartWrapper from '../ChartWrapper.es';

const NULL_VALUE = 1.4E-45;

function formatData(data) {
	if (data.actual !== NULL_VALUE) {
		return data.actual;
	}
	return {
		low: data.forecastLowerBound,
		mid: data.forecast,
		high: data.forecastUpperBound,
	};
}

function loadData(commerceAccountId) {
	return fetch(
		`/o/headless-commerce-machine-learning/v1.0/accountCategoryForecasts/by-monthlyRevenue?accountIds=${commerceAccountId}&pageSize=100`,
		{
			headers: {
				'Content-Type': 'application/json',
			},
		}
	)
		.then(response => response.json())
		.then(points => ({
			categories: [...(points.items || []).reduce((a, c) => {
				a.set(c.category, c.categoryTitle);
				return a;
			}, new Map()).entries()].map(([id, name]) => ({
				id,
				name
			})),
			points: points.items || [],
		}));
}

function filterCategory(id) {
	return data => data.category === id;
}

function getDates(points) {
	return points.filter(filterCategory(points[0].category)).map(d => d.timestamp.slice(0, 10));
}

function getValuesForCategory(points, category) {
	return points.filter(filterCategory(category.id)).map(formatData);
}

function formatColumns(categories, points) {
	return !points.length ? [] : [
		['x', ...getDates(points)],
		...categories.map(c => [`${c.id}`, ...getValuesForCategory(points, c)]),
	];
}

function findPredictionDate(points) {
	return !points.length ? null : points
		.slice()
		.reverse()
		.find(d => d.actual !== NULL_VALUE).timestamp.slice(0, 10);
}

function getLineTypes(categories) {
	return categories.reduce(
		(a, c) => Object.assign(a, { [`${c.id}`]: 'area-line-range' }),
		{}
	);
}

function formatCategoriesName(categories) {
	return categories.reduce(
		(a, c) => Object.assign(a, { [`${c.id}`]: c.name }),
		{}
	);
}

function sortData(data) {
	return {
		...data,
		points: data.points.sort((a, b) => new Date(a.timestamp) - new Date(b.timestamp))
	}
}

function formatChartData(data) {
	return {
		data: {
			x: 'x',
			type: 'predictive',
			columns: formatColumns(data.categories, data.points),
			types: getLineTypes(data.categories),
			names: formatCategoriesName(data.categories),
		},
		predictionDate: findPredictionDate(data.points),
		axis: {
			x: {
				type: 'timeseries',
			},
		},
	};
}

export default function ForecastChart({ commerceAccountId }) {
	const [loading, setLoading] = useState(true);
	const [chartData, setChartData] = useState({});
	const [accountId, setAccountId] = useState(commerceAccountId);

	Liferay.on('accountSelected', ({ accountId }) => setAccountId(accountId));

	function updateData() {
		startLoading();
		loadData(accountId)
			.then(sortData)
			.then(formatChartData)
			.then(setChartData);
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
