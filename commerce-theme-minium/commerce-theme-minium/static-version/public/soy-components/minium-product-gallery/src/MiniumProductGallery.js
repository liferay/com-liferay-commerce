import template from "./MiniumProductGallery.soy.js";
import Component from "metal-component";
import Soy from "metal-soy";

function fetchImage(url) {
	return new Promise(resolve => {
		const img = new Image();
		img.src = url;
		img.onload = () => resolve(url);
	});
}

class MiniumProductGallery extends Component {
	loadImage(imageUrl) {
		return new Promise(resolve => {
			if (this.loaded.has(imageUrl)) {
				resolve(imageUrl);
			} else {
				this.loading = true;
				fetchImage(imageUrl).then(() => {
					this.loading = false;
					this.loaded.add(imageUrl);
					resolve(imageUrl);
				});
			}
		});
	}

	selectImage(selected) {
		if (selected !== this.selected && !this.loading) {
			this.loadImage(this.images[selected].preview).then(() => {
				this.selected = selected;
			});
		}
	}

	handleThumbClick(e) {
		this.selectImage(parseInt(e.delegateTarget.dataset.index, 10));
	}

	openFullscreen() {
		if (!this.loading) {
			this.loadImage(this.images[this.selected].full).then(() => {
				this.fullscreen = true;
			});
		}
	}

	closeFullscreen() {
		this.fullscreen = false;
	}

	goToPrev(e) {
		e.stopPropagation();
		this.selected = (this.images.length + this.selected - 1) % this.images.length;
	}

	goToNext(e) {
		e.stopPropagation();
		this.selected = (this.images.length + this.selected + 1) % this.images.length;
	}
}

Soy.register(MiniumProductGallery, template);

MiniumProductGallery.STATE = {
	images: {
		value: []
	},
	loaded: {
		value: new Set()
	},
	selected: {
		value: 0
	},
	fullscreen: {
		value: false
	}
};

export { MiniumProductGallery };
export default MiniumProductGallery;