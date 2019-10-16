import React from "react";
import PropTypes from 'prop-types';
import Thumbnail from './Thumbnail';

export default function Thumbnails({ images, onChange, selected = false }) {
  return (
    <div className="gallery-thumbnails">
      {images.map((image, i) => (
        <Thumbnail
          active={selected === i}
          key={image.thumbnailUrl}
          onClick={onChange ? () => onChange(i): null}
          src={image.thumbnailUrl}
          title={image.title}
        />
      ))}
    </div>
  );
}

Thumbnails.propTypes = {
  images: PropTypes.arrayOf(PropTypes.shape({
    title: PropTypes.string.isRequired,
    thumbnailUrl: PropTypes.string.isRequired,
  })),
  onChange: PropTypes.func,
  selected: PropTypes.number
}