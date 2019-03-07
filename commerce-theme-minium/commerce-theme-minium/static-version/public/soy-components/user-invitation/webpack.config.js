const webpackCommonConfig = require("webpack-config-clay");

module.exports = Object.assign(webpackCommonConfig, {
entry: "./src/UserInvitation.js",
output: Object.assign(webpackCommonConfig.output, {
	filename: "./build/globals/user-invitation.js"
})
});