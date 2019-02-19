import Component from 'metal-component';
import Soy, {Config} from 'metal-soy';

import template from './SearchBar.soy';

class SearchBar extends Component {

	created() {
		this.handleDocumentKeypress = this.handleDocumentKeypress.bind(this);

		document.addEventListener('keydown', this.handleDocumentKeypress);

		document.querySelectorAll('.js-toggle-search').forEach(
			el => {
				el.classList.toggle('is-active', status);
				el.addEventListener('click', this._toggleClick.bind(this));
			}
		);

		this._handleClickOutside = this._handleClickOutside.bind(this);
	}

	_handleClickOutside(e) {
		const suggestions = document.querySelector('.minium-suggestions');
		if (
			!(
				this.element.contains(e.target) ||
				(suggestions && suggestions.contains(e.target))
			)
		) {
			this.toggle();
		}
	}

	detached() {
		document.removeEventListener('keydown', this.handleDocumentKeypress);
	}

	handleDocumentKeypress(evt) {
		if (this.active && evt.key === 'Escape') {
			this.toggle(false);
		}
		else if (!this.active && evt.key === '/') {
			this.toggle(true);
		}
	}

	handleEmpty(evt) {
		this.updateQuery('');
	}

	handleKeyUp(evt) {
		if (evt.key == 'ArrowDown' || evt.key === 'ArrowUp') {
			evt.preventDefault();
		}

		this.updateQuery(evt.target.value);
	}

	handleSubmit(evt) {
		evt.preventDefault();

		window.Liferay.fire(
			'search-term-submit',
			{term: this.query}
		);
	}

	updateQuery(query) {
		if (query !== this.query) {
			this.toggle(true);

			this.query = query;

			window.Liferay.fire(
				'search-term-update',
				{term: query}
			);
		}
	}

	toggle(status) {
		if (status) {
			setTimeout(
				() => {
					this.refs.searchInput.focus();
					window.addEventListener('click', this._handleClickOutside);
				},
				0
			);
		}
		else {
			setTimeout(
				() => {
					this.refs.searchInput.blur();
					window.removeEventListener('click', this._handleClickOutside);
				},
				0
			);
		}

		this.active = status;

		this.emit('toogled', status);
	}

	_toggleClick() {
		this.toggle(!this.active);
	}

}

Soy.register(SearchBar, template);

SearchBar.STATE = {
	active: Config.bool(),
	placeholder: Config.value(''),
	query: Config.string()
};

export {SearchBar};
export default SearchBar;