import React from 'react';
import Images from './Images.es';
import Upload from './Upload.es';
import Zoom from './Zoom.es';

export default class Gallery extends React.Component {
	constructor(props) {
		super(props);

		this.onMouseEnter = this.onMouseEnter.bind(this);
		this.onMouseLeave = this.onMouseLeave.bind(this);
		this.onClick = this.onClick.bind(this);

		this.state = {
			zoom: false
		};
	}

	onClick(tabs) {
		this.props.onImageClick && this.props.onImageClick(tabs);
	}

	onMouseEnter(image) {
		this.setState({
			zoom: image
		});
	}

	onMouseLeave() {
		this.setState({
			zoom: false
		});
	}

	render() {
		return (
			<div className="row">
				<div className="col-sm">
					<Images
						images={this.props.images}
						onMouseEnter={this.onMouseEnter}
						onMouseLeave={this.onMouseLeave}
						onClick={this.onClick}
					/>
				</div>
				<div className="col-sm">
					<Upload />
					{this.state.zoom ? <Zoom image={this.state.zoom} /> : null}
				</div>
			</div>
		);
	}
}
