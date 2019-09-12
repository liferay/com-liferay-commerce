var Speedwell = Speedwell || { features: {} };

if (window.NodeList && !NodeList.prototype.forEach) {
	NodeList.prototype.forEach = Array.prototype.forEach;
}


AUI().ready(

	/*
	This function gets loaded when all the HTML, not including the portlets, is
	loaded.
	*/

	function() {
		console.log('%c[AUI ready]', 'background-color: #000; color: #00FFFF');

		const searchBar = Liferay.component('search-bar');
		const windowSearchBar = window.Liferay.component('search-bar');

		console.log('search bar is same as window search bar: ', searchBar === windowSearchBar);

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

		setTimeout(function() {
			Object.keys(Speedwell.features).forEach(function (feature) {
				Speedwell.features[feature].initialize();
			});
		}, 350);
	}
);

Liferay.Portlet.ready(

	/*
	This function gets loaded after each and every portlet on the page.

	portletId: the current portlet's id
	node: the Alloy Node object of the current portlet
	*/

	function(portletId, node) {
		// console.log('%c[Portlet: ' + portletId + ' ready]', 'background-color: #000; color: #00FF00');
	}
);

Liferay.on(
	'allPortletsReady',

	/*
		This function gets loaded when everything, including the portlets, is on
		the page.
	*/

	function() {
		console.log('%c[All portlets ready]', 'background-color: #000; color: #FFFF00');

		// const isMobile = document.documentElement.classList.contains('mobile');
		/*
		function sign(x) {
			return ((x > 0) - (x < 0)) || + x
		}

		let scrollThreshold = 100;
		let lastKnownScrollPosition = 0;
		let lastKnownScrollOffset = 0;
		let ticking = false;
		let myMap = new Map();
		myMap.set(-1, 'up');
		myMap.set(1, 'down');
		*/

		/*
		const speedwellWrapper = document.getElementById("speedwell");
		const speedwellTranslucentTopbar = speedwellWrapper &&
			speedwellWrapper.querySelector('.speedwell-topbar--translucent');

		const addToCardButtonInline =
			document.querySelector('.add-to-cart-button--inline .commerce-button');

		const openFilters = document.querySelector('.commerce-catalog .mobile-filters-button');
		 */

		/* ['main-menu', 'search', 'account'].forEach(function(el) {
			document.querySelectorAll('.js-toggle-' + el).forEach(function(element) {
				console.log(element);
				element.addEventListener('click', function() {
					element.focus();

					const actualElement = document.querySelector('.speedwell-' + el);
					const isOpen = actualElement.classList.contains('is-open');

					actualElement.classList.toggle('is-open', !isOpen);

					isMobile && el !== 'account' && !!openFilters &&
						openFilters.classList.toggle('is-behind', !isOpen);

					document.querySelector('.speedwell-category-nav').classList.remove('is-open');
				})
			});
		});
		 */


		// TODO RESTORE
		/*
		const carPartsMenuButton = document.querySelectorAll('.speedwell-main-menu__links .main-link')[1];

		!!carPartsMenuButton && carPartsMenuButton.addEventListener('mouseover', function() {
				let element = document.querySelector('.speedwell-category-nav');

				element.focus();
				element.classList.add('is-open')
		});
		 */

		/* window.addEventListener("scroll", function() {
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
					fixAddToCartButtonInline(addToCardButtonInline);
					ticking = false;
				});

				ticking = true;
			}
		}, false);
		 */

		/*
		function doOpenFilters(e) {
			const isOpenClass = 'is-open';
			const filtersAreClosed = !openFilters.classList.contains('is-open');
			const filtersCloseButton = document
				.querySelector('.commerce-catalog .mobile-filters-header .close-button');

			openFilters.classList.toggle(isOpenClass, filtersAreClosed);

			if (filtersAreClosed) {
				!!filtersCloseButton && filtersCloseButton.addEventListener('click', doOpenFilters);
			} else {
				!!filtersCloseButton && filtersCloseButton.removeEventListener('click', doOpenFilters);
			}
		}

		function fixAddToCartButtonInline(e) {
			if (addToCardButtonInline && !addToCardButtonInline.classList.contains('is-fixed')
				&& addToCardButtonInline.getBoundingClientRect().top <= 0) {

				addToCardButtonInline.classList.add('is-fixed');
				window.removeEventListener('scroll', fixAddToCartButtonInline);
				window.addEventListener('scroll', unfixAddToCartButtonInline);
			}
		}

		function unfixAddToCartButtonInline(element) {
			if (addToCardButtonInline.classList.contains('is-fixed') &&
				addToCardButtonInline.getBoundingClientRect().top > 0) {

				addToCardButtonInline.classList.remove('is-fixed');
				window.removeEventListener('scroll', unfixAddToCartButtonInline);
				window.addEventListener('scroll', fixAddToCartButtonInline);
			}
		}

		if (isMobile) {
			const filtersTitle = document.querySelector('.mobile-filters-header .title');

			!!filtersTitle && (filtersTitle.innerText = Liferay.Language.get('filters'));
			!!openFilters && openFilters.addEventListener('click', doOpenFilters);
		}

		!!addToCardButtonInline &&
			window.addEventListener('scroll', fixAddToCartButtonInline);
		*/
	}
);

function handleTranslucentTopbar(topbar, threshold) {
	if (!topbar) return;

	const topbarClass = 'speedwell-topbar--translucent';
	const isBeyond = window.scrollY <= threshold;

	topbar.classList.toggle(topbarClass, isBeyond);
}
