import React from 'react';
import { shallow, mount } from 'enzyme';
import Menu, {
	MenuItem
} from '../../../src/main/resources/META-INF/resources/dynamic_panel/js/components/Menu.es';

const mock = {
	active: 'comments',
	elements: [
		{
			icon: 'comment',
			url: '/api/comment',
			pageName: 'Comments',
			slug: 'comments',
			label: 'Comment Page',
			spritemap: 'custom-spritemap.svg'
		},
		{
			url: '/api/edit',
			pageName: 'Edit',
			slug: 'edit',
			label: 'Edit Page'
		},
		{
			url: '/api/edit',
			pageName: 'Edit',
			slug: 'edit',
			icon: 'edit'
		}
	],
	spritemap: 'default-spritemap.svg'
};

describe('Menu', () => {
	it('renders without crashing', () => {
		shallow(<Menu {...mock} />);
	});

	it('should contain no menu items if no elements are passed in', () => {
		const panel = mount(<Menu />);
		expect(panel.find(MenuItem).children().length).toBe(0);
	});

	it('should contain menu items if elements are passed in', () => {
		const panel = mount(
			<Menu elements={mock.elements} spritemap={mock.spritemap} />
		);
		expect(panel.find(MenuItem).children().length).toBe(3);
	});
});
