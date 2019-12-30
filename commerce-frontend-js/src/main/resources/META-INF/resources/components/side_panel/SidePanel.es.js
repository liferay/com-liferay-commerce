import ClayButton from '@clayui/button';
import ClayIcon from '@clayui/icon';
import ClayLoadingIndicator from '@clayui/loading-indicator';
import ReactDOM from 'react-dom';
import React from 'react';

import {OPEN, OPEN_SIDE_PANEL, IFRAME_LOADED} from '../../utilities/eventsDefinitions.es';
import {debounce} from '../../utilities/index.es';
import { exposeSidePanel } from '../../utilities/sidePanels.es';
import SideMenu from './SideMenu.es';
import { ClayIconSpriteContext } from '@clayui/icon';
import PropTypes from 'prop-types';
import Modal from '../modal/Modal.es';
import { iframeHandlerModalId } from '../../utilities/iframes.es';
export default class SidePanel extends React.Component {
	constructor(props) {
		super(props);
		this.state = {
			active: null,
			currentUrl: props.url || null,
			loading: true,
			moving: false,
			onAfterSubmit: props.onAfterSubmit || null,
			size: props.size || 'md',
			topDistance: 0,
			visible: !!props.visible,
		};
		this.handleIframeClickOnSubmit = this.handleIframeClickOnSubmit.bind(this);
		this.handleIframeSubmit = this.handleIframeSubmit.bind(this);
		this.handleContentLoaded = this.handleContentLoaded.bind(this);
		this.close = this.close.bind(this);
		this.open = this.open.bind(this);
		this.handlePanelOpenEvent = this.handlePanelOpenEvent.bind(this);
		this.updateTop = this.updateTop.bind(this);
		this.debouncedUpdateTop = debounce(this.updateTop, 250);
		this.panel = React.createRef();
		this.iframeRef = React.createRef();
	}

	componentDidMount() {
		if (this.props.topAnchorSelector) {
			window.addEventListener('resize', this.debouncedUpdateTop);
			this.updateTop();
		}
		if(this.props.containerSelector) {
			const container = document.querySelector(this.props.containerSelector);
			if(container) {
				container.classList.add('with-side-panel');
			} else {
				throw new Error(`Container: "${this.props.containerSelector}" not found!`)
			}
		}
		if (Liferay) {
			Liferay.on(OPEN_SIDE_PANEL, this.handlePanelOpenEvent);
			Liferay.on(OPEN, this.handlePanelOpenEvent);
		}

		exposeSidePanel(this.props.id, () => ({
			activeMenuItem: this.state.active,
			size: this.state.size,
			url: this.state.currentUrl,
			visible: this.state.visible,
		}))
	}

	handlePanelOpenEvent(e) {
		if (e.id !== this.props.id) {
			return this.close();
		}

		this.open(e.options.url, e.options.slug);

		this.setState({
			onAfterSubmit: e.options.onAfterSubmit || null
		});
	}

	setSubmitAction(callback = null) {
		this.setState({
			onAfterSubmit: callback
		});
	}

	componentWillUnmount() {
		if (this.props.topAnchorSelector) {
			window.removeEventListener('resize', this.debouncedUpdateTop);
		}
		if (Liferay) {
			Liferay.detach(OPEN_SIDE_PANEL, this.handlePanelOpenEvent);
		}
	}

	updateTop() {
		if(!this.props.topAnchorSelector) {
			return;
		}

		const topAnchor = document.querySelector(this.props.topAnchorSelector);

		if(!topAnchor) {
			return;
		}

		const {height, top} = topAnchor.getBoundingClientRect();

		this.setState({
			topDistance: top + height + 'px'
		});
	}

	load(url, refreshPageAfterSubmit) {
		this.setState(
			{
				currentUrl: url,
				loading: true,
				onAfterSubmit: refreshPageAfterSubmit
					? () => window.location.reload()
					: null
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

	open(url = this.state.currentUrl, active = null) {
		this.setState({active})
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
				active: null,
				currentUrl: null,
				loading: true,
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

	handleIframeSubmit(e) {
		if(e.id !== this.props.id) {
			return;
		}

		Liferay.detach(IFRAME_LOADED, this.handleIframeSubmit);

		if (this.state.onAfterSubmit) {
			this.state.onAfterSubmit();
		}
	}

	handleIframeClickOnSubmit() {
		Liferay.on(IFRAME_LOADED, this.handleIframeSubmit);

		setTimeout(() => {
			Liferay.detach(IFRAME_LOADED, this.handleIframeSubmit);
		}, 3000)
	}

	handleContentLoaded() {
		Liferay.fire(IFRAME_LOADED, {
			id: this.props.id
		})

		this.setState({
			loading: false
		});

		try {
			const iframeBody = this.iframeRef.current.contentDocument.querySelector(
				'body'
			);
	
			if (submitButton) {
				submitButton.addEventListener('click', this.handleIframeClickOnSubmit);
			}
		} catch (error) {
			throw new Error(`Cannot access to iframe body. Url: "${this.state.currentUrl}"`)
		}
	}

	render() {
		const visibility = this.state.visible ? 'is-visible' : 'is-hidden';
		const loading =
			this.state.loading || (this.state.moving && this.state.visible)
				? 'is-loading'
				: '';

		const content = (
			<>
				<Modal id={iframeHandlerModalId} />
				<div
					className={`side-panel side-panel-${this.state.size} ${visibility} ${loading}`}
					ref={this.panel}
					style={{top: this.state.topDistance}}
				>
					{this.props.items && this.props.items.length && (
						<SideMenu
							active={this.state.active}
							items={this.props.items}
							open={this.open}
						/>
					)}

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
				</div>
			</>
		)

		return ReactDOM.createPortal(
			this.props.spritemap 
			? (
				<ClayIconSpriteContext.Provider value={this.props.spritemap}>
					{content}
				</ClayIconSpriteContext.Provider>
			)
			: content,
			this.props.portalWrapperId
				? document.getElementById(this.props.portalWrapperId)
				: document.querySelector('body')
		);
	}
}

SidePanel.propTypes = {
	id: PropTypes.string,
	items: PropTypes.any,
	portalWrapperId: PropTypes.string,
	size: PropTypes.string,
	spritemap: PropTypes.string,
	topAnchorSelector: PropTypes.any,
}