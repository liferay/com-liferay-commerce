import launcher from './entry.es';

const props = {
	test: 'test',
	spritemap: './icons.svg',
};

window.assignTo = launcher('assignTo', 'assign-to', props);
