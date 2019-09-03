import React from 'react';
import ModalBase from './ModalBase.es';
import ClayModal from '@clayui/modal';
// import { Status, Size } from '@clayui/modal/src/types';
import ClayButton from '@clayui/button';

/*
export interface ModalProps {
    url?: string,
    spritemap?: string,
    status?: Status,
    size?: Size,
    title?: string,
    showSubmit: boolean,
    submitLabel: string,
    closeOnSubmit: boolean,
    submitAvailableAtLoading: boolean,
    showDelete: boolean,
    deleteLabel: string
    showCancel: boolean,
    cancelLabel: string
}

interface ModalState {
    open: boolean
}
*/

class Modal extends React.Component {
    constructor(props) {
        super(props);
        this.iframeRef = React.createRef();
        this.state = {
            visible: false,
            submitActive: true,
            listenersInitialized: false,
        };
    }

    attachListenerToIframe(){
        const iframeDocument = this.iframeRef.current.contentDocument;
        if (
            (
                this.props.showSubmit ||
                this.props.submitLabel
            ) &&
            iframeDocument && 
            !this.state.listenersInitialized
        ) {
            this.iframeForm = iframeDocument.querySelector('form');

            this.iframeForm.addEventListener('submitAvailable', () => this.enableSubmit());
            this.iframeForm.addEventListener('submitUnavailable', () => this.disableSubmit());

            this.setState({
                listenersInitialized: true,
            })
        }
    }

    setSubmitAvailability(active) {
        this.setState({
            submitActive: active
        });
    }

    enableSubmit() {
        this.setSubmitAvailability(true);
    }

    disableSubmit() {
        this.setSubmitAvailability(false);
    }

    open() {
        this.setState({
            visible: true
        });
    }

    close() {
        this.setState({
            visible: false,
            listenersInitialized: false,
        });
    }

    _submit(e, close) {
        e.preventDefault();
        try {
            this.iframeForm.submit();

            if(this.props.closeOnSubmit) {
                close();
            }
        } catch (error) {
            throw new Error('Form not available')            
        }
    }

    render() { 
        
        const { 
            title,
            url,
            showSubmit,
            submitLabel,
            showCancel,
            cancelLabel,
            spritemap,
            size,
            status
        } = this.props;

        return (
            <ModalBase 
                visible={this.state.visible}
                onClose={() => this.close()}
                spritemap={spritemap}
                size={size}
                status={status}
            >
                { onClose => (
                    <>
                        {
                            title && <ClayModal.Header>{title}</ClayModal.Header>
                        }
                        <div className="modal-body modal-body-iframe">
                            <iframe 
                                src={url} 
                                title={title} 
                                ref={this.iframeRef} 
                                onLoad={() => this.attachListenerToIframe()}
                            />
                        </div>
                        {
                            (
                                showSubmit ||
                                submitLabel ||
                                showCancel ||
                                cancelLabel 
                            ) && (
                                <ClayModal.Footer
                                    last={
                                        (
                                            showSubmit || submitLabel ||
                                            showCancel || cancelLabel 
                                        ) && (
                                            <ClayButton.Group spaced>
                                                {(
                                                    showCancel || cancelLabel
                                                ) && (
                                                    <ClayButton 
                                                        displayType="secondary"
                                                        onClick={onClose}
                                                    >
                                                        {cancelLabel || Liferay.Language.get('cancel')}
                                                    </ClayButton>
                                                )}
                                                {(
                                                    showSubmit || submitLabel
                                                ) && (
                                                    <ClayButton 
                                                        displayType="primary"
                                                        disabled={!this.state.submitActive}
                                                        onClick={(e) => this._submit(e, onClose)}
                                                    >
                                                        {submitLabel || Liferay.Language.get('submit')}
                                                    </ClayButton>
                                                )}
                                            </ClayButton.Group>
                                        )
                                    }
                                />
                            )
                        }
                    </>
                )}
            </ModalBase>
        )
    }
}

export default Modal;

