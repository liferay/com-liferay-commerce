import template from "./MiniumSearchBar.soy.js";
import Component from "metal-component";
import Soy from "metal-soy";

class MiniumSearchBar extends Component {
	handleSubmit(e) {
		e.preventDefault();
		this.updateQuery(e.target.querySelector(`#${this.id}`).value);
	}

	handleKeyUp(e) {
		this.updateQuery(e.target.value);
	}

	updateQuery(query) {
		window.Liferay.fire("search-term-update", query);
	}
}

Soy.register(MiniumSearchBar, template);

MiniumSearchBar.STATE = {
	id: {
		value: null
	},
	placeholder: {
		value: ""
	}
};

export { MiniumSearchBar };
export default MiniumSearchBar;