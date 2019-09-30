import ClayList from '@clayui/list';
import React from 'react';

import Expose from './Expose.es';

function Suggestions({action, list, onHover, selected}) {
	return (
		<>
			<ClayList.Header>{Liferay.Language.get('add-an-existing-specification')}</ClayList.Header>
			{list.map((s, i) => (
				<ClayList.Item
					className={selected === i + 1 ? 'is-selected' : ''}
					flex
					key={i}
					onClick={e => action(e, i + 1)}
					onMouseEnter={() => onHover(i + 1)}
					tabIndex="0"
				>
					<ClayList.ItemField expand>
						<ClayList.ItemTitle>{s.label}</ClayList.ItemTitle>
					</ClayList.ItemField>
					<ClayList.ItemField>
						<button
							className="btn btn-monospaced btn-sm btn-primary"
							onClick={e => action(e, i + 1)}
							type="button"
						>
							<svg
								className="lexicon-icon lexicon-icon-plus"
								focusable="false"
								role="presentation"
							>
								<use href="./icons.svg#plus"></use>
							</svg>
						</button>
					</ClayList.ItemField>
				</ClayList.Item>
			))}
		</>
	);
}

class AddOrCreateBase extends React.Component {
	constructor(props) {
		super(props);
		this.state = {
			focus: false,
			selected: 0,
			suggestions: [],
			value: ''
		};
		this.card = React.createRef();
		this.input = React.createRef();

		this.handleKeyDown = this.handleKeyDown.bind(this);
		this.action = this.action.bind(this);
	}

	focus() {
		this.setState({
			focus: true
		});
		window.addEventListener('keydown', this.handleKeyDown);
		if(this.props.onFocusIn) {
			this.props.onFocusIn();
		}
		this.input.current.focus();
	}

	blur() {
		this.setState({focus: false});
		window.removeEventListener('keydown', this.handleKeyDown);
	}

	clear() {
		this.setState({
			selected: 0,
			value: ''
		});
	}

	empty() {
		this.clear();
		this.input.current.focus();
	}

	getSelection(step) {
		return (
			(this.state.suggestions.length + 1 + this.state.selected + step) %
			(this.state.suggestions.length + 1)
		);
	}

	select(selected) {
		this.setState({selected});
	}

	handleKeyDown(e) {
		switch (e.key) {
			case 'ArrowDown':
				e.preventDefault();
				this.select(this.getSelection(1));
				break;
			case 'ArrowUp':
				e.preventDefault();
				this.select(this.getSelection(-1));
				break;
			case 'Enter':
				e.preventDefault();
				this.submit();
				break;
			default:
		}
	}

	action(e, i) {
		e.preventDefault();
		e.stopPropagation();
		this.submit(i);
	}

	submit(el = this.state.selected) {
		if (el > 0) {
			this.props.onSubmit({
				action: 'add',
				value: this.state.suggestions[el - 1]
			});
		} else if (this.state.value) {
			this.props.onSubmit({
				action: 'create',
				value: {id: null, label: this.state.value}
			});
		}
		this.clear();
	}

	onChange(e) {
		this.setState({
			focus: true,
			selected: 0,
			suggestions: this.props.onSearch(e.target.value),
			value: e.target.value
		});
	}

	componentDidUpdate() {
		if (!this.props.active) {
			this.input.current.blur();
		}
		setTimeout(
			() => this.card.current.scrollIntoView({behavior: 'smooth'}),
			0
		);
	}

	handleFocusOut() {
		this._timeoutID = setTimeout(() => {
			if(this.props.onFocusOut) {
				this.props.onFocusOut();
			}
		}, 0);
	}

	handleFocusIn() {
		clearTimeout(this._timeoutID);
	}

	render() {
		return (
			<div
				className={`card add-or-create ${
					this.state.focus ? 'has-focus' : ''
				}`}
				onBlur={e => this.handleFocusOut(e)}
				onFocus={e => this.handleFocusIn(e)}
				ref={this.card}
			>
				<div className="card-header">Add new option</div>
				<div className="card-body">
					<div className="input-group">
						<div className="input-group-item">
							<input
								className="form-control input-group-inset input-group-inset-after"
								onBlur={e => this.blur(e)}
								onChange={e => this.onChange(e)}
								onFocus={e => this.focus(e)}
								placeholder="Find existing option or add a new one"
								ref={this.input}
								type="text"
								value={this.state.value}
							/>
							<span className="input-group-inset-item input-group-inset-item-after">
								{this.state.value && (
									<button
										className="btn btn-unstyled"
										onClick={e => this.empty(e)}
										type="button"
									>
										<svg
											className="lexicon-icon lexicon-icon-times"
											focusable="false"
											role="presentation"
										>
											<use href="./icons.svg#times" />
										</svg>
									</button>
								)}
							</span>
						</div>
					</div>
				</div>
				{this.state.value && this.props.active && (
					<div className="card-body">
						<ClayList>
							<ClayList.Header>
								Create new specification
							</ClayList.Header>
							<ClayList.Item
								className={
									this.state.selected === 0
										? 'is-selected'
										: ''
								}
								flex
								onClick={e => this.action(e, 0)}
								onMouseEnter={() => this.select(0)}
								tabIndex="0"
							>
								<ClayList.ItemField expand>
									<ClayList.ItemTitle>
										{this.state.value}
									</ClayList.ItemTitle>
								</ClayList.ItemField>

								<ClayList.ItemField>
									<button
										className="btn btn-link btn-sm"
										onClick={e => this.action(e, 0)}
										type="button"
									>
									</button>
								</ClayList.ItemField>
										Create new specification
							</ClayList.Item>

							{this.state.suggestions.length ? (
								<Suggestions
									action={this.action}
									list={this.state.suggestions}
									onHover={e => this.select(e)}
									selected={this.state.selected}
								/>
							) : null}
						</ClayList>
					</div>
				)}
			</div>
		);
	}
}

export default React.forwardRef((props, ref) => {
	const [active, setActive] = React.useState(false);

	function closeAndSubmit(e) {
		props.onSubmit(e);
		setActive(false);
	}

	return (
		<Expose active={active} onClose={() => setActive(false)}>
			<AddOrCreateBase
				{...props}
				active={active}
				innerRef={ref}
				onFocusIn={() => setActive(true)}
				onFocusOut={() => setActive(false)}
				onSubmit={closeAndSubmit}
			/>
		</Expose>
	);
});
