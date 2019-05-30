import React, {
    useEffect,
    useCallback,
    useRef
} from 'react';

import { globalEval } from 'metal-dom'
  
function Content(props) {

    const wrapper = useRef();

    const getContent = useCallback(
        () => fetch(props.url)
            .then(res => res.text())
            .then(content => injectHtml(wrapper.current, content)),
        [ props.url ]
    )

    const injectHtml = (wrapper, content) => {
        wrapper.innerHTML = content;
        try {
        globalEval.runScriptsInElement(wrapper)
        } catch (error) {
        console.error(error)      
        }
    }

    useEffect(() => {
        props.url && getContent(props.url)
    })

    return (
        <div className="dynamic-menu-content">
        <button className="dynamic-menu-content__close" onClick={props.close}>
            {props.closeIcon}
        </button>
        <div className="dynamic-menu-content__board" ref={wrapper} />
        </div>
    )
}

export default Content