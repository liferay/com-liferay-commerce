import React from 'react';

export default class Tabs extends React.Component {
	constructor(props) {
		super(props);
	}

	render() {
		return (
			<ul className="nav nav-underline" role="tablist">
				{this.props.tabs.map(tab => (
					<li className="nav-item" key={tab.slug}>
						<a aria-controls="navUnderlineFields" aria-expanded="true" className={`nav-link ${tab.slug === this.props.current && 'active'}`} data-toggle="tab" href="#navUnderlineFields" id="navUnderlineFieldsTab" role="tab" onClick={() => this.props.onChange(tab.slug)}>{tab.pageName}</a>
					</li>
				))}
			</ul>
		);
	}
}