import assignToLauncher from './entry.es';

const props = {
	test: 'test',
	spritemap: './icons.svg'
};

window.assignTo = assignToLauncher('assignTo', 'assign-to', props);
