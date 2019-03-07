/* eslint-disable import/no-extraneous-dependencies */
import { storiesOf } from "@storybook/vue";
import "../styles/minium.scss";

import AccountList from "../components/AccountList.vue";

storiesOf("Account List", module).add("default", () => ({
	components: { AccountList },
	render() {
		return <commerce-account-list />;
	}
}));