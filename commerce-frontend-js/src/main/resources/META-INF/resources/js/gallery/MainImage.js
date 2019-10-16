import React from "react";
import PropTypes from 'prop-types';
import ClayLoadingIndicator from "@clayui/loading-indicator";
import Arrows from './Arrows';

export default function MainImage({ loading = false, onNext, onPrev, onZoom, src, title }) {
  return (
    <div className="card main-image" onClick={onZoom}>
      <div className="aspect-ratio aspect-ratio-4-to-3">
        <img
          alt={title}
          className="aspect-ratio-item-center-middle aspect-ratio-item-fluid"
          src={src}
        />
      </div>
      <Arrows onNext={onNext} onPrev={onPrev} />
      {loading ? <ClayLoadingIndicator /> : null}
    </div>
  );
}

MainImage.propTypes = {
  loading: PropTypes.bool,
  onNext: PropTypes.func,
  onPrev: PropTypes.func,
  onZoom: PropTypes.func,
  title: PropTypes.string.isRequired,
  src: PropTypes.string.isRequired
}
