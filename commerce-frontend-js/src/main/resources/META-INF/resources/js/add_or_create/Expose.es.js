import React, {useEffect} from 'react';

export default function Expose({active, children, onClose}) {
	const [height, setHeight] = React.useState(0);
	const content = React.useRef();

	useEffect(() => {
		setHeight(content.current.getBoundingClientRect().height);
	}, [content]);

	useEffect(() => {
		const handleEscKey = e => e.key === 'Escape' && onClose();

		if(active) {
			window.addEventListener('keydown', handleEscKey)
		} else {
			window.removeEventListener('keydown', handleEscKey);
		}
	}, [active, onClose]);

	return (
		<div
			className={`expose ${active ? 'is-open' : 'is-closed'}`}
			style={active ? {height} : {}}
		>
			<div className="expose__backdrop" onClick={onClose} />
			<div className="expose__content" ref={content}>
				{children}
			</div>
		</div>
	);
}
