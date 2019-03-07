import template from "./MiniumSearchResults.soy.js";
import Component from "metal-component";
import Soy from "metal-soy";
import { EventEmitter } from "metal-events";

const emitter = new EventEmitter();

class MiniumSearchResults extends Component {
	created() {
		window.Liferay.on("search-term-update", this.updateQuery.bind(this));
		this.handleKeyDown = this.handleKeyDown.bind(this);
	}

	updateQuery(q) {
		this.visible = q !== "";
		this.queryString = q;
	}

	willUpdate({ visible }) {
		if (visible) {
			if (visible.newVal) {
				document.addEventListener("keydown", this.handleKeyDown);
			} else {
				document.removeEventListener("keydown", this.handleKeyDown);
			}
		}
	}

	handleKeyDown(e) {
		if (e.key === "ArrowDown") {
			e.preventDefault();
			const nexts = this.results.filter(
				i => i.pos > this.selectedIndex && i.type !== "label"
			);
			this.selectedIndex = nexts.length ? nexts[0].pos : -1;
		}
		if (e.key === "ArrowUp") {
			e.preventDefault();
			if (this.selectedIndex === -1) {
				this.selectedIndex = -2;
			} else {
				const prevs = this.results
					.filter(i => i.pos < this.selectedIndex && i.type !== "label")
					.reverse();
				this.selectedIndex = prevs.length ? prevs[0].pos : -1;
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

Soy.register(MiniumSearchResults, template);

MiniumSearchResults.STATE = {
	results: {
		value: []
	},
	queryString: {
		value: ""
	},
	selectedIndex: {
		setter: "setSelected",
		value: -1
	},
	visible: {
		value: false
	}
};

export { MiniumSearchResults };
export default MiniumSearchResults;