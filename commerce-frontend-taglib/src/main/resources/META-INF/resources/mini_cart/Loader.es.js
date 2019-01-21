import template from './Loader.soy';
import Component from 'metal-component';
import Soy, {Config} from 'metal-soy';

import '../price/Price.es';

class Loader extends Component {}

Loader.STATE = {

	inverted: Config.bool(),

	direction: Config.string()

};

Soy.register(Loader, template);

export {Loader};
export default Loader;