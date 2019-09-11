import React from 'react';
// import classnames from 'classnames';
import ClayList from '@clayui/list';
import Expose from './Expose.es';

function Suggestions({list, selected, action, onHover}) {
	return (
		<>
			<ClayList.Header>Add an existing specification</ClayList.Header>
			{list.map((s, i) => (
				<ClayList.Item
					flex
					key={i}
					className={selected === (i + 1) ? 'is-selected' : ''}
					onMouseEnter={() => onHover(i + 1)}
					onClick={e => action(e, i + 1)}
					tabIndex="0"
				>
					<ClayList.ItemField expand>
						<ClayList.ItemTitle>{s.label}</ClayList.ItemTitle>
					</ClayList.ItemField>
					<ClayList.ItemField>
						<button className="btn btn-monospaced btn-sm btn-primary" type="button" onClick={e => action(e, i + 1)}>
							<svg className="lexicon-icon lexicon-icon-plus" focusable="false" role="presentation">
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
			value: '',
		}
		this.card = React.createRef();
		this.input = React.createRef();

		this.handleKeyDown = this.handleKeyDown.bind(this);
		this.action = this.action.bind(this);
	}

	focus() {
		this.setState({
			focus: true,
		});
		window.addEventListener('keydown', this.handleKeyDown);
		this.props.onFocusIn && this.props.onFocusIn();
		this.input.current.focus();
	}

	blur() {
		this.setState({ focus: false });
		window.removeEventListener('keydown', this.handleKeyDown);
	}

	clear() {
		this.setState({
			value: '',
			selected: 0
		});
	}

	empty() {
		this.clear();
		this.input.current.focus();
	}

	getSelection(step) {
		return (this.state.suggestions.length + 1 + this.state.selected + step) % (this.state.suggestions.length + 1);
	}

	select(selected) {
		this.setState({ selected });
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
			selected: 0,
			focus: true,
			suggestions: this.props.onSearch(e.target.value),
			value: e.target.value,
		});
	}

	componentDidUpdate() {
		if (!this.props.active) {
			this.input.current.blur();
		}
		setTimeout(() => this.card.current.scrollIntoView({behavior: "smooth"}), 0);
	}

	handleFocusOut(e) {
		this._timeoutID = setTimeout(() => {
			this.props.onFocusOut && this.props.onFocusOut();
    }, 0);
	}

	handleFocusIn(e) {
    clearTimeout(this._timeoutID);
	}

	render() {
		return (
			<div
				className={`card add-or-create ${this.state.focus ? 'has-focus' : ''}`}
				ref={this.card}
				onBlur={e => this.handleFocusOut(e)}
				onFocus={e => this.handleFocusIn(e)}
			>
				<div className="card-header">
					Add new option
				</div>
				<div className="card-body">
					<div className="input-group">
						<div className="input-group-item">
							<input
								className="form-control input-group-inset input-group-inset-after"
								placeholder="Find existing option or add a new one"
								type="text"
								onFocus={e => this.focus(e)}
								onBlur={e => this.blur(e)}
								onChange={e => this.onChange(e)}
								value={this.state.value}
								ref={this.input}
							/>
							<span className="input-group-inset-item input-group-inset-item-after">
								{this.state.value &&
									<button
										className="btn btn-unstyled"
										type="button"
										onClick={e => this.empty(e)}
									>
										<svg className="lexicon-icon lexicon-icon-times" focusable="false" role="presentation">
											<use href="./icons.svg#times" />
										</svg>
									</button>
								}
							</span>
						</div>
					</div>
				</div>
				{this.state.value && this.props.active &&
					<div className="card-body">
						<ClayList>
							<ClayList.Header>Create new specification</ClayList.Header>
							<ClayList.Item
								flex
								className={this.state.selected === 0 ? 'is-selected' : ''}
								onMouseEnter={() => this.select(0)}
								onClick={e => this.action(e, 0)}
								tabIndex="0"
							>
								<ClayList.ItemField expand>
									<ClayList.ItemTitle>{this.state.value}</ClayList.ItemTitle>
								</ClayList.ItemField>

								<ClayList.ItemField>
									<button
										className="btn btn-link btn-sm"
										type="button"
										onClick={e => this.action(e, 0)}
									>
										Create new specification
									</button>
								</ClayList.ItemField>
							</ClayList.Item>

							{this.state.suggestions.length ?
								<Suggestions
									list={this.state.suggestions}
									selected={this.state.selected}
									action={this.action}
									onHover={e => this.select(e)}
								/>
							: null}
						</ClayList>
					</div>
				}
			</div>
		);
	}
}


export default React.forwardRef((props, ref) => {
	const [active, setActive] = React.useState(false);
	
	function closeAndSubmit(e) {
		props.onSubmit(e);
		setActive(false)
	}

  return (
		<Expose active={active} onClose={() => setActive(false)}>
			<AddOrCreateBase
				{...props}
				active={active}
				onSubmit={closeAndSubmit}
				onFocusIn={() => setActive(true)}
				onFocusOut={() => setActive(false)}
				innerRef={ref}
			/>
		</Expose>
  );
});
