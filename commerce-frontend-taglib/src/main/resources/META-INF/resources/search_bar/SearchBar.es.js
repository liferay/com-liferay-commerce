import Component from 'metal-component';
import Soy, {Config} from 'metal-soy';

import template from './SearchBar.soy';

class SearchBar extends Component {
	created() {
		this.handleDocumentKeypress = this.handleDocumentKeypress.bind(this);
		document.addEventListener('keydown', this.handleDocumentKeypress);
		document.querySelectorAll('.js-toggle-search').forEach(el => {
			el.classList.toggle('is-active', status);
			el.addEventListener('click', this._toogleClick.bind(this));
		});
	}

	detached() {
		document.removeEventListener('keydown', this.handleDocumentKeypress);
	}

	_toogleClick() {
		this.toogle(!this.active);
	}

	handleSubmit(e) {
		e.preventDefault();
		window.Liferay.fire('search-term-submit', {term: this.query});
	}

	handleEmpty(e) {
		this.updateQuery('');
	}

	handleKeyUp(e) {
		if (e.key == 'ArrowDown' || e.key === 'ArrowUp') {
e.preventDefault();
}
		this.updateQuery(e.target.value);
	}

	updateQuery(query) {
		if (query !== this.query) {
			this.toogle(true);
			this.query = query;
			window.Liferay.fire('search-term-update', {term: query});
		}
	}

	handleDocumentKeypress(e) {
		if (this.active && e.key === 'Escape') {
			this.toogle(false);
		}
		else if (!this.active && e.key === '/') {
			this.toogle(true);
		}
	}

	toogle(status) {
		if (status) {
setTimeout(() => this.refs.searchInput.focus(), 0);
}
		else {
setTimeout(() => this.refs.searchInput.blur(), 0);
}

		this.active = status;
		this.emit('toogled', status);
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
