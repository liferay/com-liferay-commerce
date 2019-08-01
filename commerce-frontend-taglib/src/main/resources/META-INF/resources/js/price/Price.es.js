import template from './Price.soy';
import Component from 'metal-component';
import Soy, {Config} from 'metal-soy';

function truncateNullDecimals(decimalNumber) {
	return !!decimalNumber && decimalNumber.includes('.00') ?
		decimalNumber.split('.')[0] : decimalNumber;
}

class Price extends Component {
	created() {
		this.prices.discountPercentage = truncateNullDecimals(this.prices.discountPercentage);

		if (!!this.prices.discountPercentages &&
			this.prices.discountPercentages.length) {
			this.prices.discountPercentages =
				this.prices.discountPercentages.map(truncateNullDecimals);
		}
	}
}

Price.STATE = {
	additionalDiscountClasses: Config.string(),
	additionalPromoPriceClasses: Config.string(),
	additionalPriceClasses: Config.string(),
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