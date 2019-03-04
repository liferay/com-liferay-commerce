import {Config} from 'metal-state';
import Component from 'metal-component';
import Soy from 'metal-soy';

import template from './TableSummary.soy';

class TableSummary extends Component {}

Soy.register(TableSummary, template);

TableSummary.STATE = {
	data: Config.array(
		Config.shapeOf(
			{
				name: Config.string().required(),
				title: Config.string().required(),
				modifiers: Config.string(),
				type: Config.string()
			}
		)
	)
};

export {TableSummary};
export default TableSummary;