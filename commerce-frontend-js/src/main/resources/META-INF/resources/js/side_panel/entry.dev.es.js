import launcher from './entry.es';
import './_side_panel.scss';

window.SidePanel = launcher('sidePanel', 'side-panel', {
	pages: [
		{
			pageName: 'Index',
			url: `/index.html?${Math.random()}`
		},
		{
			pageName: 'Add or create',
			url: `/addorcreate.html?${Math.random()}`
		},
		{
			pageName: 'Gallery',
			url: `/gallery.html?${Math.random()}`
		}
	],
	size: 'medium',
	spritemap: './icons.svg',
	topAnchor: document.querySelector('.top-anchor'),
	visible: false,
});
