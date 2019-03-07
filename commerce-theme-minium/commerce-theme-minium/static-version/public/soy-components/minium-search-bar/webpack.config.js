const webpackCommonConfig = require("webpack-config-clay");

module.exports = Object.assign(webpackCommonConfig, {
	entry: "./src/MiniumSearchBar.js",
	output: Object.assign(webpackCommonConfig.output, {
		filename: "./build/globals/commerce-search-bar.js"
	})
});