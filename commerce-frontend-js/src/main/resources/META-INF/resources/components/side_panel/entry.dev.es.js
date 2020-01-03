import launcher from './entry.es';

import '../../styles/main.scss';

window.SidePanel = launcher('sidePanel', 'side-panel-root-id', {
	containerSelector: '.container',
	id: 'sidePanelTestId',
	items: [
		{
			href: '/side-panel/comments.html',
			icon: 'comments',
			slug: 'comments',
		},
		{
			href: '/side-panel/edit.html',
			icon: 'pencil',
			slug: 'edit',
		},
		{
			href: '/side-panel/changelog.html',
			icon: 'restore',
			slug: 'changelog',
		},
	],
	size: 'md',
	spritemap: './assets/icons.svg',
	topAnchorSelector: '.top-anchor'
});
