import React from 'react';
import { shallow, mount } from 'enzyme';
import DynamicPanel from '../../../src/main/resources/META-INF/resources/dynamic_panel/js/DynamicPanel.es';
import Menu from '../../../src/main/resources/META-INF/resources/dynamic_panel/js/components/Menu.es';

const mock = {
  elements : [
      {
        icon: 'list-view',
        url: 'http://localhost:4000/api/text/list',
        pageName: 'Comments',
        slug: 'comments',
        spritemap : 'commerce-icons.svg'
      }
  ],
  spritemap : 'commerce-icons.svg'
}

it('renders without crashing', () => {
  shallow(<DynamicPanel />);
});

describe('Url handling', () => {
  it('should be closed if no "current" prop passed', () => {
    const panel = shallow(<DynamicPanel/>);
    const wrapper = panel.find('.dynamic-panel');
    expect(wrapper.hasClass('open')).toEqual(false)
  })

  it('should be opened if "current" prop is passed', () => {
    const panel = shallow(<DynamicPanel current={
      {slug: 'test', url: '/url'}
    }/>);
    const wrapper = panel.find('.dynamic-panel');
    expect(wrapper.hasClass('open')).toEqual(true)
  })

  it('should be opened if "openCustomPage" method is called from the outside', () => {
    const panel = shallow(<DynamicPanel />);
    const instance = panel.instance();
    instance.openCustomPage('/test');

    const wrapper = panel.find('.dynamic-panel');
    expect(wrapper.hasClass('open')).toEqual(true)
  })
});

describe('Menu handling', () => {
  it('should not contain a menu if no menu elements are passed as props', () => {
    const panel = mount(<DynamicPanel />);
    const menu = panel.find(Menu).get(0);
    expect(menu).toBeFalsy();
  })

  it('should contain a menu if menu elements are correctly passed as props', () => {
    const panel = mount(<DynamicPanel elements={mock.elements} />);
    const menu = panel.find(Menu).get(0);
    expect(menu).toBeTruthy();
  })
})