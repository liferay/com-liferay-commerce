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
		disableHostCheck: true,
		historyApiFallback: true,
		hot: true,
		open: true,
		openPage: 'table.html',
		port: 9000,
		publicPath: '/',
		// filename: path.join(outputPath, '/bundle.js'),
	},
	devtool: 'inline-source-map',
	entry: {
		// add_or_create: getComponentPath('add_or_create', 'entry.dev.es.js'),
		// assign_to: getComponentPath('assign_to', 'entry.dev.es.js'),
		// gallery: getComponentPath('gallery', 'entry.dev.es.js'),
		// modal: getComponentPath('modal', 'entry.dev.es.js'),
		// side_panel: getComponentPath('side_panel', 'entry.dev.es.js'),
		// step_tracker: getComponentPath('step_tracker', 'entry.dev.es.js'),
		table: getComponentPath('smart_table', 'entry.dev.es.js'),
		// table_toolbar: getComponentPath('table_toolbar', 'entry.dev.es.js'),
		// utilities: getComponentPath('utilities', 'index.es.js'),
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
