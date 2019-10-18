import launcher from './entry.es';

import '../../styles/main.scss';

const props = {
	spritemap: './assets/icons.svg'
};

launcher('exampleId', 'example-root-id', props);
