import React from 'react';
import Tabs from './Tabs.es';
import { globalEval } from 'metal-dom';

export default class SidePanel extends React.Component {
	constructor(props) {
		super(props);
		this.state = {
			show: !!props.show,
			content: '',
			pages: []
		}
		this.selectPane = this.selectPane.bind(this);
		this.content = React.createRef();
	}

	componentDidMount() {
		this.load(this.props.pages);		
	}

	load(pages) {
		this.setState({
			pages: typeof pages === 'string' ? [{url: pages}] : pages,
		}, () => {
			this.setState({
				currentTab: this.state.pages[0].url,
			});
		});
	}

	toggle() {
		this.setState({show: !this.state.show});
	}

	open(content) {
		content && this.load(content);
		this.setState({show: true});
	}

	close() {
		this.setState({show: false});
	}

	selectPane(url) {
		const currentTab = this.state.pages.find(el => el.url === url).url;
		this.setState({ currentTab });
	}

	render() {
		const visibility = this.state.show ? 'is-visible' : 'is-hidden';

		return (
			<div className={`side-panel side-panel--${this.props.size} ${visibility}`}>
				{this.state.pages.length > 1 && <Tabs tabs={this.state.pages} onChange={this.selectPane} current={this.state.currentTab} />}
				<div className="tab-content">
					<div className="active fade show tab-pane" role="tabpanel" ref={this.content}>
						{this.state.show &&
							<iframe src={this.state.currentTab} frameBorder="0"></iframe>
						}
					</div>
				</div>
			</div>
		);
	}
}