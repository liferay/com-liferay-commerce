import Modal from './Modal.es';
import {launcher} from '../utilities/entry';

const ModalLauncher = (...data) => launcher(Modal, ...data);

export default ModalLauncher;
