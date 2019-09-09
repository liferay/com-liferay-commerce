import React from 'react';

export default class Tabs extends React.Component {
	constructor(props) {
		super(props);
	}

	render() {
		return (
			<ul className="nav nav-underline" role="tablist">
				{this.props.tabs.map(tab => (
					<li className="nav-item" key={tab.url}>
						<a aria-controls="navUnderlineFields" aria-expanded="true" className={`nav-link ${tab.url === this.props.current && 'active'}`} data-toggle="tab" id="navUnderlineFieldsTab" role="tab" onClick={() => this.props.onChange(tab.url)}>{tab.pageName}</a>
					</li>
				))}
			</ul>
		);
	}
}