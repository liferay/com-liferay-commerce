import template from './Loader.soy';
import Component from 'metal-component';
import Soy, {Config} from 'metal-soy';

import '../price/Price.es';

class Loader extends Component {}

Loader.STATE = {
	direction: Config.string(),
	inverted: Config.bool()
};

Soy.register(Loader, template);

export {Loader};
export default Loader;