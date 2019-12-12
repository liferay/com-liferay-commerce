import PropTypes from 'prop-types';
import React, {useEffect} from 'react';

import ActiveFiltersBar from './components/ActiveFiltersBar.es';
import BulkActions from './components/BulkActions.es';
import useAppState, {StoreProvider} from './components/Context.es';
import NavBar from './components/NavBar.es';

const ManagementBar = props => {
	const {state} = useAppState();

	useEffect(() => {
		if (state.filters.find(filter => filter.slug === 'keyword')) {
			props.onFiltersChange(state.filters);
		}

		// eslint-disable-next-line react-hooks/exhaustive-deps
	}, [JSON.stringify(state.filters)]);

	return (
		<>
			{props.selectedItemsId.length ? (
				<BulkActions
					bulkActions={props.bulkActions}
					selectAllItems={props.selectAllItems}
					selectedItemsId={props.selectedItemsId}
					totalItemsCount={props.totalItemsCount}
				/>
			) : (
				<NavBar
					onFiltersChange={props.onFiltersChange}
				/>
			)}
			<ActiveFiltersBar disabled={!!props.selectedItemsId.length} />
		</>
	);
};

const Wrapper = props => {
	const {filters, ...otherProps} = props;

	const richFilters = filters.concat({
		invisible: true,
		label: '',
		operator: 'startswith',
		slug: 'keyword',
		type: 'text',
	});

	return (
		<StoreProvider filters={richFilters}>
			<ManagementBar {...otherProps} />
		</StoreProvider>
	);
};

const baseValues = {
	label: PropTypes.string,
	operator: PropTypes.oneOf([
		'eq',
		'ne',
		'gt',
		'ge',
		'lt',
		'le',
		'and',
		'or',
		'not',
		'startswith'
	]),
	slug: PropTypes.string
};

Wrapper.propTypes = {
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
				items: PropTypes.arrayOf(
					PropTypes.shape({
						label: PropTypes.string,
						value: PropTypes.oneOfType([
							PropTypes.string,
							PropTypes.number
						])
					})
				),
				type: PropTypes.oneOf(['checkbox']).isRequired,
				value: PropTypes.arrayOf(
					PropTypes.oneOfType([PropTypes.string, PropTypes.number])
				)
			}),
			PropTypes.shape({
				...baseValues,
				items: PropTypes.arrayOf(
					PropTypes.shape({
						label: PropTypes.string,
						value: PropTypes.oneOfType([
							PropTypes.string,
							PropTypes.number
						])
					})
				),
				type: PropTypes.oneOf(['select', 'radio']).isRequired,
				value: PropTypes.oneOfType([PropTypes.string, PropTypes.number])
			}),
			PropTypes.shape({
				...baseValues,
				type: PropTypes.oneOf(['date']).isRequired,
				value: PropTypes.shape({
					day: PropTypes.number,
					month: PropTypes.number,
					year: PropTypes.number
				})
			})
		])
	),
	onFiltersChange: PropTypes.func.isRequired
};

Wrapper.defaultProps = {
	filters: [],
};

export default Wrapper;
