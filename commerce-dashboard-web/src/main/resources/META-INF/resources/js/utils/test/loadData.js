/**
 * © 2019 Liferay, Inc. <https://liferay.com>
 *
 * SPDX-License-Identifier: BSD-3-Clause
 */

import {
	formatCategoriesForChart,
	formatPointForForecast,
	formatPointsForChart,
	getCategoriesArray,
	getDates,
	getDateString,
	getLineTypesForChart,
	getPoints,
	getPredictionDate,
	getValuesForCategory,
	isPartOfCategory,
	parseData,
} from '../loadData.es';

import {
	input,
	output,
	simpleInput,
	simpleOutput,
} from '../mock/data';

const categoriesSubset = getCategoriesArray(simpleInput.items);

const loadData = data => new Promise(resolve => resolve(data));

describe('Chart loadData utils', () => {
	it('Format categories for chart', () => {
		expect(formatCategoriesForChart(categoriesSubset)).toEqual({
			1: 'Cat 1',
			2: 'Cat 2',
		});
	});

	it('Format point for forecast', () => {
		const pastItem = simpleInput.items.find(i => i.actual !== 1.4e-45);
		const futureItem = simpleInput.items.find(i => i.actual === 1.4e-45);

		expect(formatPointForForecast(pastItem)).toBe(pastItem.actual);
		expect(formatPointForForecast(futureItem)).toEqual({
			low: futureItem.forecastLowerBound,
			mid: futureItem.forecast,
			high: futureItem.forecastUpperBound,
		});
	});

	it('Format points for chart', () => {
		expect(formatPointsForChart(simpleInput.items, categoriesSubset)).toEqual([
			[
				'x',
				'2019-04-01',
				'2019-05-01',
			],
			[
				'1',
				15,
				{
					high: 18,
					low: 12,
					mid: 16,
				},
			],
			[
				'2',
				25,
				{
					high: 28,
					low: 22,
					mid: 26,
				},
			],
		]);
		expect(formatPointsForChart([], [])).toEqual([]);
	});

	it('Get categories array', () => {
		expect(getCategoriesArray(simpleInput.items)).toEqual([{
			id: 1,
			name: 'Cat 1',
		}, {
			id: 2,
			name: 'Cat 2',
		}]);
	});

	it('Get dates list', () => {
		expect(getDates(simpleInput.items)).toEqual([
			'2019-04-01',
			'2019-05-01',
		]);
	});

	it('Convert date to string', () => {
		expect(getDateString('2019-07-01T00:00:00Z')).toBe('2019-07-01');
		expect(getDateString('')).toBe('');
		expect(getDateString(undefined)).toBe('');
	});

	it('Get line types for chart', () => {
		expect(getLineTypesForChart(categoriesSubset)).toEqual({
			1: 'area-line-range',
			2: 'area-line-range',
		});
	});

	it('Extract points from data object', () => {
		expect(getPoints(simpleInput)).toEqual(simpleInput.items);
	});

	it('Find the prediction date', () => {
		expect(getPredictionDate(input.items)).toBe('2019-11-01');
		expect(getPredictionDate(simpleInput.items)).toBe('2019-04-01');
	});

	it('Get values for a category', () => {
		expect(getValuesForCategory(simpleInput.items, { id: 1 })).toEqual([
			15,
			{
				low: 12,
				mid: 16,
				high: 18,
			},
		]);
	});

	it('Is part of a category', () => {
		expect(isPartOfCategory(1)({ category: 1 })).toBe(true);
		expect(isPartOfCategory(1)({ category: 2 })).toBe(false);
		expect(isPartOfCategory(2)({ category: 1 })).toBe(false);
	});

	it('Parse data', () => {
		return parseData(loadData(input)).then(data => {
			expect(data).toEqual(output);
		});
	});

	it('Parse simple data', () => {
		return parseData(loadData(simpleInput)).then(data => {
			expect(data).toEqual(simpleOutput);
		});
	});
});
