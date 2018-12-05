import template from './Price.soy';
import Component from 'metal-component';
import Soy, { Config } from 'metal-soy';

class Price extends Component {}

Price.STATE = {
	formattedPrice: Config.string(),
	formattedPromoPrice: Config.string(),
	additionalDiscountedClasses: Config.string().value(''),
	additionalOldPriceClasses: Config.string().value(''),
	additionalPriceClasses: Config.string().value('')
};

Soy.register(Price, template);

export {Price};
export default Price;