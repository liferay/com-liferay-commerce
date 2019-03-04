'use strict';

import template from './CommerceInputText.soy';
import Component from 'metal-component';
import Soy, {Config} from 'metal-soy';

class CommerceInputText extends Component {
}

Soy.register(CommerceInputText, template);

CommerceInputText.STATE = {
	contextName: Config.string(),
	label: Config.string(),
	name: Config.string(),
	pattern: Config.any(),
	required: Config.bool(),
	type: Config.string(),
	value: Config.oneOfType(
		[
			Config.string(),
			Config.number()
		]
	),
	_handleInputKeyUp: Config.func()
};

export {CommerceInputText};
export default CommerceInputText;