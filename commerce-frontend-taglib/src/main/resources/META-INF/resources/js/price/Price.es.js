import template from './Price.soy';
import Component from 'metal-component';
import Soy, {Config} from 'metal-soy';

class Price extends Component {}

Price.STATE = {
	additionalDiscountClasses: Config.string(),
	additionalPromoPriceClasses: Config.string(),
	additionalPriceClasses: Config.string(),
	prices: Config.shapeOf(
		{
			price: Config.string().required(),
			promoPrice: Config.string(),
			discount: Config.string()
		}
	)
};

Soy.register(Price, template);

export {Price};
export default Price;