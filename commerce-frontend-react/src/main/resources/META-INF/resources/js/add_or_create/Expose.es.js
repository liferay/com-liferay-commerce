import React from "react";

export default function Expose({active, onClose, children}) {
  const [height, setHeight] = React.useState(0);
  const content = React.useRef();

  React.useEffect(() => {
    setHeight(content.current.getBoundingClientRect().height);
  }, [content]);

  React.useEffect(() => {
    const handleEscKey = (e) => (e.key === "Escape") && onClose();

    active
      ? window.addEventListener("keydown", handleEscKey)
      : window.removeEventListener("keydown", handleEscKey);
  }, [active, onClose]);

  return (
    <div
      className={`expose ${active ? "is-open" : "is-closed"}`}
      style={active ? { height } : {}}
    >
      <div className="expose__backdrop" onClick={onClose} />
      <div className="expose__content" ref={content}>
        {children}
      </div>
    </div>
  );
}
