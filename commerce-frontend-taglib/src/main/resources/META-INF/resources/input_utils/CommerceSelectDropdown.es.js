'use strict';

import template from './CommerceSelectDropdown.soy';
import Component from 'metal-component';
import Soy, {Config} from 'metal-soy';

const fakeData = [
    {
        value: 'mercedes',
        label: 'Mercedes'
    },
    {
        value: 'audi',
        label: 'Audi'
    },
    {
        value: 'ferrari',
        label: 'Ferrari'
    },
    {
        value: 'alfa-romeo',
        label: 'Alfa Romeo'
    },
    {
        value: 'renault',
        label: 'Renault'
    }
]

class CommerceSelectDropdown extends Component {
    attached() {
    }
    
    willReceiveState() {
        this.data = fakeData;
    }

    _handleDeleteElement(e) {
        const element = this.getDataElementFromValue(e.target.dataset.value);
        return element && this.removeElement(element);
    }

    _handleSelectData(e) {
        const element = this.getDataElementFromValue(e.target.dataset.value);
        return element && e.target.checked ? this.addElement(element) : this.removeElement(element);
    }

    _handleInputKeyup(e) {
        console.log(e)
        this.query = e.target.value
    }

    _filterDataByQuery(query) {
        if(this.data) {
            this.filteredData = this.data.filter(el => el.indexOf(query) > -1);
        }
    }

    syncQuery(query) {
        if(this.queryUrl) {
            this._getDataFromApi(query)
        } else {
            this._filterDataByQuery(query)
        }
    }

    syncValue(value) {
        console.log(value);
        this.emit('valueUpdated', value);
    }

    addElement(element) {
        if(this.multiSelect) {
            this.value = !!this.value ? [...this.value, element] : [element]
        } else {
            this.value = element
        }
        return this.value;
    }

    removeElement(element) {
        this.value = this.multiSelect ? this.value.filter((el) => el.value !== element.value) : null
    }

    getDataElementFromValue(value) {
        const element = this.data.reduce(
            (elementFound, el) => elementFound || el.value == value && el,
            false
        )
        return element;
    }
}

Soy.register(CommerceSelectDropdown, template);

const elementMock = Config.shapeOf(
    {
        label: Config.oneOfType(
            [
                Config.string(),
                Config.number()
            ]
        ),
        value: Config.oneOfType(
            [
                Config.string(),
                Config.number()
            ]
        )
    }
);

CommerceSelectDropdown.STATE = {
    formId: Config.string(),
	data: Config.array(
        Config.shapeOf(
            {
                label: Config.oneOfType(
                    [
                        Config.string(),
                        Config.number()
                    ]
                ),
                value: Config.oneOfType(
                    [
                        Config.string(),
                        Config.number()
                    ]
                )
            }
        )
    ),
    filteredData: Config.array(
        elementMock
    ),
    query: Config.string().value(''),
	label: Config.string(),
    multiSelect: Config.bool(),
    value: Config.oneOfType(
        [
            elementMock,
            Config.array(
                elementMock
            )
        ]
    ),
    selectName: Config.string().required(),
    spritemap: Config.string().required(),
    queryUrl: Config.string()
};

export {CommerceSelectDropdown};
export default CommerceSelectDropdown;