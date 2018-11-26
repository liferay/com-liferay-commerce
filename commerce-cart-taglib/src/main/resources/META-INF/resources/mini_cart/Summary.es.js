import template from './Summary.soy';
import Component from 'metal-component';
import Soy from 'metal-soy';

import './CartProduct.es';
import './Loader.es';

class Summary extends Component {}

Summary.STATE = {
	isLoading: {
		value: false
	}
};

Soy.register(Summary, template);

export {Summary};
export default Summary;