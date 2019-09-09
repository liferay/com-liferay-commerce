import React from 'react';
import Tabs from './Tabs.es';
import { globalEval } from 'metal-dom';

import './side_panel.scss';

export default class SidePanel extends React.Component {
	constructor(props) {
		super(props);
		this.state = {
			show: !!props.show,
			tabs: props.tabs,
			currentTab: props.currentTab || props.tabs[0],
			content: '',
		}
		this.selectPane = this.selectPane.bind(this);
		this.content = React.createRef();
	}

	load(tabs) {
		this.setState({
			tabs,
			currentTab: tabs[0],
			show: true,
		});
		this.getContent(tabs[0]);
	}

	toggle() {
		this.setState({show: !this.state.show});
	}

	close() {
		this.setState({show: false});
	}

	selectPane(slug) {
		const currentTab = this.state.tabs.find(el => el.slug === slug);
		this.getContent(currentTab);
	}

	getContent(currentTab) {
		this.setState({ currentTab })
		// return fetch(currentTab.url)
		// 	.then(res => res.text())
		// 	.then(content => this.setState({ content, currentTab }))
		// 	.then(() => globalEval.runScriptsInElement(this.content.current));
	}

	render() {
		const visibility = this.state.show ? 'is-visible' : 'is-hidden';

		return (
			<div className={`side-panel side-panel--${this.props.size} ${visibility}`}>
				{this.state.tabs.length > 1 && <Tabs tabs={this.state.tabs} onChange={this.selectPane} current={this.state.currentTab.slug} />}
				<div className="tab-content">
					<div className="active fade show tab-pane" role="tabpanel" ref={this.content}>
						<iframe src={this.state.currentTab.url} frameBorder="0"></iframe>
					</div>
				</div>
			</div>
		);
	}
}