import React from 'react';
import ReactDOM from 'react-dom';
import ClayLoadingIndicator from '@clayui/loading-indicator';
import ClayIcon from '@clayui/icon';
import ClayButton from '@clayui/button';
import {debounce} from '../utilities/entry';
export default class SidePanel extends React.Component {
	constructor(props) {
		super(props);
		this.state = {
			visible: !!props.visible,
			moving: false,
			loading: true,
			tabs: props.tabs,
			currentUrl: props.currentUrl || null,
			size: props.size || 'md',
			topDistance: 0
		}
		this.selectTab = this.selectTab.bind(this);
		this.handleContentLoaded = this.handleContentLoaded.bind(this);
		this.close = this.close.bind(this);
		this.updateTop = this.updateTop.bind(this);
		this.debouncedUpdateTop = debounce(this.updateTop, 250);
		this.panel = React.createRef();
		this.iframeRef = React.createRef();
	}

	componentDidMount() {
		if(this.props.topAnchor) {
			window.addEventListener('resize', this.debouncedUpdateTop);
			this.updateTop();
		}
	}

	componentWillUnmount() {
		if(this.props.topAnchor) {
			window.removeEventListener('resize', this.debouncedUpdateTop);
		}
	}

	updateTop() {
		const {top, height} = this.props.topAnchor.getBoundingClientRect()
		this.setState({
			topDistance: top + height + 'px'
		})
	}

	setTabs(tabs = []) {
		this.setState({
			tabs
		})
	}

	load(url) {
		this.setState({
			loading: true,
			currentUrl: url
		}, () => {
			if(
				this.iframeRef.current && 
				this.iframeRef.current.contentWindow
			) {
				this.iframeRef.current.contentWindow.location = this.state.currentUrl;
			}
		});
	}

	setSize(size = 'md') {
		this.setState({size});
	}

	open(url = this.state.currentUrl) {
		if(!this.state.visible) {
			this.toggle(true)
				.then(() => {
					this.load(url);
				});
		} else {
			this.load(url)
		}
	}

	close() {
		this.toggle(false)
			.then(() => {
				this.setState({
					currentUrl: null,
					loading: true
				})
			})
	}

	toggle(status = !this.state.visible) {
		return new Promise((resolve) => {
			this.setState({visible: status, moving: true});
			this.panel.current.addEventListener(
				'transitionend',
				() => {
					this.setState(
						{moving: false},
						() => resolve(status)
					);
				},
				{
					once: true
				}
			);
		})
	}

	selectTab(url) {
		const currentUrl = this.state.pages.find(el => el.url === url).url;
		this.setState({
			loading: true,
			currentUrl
		});
	}

	handleContentLoaded() {
		this.setState({
			loading: false,
		});
		const iframeBody = this.iframeRef.current.contentDocument.querySelector('body');
		iframeBody.classList.add('within-commerce-iframe')
	}

	render() {
		const visibility = this.state.visible ? 'is-visible' : 'is-hidden';
		const loading = this.state.loading || (this.state.moving && this.state.visible) ? 'is-loading' : '';
		
		return ReactDOM.createPortal(
			<div 
				className={`side-panel side-panel-${this.state.size} ${visibility} ${loading}`}
				style={{top: this.state.topDistance}}
				ref={this.panel}
			>
				<ClayButton 
					displayType="monospaced"
					className="btn-close"
					onClick={() => this.close()}
				>
					<ClayIcon symbol="times" spritemap={this.props.spritemap} />
				</ClayButton>
				
				<div className="tab-content">
					<div className="loader">
						<ClayLoadingIndicator />
					</div>
					<div className="active fade show tab-pane" role="tabpanel">
						{
							!(this.state.moving && this.state.visible) &&
							<iframe 
								src={this.state.currentUrl}
								frameBorder="0"
								ref={this.iframeRef}
								onLoad={this.handleContentLoaded}
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