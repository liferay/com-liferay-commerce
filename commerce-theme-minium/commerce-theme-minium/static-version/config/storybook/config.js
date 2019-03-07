/* eslint-disable import/no-extraneous-dependencies */
import { configure, addDecorator } from "@storybook/vue";

const req = require.context("../../src/stories", true, /.stories.js$/);

function loadStories() {
	req.keys().forEach(filename => req(filename));
}

addDecorator(() => ({
	template: '<div id="minium"><story /></div>'
}));

configure(loadStories, module);