import React from 'react';
import PropTypes from 'prop-types';
import MainImage from './MainImage';
import Overlay from './Overlay';
import Thumbnails from './Thumbnails';

function fetchImage(url) {
	return new Promise(resolve => {
		const img = new Image();

		img.src = url;

		img.onload = () => resolve(url);
	});
}

export default class Gallery extends React.Component {
	constructor(props) {
		super(props);

		this.state = {
			fullscreen: false,
			loaded: new Set(),
			loading: false,
			selected: 0
		};

		this.fullscreenClose = this.fullscreenClose.bind(this);
		this.fullscreenOpen = this.fullscreenOpen.bind(this);
		this.goTo = this.goTo.bind(this);
		this.goToNext = this.goToNext.bind(this);
		this.goToPrev = this.goToPrev.bind(this);
		this.imageLoad = this.imageLoad.bind(this);
		this.imageSelect = this.imageSelect.bind(this);
	}

	fullscreenOpen() {
		if (!this.state.loading) {
			this.imageLoad(this.props.images[this.state.selected].bigUrl).then(
				() => {
					this.setState({fullscreen: true});
				}
			);
		}
	}

	fullscreenClose() {
		this.setState({fullscreen: false});
	}

	goTo(pos) {
		this.imageSelect(
			(this.props.images.length + pos) % this.props.images.length
		);
	}

	goToPrev(e) {
		e.stopPropagation();
		this.goTo(this.state.selected - 1);
	}

	goToNext(e) {
		e.stopPropagation();
		this.goTo(this.state.selected + 1);
	}

	imageLoad(imageUrl) {
		return new Promise(resolve => {
			if (this.state.loaded.has(imageUrl)) {
				resolve(imageUrl);
			} else {
				this.setState({loading: true});
				fetchImage(imageUrl).then(() => {
					this.setState(
						{
							loaded: new Set(this.state.loaded).add(imageUrl),
							loading: false
						},
						() => {
							resolve(imageUrl);
						}
					);
				});
			}
		});
	}

	imageSelect(toSelect) {
		if (toSelect !== this.state.selected && !this.state.loading) {
			this.imageLoad(this.props.images[toSelect].smallUrl).then(() => {
				this.setState({selected: toSelect});
			});
		}
	}

	render() {
		const {images, background} = this.props;
		const {fullscreen, loading, selected} = this.state;

		return (
			<div className="product-gallery">
				<MainImage
					background={background}
					loading={loading}
					onNext={images.length > 1 ? this.goToNext : null}
					onPrev={images.length > 1 ? this.goToPrev : null}
					onZoom={this.fullscreenOpen}
					src={images[selected].smallUrl}
					title={images[selected].title}
				/>

				{images.length > 1 ? (
					<Thumbnails
						background={background}
						images={images}
						onChange={this.imageSelect}
						selected={selected}
					/>
				) : null}

				{fullscreen ? (
					<Overlay
						background={background}
						onClose={this.fullscreenClose}
						onNext={images.length > 1 ? this.goToNext : null}
						onPrev={images.length > 1 ? this.goToPrev : null}
						src={images[selected].bigUrl}
						title={images[selected].title}
					/>
				) : null}
			</div>
		);
	}
}

Gallery.propTypes = {
	background: PropTypes.string,
	images: PropTypes.arrayOf(
		PropTypes.shape({
			bigUrl: PropTypes.string.isRequired,
			smallUrl: PropTypes.string.isRequired,
			thumbnailUrl: PropTypes.string.isRequired,
			title: PropTypes.string.isRequired
		})
	)
};
