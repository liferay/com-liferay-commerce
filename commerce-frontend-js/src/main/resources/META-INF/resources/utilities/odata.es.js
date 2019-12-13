export function createOdataFilterString(key, operator, value) {
	let formattedValue = value;

	if(value instanceof String) {
		formattedValue = `'${value}'`;
	}
	if(value instanceof Object) {
		formattedValue = JSON.stringify(value);
	}

	return `${key} ${operator} ${formattedValue}`
}

export function createOdataFilterStrings(filters) {
	const oDataFilterStrings = filters.map(filter => {
		if (filter.value instanceof Array) {
			return filter.value.map(
                value => `(${createOdataFilterString(filter.id, filter.operator, value)})`).join(' or ')
		}
		if(filter.main) {
			return `(startwith(${filter.id}, '${filter.value}') eq true)`
		}
		return createOdataFilterString(filter.id, filter.operator, filter.value)
    })
        .map(filterString => `(${filterString})`)
        .join(' and ');

	const oDataFilters = oDataFilterStrings.length ? `$filter=${oDataFilterStrings}` : '';
	return oDataFilters
}