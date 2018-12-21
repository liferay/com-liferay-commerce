'use strict';

import template from './UserInvitationListItem.soy.js';
import Component from 'metal-component';
import Soy, { Config } from 'metal-soy';

import { EMAIL_REGEX } from './UserInvitation';

class UserInvitation extends Component {

    attached(){
        return this._updateStatus();
    }

    willUpdate(){
        return this._updateStatus();
    }

    syncAddedUsers(){
        return this._invited = this.addedUsers.reduce((hasBeenInvited, user) => hasBeenInvited || user.email === this.email, false)
    }

    _updateStatus() {
        if(!!this.id) {
            return this._status = 'valid';
        }
        if (this.email.indexOf('@') < 0) {
            return this._status = 'user-not-found';
        }
        if (!EMAIL_REGEX.test(this.email)) {
            return this._status = 'email-not-valid';
        }
        return this._status = 'valid';
    }

    _handleToggleInvitation() {
        return this.emit(
            'toggleInvitation',
            Object.assign(
                {
                    email: this.email
                },
                !!this.id ? {
                    id: this.id,
                    name: this.name,
                    thumbnail: this.thumbnail
                } : {}
            )
        )
    }

};

Soy.register(UserInvitation, template);

UserInvitation.STATE = {
    id: Config.oneOfType(
        [
            Config.string(),
            Config.number()
        ]
    ),
    thumbnail: Config.string(),
    name: Config.string(),
    email: Config.string().required(),
    addedUsers: Config.array(
        Config.shapeOf(
            {
                email: Config.string()
            }
        )
    ).value(
        []
    ),
    query: Config.string(),
    _invited: Config.bool().value(false),
    _status: Config.string().value('valid')
}

export { UserInvitation };
export default UserInvitation;