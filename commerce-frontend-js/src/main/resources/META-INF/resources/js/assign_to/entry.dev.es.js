import assignToLauncher from './entry.es';

const props = {
	spritemap: './icons.svg',
	test: 'test'
};

window.assignTo = assignToLauncher('assignTo', 'assign-to', props);
