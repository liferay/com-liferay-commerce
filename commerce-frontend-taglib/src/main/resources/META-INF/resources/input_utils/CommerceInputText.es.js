'use strict';

import template from './CommerceInputText.soy';
import Component from 'metal-component';
import Soy, {Config} from 'metal-soy';

class CommerceInputText extends Component {}

Soy.register(CommerceInputText, template);

CommerceInputText.STATE = {
	contextName: Config.string(),
	label: Config.oneOfType(
		[
			Config.string(),
			Config.func()
		]
	),
	name: Config.string(),
	margin: Config.oneOf([
		'top',
		'bottom'
	]),
	additionalClasses: Config.string(),
	pattern: Config.any(),
	required: Config.bool(),
	type: Config.string(),
	value: Config.oneOfType(
		[
			Config.string(),
			Config.number()
		]
	),
	keyUpHandler: Config.func(),
	changeHandler: Config.func(),
};

export {CommerceInputText};
export default CommerceInputText;