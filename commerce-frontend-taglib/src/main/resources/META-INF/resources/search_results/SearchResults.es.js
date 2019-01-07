import Component from 'metal-component';
import Soy from 'metal-soy';
import debounce from 'metal-debounce';
import template from './SearchResults.soy';

class SearchResults extends Component {
	created() {
		this.search = debounce(this.search.bind(this), 500);
		this.handleKeyDown = this.handleKeyDown.bind(this);
		window.Liferay.on('search-term-update', this.updateQuery, this);
		window.Liferay.on('search-term-submit', this.goToSelected, this);
	}

	detached() {
		window.Liferay.detach('search-term-update', this.updateQuery, this);
		window.Liferay.detach('search-term-submit', this.goToSelected, this);
	}

	updateQuery(event) {
		this.loading = true;
		this.visible = event.term !== '';
		this.queryString = event.term;
		this.search();
	}

	search() {
		if (this.lock) {
return;
}

		this.lock = true;
		fetch(`${this.searchAPI}${themeDisplay.getPlid()}?q=${this.queryString}`, {
			method: 'GET'
		})
			.then(response => response.json())
			.then(results => {
				this.loading = false;
				this.lock = false;
				this.queryValue = this.queryString;
				this.results = results;
				this.selectedIndex = -1;
				this.selectNext();
			});
	}

	willUpdate({visible}) {
		if (visible) {
			if (visible.newVal) {
				document.addEventListener('keydown', this.handleKeyDown);
			}
			else {
				document.removeEventListener('keydown', this.handleKeyDown);
			}
		}
	}

	getFirstSuggestion() {
		const selectables = this.results.filter(i => i.type !== 'label');
		return selectables.length ? selectables[0].pos : -1;
	}

	getLastSuggestion() {
		const selectables = this.results.filter(i => i.type !== 'label').reverse();
		return selectables.length ? selectables[0].pos : -1;
	}

	selectNext() {
		const nexts = this.results.filter(
			i => i.pos > this.selectedIndex && i.type !== 'label'
		);
		this.selectedIndex = nexts.length ?
			nexts[0].pos :
			this.getFirstSuggestion();
	}

	selectPrevious() {
		const prevs = this.results
			.filter(i => i.pos < this.selectedIndex && i.type !== 'label')
			.reverse();
		this.selectedIndex = prevs.length ? prevs[0].pos : this.getLastSuggestion();
	}

	goToSelected() {
		const selected = this.results.filter(i => i.selected);

		if (selected.length) {
			if (selected[0].url) {
				if (Liferay.SPA) {
					Liferay.SPA.app.navigate(selected[0].url);
				}
				else {
					window.location.href = selected[0].url;
				}
			}
		}
	}

	handleKeyDown(e) {
		if (e.key === 'ArrowDown') {
			this.selectNext();
		}
		else if (e.key === 'ArrowUp') {
			this.selectPrevious();
		}
	}

	_handleClick() {
		this.goToSelected();
	}

	handleMouseEnter(e) {
		this.selectedIndex = parseInt(e.delegateTarget.dataset.pos, 10);
	}

	handleMouseLeave(e) {
		this.selectedIndex = this.getFirstSuggestion();
	}

	setSelected(sel) {
		sel = ((sel + 1 + this.results.length + 1) % (this.results.length + 1)) - 1;
		this.results = this.results.map((item, i) =>
			Object.assign({}, item, {
				selected: i === sel,
				pos: i
			})
		);

		return sel;
	}
}

Soy.register(SearchResults, template);

SearchResults.STATE = {
	loading: {
		value: false
	},
	queryString: {
		value: ''
	},
	queryValue: {
		value: ''
	},
	results: {
		value: []
	},
	searchAPI: {
		value: ''
	},
	selectedIndex: {
		setter: 'setSelected',
		value: -1
	},
	visible: {
		value: false
	}
};

export {SearchResults};
export default SearchResults;
