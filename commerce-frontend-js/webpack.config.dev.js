const {defineServerResponses} = require('./dev/fakeServerUtilities');

const path = require('path');
const HtmlWebpackPlugin = require('html-webpack-plugin');

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

module.exports = {
	entry: {
		add_or_create: getComponentPath('add_or_create', 'entry.dev.es.js'),
		assign_to: getComponentPath('assign_to', 'entry.dev.es.js'),
		table_toolbar: getComponentPath('table_toolbar', 'entry.dev.es.tsx'),
		gallery: getComponentPath('gallery', 'entry.dev.es.js'),
		modal: getComponentPath('modal', 'entry.dev.es.js'),
		side_panel: getComponentPath('side_panel', 'entry.dev.es.js'),
		utilities: getComponentPath('utilities', 'entry.js'),
	},
	mode: 'development',
	devtool: 'inline-source-map',
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
				test: /\.tsx?$/,
				use: 'ts-loader',
				exclude: /node_modules/,
			},
		],
	},
	output: {
		filename: '[name].js',
		path: outputPath,
	},
	plugins: [
		new HtmlWebpackPlugin({
			template: path.resolve(__dirname, './dev/public/index.html'),
			inject: false,
		}),
	],
	resolve: {
		extensions: ['.js', '.jsx', '.ts', '.tsx'],
	},
	devServer: {
		compress: false,
		publicPath: '/',
		contentBase: './dev/public',
		// filename: path.join(outputPath, '/bundle.js'),
		open: true,
		port: 9000,
		hot: true,
		historyApiFallback: true,
		before: function(app) {
			defineServerResponses(app);
		},
	},
};
