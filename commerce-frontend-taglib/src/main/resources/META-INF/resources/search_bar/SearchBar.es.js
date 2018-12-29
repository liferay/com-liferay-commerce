import Component from 'metal-component';
import debounce from 'metal-debounce';
import Soy, {Config} from 'metal-soy';

import template from './SearchBar.soy';

class SearchBar extends Component {

	created() {
		this.updateQuery = debounce(this.updateQuery.bind(this), 500);

		document.addEventListener('keydown', this.handleEscape.bind(this));

		document.querySelectorAll('.js-toggle-search').forEach(el => {
			el.addEventListener('click', this._toogleClick.bind(this));
		});
	}

	_toogleClick() {
		this.toogle(!this.active);
	}

	handleSubmit(e) {
		e.preventDefault();
		this.updateQuery(this.refs.searchInput.value);
	}

	handleKeyUp(e) {
		this.updateQuery(e.target.value);
	}

	updateQuery(query) {
		this.toogle(true);

		window.Liferay.fire('search-term-update', {'term': query});
	}

	handleEscape(e) {
		if (this.active && e.key === 'Escape') {
			this.toogle();
		}
	}

	toogle(status) {
		this.active = status;

		if (this.active) {
			this.elementClasses = 'show';
		}
		else {
			this.elementClasses = 'hide';
		}

		this.emit('toogled', this.active);
	}
}

Soy.register(SearchBar, template);

SearchBar.STATE = {
	placeholder: {
		value: ''
	},
	query: Config.string(),
	active: Config.bool()
};

export {SearchBar};
export default SearchBar;