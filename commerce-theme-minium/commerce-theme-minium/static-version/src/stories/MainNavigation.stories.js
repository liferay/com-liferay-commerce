/* eslint-disable import/no-extraneous-dependencies */
import { storiesOf } from "@storybook/vue";
import "../styles/minium.scss";

import MainNavigation from "../components/MainNavigation.vue";

storiesOf("Main Navigation", module).add("default", () => ({
	components: { MainNavigation },
	render() {
		return <minium-main-navigation />;
	}
}));