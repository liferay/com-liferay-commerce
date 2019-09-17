import React, { useState, useRef, useEffect } from 'react';
import ClayModal, {useModal} from '@clayui/modal';
// import { Status, Size } from '@clayui/modal/src/types';
import ClayButton from '@clayui/button';

/*
export interface ModalProps {
    id: string
    url?: string,
    spritemap?: string,
    status?: Status,
    size?: Size,
    title?: string,
    showSubmit: boolean,
    submitLabel: string,
    closeOnSubmit: boolean,
    showDelete: boolean,
    deleteLabel: string
    showCancel: boolean,
    cancelLabel: string
}

interface ModalState {
    open: boolean
}
*/

const Modal = (props) => {

    const [ visible, setVisible ] = useState(false);

    function open() {
        setVisible(true);
    }

    function reset() {
        setVisible(false);
        setIframeLoadingCounter(0);
        setSubmitActive(
            typeof props.submitActiveAtLoading === 'boolean' 
                ? props.submitActiveAtLoading 
                : true
        )
    }

    const [ iframeLoadingCounter, setIframeLoadingCounter ] = useState(0);
    const [ submitActive, setSubmitActive ] = useState(
        typeof props.submitActiveAtLoading === 'boolean' 
            ? props.submitActiveAtLoading 
            : true
    )
    const iframeRef = useRef(null);
    const { observer, onClose } = useModal({onClose: reset});

    function cleanUpListeners(e) {
        if(e.portletId === props.portletId) {
            Liferay.detach(`${props.id}-open`, open);
            Liferay.detach('destroyPortlet', cleanUpListeners);
        }
    }

    useEffect( () => {
        if(Liferay.on){
            Liferay.on(`${props.id}-open`, open);
            Liferay.on('destroyPortlet', cleanUpListeners);
        } else {
            window.addEventListener(`${props.id}-open`, open);
        }

    }, [ props.id ] )

    function _handleIframeLoad(){
        setIframeLoadingCounter(iframeLoadingCounter + 1);
    }

    useEffect(() => {
        switch (true) {
            case iframeLoadingCounter > 1:
                return _handleFormSubmit();
            case iframeLoadingCounter === 1:
                return _handleIframeFirstLoad();
            default:
                break;
        }
    }, [iframeLoadingCounter])

    function _handleFormSubmit() {
        setSubmitActive(false);

        if(props.closeOnSubmit) {
            setTimeout(() => {
                onClose();
            }, 1000);
        }
    }

    function _handleIframeFirstLoad() {

        const iframeDocument = iframeRef.current.contentDocument;
        const iframeWindow = iframeRef.current.contentWindow;

        if (
            (
                props.showSubmit ||
                props.submitLabel
            ) &&
            iframeDocument 
        ) {
            const iframeForm = iframeDocument.querySelector('form');

            iframeForm.addEventListener('submitAvailable', () => enableSubmit());
            iframeForm.addEventListener('submitUnavailable', () => disableSubmit());
            
            if(iframeWindow.Liferay && iframeWindow.Liferay.on) {
                iframeWindow.Liferay.on(
                    'endNavigate',
                    (e) => {
                        e.preventDefault();
                        _handleIframeLoad();
                    }
                )
            }
        }
    }

    function setSubmitAvailability(state) {
        setSubmitActive(state);
    }

    function enableSubmit() {
        setSubmitAvailability(true);
    }

    function disableSubmit() {
        setSubmitAvailability(false);
    }

    function _submit(e) {
        e.preventDefault();
        try {
            const iframeForm = iframeRef.current.contentDocument.querySelector('form');
            const iframeSubmitButton = iframeForm.querySelector('[type="submit"]');

            if(iframeSubmitButton) {
                iframeSubmitButton.click();
            } else {
                iframeForm.submit();
            }

        } catch (error) {
            throw new Error('Form not available')  ;          
        }
    }

    return (
        visible ? (
            <ClayModal
                observer={observer}
                spritemap={props.spritemap}
                size={props.size}
                status={props.status}
            >
                {
                    props.title && <ClayModal.Header>{props.title}</ClayModal.Header>
                }
                <div className="modal-body modal-body-iframe" style={{height: '450px', maxHeight: '100%'}}>
                    <iframe 
                        src={props.url} 
                        title={props.title} 
                        ref={iframeRef} 
                        onLoad={_handleIframeLoad}
                    />
                </div>
                {
                    (
                        props.showSubmit ||
                        props.submitLabel ||
                        props.showCancel ||
                        props.cancelLabel 
                    ) && (
                        <ClayModal.Footer
                            last={
                                (
                                    props.showSubmit || props.submitLabel ||
                                    props.showCancel || props.cancelLabel 
                                ) && (
                                    <ClayButton.Group spaced>
                                        {(
                                            props.showCancel || props.cancelLabel
                                        ) && (
                                            <ClayButton 
                                                displayType="secondary"
                                                onClick={onClose}
                                            >
                                                {props.cancelLabel || Liferay.Language.get('cancel')}
                                            </ClayButton>
                                        )}
                                        {(
                                            props.showSubmit || props.submitLabel
                                        ) && (
                                            <ClayButton 
                                                displayType="primary"
                                                disabled={!submitActive}
                                                onClick={_submit}
                                            >
                                                {props.submitLabel || Liferay.Language.get('submit')}
                                            </ClayButton>
                                        )}
                                    </ClayButton.Group>
                                )
                            }
                        />
                    )
                }
            </ClayModal>
        )
        : null
    )
}

export default Modal;

