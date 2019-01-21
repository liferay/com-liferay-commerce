'use strict';

import template from './CommerceInputText.soy';
import Component from 'metal-component';
import Soy, {Config} from 'metal-soy';

class CommerceInputText extends Component {}

Soy.register(CommerceInputText, template);

CommerceInputText.STATE = {
	contextName: Config.string(),
	name: Config.string(),
	label: Config.string(),
	value: Config.string(),
	type: Config.string(),
	pattern: Config.any(),
	required: Config.bool(),
	_handleInputKeyUp: Config.func()
};

export {CommerceInputText};
export default CommerceInputText;