import launcher from './entry.es';
import './side_panel.scss';

window.SidePanel = launcher('sidePanel', 'side-panel', {
	size: 'medium',
	show: false,
	pages: [
		{
			url: `/sidepanel.html?${Math.random()}`,
			pageName: 'Comments',
		},
		{
			url: `/sidepanel.html?${Math.random()}`,
			pageName: 'Edit',
		},
		{
			url: `/sidepanel.html?${Math.random()}`,
			pageName: 'Changelog',
		},
	],
});
