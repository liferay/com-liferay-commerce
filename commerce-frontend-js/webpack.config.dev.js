const HtmlWebpackPlugin = require('html-webpack-plugin');
const path = require('path');

const {defineServerResponses} = require('./dev/fakeServerUtilities');

const outputPath = path.resolve(__dirname, './dev/public');

const getComponentPath = (component, entry) => path.join(
	__dirname,
	'src',
	'main',
	'resources',
	'META-INF',
	'resources',
	'js',
	component,
	entry
);

// eslint-disable-next-line no-undef
module.exports = {
	devServer: {
		before(app) {
			defineServerResponses(app);
		},
		compress: false,
		contentBase: './dev/public',
		open: true,
		openPage: 'index.html',
		port: 9000,
		proxy: {
			'/o': {
				target: 'http://localhost:8080/'
			}
		},
		publicPath: '/',
	},
	devtool: 'inline-source-map',
	entry: {
		dataset_display: getComponentPath('dataset_display', 'entry.dev.es.js'),
		example: getComponentPath('example', 'entry.dev.es.js'),
		gallery: getComponentPath('gallery', 'entry.dev.es.js'),
		side_panel: getComponentPath('side_panel', 'entry.dev.es.js'),
	},
	mode: 'development',
	module: {
		rules: [
			{
				exclude: /node_modules/,
				test: /\.(js|jsx)$/,
				use: [
					{
						loader: 'babel-loader',
					},
				],
			},
			{
				test: /\.(scss|css)$/,
				use: [
					{loader: 'style-loader'},
					{loader: 'css-loader'},
					{loader: 'sass-loader'},
				],
			},
			{
				exclude: /node_modules/,
				test: /\.tsx?$/,
				use: 'ts-loader',
			},
		],
	},
	output: {
		filename: '[name].js',
		path: outputPath,
	},
	plugins: [
		new HtmlWebpackPlugin({
			inject: false,
			template: path.resolve(__dirname, './dev/public/index.html'),
		}),
	],
	resolve: {
		extensions: ['.js', '.jsx', '.ts', '.tsx'],
	},
};
