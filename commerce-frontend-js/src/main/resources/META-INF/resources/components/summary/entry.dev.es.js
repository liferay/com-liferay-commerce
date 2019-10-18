import launcher from './entry.es';

launcher('summary', 'summary', {
	items: [
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
			style: 'big',
			value: '$ 2,258.50'
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
