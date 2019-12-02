/* eslint-disable require-jsdoc */
const NULL_VALUE = 1.4E-45;
const headers = {
	'Content-Type': 'application/json',
};

function formatDataForChart(data) {
	const categories = getCategoriesArray(data.items || []);
	const points = sortPointsByDate(data.items || []);

	return {
		data: {
			x: 'x',
			type: 'predictive',
			columns: formatPointsForChart(categories, points),
			types: getLineTypesForChart(categories),
			names: getCategoriesNameForChart(categories),
		},
		predictionDate: getPredictionDate(points),
		axis: {
			x: {
				type: 'timeseries',
			},
		},
	};
}

function formatPointForForecast(data) {
	return (data.actual !== NULL_VALUE) ? data.actual : {
		low: data.forecastLowerBound,
		mid: data.forecast,
		high: data.forecastUpperBound,
	};
}

function formatPointsForChart(categories, points) {
	return !points.length ? [] : [
		['x', ...getDates(points)],
		...categories.map(c => [`${c.id}`, ...getValuesForCategory(points, c)]),
	];
}

function getCategoriesArray(items = []) {
	return [...items.reduce((a, c) => {
		a.set(c.category, c.categoryTitle);

		return a;
	}, new Map()).entries()].map(([id, name]) => ({
		id,
		name,
	}));
}

function getCategoriesNameForChart(categories) {
	return categories.reduce(
		(a, c) => Object.assign(a, { [`${c.id}`]: c.name }),
		{}
	);
}

function getDates(points) {
	return points
		.filter(isPartOfCategory(points[0].category))
		.map(d => getDateString(d.timestamp));
}

function getDateString(ts) {
	return ts.slice(0, 10);
}

function getLineTypesForChart(categories) {
	return categories.reduce(
		(a, c) => Object.assign(a, { [`${c.id}`]: 'area-line-range' }),
		{}
	);
}

function getPredictionDate(points) {
	return !points.length ? null : getDateString(points
		.slice()
		.reverse()
		.find(d => d.actual !== NULL_VALUE).timestamp);
}

function getValuesForCategory(points, category) {
	return points
		.filter(isPartOfCategory(category.id))
		.map(formatPointForForecast);
}

function isPartOfCategory(id) {
	return ({ category }) => category === id;
}

export function loadData(url) {
	return fetch(url, { headers: headers })
		.then(responseToJson)
		.then(formatDataForChart);
}

function responseToJson(response) {
	return response.json();
}

function sortPointsByDate(points) {
	return points.sort((a, b) => new Date(a.timestamp) - new Date(b.timestamp));
}
