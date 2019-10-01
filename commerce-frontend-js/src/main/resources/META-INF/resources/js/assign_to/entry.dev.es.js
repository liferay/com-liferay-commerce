import assignToLauncher from './entry.es';

const props = {
	spritemap: './assets/icons.svg',
	test: 'test'
};

window.assignTo = assignToLauncher('assignTo', 'assign-to', props);
