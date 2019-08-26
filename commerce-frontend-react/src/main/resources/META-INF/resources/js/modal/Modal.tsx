import React from 'react';
import ClayModal from '@clayui/modal';
import { Status, Size } from '@clayui/modal/src/types';
import ClayButton from '@clayui/button';

interface Actions {
    definition: 'cancel' | 'save' | 'add' | 'edit'
}

export interface ModalProps {
    id: string,
    url?: string,
    actions?: Actions[],
    spritemap?: string,
    status: Status,
    size: Size,
    title?: string,
}

interface ModalState {
    open: boolean
}

class Modal extends React.Component<ModalProps, ModalState> {
    constructor(props: ModalProps) {
        super(props);

        this.state = {
            open: false
        }
    }

    open() {
        this.setState({
            open: true
        })
    }

    close() {
        this.setState({
            open: false
        })
    }

    render() {
        return (
            this.state.open && (
                <ClayModal 
					onClose={() => this.setState({open: false})}
					size={this.props.size}
					spritemap={this.props.spritemap}
					status={this.props.status}
                >
                    {
                        onClose => (
                            <>
                                (
                                    {
                                        this.props.title && <ClayModal.Header>{this.props.title}</ClayModal.Header>
                                    }
                                    {
                                        this.props.url 
                                        ? <ClayModal.Body url={this.props.url} />
                                        : <ClayModal.Body>{this.props.children}</ClayModal.Body>
                                    }
                                    {
                                        this.props.actions 
                                        && this.props.actions.length
                                        && (
                                            <ClayModal.Footer
                                                first={
                                                    <ClayButton.Group spaced>
                                                        <ClayButton displayType="secondary">
                                                            {'Secondary'}
                                                        </ClayButton>
                                                        <ClayButton displayType="secondary">
                                                            {'Secondary'}
                                                        </ClayButton>
                                                    </ClayButton.Group>
                                                }
                                                last={
                                                    <ClayButton onClick={onClose}>{'Primary'}</ClayButton>
                                                }
                                            />
                                        )
                                    }
                                )
                            </>
                        )
                    }
                </ClayModal>
            )
        )
    }
}

export default Modal;