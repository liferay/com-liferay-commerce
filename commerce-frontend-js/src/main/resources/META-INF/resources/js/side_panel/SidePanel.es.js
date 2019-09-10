import React from 'react';
import Tabs from './Tabs.es';
import ClayLoadingIndicator from '@clayui/loading-indicator';

export default class SidePanel extends React.Component {
	constructor(props) {
		super(props);
		this.state = {
			show: !!props.show,
			animating: false,
			loading: !!props.pages,
			loaded: !!props.pages,
			pages: this.formatPages(props.pages),
			currentTab: this.getFirstTab(props.pages),
			size: props.size || 'medium',
		}
		this.selectTab = this.selectTab.bind(this);
		this.contentLoaded = this.contentLoaded.bind(this);
		this.panel = React.createRef();
	}

	load(pages, size = this.state.size) {
		this.setState({
			loading: true,
			loaded: false,
			pages: this.formatPages(pages),
			currentTab: this.getFirstTab(pages),
			size,
		});
	}

	setSize(size = 'medium') {
		this.setState({size});
	}

	getFirstTab(pages = '') {
		return typeof pages === 'string' ? pages : pages[0].url
	}

	formatPages(pages = []) {
		return typeof pages === 'string' ? [{url: pages}] : pages;
	}

	open(content = false, size) {
		content && this.load(content, size);
		this.toggle(true);
	}

	close() {
		this.toggle(false);
	}

	toggle(status = !this.state.show) {
		this.setState({show: status, animating: true});
		this.panel.current.addEventListener('transitionend', () => {
			this.setState({animating: false});
		}, {once: true});
	}

	selectTab(url) {
		const currentTab = this.state.pages.find(el => el.url === url).url;
		this.setState({
			loading: true,
			loaded: false,
			currentTab
		});
	}

	contentLoaded() {
		this.setState({
			loading: false,
			loaded: true,
		});
	}

	showIframe() {
		return this.state.loaded || (this.state.show ^ this.state.animating);
	}

	render() {
		const visibility = this.state.show ? 'is-visible' : 'is-hidden';
		const loading = this.state.loading ? 'is-loading' : '';

		return (
			<div className={`side-panel side-panel--${this.state.size} ${visibility} ${loading}`} ref={this.panel}>
				{this.state.pages.length > 1 && <Tabs tabs={this.state.pages} onChange={this.selectTab} current={this.state.currentTab} />}
				<div className="tab-content">
					<div className="side-panel__loader"><ClayLoadingIndicator /></div>
					<div className="active fade show tab-pane" role="tabpanel">
						{this.showIframe() && <iframe src={this.state.currentTab} frameBorder="0" onLoad={this.contentLoaded}></iframe>}
					</div>
				</div>
			</div>
		);
	}
}