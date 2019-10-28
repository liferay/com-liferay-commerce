import launcher from './entry.es';

import '../../styles/main.scss';

window.SidePanel = launcher('sidePanel', 'side-panel', {
	containerSelector: '.smart-table-wrapper',
	id: 'sidePanelTestId',
	size: 'md',
	spritemap: './assets/icons.svg',
	topAnchorSelector: '.top-anchor',
	items: [
		{
			slug: 'comments',
			href: 'http://google.com',
			icon: 'comments'
		},
		{
			slug: 'edit',
			href: 'http://google.com',
			icon: 'pencil'
		},
		{
			slug: 'changelog',
			href: 'http://google.com',
			icon: 'restore'
		},
	]
});
