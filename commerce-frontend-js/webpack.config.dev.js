const {defineServerResponses} = require('./dev/fakeServerUtilities');

const path = require('path');
const HtmlWebpackPlugin = require('html-webpack-plugin');

const outputPath = path.resolve(__dirname, './dev/public');

module.exports = {
	entry: {
		components: [
			path.join(__dirname, './src/main/resources/META-INF/resources/js/add_or_create/entry.dev.es.js'),
			path.join(__dirname, './src/main/resources/META-INF/resources/js/assign_to/entry.dev.es.js'),
			path.join(__dirname, './src/main/resources/META-INF/resources/js/table_toolbar/entry.dev.es.tsx'),
			path.join(__dirname, './src/main/resources/META-INF/resources/js/gallery/entry.dev.es.js'),
			path.join(__dirname, './src/main/resources/META-INF/resources/js/modal/entry.dev.es.js'),
			path.join(__dirname, './src/main/resources/META-INF/resources/js/side_panel/entry.dev.es.js'),
			path.join(__dirname, './src/main/resources/META-INF/resources/js/utilities/entry.js'),
		],
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
		filename: `bundle.js`,
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
