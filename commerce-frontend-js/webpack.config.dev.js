const {defineServerResponses} = require('./dev/fakeServerUtilities');

const path = require('path');
const HtmlWebpackPlugin = require('html-webpack-plugin');

const outputPath = path.resolve(__dirname, './dev/public');

const getComponentPath = (component, entry) => path.join(
	// eslint-disable-next-line no-undef
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
	entry: {
		// add_or_create: getComponentPath('add_or_create', 'entry.dev.es.js'),
		// assign_to: getComponentPath('assign_to', 'entry.dev.es.js'),
		// table_toolbar: getComponentPath('table_toolbar', 'entry.dev.es.js'),
		// gallery: getComponentPath('gallery', 'entry.dev.es.js'),
		// modal: getComponentPath('modal', 'entry.dev.es.js'),
		// side_panel: getComponentPath('side_panel', 'entry.dev.es.js'),
		// step_tracker: getComponentPath('step_tracker', 'entry.dev.es.js'),
		// utilities: getComponentPath('utilities', 'index.js'),
		table: getComponentPath('table', 'entry.dev.es.js'),
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
			// eslint-disable-next-line no-undef
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
		disableHostCheck: true,
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
