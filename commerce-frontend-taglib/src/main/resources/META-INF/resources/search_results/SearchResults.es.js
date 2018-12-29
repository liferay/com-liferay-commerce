import template from './SearchResults.soy';
import Component from 'metal-component';
import Soy from 'metal-soy';

class SearchResults extends Component {
	created() {
		window.Liferay.on('search-term-update', this.updateQuery.bind(this));
		this.handleKeyDown = this.handleKeyDown.bind(this);
	}

	updateQuery(event) {
		this.visible = event.term !== '';
		this.queryString = event.term;
		this.search();
	}

	search() {
		return fetch(
			this.searchAPI + themeDisplay.getPlid() + '?q=' + this.queryString,
			{
				method: 'GET'
			}
		)
			.then(
				response => response.json()
			)
			.then(
				results => {
					return this.results = results;
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

	handleKeyDown(e) {
		if (e.key === 'ArrowDown') {
			e.preventDefault();
			const nexts = this.results.filter(
				i => i.pos > this.selectedIndex && i.type !== 'label'
			);
			this.selectedIndex = nexts.length ? nexts[0].pos : -1;
		}
		if (e.key === 'ArrowUp') {
			e.preventDefault();
			if (this.selectedIndex === -1) {
				this.selectedIndex = -2;
			}
			else {
				const prevs = this.results
					.filter(i => i.pos < this.selectedIndex && i.type !== 'label')
					.reverse();
				this.selectedIndex = prevs.length ? prevs[0].pos : -1;
			}
		}
	}

	_handleClick(e) {
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

	handleMouseEnter(e) {
		this.selectedIndex = parseInt(e.target.dataset.pos, 10);
	}

	handleMouseLeave() {
		this.selectedIndex = -1;
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
	results: {
		value: []
	},
	queryString: {
		value: ''
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