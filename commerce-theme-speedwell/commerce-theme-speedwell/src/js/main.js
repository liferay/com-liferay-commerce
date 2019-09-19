if (window.NodeList && !NodeList.prototype.forEach) {
	NodeList.prototype.forEach = Array.prototype.forEach;
}

AUI().ready(

	/*
	This function gets loaded when all the HTML, not including the portlets, is
	loaded.
	*/

	function() {
		const searchBar = Liferay.component('search-bar');

		if (searchBar) {
			searchBar.on('toggled', function(status) {
				document.querySelectorAll(".js-toggle-search").forEach(function (el) {
					el.classList.toggle("is-active", status);
				});

				document.getElementById("speedwell")
					.classList.toggle("has-search", status);

				document.querySelector('.speedwell-search')
					.classList.toggle('is-open', status);
			});
		}
	}
);

Liferay.Portlet.ready(

	/*
	This function gets loaded after each and every portlet on the page.

	portletId: the current portlet's id
	node: the Alloy Node object of the current portlet
	*/

	function(portletId, node) {
	}
);

Liferay.on(
	'allPortletsReady',

	/*
	This function gets loaded when everything, including the portlets, is on
	the page.
	*/

	function() {

		function sign(x) {
			return ((x > 0) - (x < 0)) || + x
		}

		['main-menu', 'search', 'account'].forEach(function(el) {
			document.querySelectorAll('.js-toggle-' + el).forEach(function(element) {
				element.addEventListener('click', function() {
					element.focus();
					document.querySelector('.speedwell-' + el).classList.toggle('is-open');

					document.querySelector('.speedwell-category-nav').classList.remove('is-open');
				})
			});
		});

		document.querySelector('.speedwell-main-menu__links .main-link')
			.addEventListener('mouseover', function() {
				var element = document.querySelector('.speedwell-category-nav');

				element.focus();
				element.classList.add('is-open')
		});

		let scrollThreshold = 100;
		let lastKnownScrollPosition = 0;
		let lastKnownScrollOffset = 0;
		let ticking = false;
		var myMap = new Map();
		myMap.set(-1, 'up');
		myMap.set(1, 'down');

		const speedwellWrapper = document.getElementById("speedwell");
		const speedwellTranslucentTopbar = speedwellWrapper &&
			speedwellWrapper.querySelector('.speedwell-topbar--translucent');

		window.addEventListener("scroll", function() {
			const offset = window.scrollY - lastKnownScrollPosition;
			lastKnownScrollPosition = window.scrollY;
			lastKnownScrollOffset =
				sign(offset) === sign(lastKnownScrollOffset)
					? lastKnownScrollOffset + offset
					: offset;

			if (!ticking) {
				window.requestAnimationFrame(function () {
					if (Math.abs(lastKnownScrollOffset) > scrollThreshold) {
						speedwellWrapper
							.classList.add("is-scrolling-" + myMap.get(Math.sign(lastKnownScrollOffset)));
						speedwellWrapper
							.classList
							.remove("is-scrolling-" + myMap.get(-1 * Math.sign(lastKnownScrollOffset)));
					}

					speedwellWrapper
						.classList.toggle("is-scrolled", window.scrollY > scrollThreshold);

					handleTranslucentTopbar(speedwellTranslucentTopbar, scrollThreshold);
					ticking = false;
				});

				ticking = true;
			}
		}, false);

		function removeUnusedListeners() {
			window.removeEventListener('keydown', needsAccessibility);
		}

		function needsAccessibility(e) {
			const isTabbing = e.which === 9;

			if (isTabbing) {
				document.body.classList.add('is-accessible');
				removeUnusedListeners();
			}
		}

		window.addEventListener('keydown', needsAccessibility);

		setTimeout(removeUnusedListeners, 5000);
	}
);

function handleTranslucentTopbar(topbar, threshold) {
	if (!topbar) return;

	const topbarClass = 'speedwell-topbar--translucent';
	const isBeyond = window.scrollY <= threshold;

	topbar.classList.toggle(topbarClass, isBeyond);
}
