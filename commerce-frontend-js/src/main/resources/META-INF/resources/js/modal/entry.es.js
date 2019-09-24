import Modal from './Modal.es';
import {launcher} from '../utilities/index.es';

const ModalLauncher = (...data) => launcher(Modal, ...data);

export default ModalLauncher;
