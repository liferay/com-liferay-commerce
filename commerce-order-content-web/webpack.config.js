const path = require('path');

module.exports = {
	entry: './src/main/resources/META-INF/resources/dynamic_panel/js/index.es.js',
	mode: 'production',
	module: {
		rules: [
			{
				exclude: /node_modules/,
				test: /\.(js|jsx)$/,
				use: [
					{
						loader: 'babel-loader'
					}
				]
			}
		]
	},
	output: {
		filename: '[name].es.js',
		path: path.resolve('./classes/META-INF/resources/dynamic_panel/js')
	}
};