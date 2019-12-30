import ClayButton from '@clayui/button';
import ClayLoadingIndicator from '@clayui/loading-indicator';
import ClayModal, {useModal} from '@clayui/modal';
import PropTypes from 'prop-types';
import React, {useState, useRef, useEffect} from 'react';

import {OPEN, OPEN_MODAL, CLOSE_MODAL} from '../../utilities/eventsDefinitions.es';

let test = 0;

const Modal = props => {
	const [visible, setVisible] = useState(false);
	const [loading, setLoading] = useState(false);
	const [onClose, setOnClose] = useState(null);
	const [iframeLoadingCounter, setIframeLoadingCounter] = useState(0);
	const [title, setTitle] = useState(props.title);
	const [url, setUrl] = useState(props.url);
	const iframeRef = useRef(null);
	
	const {observer, onClose: closeModal} = useModal({onClose: () => {
		if(iframeLoadingCounter > 1) {
			if (onClose) {
				onClose();
			} else if (props.onClose) {
				props.onClose()
			}
		}
		
		setIframeLoadingCounter(() => 0);
		test = 0;
		setLoading(false);
		setVisible(false);
	}});

	useEffect(() => {
		function handleOpenEvent(data) {
			if ((props.id !== data.id) || visible) {
				return;
			}

			setLoading(true);
			setVisible(true);

			if(data.url) {
				setUrl(data.url);
			}

			if(data.onClose) {
				setOnClose(data.onClose);
			}

			if(data.title) {
				setTitle(data.title);
			}
		}

		function cleanUpListeners(e) {
			if (e.portletId === props.portletId) {
				Liferay.detach(OPEN, handleOpenEvent);
				Liferay.detach(OPEN_MODAL, handleOpenEvent);
				Liferay.detach(CLOSE_MODAL, closeModal);
				Liferay.detach('destroyPortlet', cleanUpListeners);
			}
		}

		if (Liferay.on) {
			Liferay.on(OPEN, handleOpenEvent);
			Liferay.on(OPEN_MODAL, handleOpenEvent);
			Liferay.on(CLOSE_MODAL, closeModal);
			Liferay.on('destroyPortlet', cleanUpListeners);
		}

		return () => cleanUpListeners({portletId: props.portletId})
	}, [props.id, props.portletId, closeModal]);

	useEffect(() => {
		setOnClose(() => props.onClose);
	}, [])

	function handleIframeLoad() {
		setLoading(false);
		setIframeLoadingCounter(c => c + 1);
		test++;
		
		const iframeDocument = iframeRef.current.contentDocument;
		const iframeWindow = iframeRef.current.contentWindow;
		
		if (iframeDocument && iframeWindow) {
			if (iframeWindow.Liferay && iframeWindow.Liferay.on) {
				iframeWindow.Liferay.on('endNavigate', e => {
					e.preventDefault();
					setIframeLoadingCounter(c => c + 1);
					test++;
				});
			}
		}
	}

	function handleClickOnSubmit(e) {
		e.preventDefault();
		const iframeForm = iframeRef.current.contentDocument.querySelector(
			'form'
		);

		if (iframeForm) {
			iframeRef.current.contentWindow.submitForm(iframeForm)
		} else {
			throw new Error('Form not available');
		}
	}

	return visible ? (
		<ClayModal
			observer={observer}
			size={"lg"}
			spritemap={props.spritemap}
			status={props.status}
		>
			{title && <ClayModal.Header>{title}</ClayModal.Header>}
			<div
				className="modal-body modal-body-iframe"
				style={{height: '450px', maxHeight: '100%'}}
			>
				<iframe
					onLoad={handleIframeLoad}
					ref={iframeRef}
					src={url}
					title={title}
				/>
				{loading && 
					<div className="loader-container">
						<ClayLoadingIndicator />
					</div>
				}
			</div>
			{(props.showSubmit ||
				props.submitLabel ||
				props.showCancel ||
				props.cancelLabel) && (
				<ClayModal.Footer
					last={
						<ClayButton.Group spaced>
							{(props.showCancel || props.cancelLabel) && (
								<ClayButton
									displayType="secondary"
									onClick={closeModal}
								>
									{props.cancelLabel ||
										Liferay.Language.get('cancel')}
								</ClayButton>
							)}
							{(props.showSubmit || props.submitLabel) && (
								<ClayButton
									displayType="primary"
									onClick={handleClickOnSubmit}
								>
									{props.submitLabel ||
										Liferay.Language.get('submit')}
								</ClayButton>
							)}
						</ClayButton.Group>
					}
				/>
			)}
		</ClayModal>
	) : null;
};

Modal.propTypes = {
	cancelLabel: PropTypes.string,
	closeOnSubmit: PropTypes.bool,
	id: PropTypes.string.isRequired,
	onClose: PropTypes.func,
	portletId: PropTypes.string,
	showCancel: PropTypes.bool,
	showSubmit: PropTypes.bool,
	size: PropTypes.string,
	spritemap: PropTypes.string,
	status: PropTypes.string,
	submitLabel: PropTypes.string,
	title: PropTypes.string,
	url: PropTypes.string
};

Modal.defaultProps = {
	showCancel: false,
	showSubmit: false
};

export default Modal;
