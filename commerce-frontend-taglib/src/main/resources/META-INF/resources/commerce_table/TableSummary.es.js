import {Config} from 'metal-state';
import Component from 'metal-component';
import Soy from 'metal-soy';

import template from './TableSummary.soy';

import {translate} from '../js_utils/localization.es';

class TableSummary extends Component {
	attached() {
		this.data = this.data.map(
			(item) => Object.assign(
				{},
				item,
				{
					title: translate(item.title)
				}
			)
		)
	}
}

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