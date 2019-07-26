if (window.NodeList && !NodeList.prototype.forEach) {
	NodeList.prototype.forEach = Array.prototype.forEach;
}

AUI().ready(

	/*
	This function gets loaded when all the HTML, not including the portlets, is
	loaded.
	*/

	function() {
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
				})
			});
		});

		let scrollThreshold = 100;
		let lastKnownScrollPosition = 0;
		let lastKnownScrollOffset = 0;
		let ticking = false;
		var myMap = new Map();
		myMap.set(-1, 'up');
		myMap.set(1, 'down');

		const speedwellWrapper = document.getElementById("speedwell");
		const speedwellTranslucentTopbar = speedwellWrapper.querySelector('.speedwell-topbar--translucent');

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
						speedwellWrapper.classList.add("is-scrolling-" + myMap.get(sign(lastKnownScrollOffset)));
						speedwellWrapper.classList.remove("is-scrolling-" + myMap.get(-1 * sign(lastKnownScrollOffset)));
					}

					speedwellWrapper.classList.toggle("is-scrolled", window.scrollY > scrollThreshold);
					handleTranslucentTopbar(speedwellTranslucentTopbar, scrollThreshold);
					ticking = false;
				});
				ticking = true;
			}
		}, false);
	}
);

function handleTranslucentTopbar(topbar, threshold) {
	if (!topbar) return;

	const topbarClass = 'speedwell-topbar--translucent';
	const isBeyond = window.scrollY <= threshold;

	topbar.classList.toggle(topbarClass, isBeyond);
}
