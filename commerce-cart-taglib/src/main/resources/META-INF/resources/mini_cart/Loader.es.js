import template from './Loader.soy';
import Component from 'metal-component';
import Soy from 'metal-soy';

import './Price.es';

class Loader extends Component {}

Loader.STATE = {
	content: {
		value: null
	},
	isUpdating: {
		value: false
	},
	inverted: {
		value: false
	},
	loaderType: {
		value: null
	},
	type: {
		value: 'default'
	}
};

Soy.register(Loader, template);

export {Loader};
export default Loader;