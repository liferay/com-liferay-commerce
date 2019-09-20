import launcher from './entry.es';
import './_side_panel.scss';

window.SidePanel = launcher('sidePanel', 'side-panel', {
	size: 'medium',
	visible: false,
	spritemap: './icons.svg',
	pages: [
		{
			url: `/index.html?${Math.random()}`,
			pageName: 'Index',
		},
		{
			url: `/addorcreate.html?${Math.random()}`,
			pageName: 'Add or create',
		},
		{
			url: `/gallery.html?${Math.random()}`,
			pageName: 'Gallery',
		},
	],
	topAnchor: document.querySelector('.top-anchor'),
});
