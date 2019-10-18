import launcher from './entry.es';

import '../../styles/main.scss';

window.SidePanel = launcher('sidePanel', 'side-panel', {
	id: 'sidePanelTestId',
	size: 'md',
	spritemap: './assets/icons.svg',
	topAnchor: document.querySelector('.top-anchor')
});
