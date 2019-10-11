import {launcher} from '../utilities/index.es';
import Modal from './Modal.es';

const ModalLauncher = (...data) => launcher(Modal, ...data);

export default ModalLauncher;
