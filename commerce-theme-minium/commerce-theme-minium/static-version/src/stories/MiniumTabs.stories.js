/* eslint-disable import/no-extraneous-dependencies */
import { storiesOf } from "@storybook/vue";
import "../styles/minium.scss";

import MiniumTabs from "../components/MiniumTabs.vue";

storiesOf("Tabs", module).add("default", () => ({
	components: { MiniumTabs },
	render() {
		return <minium-tabs />;
	}
}));