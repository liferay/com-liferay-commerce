/* eslint-disable require-jsdoc */
const faker = require('faker');

function defineServerResponses(app) {
	app.get('/test-endpoint', (_, res) => {
		res.json({
			randomId: faker.random.uuid(),
		});
	});
}

module.exports = {
	defineServerResponses,
};
