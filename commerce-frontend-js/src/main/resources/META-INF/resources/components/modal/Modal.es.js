import ClayButton from '@clayui/button';
import ClayModal, {useModal} from '@clayui/modal';
import PropTypes from 'prop-types';
import React, {useState, useRef, useEffect} from 'react';

import {OPEN} from '../../utilities/eventsDefinitions.es';

const Modal = props => {
	const [visible, setVisible] = useState(props.visible || false);

	function reset() {
		if (props.onClose) {
			props.onClose();
		}
		if (typeof props.visible === 'undefined') {
			setVisible(false);
		}
		setIframeLoadingCounter(0);
		setSubmitActive(
			typeof props.submitActiveAtLoading === 'boolean'
				? props.submitActiveAtLoading
				: true
		);
	}

	const [iframeLoadingCounter, setIframeLoadingCounter] = useState(0);
	const [submitActive, setSubmitActive] = useState(
		typeof props.submitActiveAtLoading === 'boolean'
			? props.submitActiveAtLoading
			: true
	);
	const iframeRef = useRef(null);
	const {observer, onClose} = useModal({onClose: reset});

	useEffect(() => {
		setVisible(props.visible);
	}, [props.visible]);

	useEffect(() => {
		if (!props.id) {
			return;
		}

		function handleOpenEvent(data) {
			if (props.id === data.id) {
				setVisible(true);
			}
		}

		function cleanUpListeners(e) {
			if (e.portletId === props.portletId) {
				Liferay.detach(OPEN, handleOpenEvent);
				Liferay.detach('destroyPortlet', cleanUpListeners);
			}
		}

		if (Liferay.on) {
			Liferay.on(OPEN, handleOpenEvent);
			Liferay.on('destroyPortlet', cleanUpListeners);
		}
	}, [props.id, props.portletId]);

	function _handleIframeLoad() {
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
		// eslint-disable-next-line react-hooks/exhaustive-deps
	}, [iframeLoadingCounter]);

	function _handleFormSubmit() {
		setSubmitActive(false);

		if (props.closeOnSubmit) {
			setTimeout(() => {
				onClose();
			}, 1000);
		}
	}

	function _handleIframeFirstLoad() {
		const iframeDocument = iframeRef.current.contentDocument;
		const iframeWindow = iframeRef.current.contentWindow;

		if (iframeDocument) {
			iframeDocument
				.querySelector('body')
				.classList.add('within-commerce-iframe');
		}

		if ((props.showSubmit || props.submitLabel) && iframeDocument) {
			const iframeForm = iframeDocument.querySelector('form');

			iframeForm.addEventListener('submitAvailable', () =>
				enableSubmit()
			);
			iframeForm.addEventListener('submitUnavailable', () =>
				disableSubmit()
			);

			if (iframeWindow.Liferay && iframeWindow.Liferay.on) {
				iframeWindow.Liferay.on('endNavigate', e => {
					e.preventDefault();
					_handleIframeLoad();
				});
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
			const iframeForm = iframeRef.current.contentDocument.querySelector(
				'form'
			);
			const iframeSubmitButton = iframeForm.querySelector(
				'[type="submit"]'
			);

			if (iframeSubmitButton) {
				iframeSubmitButton.click();
			} else {
				iframeForm.submit();
			}
		} catch (error) {
			throw new Error('Form not available');
		}
	}

	return visible ? (
		<ClayModal
			observer={observer}
			size={props.size}
			spritemap={props.spritemap}
			status={props.status}
		>
			{props.title && <ClayModal.Header>{props.title}</ClayModal.Header>}
			<div
				className="modal-body modal-body-iframe"
				style={{height: '450px', maxHeight: '100%'}}
			>
				<iframe
					onLoad={_handleIframeLoad}
					ref={iframeRef}
					src={props.url}
					title={props.title}
				/>
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
									onClick={onClose}
								>
									{props.cancelLabel ||
										Liferay.Language.get('cancel')}
								</ClayButton>
							)}
							{(props.showSubmit || props.submitLabel) && (
								<ClayButton
									disabled={!submitActive}
									displayType="primary"
									onClick={_submit}
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
	id: PropTypes.string,
	onClose: PropTypes.func,
	portletId: PropTypes.string,
	showCancel: PropTypes.bool,
	showSubmit: PropTypes.bool,
	size: PropTypes.string,
	spritemap: PropTypes.string,
	status: PropTypes.string,
	submitActiveAtLoading: PropTypes.bool,
	submitLabel: PropTypes.string,
	title: PropTypes.string,
	url: PropTypes.string
};

Modal.defaultProps = {
	showCancel: false,
	showSubmit: false
};

export default Modal;
