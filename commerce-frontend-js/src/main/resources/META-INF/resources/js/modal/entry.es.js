import Modal from './Modal.es';
import {launcher} from '../utilities';

const ModalLauncher = (...data) => launcher(Modal, ...data);

export default ModalLauncher;
