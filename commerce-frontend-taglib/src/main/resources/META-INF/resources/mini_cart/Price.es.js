import template from './Price.soy';
import Component from 'metal-component';
import Soy, {Config} from 'metal-soy';

class Price extends Component {}

Price.STATE = {

	prices: Config.shapeOf(
		{
			price: Config.string().value('').required(),
			promoPrice: Config.string()
		}
	),

	additionalDiscountedClasses: Config.string().value(''),

	additionalOldPriceClasses: Config.string().value(''),

	additionalPriceClasses: Config.string().value('')

};

Soy.register(Price, template);

export {Price};
export default Price;