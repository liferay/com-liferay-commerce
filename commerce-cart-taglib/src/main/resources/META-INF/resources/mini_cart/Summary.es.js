import template from './Summary.soy';
import Component from 'metal-component';
import Soy from 'metal-soy';

import './CartProduct.es';
import './Loader.es';

class Summary extends Component {}

Summary.STATE = {
	isLoading: {
		value: false
	},
	checkoutUrl: {
		value: null
	},
	subtotal: {
		value: null
	},
	grandTotal: {
		value: null
	},
	discount: {
		value: null
	},
	taxes: {
		value: null
	},
	totalUnits: {
		value: null
	},
	productsCount: {
		value: null
	}
};

Soy.register(Summary, template);

export {Summary};
export default Summary;