import React from "react";
import PropTypes from 'prop-types';
import Arrows from './Arrows';

export default function Overlay({ onClose, onNext, onPrev, src, title }) {
  return (
    <div className="gallery-overlay" onClick={onClose}>
      <img alt={title} src={src} />
      <Arrows onNext={onNext} onPrev={onPrev} />
    </div>
  );
}

Overlay.propTypes = {
  onClose: PropTypes.func,
  onNext: PropTypes.func,
  onPrev: PropTypes.func,
  src: PropTypes.string,
  title: PropTypes.string
}