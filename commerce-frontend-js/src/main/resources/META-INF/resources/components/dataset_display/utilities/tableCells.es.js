import Checkbox from '../content_renderers/table/cellTemplate/Checkbox.es';
import Default from '../content_renderers/table/cellTemplate/Default.es';
import ImageText from '../content_renderers/table/cellTemplate/ImageText.es';
import Label from '../content_renderers/table/cellTemplate/Label.es';
import Link from '../content_renderers/table/cellTemplate/Link.es';
import Price from '../content_renderers/table/cellTemplate/Price.es';
import SidePanelLink from '../content_renderers/table/cellTemplate/SidePanelLink.es';

const idToCellTemplateMapping = {
	checkbox: Checkbox,
	commerceTableCellImageName: ImageText,
	commerceTablePrice: Price,
	default: Default,
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