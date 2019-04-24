'use strict';

import template from './Datalist.soy';
import Component from 'metal-component';
import Soy, {Config} from 'metal-soy';
import {debounce} from 'metal-debounce';

import '../autocomplete_item/AutocompleteItem.es';
import 'clay-icon';

class Datalist extends Component {
    
	attached() {
        this._handleClickOutside = this._handleClickOutside.bind(this);
        this._getData = debounce(() => this._getData(), 300);
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

	_handleClickOutside(e) {
		if ( !this.element.contains(e.target) ) {
            this.closeDropdown();
        }
        return false;
	}

    _handleInputWrapperClick(e){
        if(this.dropdownVisibilityState === 'collapsed') {
            this.openDropdown();
        }
    }

    _handleResetButtonClick(e){
        e.preventDefault();
        this._resetInputBox();
    }

    _resetInputBox(){
        this.addedData = null;
        return this.closeDropdown();
    }

    _handleIconClick(e) {
        e.preventDefault();
        return this.toggleDropdown();
    }

    _handleDeleteElement(e) {
        e.preventDefault();
        const elementValue = this._getDatasetValue(e.target);
        const element = this._getDataElementFromValue(elementValue);
        return element && this.removeElement(element);
    }

    _handleSelectData(e) {
        const elementValue = this._getDatasetValue(e.target);
        const element = this._getDataElementFromValue(elementValue);
        if(!elementValue && !this.multiselect) {
            this.addedData = null;
            this.query = null;
            this.closeDropdown();
            return null;
        }
        if (this.multiselect) {
            return element && e.target.checked ? 
                this.addElement(element) : 
                this.removeElement(element);
        } else {
            return element && 
                this.addElement(element);
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
            const dataToBeFiltered = this._filteredValues || this.data && this.data.map(el => el.value)
            if(!!dataToBeFiltered && dataToBeFiltered.length) {
                const firstElement = this._getDataElementFromValue(dataToBeFiltered[0])
                const firstElementAlreadyAdded = !!this.addedData && this.addedData.reduce(
                    (added, el) => added || (el.value || el) === firstElement.value,
                    false
                )
                return firstElementAlreadyAdded ? this.removeElement(firstElement) : this.addElement(firstElement);
            }
        }
        if (e.keyCode === 8 && !this.query.length && this.multiselect && this.addedData.length) {
			this.addedData = this.addedData.slice(0, -1);
		}
        if(e.target.value !== this.query) {
            this.query = e.target.value
        }
        if(e.target.selectionStart === 0 && e.keyCode == 37) {
            e.preventDefault()
            this.refs.queryWrapper.scrollTo(this.refs.queryWrapper.scrollLeft - 30, 0);
        }
    }

    _getDataElementFromValue(value) {
        const element = this.data.reduce(
            (elementFound, el) => elementFound || el.value == value && el,
            false
        )
        return element;
    }

    syncAddedData() {
        if(!this.addedData) {
            this.selected = null
            return false
        }
        this.selected = Array.isArray(this.addedData) 
            ? this.addedData.map(el => el.value)
            : this.addedData.value

        return true
    }

    syncSelected(selected) {
        this.stringifiedSelected = selected ? JSON.stringify(selected) : '';
        this.emit('selected')
    }

    toggleDropdown() {
        return this.dropdownVisibilityState === 'collapsed' 
            ? this.openDropdown()
            : this.closeDropdown()
    }

    syncQuery(query) {
        return this.emit(
            'updateQueryFilter',
            query
        )
    }

    addElement(element) {
        if(this.multiselect) {
            this.addedData = !!this.addedData 
                ? [ ...this.addedData, element ] 
                : [ element ];
            this.refs.inputText.focus();
            this.refs.queryWrapper.scrollTo(this.refs.queryWrapper.scrollWidth, 0);
        } else {
            this.addedData = element;
            this.closeDropdown();
        }
        return this.addedData;
    }

    removeElement(element) {
        this.addedData = this.multiselect 
            ? this.addedData.filter(
                (el) => el.value !== element.value
            )
            : null;
    }
}

Soy.register(Datalist, template);

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

Datalist.STATE = {
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
    selected: Config.oneOfType(
        [
            Config.string(),
            Config.number(),
            Config.array(
                Config.oneOfType(
                    [
                        Config.string(),
                        Config.number()
                    ]
                )
            )
        ]
    ),
    stringifiedSelected: Config.string(),
    addedData: Config.oneOfType(
        [
            elementMock,
            Config.array(
                elementMock
            )
        ]
    ),
    spritemap: Config.string().required()
};

export {Datalist};
export default Datalist;