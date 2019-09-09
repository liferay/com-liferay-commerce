import launcher from './entry.es';

window.SidePanel = launcher('sidePanel', 'side-panel', {
	size: 'medium',
	show: false,
	tabs: [
		{
			url: '/iframe.html',
			pageName: 'Comments',
			slug: 'comments',
		},
		{
			url: '/iframe.html',
			pageName: 'Edit',
			slug: 'edit',
		},
		{
			url: '/iframe.html',
			pageName: 'Changelog',
			slug: 'changelog',
		},
	],
});
