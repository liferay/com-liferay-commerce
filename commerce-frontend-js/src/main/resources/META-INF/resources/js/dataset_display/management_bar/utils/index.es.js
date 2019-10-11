import React from 'react';

import CheckboxesFilter from '../components/filters/Checkboxes.es';
import DateFilter from '../components/filters/Date.es';
import DateTimeFilter from '../components/filters/DateTime.es';
import NumberFilter from '../components/filters/Number.es';
import RadioFilter from '../components/filters/Radio.es';
import SelectFilter from '../components/filters/Select.es';
import TextFilter from '../components/filters/Text.es';

export const filterSlugToComponentMap = {
	checkbox: CheckboxesFilter,
	date: DateFilter,
	'date-time': DateTimeFilter,
	number: NumberFilter,
	radio: RadioFilter,
	select: SelectFilter,
	text: TextFilter
};

export const renderFilter = (item, panelType) => {
	const Filter = filterSlugToComponentMap[item.type];

	return <Filter {...item} panelType={panelType} />;
};
