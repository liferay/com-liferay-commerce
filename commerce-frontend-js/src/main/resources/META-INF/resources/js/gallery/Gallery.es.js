import React from "react";
import classNames from "classnames";
import ClayLoadingIndicator from "@clayui/loading-indicator";
// import ClayCard from "@clayui/card";

function fetchImage(url) {
  return new Promise(resolve => {
    const img = new Image();
    img.src = url;
    img.onload = () => resolve(url);
  });
}

function MainImage({ url, title, loading, onZoom, onPrev, onNext }) {
  return (
    <div onClick={onZoom} className="card main-image">
      <div className="aspect-ratio aspect-ratio-4-to-3">
        <img
          className="aspect-ratio-item-center-middle aspect-ratio-item-fluid"
          src={url}
          alt={title}
        />
      </div>
      {loading ? <ClayLoadingIndicator /> : null}
      {onPrev && <div className="prev" onClick={onPrev} />}
      {onNext && <div className="next" onClick={onNext} />}
    </div>
  );
}

function Overlay({ url, title, onClose, onPrev, onNext }) {
  return (
    <div className="gallery-overlay" onClick={onClose}>
      <img src={url} alt={title} />

      {onPrev && <div className="prev" onClick={onPrev} />}
      {onNext && <div className="next" onClick={onNext} />}
    </div>
  );
}

function Thumbnail({ image, active, onClick }) {
  const cardClasses = classNames(
    'card',
    'card-interactive',
    'card-interactive-primary',
    'card-type-template',
    'template-card',
    {active: active}
  );

  return (
    <div
      key={image.thumbnailUrl}
      onClick={onClick}
      className={cardClasses}
    >
      <div className="aspect-ratio aspect-ratio-4-to-3">
        <img
          className="aspect-ratio-item-center-middle aspect-ratio-item-fluid"
          alt={image.title}
          src={image.thumbnailUrl}
        />
      </div>
    </div>
  );
}

function Thumbnails({ images, selected, onChange }) {
  return (
    <div className="gallery-thumbnails">
      {images.map((image, i) => (
        <Thumbnail
          image={image}
          active={selected === i}
          onClick={() => onChange(i)}
        />
      ))}
    </div>
  );
}

export default class Gallery extends React.Component {
  constructor(props) {
    super(props);
    this.state = {
      fullscreen: false,
      loading: false,
      loaded: new Set(),
      selected: 0
    };

    this.goToPrev = this.goToPrev.bind(this);
    this.goToNext = this.goToNext.bind(this);
    this.loadImage = this.loadImage.bind(this);
    this.selectImage = this.selectImage.bind(this);
    this.openFullscreen = this.openFullscreen.bind(this);
    this.closeFullscreen = this.closeFullscreen.bind(this);
  }

  loadImage(imageUrl) {
    return new Promise(resolve => {
      if (this.state.loaded.has(imageUrl)) {
        resolve(imageUrl);
      } else {
        this.setState({ loading: true });
        fetchImage(imageUrl).then(() => {
          this.setState(
            {
              loading: false,
              loaded: new Set(this.state.loaded).add(imageUrl)
            },
            () => {
              resolve(imageUrl);
            }
          );
        });
      }
    });
  }

  selectImage(toSelect) {
    if (toSelect !== this.state.selected && !this.state.loading) {
      this.loadImage(this.props.images[toSelect].smallUrl).then(() => {
        this.setState({ selected: toSelect });
      });
    }
  }

  openFullscreen() {
    if (!this.state.loading) {
      this.loadImage(this.props.images[this.state.selected].url).then(() => {
        this.setState({ fullscreen: true });
      });
    }
  }

  closeFullscreen() {
    this.setState({ fullscreen: false });
  }

  goTo(pos) {
    this.setState({
      selected: (this.props.images.length + pos) % this.props.images.length
    });
  }

  goToPrev(e) {
    e.stopPropagation();
    this.goTo(this.state.selected - 1);
  }

  goToNext(e) {
    e.stopPropagation();
    this.goTo(this.state.selected + 1);
  }

  render() {
    const { loading, selected, fullscreen } = this.state;
    const { images } = this.props;

    return (
      <div className="product-gallery">
        <MainImage
          url={images[selected].smallUrl}
          title={images[selected].title}
          loading={loading}
          onZoom={this.openFullscreen}
          onPrev={images.length > 1 ? this.goToPrev : null}
          onNext={images.length > 1 ? this.goToNext : null}
        />

        {fullscreen ? (
          <Overlay
            onClose={this.closeFullscreen}
            url={images[selected].url}
            title={images[selected].title}
            onPrev={images.length > 1 ? this.goToPrev : null}
            onNext={images.length > 1 ? this.goToNext : null}
          />
        ) : null}

        {images.length > 1 ? (
          <Thumbnails
            images={images}
            selected={selected}
            onChange={this.selectImage}
          />
        ) : null}
      </div>
    );
  }
}
