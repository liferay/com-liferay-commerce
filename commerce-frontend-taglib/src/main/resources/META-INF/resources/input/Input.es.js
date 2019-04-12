'use strict';

import template from './Input.soy';
import Component from 'metal-component';
import Soy, {Config} from 'metal-soy';

class Input extends Component {
}

Soy.register(Input, template);

Input.STATE = {
	contextName: Config.string(),
	label: Config.string(),
	name: Config.string(),
	pattern: Config.any(),
	required: Config.bool(),
	type: Config.string(),
	value: Config.string(),
	_handleInputKeyUp: Config.func()
};

export {Input};
export default Input;