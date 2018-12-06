import template from './Summary.soy';
import Component from 'metal-component';
import Soy, {Config} from 'metal-soy';

import './CartProduct.es';
import './Loader.es';

class Summary extends Component {}

Summary.STATE = {

	isLoading: Config.bool().value(false),

	checkoutUrl: Config.string(),

	subtotal: Config.string(),

	total: Config.string(),

	discount: Config.string(),

	taxes: Config.string(),

	itemsQuantity: Config.number(),

	productsCount: Config.number()

};

Soy.register(Summary, template);

export {Summary};
export default Summary;