import Component from 'metal-component';
import Soy, {Config} from 'metal-soy';
import debounce from 'metal-debounce';
import template from './SearchResults.soy';

class SearchResults extends Component {

	created() {
		this._search = debounce(this._search.bind(this), 500);
		this._handleKeyDown = this._handleKeyDown.bind(this);
		window.Liferay.on('search-term-update', this._updateQuery, this);
		window.Liferay.on('search-term-submit', this._goToSelected, this);
	}

	willUpdate({visible}) {
		if (visible) {
			if (visible.newVal) {
				document.addEventListener('keydown', this._handleKeyDown);
			}
			else {
				document.removeEventListener('keydown', this._handleKeyDown);
			}
		}
	}

	detached() {
		window.Liferay.detach('search-term-update', this._updateQuery, this);
		window.Liferay.detach('search-term-submit', this._goToSelected, this);
		document.removeEventListener('keydown', this._handleKeyDown);
	}

	_getFirstSuggestion() {
		const selectables = this.results.filter(i => i.type !== 'label');
		return selectables.length ? selectables[0].pos : -1;
	}

	_getLastSuggestion() {
		const selectables = this.results.filter(i => i.type !== 'label').reverse();
		return selectables.length ? selectables[0].pos : -1;
	}

	_goToSelected() {
		const selected = this.results.filter(i => i.selected);
		if (selected.length && selected[0].url) {
			if (Liferay.SPA) {
				Liferay.SPA.app.navigate(selected[0].url);
			}
			else {
				window.location.href = selected[0].url;
			}
		}
	}

	_handleKeyDown(e) {
		if (e.key === 'ArrowDown') {
			this._selectNext();
		}
		else if (e.key === 'ArrowUp') {
			this._selectPrevious();
		}
	}

	_handleMouseEnter(e) {
		this.selectedIndex = parseInt(e.delegateTarget.dataset.pos, 10);
	}

	_handleMouseLeave(e) {
		this.selectedIndex = this._getFirstSuggestion();
	}

	_search() {
		if (this.lock) {
			return;
		}

		this.lock = true;

		fetch(
			`${this.searchAPI}${themeDisplay.getPlid()}?q=${this.queryString}&groupId=${themeDisplay.getScopeGroupId()}&commerceAccountId=${this.commerceAccountId}`,
			{
				method: 'GET'
			}
		)
			.then(response => response.json())
			.then(
				results => {
					this.loading = false;
					this.lock = false;
					this.queryValue = this.queryString;
					this.results = results;
					this.selectedIndex = -1;
					this._selectNext();
				}
			);
	}

	_selectNext() {
		const nexts = this.results.filter(
			i => i.pos > this.selectedIndex && i.type !== 'label'
		);

		this.selectedIndex = nexts.length ?
			nexts[0].pos :
			this._getFirstSuggestion();
	}

	_selectPrevious() {
		const prevs = this.results
			.filter(i => i.pos < this.selectedIndex && i.type !== 'label')
			.reverse();

		this.selectedIndex = prevs.length ? prevs[0].pos : this._getLastSuggestion();
	}

	_setSelected(sel) {
		sel = ((sel + 1 + this.results.length + 1) % (this.results.length + 1)) - 1;

		this.results = this.results.map(
			(item, i) => Object.assign(
				{},
				item,
				{
					pos: i,
					selected: i === sel
				}
			)
		);

		return sel;
	}

	_updateQuery(event) {
		this.loading = true;
		this.queryString = event.term;
		this.visible = event.term !== '';

		this._search();
	}

	_handleClick() {
		this._goToSelected();
	}

}

Soy.register(SearchResults, template);

SearchResults.STATE = {
	commerceAccountId: Config.oneOfType(
		[
			Config.number(),
			Config.string()
		]
	),
	loading: Config.bool().value(false),
	queryString: Config.string().value(''),
	queryValue: Config.string().value(''),
	results: {
		value: []
	},
	searchAPI: Config.string().required(),
	selectedIndex: {
		setter: '_setSelected',
		value: -1
	},
	spritemap: Config.string().required(),
	visible: Config.bool().value(false)
};

export {SearchResults};
export default SearchResults;