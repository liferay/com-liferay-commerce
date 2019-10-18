import ClayButton from '@clayui/button';
import ClayIcon from '@clayui/icon';
import ClayLoadingIndicator from '@clayui/loading-indicator';
import ReactDOM from 'react-dom';
import React from 'react';

import {OPEN_SIDE_PANEL} from '../../utilities/eventsDefinitions.es';
import {debounce} from '../../utilities/index.es';
export default class SidePanel extends React.Component {
	constructor(props) {
		super(props);
		this.state = {
			currentUrl: props.url || null,
			loading: true,
			moving: false,
			size: props.size || 'md',
			topDistance: 0,
			visible: !!props.visible
		};
		this.handleContentLoaded = this.handleContentLoaded.bind(this);
		this.close = this.close.bind(this);
		this.handlePanelOpenEvent = this.handlePanelOpenEvent.bind(this);
		this.updateTop = this.updateTop.bind(this);
		this.debouncedUpdateTop = debounce(this.updateTop, 250);
		this.panel = React.createRef();
		this.iframeRef = React.createRef();
	}

	componentDidMount() {
		if (this.props.topAnchor) {
			window.addEventListener('resize', this.debouncedUpdateTop);
			this.updateTop();
		}
		if (Liferay) {
			Liferay.on(OPEN_SIDE_PANEL, this.handlePanelOpenEvent);
		}
	}

	handlePanelOpenEvent(e) {
		if (e.id !== this.props.id) {
			return this.close();
		}

		this.open(e.options.url);

		if (e.options.onAfterSubmit) {
			this.setState({
				onAfterSubmit: e.options.onAfterSubmit
			});
		}
	}

	componentWillUnmount() {
		if (this.props.topAnchor) {
			window.removeEventListener('resize', this.debouncedUpdateTop);
		}
		if (Liferay) {
			Liferay.detach(OPEN_SIDE_PANEL, this.handlePanelOpenEvent);
		}
	}

	updateTop() {
		const {height, top} = this.props.topAnchor.getBoundingClientRect();

		this.setState({
			topDistance: top + height + 'px'
		});
	}

	load(url) {
		this.setState(
			{
				currentUrl: url,
				loading: true
			},
			() => {
				if (
					this.iframeRef.current &&
					this.iframeRef.current.contentWindow
				) {
					this.iframeRef.current.contentWindow.location = this.state.currentUrl;
				}
			}
		);
	}

	setSize(size) {
		if (!size) {
			new Error('Size parameter is mandatory');
		}
		this.setState({size});
	}

	open(url = this.state.currentUrl) {
		switch (true) {
			case !this.state.visible:
				return this.toggle(true).then(() => {
					this.load(url);
				});
			case url !== this.state.currentUrl:
				return this.load(url);
			default:
				break;
		}
	}

	close() {
		this.toggle(false).then(() => {
			this.setState({
				currentUrl: null,
				loading: true
			});
		});
	}

	toggle(status = !this.state.visible) {
		return new Promise(resolve => {
			this.setState({moving: true, visible: status});
			this.panel.current.addEventListener(
				'transitionend',
				() => {
					this.setState({moving: false}, () => resolve(status));
				},
				{
					once: true
				}
			);
		});
	}

	handleContentLoaded() {
		this.setState({
			loading: false
		});

		const iframeBody = this.iframeRef.current.contentDocument.querySelector(
			'body'
		);

		iframeBody.classList.add('within-commerce-iframe');

		const submitButton = iframeBody.querySelector('[type="submit"]');
		if (submitButton) {
			submitButton.addEventListener('click', () => {
				if (this.props.onSubmit) {
					this.props.onSubmit();
				}
			});
		}
	}

	render() {
		const visibility = this.state.visible ? 'is-visible' : 'is-hidden';
		const loading =
			this.state.loading || (this.state.moving && this.state.visible)
				? 'is-loading'
				: '';

		return ReactDOM.createPortal(
			<div
				className={`side-panel side-panel-${this.state.size} ${visibility} ${loading}`}
				ref={this.panel}
				style={{top: this.state.topDistance}}
			>
				<ClayButton
					className="btn-close"
					displayType="monospaced"
					onClick={() => this.close()}
				>
					<ClayIcon spritemap={this.props.spritemap} symbol="times" />
				</ClayButton>

				<div className="tab-content">
					<div className="loader">
						<ClayLoadingIndicator />
					</div>
					<div className="active fade show tab-pane" role="tabpanel">
						{!(this.state.moving && this.state.visible) && (
							<iframe
								frameBorder="0"
								onLoad={this.handleContentLoaded}
								ref={this.iframeRef}
								src={this.state.currentUrl}
							></iframe>
						)}
					</div>
				</div>
			</div>,
			this.props.portalWrapperId
				? document.getElementById(this.props.portalWrapperId)
				: document.querySelector('body')
		);
	}
}
