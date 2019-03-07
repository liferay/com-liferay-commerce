/* eslint-disable import/no-extraneous-dependencies */
import { storiesOf } from "@storybook/vue";
import "../styles/minium.scss";

import MiniumToast from "../components/MiniumToast.vue";

storiesOf("Toast", module).add("default", () => ({
	components: { MiniumToast },
	render() {
		return <minium-toast>Lorem ipsum</minium-toast>;
	}
}));