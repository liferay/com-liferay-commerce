import React, { Component } from 'react';

import Menu from './components/Menu.es';
import Content from './components/Content.es';
import Icon from './components/utilities/Icon.es';

class DynamicPanel extends Component {
	constructor(props) {
		super(props);
		this.state = {
			current: props.current || null,
			content: null
		};
		document.querySelector('body').classList.add('with-dynamic-panel');
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

		return this._getContent(current.url)
			.then(
				() => this.setState({
					current
				})
			);
	}

	onError(message) {
		this._reset();
		return this.props.onError
			? this.props.onError(message)
			: console.error(message);
	}

	openCustomPage(url, slug = null) {
		if(!url) {
			return false
		}
		return this._getContent(url)
			.then(
				() => this.setState({
					current: {
						slug,
						url
					}
				})
			);
	}

	close() {
		this._reset();
	}

	_reset() {
		this.setState({
			current: null,
			content: null
		})
	}

	_getContent(url) {
		return fetch(url)
			.then(res => {
				if(res.status !== 200) {
					throw new Error(`Request failed with statusCode: ${res.status}`);
				}
				return res.text()
			})
			.then(content => {
				return this.setState({
					content
				})
			})
			.catch(err => this.onError(err))
	}

	render() {
		return (
			<div
				className={`dynamic-panel${
					this.state.content ? ' open' : ''
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
					content={this.state.content}
					close={() => this.close()}
					closeIcon={<Icon spritemap={this.props.spritemap} symbol="close" />}
				/>
			</div>
		);
	}
}

export default DynamicPanel;
