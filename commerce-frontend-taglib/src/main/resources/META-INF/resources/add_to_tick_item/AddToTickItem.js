'use strict';

import template from './AddToTickItem.soy.js';
import Component from 'metal-component';
import Soy, { Config } from 'metal-soy';

class AddToTickItem extends Component {};

Soy.register(AddToTickItem, template);

AddToTickItem.STATE = {
    isAdded: Config.bool().value(false)
}

export { AddToTickItem };
export default AddToTickItem;
