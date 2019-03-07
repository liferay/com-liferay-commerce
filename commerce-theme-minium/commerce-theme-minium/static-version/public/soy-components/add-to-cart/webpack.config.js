const webpackCommonConfig = require("webpack-config-clay");

module.exports = Object.assign(webpackCommonConfig, {
	entry: "./src/AddToCartButton.js",
	output: Object.assign(webpackCommonConfig.output, {
		filename: "./build/globals/add-to-cart-button.js"
	})
});