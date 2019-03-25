'use strict';

import template from './CommerceDatalist.soy';
import Component from 'metal-component';
import Soy, {Config} from 'metal-soy';
import {debounce} from 'metal-debounce';

import '../autocomplete_item/AutocompleteItem.es';
import 'clay-icon';

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

class CommerceDatalist extends Component {
    
	attached() {
        if(this.queryUrl) {
            this._getDataFromApi = debounce(
                this._getDataFromApi.bind(this),
                this.apiDebounceTime
            );
        }
        this._handleClickOutside = this._handleClickOutside.bind(this);
	}

    openDropdown(){
		this.dropdownVisibilityState = 'expanding';

		return setTimeout(
			() => {
				this.dropdownVisibilityState = 'expanded';
				window.addEventListener('click', this._handleClickOutside);
			},
			100
		);
    }

    closeDropdown(){
		this.dropdownVisibilityState = 'collapsing';

		return setTimeout(
			() => {
				this.dropdownVisibilityState = 'collapsed';
				window.removeEventListener('click', this._handleClickOutside);
			},
			100
		);
    }

    willReceiveState() {
        this.data = fakeData;
    }

	_handleClickOutside(e) {
		if ( !this.element.contains(e.target) ) {
            this.closeDropdown();
        }
        return false
	}

    _handleInputWrapperClick(e){
        if(this.dropdownVisibilityState === 'collapsed') {
            this.openDropdown();
        }
    }

    _handleIconClick(e) {
        e.preventDefault();
        return this.toggleDropdown();
    }

    toggleDropdown() {
        return this.dropdownVisibilityState === 'collapsed' 
            ? this.openDropdown()
            : this.closeDropdown()
    }

    _handleDeleteElement(e) {
        const elementValue = this._getDatasetValue(e.target);
        const element = this._getDataElementFromValue(elementValue);
        return element && this.removeElement(element);
    }

    _handleSelectData(e) {
        const elementValue = this._getDatasetValue(e.target);
        const element = this._getDataElementFromValue(elementValue);
        if (this.multiselect) {
            return element && e.target.checked ? this.addElement(element) : this.removeElement(element);
        } else {
            return element && this.addElement(element);
        }
    }

    _getDatasetValue(target) {
        return target.closest('[data-value]').dataset.value
    }

    _handleInputKeyup(e) {
        if(e.keyCode === 13 && this.dropdownVisibilityState === 'collapsed') {
            return this.emit('submitForm');
        }
        if(e.keyCode === 13 && this.dropdownVisibilityState !== 'collapsed') {
            if(this._filteredValues.length) {
                const firstElement = this._getDataElementFromValue(this._filteredValues[0])
                const firstElementAlreadyAdded = this._filteredValues.reduce((added, el) => added || el.value === firstElement.value, false)
                return firstElementAlreadyAdded ? this.removeElement(firstElement) : this.addElement(firstElement);
            }
        }
        if (e.keyCode === 8 && !this.query.length && this.multiselect && this.value.length) {
			this.value = this.value.slice(0, -1);
		}
        if(e.target.value !== this.query) {
            this.query = e.target.value
        }
        if(e.selectionStart === 0 && e.keyCode == 37) {
            this.refs.scrollTo(500, 0);
        }
    }

    _filterDataByQuery(query) {
        if(!query) {
            this._filteredValues = null;
        }
        if(query && this.data) {
            this._filteredValues = this.data
                .filter(
                    el => el.label.toLowerCase().indexOf(query.toLowerCase()) > -1
                ).map(
                    el => el.value
                );
        }
        return this._filteredValues;
    }

    _getDataFromApi(query) {
        return fetch(
            this.queryUrl + '/' + query,
            {
                type: 'GET'
            }
        )
        .then(response => response.json())
        .then(
            jsonResponse => {
                console.log(jsonResponse)
                //this.data = jsonResponse.data
            }
        )
    }

    _getDataElementFromValue(value) {
        const element = this.data.reduce(
            (elementFound, el) => elementFound || el.value == value && el,
            false
        )
        return element;
    }

    syncValue(value) {
        return this.emit(
            'valueUpdated',
            {
                name: this.name,
                value
            }
        );
    }

    syncData(data) {
        this.emit(
            'dataUpdated',
            {
                name: this.name,
                data
            }
        )
    }

    syncQuery(query) {
        if(this.queryUrl) {
            this._getDataFromApi(query)
        } else {
            this._filterDataByQuery(query)
        }
    }

    addElement(element) {
        if(this.multiselect) {
            this.value = !!this.value 
                ? [ ...this.value, element ] 
                : [ element ];
            this.refs.inputText.focus();
            this.refs.queryWrapper.scrollTo(this.refs.queryWrapper.scrollWidth, 0);
        } else {
            this.value = element;
            this.closeDropdown();
        }
        return this.value;
    }

    removeElement(element) {
        this.value = this.multiselect 
            ? this.value.filter(
                (el) => el.value !== element.value
            )
            : null
    }
}

Soy.register(CommerceDatalist, template);

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

CommerceDatalist.STATE = {
    _filteredValues: Config.array(
        Config.string()
    ).internal(),
    additionalClasses: Config.string(),
    apiDebounceTime: Config.number().value(200),
    data: Config.array(
        elementMock
    ),
    dropdownVisibilityState: Config.oneOf(
        [
            'expanded',
            'expanding',
            'collapsing',
            'collapsed'
        ]
    ).value('collapsed'),
    formId: Config.string(),
	label: Config.string(),
    multiselect: Config.bool(),
    name: Config.string().required(),
    query: Config.string().value(''),
    value: Config.oneOfType(
        [
            elementMock,
            Config.array(
                elementMock
            )
        ]
    ),
    spritemap: Config.string().required(),
    queryUrl: Config.string()
};

export {CommerceDatalist};
export default CommerceDatalist;
