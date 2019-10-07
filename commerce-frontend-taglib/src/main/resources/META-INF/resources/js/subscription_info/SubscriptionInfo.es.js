import template from './SubscriptionInfo.soy';
import Component from 'metal-component';
import Soy, {Config} from 'metal-soy';

class SubscriptionInfo extends Component {}

SubscriptionInfo.STATE = {
	durationPeriod: Config.string(),
	subscriptionPeriod: Config.string()
};

Soy.register(SubscriptionInfo, template);

export {SubscriptionInfo};
export default SubscriptionInfo;