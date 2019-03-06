import 'clay-icon';
import {Config} from 'metal-state';
import Component from 'metal-component';
import Soy from 'metal-soy';

import template from './AccordionPanel.soy';

class AccordionPanel extends Component {

    attached(){
        this.expand = this.expand.bind(this);
        this.collapse = this.collapse.bind(this);
    }

    syncStatus(){
        this.emit('updateStatus', this.status);
    }

    _handleTogglerClick(){
        switch (this.status) {
            case 'closed':
                this.status = 'opening';
                this.expand();
                break;
            case 'opened':
                this.status = 'closing';
                this.collapse();
                break;
            default:
                break;
        }
    }

    _updateStatus(newStatus){
        this.status = newStatus;
        this.refs.content.removeEventListener(
            'transitionend',
            this._updateStatus.bind(this, newStatus)
        )
    }

    expand(){
        this.refs.content.style.maxHeight = this.refs.content.scrollHeight + 'px';
        this.refs.content.addEventListener(
            'transitionend',
            this._updateStatus.bind(this, 'opened')
        );
    }
    
    collapse(){
        this.refs.content.style.maxHeight = this.refs.content.scrollHeight + 'px';
        setTimeout(
            () => {
                this.refs.content.style.maxHeight = '0px';
            },
            100
        );
        this.refs.content.addEventListener(
            'transitionend',
            this._updateStatus.bind(this, 'closed')
        );
    }

}


Soy.register(AccordionPanel, template);

AccordionPanel.STATE = {
    title: Config.oneOfType(
        [
            Config.string(),
            Config.func()
        ]
    ),
    content: Config.oneOfType(
        [
            Config.string(),
            Config.func()
        ]
    ),
    status: Config.oneOf(
        [
            'closing',
            'closed',
            'opened',
            'opening'
        ]
    ),
    spritemap: Config.string()
};

export {AccordionPanel};
export default AccordionPanel;