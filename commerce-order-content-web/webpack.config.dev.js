const path = require('path');
const HtmlWebpackPlugin = require('html-webpack-plugin');
const CleanWebpackPlugin = require('clean-webpack-plugin');

module.exports = {
	entry:
		path.join(__dirname, './src/main/resources/META-INF/resources/dynamic_panel/js/index.dev.es.js'),
	mode: 'development',
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
			},
			{
				test: /\.(scss|css)$/,
				use: [
					{ loader: 'style-loader' },
					{ loader: 'css-loader' },
					{ loader: 'sass-loader' }
				]
			}
		]
	},
	output: {
		filename: `[name].js`,
		path: path.resolve(__dirname, './dev/public')
	},
	plugins: [
		// new CleanWebpackPlugin(),
		new HtmlWebpackPlugin({
			template: path.resolve(__dirname, './dev/public/index.html')
		})
	],
	resolve: {
		extensions: ['.js', '.jsx', '.svg']
	},

	devServer: {
		compress: false,
		contentBase: path.join(__dirname, './dev/public'),
		open: true,
		port: 9000,
		// proxy: {
		// 	'**': 'http://0.0.0.0:8080'
		// }
	},
};
