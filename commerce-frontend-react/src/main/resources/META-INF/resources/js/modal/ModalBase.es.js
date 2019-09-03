import React, { Children } from 'react';
import ClayModal, {useModal} from '@clayui/modal';
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
const ModalBase = (props) => {
        const {observer, onClose} = useModal({
            onClose: props.onClose,
        });

        return (
            props.visible ? (
                <ClayModal
                    observer={observer}
                    spritemap={props.spritemap}
                    size={props.size}
                    status={props.status}
                >
                    {props.children instanceof Function
                        ? <>{props.children(onClose)}</>
                        : props.children
                    }
                </ClayModal>
            )
            : null
        )
    };

export default ModalBase;

