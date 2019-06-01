import React, { useEffect, useCallback, useRef } from 'react';

import { globalEval } from 'metal-dom';

const Content = React.memo((props) => {
	const wrapper = useRef();

	const injectHtml = useCallback(
		(wrapper, content) => {
			wrapper.innerHTML = content;
			try {
				globalEval.runScriptsInElement(wrapper);
			} catch (err) {
				props.onError(err);
			}
		},
		[props.content]
	);

	useEffect(() => {
		if(props.content && wrapper.current) {
			injectHtml(wrapper.current, props.content);
		}
	});

	return (
		<div className="dynamic-panel__content">
			<button className="dynamic-panel__close" onClick={props.close}>
				{props.closeIcon}
			</button>
			<div className="dynamic-panel__board" ref={wrapper} />
		</div>
	);
})

export default Content;