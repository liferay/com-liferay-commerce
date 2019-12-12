import template from './Price.soy';
import Component from 'metal-component';
import Soy, {Config} from 'metal-soy';

class Price extends Component {}

Price.STATE = {
	additionalDiscountClasses: Config.string(),
	additionalPromoPriceClasses: Config.string(),
	additionalPriceClasses: Config.string(),
	displayDiscountLevels: Config.bool().value(false),
	prices: Config.shapeOf(
		{
			price: Config.string().required(),
			promoPrice: Config.string(),
			discountPercentage: Config.string(),
			discountPercentages: Config.array().value(null),
			finalPrice: Config.string()
		}
	)
};

Soy.register(Price, template);

export {Price};
export default Price;