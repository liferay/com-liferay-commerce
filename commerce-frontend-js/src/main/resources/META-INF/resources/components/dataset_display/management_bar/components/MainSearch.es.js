import Icon from '@clayui/icon';
import React, { useState, useEffect } from 'react';
import classNames from 'classnames'
import getAppContext from './Context.es';

const MainSearch = () => {
	const {actions, state} = getAppContext();
	const mainFilter = state.filters.find(f => f.main);

	const [ inputValue, updateInputValue ] = useState((mainFilter && mainFilter.value) || '');

	useEffect(() => {
		updateInputValue(mainFilter.value || '');
	}, [mainFilter.value])

	return (
		<div className="d-inline">
			<form
				onSubmit={e => {
					e.preventDefault();
					actions.updateFilterValue(mainFilter.id, inputValue)
				}}
				role="search"
			>
				<div className="input-group">
					<div className="input-group-item">
						<input
							className="form-control input-group-inset input-group-inset-after"
							onChange={e => updateInputValue(e.target.value)}
							placeholder={mainFilter.placeholder || Liferay.Language.get(
								'search-for'
							)}
							type="text"
							value={inputValue}
						/>
						<span className="input-group-inset-item input-group-inset-item-after">
							<button
									className={classNames("btn btn-unstyled", !inputValue.length && 'invisible')}
									disabled={!inputValue.length}
									onClick={() => updateInputValue('')}
									type="button"
								>
								<Icon symbol="times-circle" />
							</button>
							<button
								className="btn btn-unstyled"
								onSubmit={e => {
									e.preventDefault();
									actions.updateFilterValue(mainFilter.id, inputValue)
								}}
							>
								<Icon symbol="search" />
							</button>
						</span>
					</div>
				</div>
			</form>
		</div>
	);
};

export default MainSearch;
