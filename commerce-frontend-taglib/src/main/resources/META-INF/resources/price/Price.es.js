import template from './Price.soy';
import Component from 'metal-component';
import Soy, {Config} from 'metal-soy';

class Price extends Component {}

Price.STATE = {
	additionalDiscountClasses: Config.string(),
	additionalOriginalPriceClasses: Config.string(),
	additionalFinalPriceClasses: Config.string(),
	prices: Config.shapeOf(
		{
			price: Config.string().required(),
			finalPrice: Config.string(),
			promoPrice: Config.string(),
			discount: Config.string()
		}
	)
};

Soy.register(Price, template);

export {Price};
export default Price;