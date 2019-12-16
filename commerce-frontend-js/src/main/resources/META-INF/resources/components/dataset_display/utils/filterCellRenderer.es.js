import React from 'react';

import CheckboxesFilter from '../management_bar/components/filters/Checkboxes.es';
import DateFilter from '../management_bar/components/filters/Date.es';
import DateTimeFilter from '../management_bar/components/filters/DateTime.es';
import NumberFilter from '../management_bar/components/filters/Number.es';
import RadioFilter from '../management_bar/components/filters/Radio.es';
import SelectFilter from '../management_bar/components/filters/Select.es';
import TextFilter from '../management_bar/components/filters/Text.es';

export const filterIdToComponentMap = {
	checkbox: CheckboxesFilter,
	date: DateFilter,
	'date-time': DateTimeFilter,
	number: NumberFilter,
	radio: RadioFilter,
	select: SelectFilter,
	text: TextFilter
};

export const renderFilter = (item, panelType) => {
	const Filter = filterIdToComponentMap[item.type];

	return <Filter {...item} panelType={panelType} />;
};

