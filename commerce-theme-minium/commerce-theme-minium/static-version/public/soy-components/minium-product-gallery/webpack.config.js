const webpackCommonConfig = require("webpack-config-clay");

module.exports = Object.assign(webpackCommonConfig, {
	entry: "./src/MiniumProductGallery.js",
	output: Object.assign(webpackCommonConfig.output, {
		filename: "./build/globals/minium-product-gallery.js"
	})
});