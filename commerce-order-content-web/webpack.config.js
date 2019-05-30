const path = require('path');

module.exports = {
	module: {
		rules: [{
			test: /\.(js|jsx)$/,
			exclude: /node_modules/,
			use: ['babel-loader']
		}]
	},
	entry: path.resolve(__dirname, './src/main/resources/META-INF/resources/dynamic_menu/js/index.es.js'),
	mode: 'production',
	resolve: {
		extensions: ['*', '.js', '.jsx'],
	},
	output: {
		path: path.resolve('./classes/META-INF/resources/dynamic_menu/js'),
		filename: 'dynamicMenu.js'
	}
};