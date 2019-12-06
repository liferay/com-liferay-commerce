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
			href: '/side-panel/comments.html',
			icon: 'comments'
		},
		{
			slug: 'edit',
			href: '/side-panel/edit.html',
			icon: 'pencil'
		},
		{
			slug: 'changelog',
			href: '/side-panel/changelog.html',
			icon: 'restore'
		},
	]
});
