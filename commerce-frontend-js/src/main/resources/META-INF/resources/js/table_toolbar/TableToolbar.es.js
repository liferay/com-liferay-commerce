import {ClayIconSpriteContext} from '@clayui/icon';
import PropTypes from 'prop-types';
import React, {useEffect, useState} from 'react';

import ActiveFiltersBar from './ActiveFiltersBar.es';
import useAppState, {StoreProvider} from './Context.es';
import NavBar from './NavBar.es';

const TableToolbar = () => {
	const {state} = useAppState();
	const [initialized, setInitialized] = useState(false);

	useEffect(() => {
		if (!initialized) {
			return setInitialized(true);
		}
		if(state.onFilterChange) {
			const serializedFilters = state.filters.concat({
				label: '',
				operator: 'contains',
				slug: 'keyword',
				type: 'text',
				value: state.inputSearch.value,
			});
			state.onFilterChange(serializedFilters);
		}
	}, [
		initialized,
		state.inputSearch.value,
		state,
	]);

	return (
		<>
			<NavBar />
			<ActiveFiltersBar />
		</>
	)
}

const TableToolbarWrapper = props => {
	return (
		<StoreProvider {...props}>
			<ClayIconSpriteContext.Provider value={props.spritemap}>
				<TableToolbar />
			</ClayIconSpriteContext.Provider>
		</StoreProvider>
	);
};

const baseValues = {
	label: PropTypes.string,
	operator: PropTypes.oneOf([
		'eq',
		'neq',
		'isnull',
		'isnotnull',
		'lt',
		'lte',
		'gt',
		'gte',
		'startswith',
		'doesnotstartwith',
		'endswith',
		'doesnotendwith',
		'contains',
		'doesnotcontain',
		'isempty',
		'isnotempty'
	]),
	slug: PropTypes.string
}

TableToolbarWrapper.propTypes = {
	filters: PropTypes.arrayOf(
		PropTypes.oneOfType([
			PropTypes.shape({
				...baseValues,
				inputText: PropTypes.string,
				max: PropTypes.number,
				min: PropTypes.number,
				type: PropTypes.oneOf(['number']).isRequired,
				value: PropTypes.number
			}),
			PropTypes.shape({
				...baseValues,
				inputText: PropTypes.string,
				type: PropTypes.oneOf(['text']).isRequired,
				value: PropTypes.string
			}),
			PropTypes.shape({
				...baseValues,
				items: PropTypes.arrayOf(PropTypes.shape({
					label: PropTypes.string,
					value: PropTypes.oneOfType([
						PropTypes.string,
						PropTypes.number
					]),
				})),
				type: PropTypes.oneOf(['checkbox']).isRequired,
				value: PropTypes.arrayOf(PropTypes.oneOfType([
					PropTypes.string,
					PropTypes.number
				]))
			}),
			PropTypes.shape({
				...baseValues,
				items: PropTypes.arrayOf(PropTypes.shape({
					label: PropTypes.string,
					value: PropTypes.oneOfType([
						PropTypes.string,
						PropTypes.number
					]),
				})),
				type: PropTypes.oneOf(['select', 'radio']).isRequired,
				value: PropTypes.oneOfType([
					PropTypes.string,
					PropTypes.number
				])
			}),
			PropTypes.shape({
				...baseValues,
				type: PropTypes.oneOf(['date']).isRequired,
				value: PropTypes.shape({
					day: PropTypes.number,	
					month: PropTypes.number,	
					year: PropTypes.number,	
				})
			}),
		])
	),
	onFilterChange: PropTypes.func.isRequired,
	spritemap: PropTypes.string.isRequired,
}

export default TableToolbarWrapper;
