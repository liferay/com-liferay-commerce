'use strict';

const gulp = require('gulp');
const liferayThemeTasks = require('liferay-theme-tasks');
const path = require('path');
const runSequence = require('run-sequence');
const svgmin = require('gulp-svgmin');
const rename = require('gulp-rename');

const svgstore = require('gulp-svgstore');

const baseThemePath = __dirname;

const buildPath = path.join(baseThemePath, 'build');
const srcPath = path.join(baseThemePath, 'src');
const allSvgPath = '/images/minium-icons';

const svgMinConfig = file => {
	const prefix = path.basename(file.relative, path.extname(file.relative));
	return {
		plugins: [
			{
				cleanupIDs: {
					prefix: prefix + '-'
				}
			}
		]
	};
};

gulp.task(
	'build:compile-svgs',
	() => (
		gulp
		.src(`${srcPath}${allSvgPath}/*.svg`)
		.pipe(svgmin(svgMinConfig))
		.pipe(svgstore({}))
		.pipe(rename('icons.svg'))
		.pipe(gulp.dest(`${buildPath}${allSvgPath}`))
	)
);

liferayThemeTasks.registerTasks({
	gulp: gulp,
	hookFn: function(gulp) {
		gulp.hook('after:build:src', done =>
			runSequence('build:compile-svgs', done)
		);
	}
});