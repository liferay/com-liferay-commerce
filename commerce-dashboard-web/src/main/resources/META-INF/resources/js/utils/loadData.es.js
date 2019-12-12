/* eslint-disable require-jsdoc */
const NULL_VALUE = 1.4E-45;
const headers = {
	'Content-Type': 'application/json',
};

function byDate(a, b) {
	return new Date(a.timestamp) - new Date(b.timestamp)
}

function byName(a, b) {
	return a.name.localeCompare(b.name);
}

export function formatCategoriesForChart(categories) {
	return categories.reduce(
		(a, c) => Object.assign(a, { [`${c.id}`]: c.name }),
		{}
	);
}

export function formatDataForChart(points = []) {
	const categories = getCategoriesArray(points);

	return {
		data: {
			x: 'x',
			type: 'predictive',
			columns: formatPointsForChart(points, categories),
			types: getLineTypesForChart(categories),
			names: formatCategoriesForChart(categories),
		},
		predictionDate: getPredictionDate(points),
		axis: {
			x: {
				type: 'timeseries',
			},
		},
	};
}

export function formatPointForForecast(point) {
	return (point.actual !== NULL_VALUE) ? point.actual : {
		low: point.forecastLowerBound,
		mid: point.forecast,
		high: point.forecastUpperBound,
	};
}

export function formatPointsForChart(points, categories) {
	return !points.length ? [] : [
		['x', ...getDates(points)],
		...categories.map(c => [`${c.id}`, ...getValuesForCategory(points, c)]),
	];
}

export function getCategoriesArray(items = []) {
	return [...items.reduce((a, c) => {
		a.set(c.category, c.categoryTitle);

		return a;
	}, new Map()).entries()].map(([id, name]) => ({
		id,
		name,
	})).sort(byName);
}

export function getDates(points) {
	return points
		.filter(isPartOfCategory(points[0].category))
		.map(d => getDateString(d.timestamp));
}

export function getDateString(ts) {
	return (ts || '').slice(0, 10);
}

export function getLineTypesForChart(categories) {
	return categories.reduce(
		(a, c) => Object.assign(a, { [`${c.id}`]: 'area-line-range' }),
		{}
	);
}

export function getPoints({ items }) {
	return items;
}

export function getPredictionDate(points) {
	return !points.length ? null : getDateString(points
		.slice()
		.reverse()
		.find(d => d.actual !== NULL_VALUE).timestamp);
}

export function getValuesForCategory(points, category) {
	return points
		.filter(isPartOfCategory(category.id))
		.map(formatPointForForecast);
}

export function isPartOfCategory(id) {
	return ({ category }) => category === id;
}

export function loadData(url) {
	return parseData(fetch(url, { headers }).then(responseToJson));
}

export function parseData(loadData) {
	return loadData
		.then(getPoints)
		.then(sortPointsByDate)
		.then(formatDataForChart);
}

function responseToJson(response) {
	return response.json();
}

function sortPointsByDate(points = []) {
	return points.sort(byDate);
}
