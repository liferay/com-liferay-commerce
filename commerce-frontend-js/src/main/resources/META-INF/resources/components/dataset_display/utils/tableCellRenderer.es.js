import Checkbox from '../table/cellTemplate/Checkbox.es';
import Default from '../table/cellTemplate/Default.es';
import Dropdown from '../table/cellTemplate/Dropdown.es';
import ImageText from '../table/cellTemplate/ImageText.es';
import Label from '../table/cellTemplate/Label.es';
import Link from '../table/cellTemplate/Link.es';
import Price from '../table/cellTemplate/Price.es';
import SidePanelLink from '../table/cellTemplate/SidePanelLink.es';

const idToCellTemplateMapping = {
	checkbox: Checkbox,
	commerceTableCellImageName: ImageText,
	commerceTablePrice: Price,
	default: Default,
	dropdown: Dropdown,
	imageTitle: ImageText,
	label: Label,
	link: Link,
	price: Price,
	sidePanelLink: SidePanelLink
};

export function getCustomCellTemplate(id, customTemplates = {}) {
	const templates = {
		...idToCellTemplateMapping,
		...customTemplates
	};

	return templates[id] || templates.default;
}