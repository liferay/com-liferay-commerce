/* eslint-disable import/no-extraneous-dependencies */
import { storiesOf } from "@storybook/vue";
import "../styles/minium.scss";

import MiniumSuggestions from "../components/MiniumSuggestions.vue";

storiesOf("Suggestions", module).add("default", () => ({
	components: { MiniumSuggestions },
	render() {
		return <commerce-suggestions />;
	}
}));