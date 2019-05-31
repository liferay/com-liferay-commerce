import React from 'react';
import { shallow, mount } from 'enzyme';
import Menu, {
    MenuElement
} from '../../../src/main/resources/META-INF/resources/dynamic_panel/js/components/Menu.es';

const mock = {
  active: 'comments',
  elements : [
      {
        icon: 'comment',
        url: '/api/comment',
        pageName: 'Comments',
        slug: 'comments',
        spritemap : 'custom-spritemap.svg'
      },
      {
        icon: 'edit',
        url: '/api/edit',
        pageName: 'Edit',
        slug: 'edit',
      }
  ],
  spritemap : 'default-spritemap.svg'
}

it('renders without crashing', () => {
  shallow(<Menu {...mock}/>);
});

describe('Menu items handling', () => {

    it('should be closed if no "current" prop passed', () => {
        const panel = mount(<Menu />);
        const menuElements = panel.find(MenuElement);
    })

});