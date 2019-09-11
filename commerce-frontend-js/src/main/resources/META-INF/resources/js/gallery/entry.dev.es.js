import launcher from './entry.es';
import './_gallery.scss';

window.Gallery = launcher('gallery', 'gallery', {
	onImageClick: console.log,
	images: ['#1abc9c', '#2ecc71', '#3498db', '#f1c40f', '#e67e22', '#e74c3c', '#9b59b6', '#34495e', '#95a5a6'].map(c => ({
		id: c.slice(1),
		url: `https://via.placeholder.com/200/${c.slice(1)}?text=%20`,
	})),
});
