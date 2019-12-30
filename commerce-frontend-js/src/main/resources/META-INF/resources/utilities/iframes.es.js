import { OPEN_MODAL } from './eventsDefinitions.es';

export const iframeHandlerModalId = 'iframe-handler-modal'; 

export function initializeIframeListeners() {
    Liferay.on(OPEN_MODAL, function(payload) {
        if(!window.parent) {
            return;
        }
    
        window.parent.Liferay.fire(
            OPEN_MODAL,
            Object.assign(
                {
                    id: iframeHandlerModalId,
                    onClose: function() {
                        window.location.reload()
                    }
                },
                payload
            )
        );
    })
}