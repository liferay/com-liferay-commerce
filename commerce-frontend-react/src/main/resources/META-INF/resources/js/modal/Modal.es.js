import React from 'react';
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
            open: false,
            submitActive: !!this.props.submitAvailableAtLoading,
            listenersInitialized: false,
        };
    }

    attachListenerToIframe(){
        const iframeDocument = this.iframeRef.current.contentDocument;

        if (
            (
                this.state.showSubmit ||
                this.state.submitLabel
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
            open: true
        });
    }

    close() {
        this.setState({
            open: false,
            submitActive: !!this.props.submitAvailableAtLoading,
            listenersInitialized: false,
        });
    }

    _submit(e, close) {
        e.preventDefault();
        try {
            this.iframeForm.submit();

            if(this.props.closeOnSubmit) {
                close();
            } else {
                this.setState({
                    submitActive: false
                })
            }
        } catch (error) {
            throw new Error('Form not available')            
        }
    }
    

    render() { 
        
        const { 
            showDelete, 
            deleteLabel, 
            showSubmit, 
            submitLabel, 
            showCancel, 
            cancelLabel 
        } = this.props;

        return (
            this.state.open && (
                <ClayModal 
					onClose={() => this.close()}
					size={this.props.size}
					spritemap={this.props.spritemap}
					status={this.props.status}
                >
                    {
                        onClose => (
                            <>
                                {
                                    this.props.title && <ClayModal.Header>{this.props.title}</ClayModal.Header>
                                }
                                {
                                    this.props.url 
                                    ? (
                                        <div className="modal-body modal-body-iframe">
                                            <iframe 
                                                src={this.props.url} 
                                                title={this.props.url} 
                                                ref={this.iframeRef} 
                                                onLoad={() => this.attachListenerToIframe()}
                                            />
                                        </div>
                                    )
                                    : <ClayModal.Body>{this.props.children}</ClayModal.Body>
                                }
                                {
                                    (
                                        showDelete ||
                                        deleteLabel ||
                                        showSubmit ||
                                        submitLabel ||
                                        showCancel ||
                                        cancelLabel 
                                    ) && (
                                        <ClayModal.Footer
                                            first={
                                                (        
                                                    showDelete || deleteLabel
                                                ) && (
                                                    <ClayButton displayType="link">
                                                        {deleteLabel || Liferay.Language.get('delete')}
                                                    </ClayButton>
                                                )
                                            }
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
                        )
                    }
                </ClayModal>
            )
        )
    }
}

export default Modal;