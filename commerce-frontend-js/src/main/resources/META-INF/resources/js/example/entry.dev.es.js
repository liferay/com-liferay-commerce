import launcher from './entry.es';

import './_example.scss';

const props = {
	spritemap: './assets/icons.svg'
};

launcher('exampleId', 'example-root-id', props);
