import template from './Summary.soy';
import Component from 'metal-component';
import Soy, {Config} from 'metal-soy';

import './CartProduct.es';
import './Loader.es';

class Summary extends Component {}

Summary.STATE = {
	checkoutUrl: Config.string(),
	discount: Config.string(),
	itemsQuantity: Config.number(),
	loading: Config.bool().value(false),
	productsQuantity: Config.number(),
	subtotal: Config.string(),
	taxes: Config.string(),
	total: Config.string()
};

Soy.register(Summary, template);

export {Summary};
export default Summary;