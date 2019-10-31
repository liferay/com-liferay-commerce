import template from './Summary.soy';
import Component from 'metal-component';
import Soy, {Config} from 'metal-soy';

import './Loader.es';

class Summary extends Component {}

Summary.STATE = {
	checkoutUrl: Config.string(),
	detailsUrl: Config.string(),
	discount: Config.string(),
	itemsQuantity: Config.number(),
	loading: Config.bool().value(false),
	productsQuantity: Config.number(),
	status: Config.number(),
	subtotal: Config.string(),
	taxes: Config.string(),
	total: Config.string(),
	valid: Config.bool()
};

Soy.register(Summary, template);

export {Summary};
export default Summary;