import launcher from './entry.es';

window.Table = launcher('table', 'table', {
	spritemap: './icons.svg',
	items: [
		{
			id: 'asd',
			panelLink: 'http://link-test.html'
		}
	],
	// dataProviderKey: 'test',
	// dataSetAPI: 'http://endpoint.html',
	// pageSize: 10,
	// paginationEntries: [
	// 	{
	// 		label: 10,
	// 	},
	// ],

	summaryName: 'Table summary',
	summaryItems: [
		{
			label: 'Items Subtotal',
			value: '$ 2,208.50'
		},
		{
			label: 'Order Discount',
			value: '$ 0.00'
		},
		{
			label: 'Promotion codes',
			value: '--'
		},
		{
			label: 'Estimated Tax',
			value: '$ 0.00'
		},
		{
			label: 'Shipping & Handing',
			value: '$ 50.00'
		},
		{
			label: 'Grand Total',
			value: '$ 2,258.50',
			style: 'big'
		},
		{
			style: 'divider'
		},
		{
			label: 'Total return refounds',
			style: 'danger',
			value: '$ 0.00'
		},
		{
			label: 'Total appeasement refounds',
			style: 'danger',
			value: '$ 0.00'
		}
	]
});
