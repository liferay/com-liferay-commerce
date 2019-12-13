export function formatFilters(filters) {
	const mainFilter = filters.find(filter => filter.main);

	const formattedFilters = mainFilter ? filters : filters.concat({
		id: 'keyword',
		main: true,
		placeholder: Liferay.Language.get('search-for'),
		value: '',
	})

	return formattedFilters;
}