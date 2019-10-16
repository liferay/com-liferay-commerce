import React from "react";
import classNames from "classnames";
import ClayLoadingIndicator from "@clayui/loading-indicator";
// import ClayCard from "@clayui/card"; // Re-render the card components when is not needed

function fetchImage(url) {
  return new Promise(resolve => {
    const img = new Image();

    img.src = url;

    img.onload = () => resolve(url);
  });
}

function Arrows({ onNext, onPrev }) {
  return (
    <>
      {onPrev && <div className="arrow prev" onClick={onPrev} />}
      {onNext && <div className="arrow next" onClick={onNext} />}
    </>
  );
}

function MainImage({ loading, onNext, onPrev, onZoom, title, url }) {
  return (
    <div className="card main-image" onClick={onZoom}>
      <div className="aspect-ratio aspect-ratio-4-to-3">
        <img
          alt={title}
          className="aspect-ratio-item-center-middle aspect-ratio-item-fluid"
          src={url}
        />
      </div>
      <Arrows onNext={onNext} onPrev={onPrev} />
      {loading ? <ClayLoadingIndicator /> : null}
    </div>
  );
}

function Overlay({ onClose, onNext, onPrev, title, url }) {
  return (
    <div className="gallery-overlay" onClick={onClose}>
      <img alt={title} src={url} />
      <Arrows onNext={onNext} onPrev={onPrev} />
    </div>
  );
}

function Thumbnail({ active, image, onClick }) {
  const cardClasses = classNames(
    "card",
    "card-interactive",
    "card-interactive-primary",
    { active: active }
  );

  return (
    <div className={cardClasses} onClick={onClick}>
      <div className="aspect-ratio aspect-ratio-4-to-3">
        <img
          alt={image.title}
          className="aspect-ratio-item-center-middle aspect-ratio-item-fluid"
          src={image.thumbnailUrl}
        />
      </div>
    </div>
  );
}

function Thumbnails({ images, onChange, selected }) {
  return (
    <div className="gallery-thumbnails">
      {images.map((image, i) => (
        <Thumbnail
          active={selected === i}
          image={image}
          key={image.thumbnailUrl}
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
      this.imageLoad(this.props.images[this.state.selected].url).then(() => {
        this.setState({ fullscreen: true });
      });
    }
  }

  fullscreenClose() {
    this.setState({ fullscreen: false });
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
        this.setState({ loading: true });
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
        this.setState({ selected: toSelect });
      });
    }
  }

  render() {
    const { images } = this.props;
    const { fullscreen, loading, selected } = this.state;

    return (
      <div className="product-gallery">
        <MainImage
          loading={loading}
          onNext={images.length > 1 ? this.goToNext : null}
          onPrev={images.length > 1 ? this.goToPrev : null}
          onZoom={this.fullscreenOpen}
          title={images[selected].title}
          url={images[selected].smallUrl}
        />

        {images.length > 1 ? (
          <Thumbnails
            images={images}
            onChange={this.imageSelect}
            selected={selected}
          />
        ) : null}

        {fullscreen ? (
          <Overlay
            onClose={this.fullscreenClose}
            onNext={images.length > 1 ? this.goToNext : null}
            onPrev={images.length > 1 ? this.goToPrev : null}
            title={images[selected].title}
            url={images[selected].url}
          />
        ) : null}
      </div>
    );
  }
}
