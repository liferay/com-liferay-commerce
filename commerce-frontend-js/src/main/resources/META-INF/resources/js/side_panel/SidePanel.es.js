import React from 'react';
import ReactDOM from 'react-dom';
import ClayLoadingIndicator from '@clayui/loading-indicator';
import ClayIcon from '@clayui/icon';
import ClayButton from '@clayui/button';
import {ClayPortal} from '@clayui/shared';
export default class SidePanel extends React.Component {
	constructor(props) {
		super(props);
		this.state = {
			visible: !!props.visible,
			animating: false,
			loading: !!props.pages,
			loaded: !!props.pages,
			tabs: props.tabs,
			currentUrl: props.tabs || null,
			size: props.size || 'md',
		}
		this.selectTab = this.selectTab.bind(this);
		this.contentLoaded = this.contentLoaded.bind(this);
		this.close = this.close.bind(this);
		this.panel = React.createRef();
	}

	setTabs(tabs = []) {
		this.setState({
			tabs
		})
	}

	load(url) {
		this.setState({
			loading: true,
			loaded: false,
			currentUrl: url
		});
	}

	setSize(size = 'md') {
		this.setState({size});
	}

	open(url) {
		url && this.load(url);
		this.toggle(true);
	}

	close(destroy = false) {
		this.toggle(false);
		destroy && this.panel.current.addEventListener(
			'transitionend',
			() => {
				this.setState({
					loading: true,
					loaded: false,
				});
			},
			{
				once: true
			}
		);
	}

	toggle(status = !this.state.visible) {
		this.setState({visible: status, animating: true});
		this.panel.current.addEventListener(
			'transitionend',
			() => {
				this.setState({animating: false});
			},
			{
				once: true
			}
		);
	}

	selectTab(url) {
		const currentUrl = this.state.pages.find(el => el.url === url).url;
		this.setState({
			loading: true,
			loaded: false,
			currentUrl
		});
	}

	contentLoaded() {
		this.setState({
			loading: false,
			loaded: true,
		});
	}

	showIframe() {
		return this.state.loaded || (this.state.visible ^ this.state.animating);
	}

	render() {
		const visibility = this.state.visible ? 'is-visible' : 'is-hidden';
		const loading = this.state.loading ? 'is-loading' : '';
		
		return ReactDOM.createPortal(
			<div 
				className={`side-panel side-panel-${this.state.size} ${visibility} ${loading}`} 
				ref={this.panel}
			>
				<ClayButton 
					displayType="monospaced"
					className="btn-close"
					onClick={this.close}
				>
					<ClayIcon symbol="times" spritemap={this.props.spritemap} />
				</ClayButton>
				
				<div className="tab-content">
					<div className="loader">
						<ClayLoadingIndicator />
					</div>
					<div className="active fade show tab-pane" role="tabpanel">
						{
							this.showIframe() && 
							<iframe 
								src={this.state.currentUrl}
								frameBorder="0" 
								onLoad={this.contentLoaded}
							></iframe>
						}
					</div>
				</div>
			</div>,
			this.props.portalWrapperId
				? document.getElementById(this.props.portalWrapperId)
				: document.querySelector('body')
		);
	}
}