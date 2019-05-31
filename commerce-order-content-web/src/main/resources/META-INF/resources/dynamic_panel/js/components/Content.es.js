import React, { useEffect, useCallback, useRef } from 'react';

import { globalEval } from 'metal-dom';

function Content(props) {
	const wrapper = useRef();

	const getContent = useCallback(
		() =>
			fetch(props.url)
				.then(res => {
					if(res.status !== 200) {
						throw new Error(`Request failed with statusCode: ${res.status}`);
					}
					res.text()
				})
                .then(content => injectHtml(wrapper.current, content))
                .catch(err => props.onError(err)),
		[props.url]
	);

	const injectHtml = (wrapper, content) => {
		wrapper.innerHTML = content;
		try {
			globalEval.runScriptsInElement(wrapper);
		} catch (err) {
			props.onError(err);
		}
	};

	useEffect(() => {
		props.url && getContent(props.url);
	});

	return (
		<div className="dynamic-panel__content">
			<button className="dynamic-panel__close" onClick={props.close}>
				{props.closeIcon}
			</button>
			<div className="dynamic-panel__board" ref={wrapper} />
		</div>
	);
}

export default Content;