/* eslint-disable import/no-extraneous-dependencies */
import { storiesOf } from "@storybook/vue";
import "../styles/minium.scss";

import SmallTable from "../components/SmallTable.vue";

storiesOf("Small Table", module).add("default", () => ({
	components: { SmallTable },
	render() {
		return <commerce-small-table />;
	}
}));