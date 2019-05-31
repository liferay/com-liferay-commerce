import React, { Component } from 'react';

import Menu from './components/Menu.es';
import Content from './components/Content.es';
import Icon from './components/utilities/Icon.es';

class DynamicPanel extends Component {
	constructor(props) {
		super(props);
		this.state = {
			current: props.current || null
		};
		this.initializeLiferayListener();
		document.querySelector('body').classList.add('with-dynamic-panel');
	}

	initializeLiferayListener() {
		if(window.Liferay) {
			window.Liferay.on('openCustomPageOnDynamicPanel', ({ url, slug }) => {
				this.openCustomPage(url,slug)
			})
		}
	}

	setCurrentBySlug(slug = null) {
		const current = slug
			? {
					slug,
					url: this.props.elements.reduce((acc, el) => {
						return acc || (el.slug === slug && el.url);
					}, null)
			  }
			: null;

		this.setState({
			current
		});
	}

	onError(message) {
		this.setState({
			current: null
		})
		return this.props.onError
			? this.props.onError(message)
			: console.error(message);
	}

	openCustomPage(url, slug = null) {
		if(!url) {
			return false
		}
		this.setState({
			current: {
				slug,
				url
			}
		});
	}

	render() {
		return (
			<div
				className={`dynamic-panel${
					this.state.current !== null ? ' open' : ''
				}`}
			>
				{this.props.elements && (
					<Menu
						spritemap={this.props.spritemap}
						elements={this.props.elements}
						selectBySlug={slug => this.setCurrentBySlug(slug)}
						active={this.state.current && this.state.current.slug}
					/>
				)}
				<Content
					url={this.state.current && this.state.current.url}
					onError={msg => this.onError(msg)}
					close={() => this.setCurrentBySlug()}
					closeIcon={<Icon spritemap={this.props.spritemap} symbol="close" />}
				/>
			</div>
		);
	}
}

export default DynamicPanel;
