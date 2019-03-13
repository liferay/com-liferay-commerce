import Soy from 'metal-soy';
import Component from 'metal-component';
import {Config} from 'metal-state';
import template from './ModalLinkCellTemplate.soy';
import 'clay-modal';

class ModalLinkCellTemplate extends Component {

	_openPopUp(e) {
		e.preventDefault();
		this._lazyLoad = true;
		this._modalVisible = true;
	}

	_handleCloseModal(e) {
		e.preventDefault();
		this._modalVisible = false;
	}
}

ModalLinkCellTemplate.STATE = {
	_lazyLoad: Config.bool().value(false),
	_modalVisible: Config.bool().value(false)
};

Soy.register(ModalLinkCellTemplate, template);

export {ModalLinkCellTemplate};
export default ModalLinkCellTemplate;