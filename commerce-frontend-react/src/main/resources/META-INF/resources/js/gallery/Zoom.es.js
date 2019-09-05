import React from 'react';
// import './zoom.scss';

export default class Zoom extends React.Component {
	constructor(props) {
		super(props);
		this.state = {
			loading: true
		}

		this.zoom = React.createRef();
		this.image = new Image();
		this.image.onload = this.imageLoaded(this.image);
		this.image.src = this.props.image;
	}

	imageLoaded(image) {
		return () => {
			this.setState({ loading: false });
			this.zoom.current && this.zoom.current.appendChild(image);
		}
	}

	componentWillUnmount() {
		this.image.onload = null;
	}

	render() {
		return (
			<div className="gallery-zoom" ref={this.zoom}>
				{this.state.loading && 'Loading'}
			</div>
		);
	}
}