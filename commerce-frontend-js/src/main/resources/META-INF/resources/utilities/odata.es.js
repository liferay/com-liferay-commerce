export function createOdataFilterString(slug, operator, value) {
	let formattedValue = value;

	if(value instanceof String) {
		formattedValue = `'${value}'`;
	}
	if(value instanceof Object) {
		formattedValue = JSON.stringify(value);
	}

	return `${slug} ${operator} ${formattedValue}`
}

export function createOdataFilterStrings(filters) {
	const oDataFilterStrings = filters.map(filter => {
		if (filter.value instanceof Array) {
			return filter.value.map(
                value => `(${createOdataFilterString(filter.slug, filter.operator, value)})`).join(' or ')
		}
		return createOdataFilterString(filter.slug, filter.operator, filter.value)
    })
        .map(filterString => `(${filterString})`)
        .join(' and ');

	const oDataFilters = `filter=${oDataFilterStrings}`
	return oDataFilters
}