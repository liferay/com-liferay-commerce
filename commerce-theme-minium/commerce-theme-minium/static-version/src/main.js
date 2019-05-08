import Vue from "vue";
import App from "./App.vue";
import { EventEmitter } from "metal-events";

window.Liferay = new EventEmitter();
window.Liferay.fire = window.Liferay.emit;

// import Cart from "../public/soy-components/Cart";
// import AccountSelector from "../public/soy-components/account-selector/src/AccountSelector";
// import UserInvitation from "../public/soy-components/user-invitation/src/UserInvitation";
import AddToCartButton from "../public/soy-components/add-to-cart/src/AddToCartButton";
import MiniumSearchBar from "../public/soy-components/commerce-search-bar/src/MiniumSearchBar";
import MiniumSearchResults from "../public/soy-components/commerce-search-results/src/MiniumSearchResults";
import MiniumTable from "../public/soy-components/minium-table/src/MiniumTable";

import "intersection-observer";

import "./styles/minium.scss";
import "./styles/test.scss";

Vue.config.productionTip = false;

// if (typeof Object.assign != 'function') {
//	 // Must be writable: true, enumerable: false, configurable: true
//	 Object.defineProperty(Object, "assign", {
//		 value: function assign(target, varArgs) { // .length of function is 2
//			 'use strict';
//			 if (target == null) { // TypeError if undefined or null
//				 throw new TypeError('Cannot convert undefined or null to object');
//			 }

//			 var to = Object(target);

//			 for (var index = 1; index < arguments.length; index++) {
//				 var nextSource = arguments[index];

//				 if (nextSource != null) { // Skip over if undefined or null
//					 for (var nextKey in nextSource) {
//						 // Avoid bugs when hasOwnProperty is shadowed
//						 if (Object.prototype.hasOwnProperty.call(nextSource, nextKey)) {
//							 to[nextKey] = nextSource[nextKey];
//						 }
//					 }
//				 }
//			 }
//			 return to;
//		 },
//		 writable: true,
//		 configurable: true
//	 });
// }

new Vue({
	render: h => h(App)
}).$mount("#app");

new IntersectionObserver(
	entries => {
		document
			.getElementById("minium")
			.classList.toggle("is-scrolled", !entries[0].isIntersecting);
	},
	{
		root: document.querySelector(".js-scroll-area"),
		rootMargin: "10px",
		threshold: 1.0
	}
).observe(document.querySelector("[name=minium-top]"));

// eslint-disable-next-line
// const badge = new clay.ClayBadge(
//	 {
//		 label: "10",
//		 style: "primary"
//	 },
//	 "#badge"
// );

// let actionItemsWithQuickItems = [
//	 {
//		 href: "#1",
//		 icon: "trash",
//		 label: "Remove",
//		 quickAction: true
//	 },
//	 {
//		 href: "#2",
//		 icon: "download",
//		 label: "Download",
//		 quickAction: true,
//		 separator: true
//	 }
// ];
let item = {
	date: "07.04.2018",
	time: "11:43 AM",
	image: "http://placehold.it/100",
	imageAlt: "Placeholder image",
	productName: "White Label Matte Orange",
	productType: "Engine",
	labels: ["New"],
	order: "2018213431",
	account: "Bob’s Sporting Mx",
	accountCode: "007274",
	created: "John Doe",
	createdLink: "#john-doe",
	referent: "Test",
	status: {
		label: "Approved",
		badge: "good"
	},
	amount: "$ 59,301.00",
	actionsLink: "#",
	actionsLinkLabel: "Work on this order",
	actions: [
		{
			label: "Good",
			href: "#",
			type: "good"
		},
		{
			label: "Bad",
			href: "#",
			type: "bad"
		},
		{
			label: "Neutral",
			href: "#",
			type: "neutral"
		},
		{
			label: "Default",
			href: "#"
		},
		{
			label: "Primary",
			href: "#",
			type: "primary"
		}
	]
};

let items = new Array(4).fill(item);

// items.forEach(item => {
//	 item.actionItems = actionItemsWithQuickItems;
// });

// eslint-disable-next-line
new MiniumTable(
	{
		items: items,
		tableClasses: "minium-table",
		schema: {
			fields: [
				{
					contentRenderer: "miniumCell",
					fieldName: "productName",
					subTitleField: "productType",
					label: "Simple"
				},
				{
					contentRenderer: "miniumImage",
					fieldName: "image",
					altField: "imageAlt",
					label: "Image"
				},
				{
					contentRenderer: "miniumCell",
					fieldName: "date",
					subTitleField: "time",
					label: "Datetime"
				},
				{
					contentRenderer: "miniumCell",
					fieldName: "account",
					label: "No subtitle"
				},
				{
					contentRenderer: "label",
					fieldName: "labels",
					label: "Label",
					labelStylesMap: {
						Spicy: "warning",
						"Very Spicy": "danger",
						"*": "success"
					}
				},
				{
					contentRenderer: "miniumLink",
					fieldName: "created",
					fieldLink: "createdLink",
					label: "Link"
				},
				{
					contentRenderer: "miniumStatus",
					fieldName: "status",
					label: "Status"
				},
				{
					contentRenderer: "miniumActions",
					fieldName: "actions",
					linkField: "actionsLink",
					linkLabelField: "actionsLinkLabel",
					label: ""
				}
			],
			inputNameField: "type",
			inputNamesMap: {
				folder: "folder",
				chef: "chef",
				"*": "recipe"
			},
			inputValueField: "id"
		},
		selectable: true,
		showActionsMenu: false
	},
	"#test-table"
);

new MiniumTable(
	{
		items: items,
		tableClasses: "minium-table minium-table--mini",
		schema: {
			fields: [
				{
					contentRenderer: "miniumCell",
					fieldName: "account",
					label: "No subtitle"
				},
				{
					contentRenderer: "label",
					fieldName: "labels",
					label: "Label",
					labelStylesMap: {
						Spicy: "warning",
						"Very Spicy": "danger",
						"*": "success"
					}
				},
				{
					contentRenderer: "miniumLink",
					fieldName: "created",
					fieldLink: "createdLink",
					label: "Link"
				},
				{
					contentRenderer: "miniumStatus",
					fieldName: "status",
					label: "Status"
				}
			],
			inputNameField: "type",
			inputNamesMap: {
				folder: "folder",
				chef: "chef",
				"*": "recipe"
			},
			inputValueField: "id"
		},
		selectable: false,
		showActionsMenu: false
	},
	"#test-table-mini"
);

// fetch( 'api/accounts/current', {
// 	method: 'GET'
// })
// 	.then((response) => response.json())
// 	.then((accountsState) => {
// 		new AccountSelector(
// 			{
// 				accountsAPI: 'api/accounts',
// 				currentAccount: accountsState.currentAccount,
// 				currentOrder: accountsState.currentOrder,
// 				viewAllAccountsLink: '/view-all-accounts',
// 				createNewAccountLink: '/create-new-account',
// 				viewAllOrdersLink: '/view-all-orders',
// 				createNewOrderLink: '/create-new-order'
// 			},
// 			'.minium-topbar__account-selector-wrapper'
// 		);
// 	})

// new AddToCartButton(
// 	{
// 		settings: {
// 			minQuantity: 1,
// 			maxQuantity: 100
// 		}
// 	},
// 	"#qsbtn1"
// );
// new AddToCartButton(
// 	{
// 		quantity: 2345,
// 		settings: {
// 			minQuantity: 1,
// 			maxQuantity: 100
// 		}
// 	},
// 	"#qsbtn2"
// );
// new AddToCartButton(
// 	{
// 		editMode: true,
// 		quantity: 2345,
// 		settings: {
// 			minQuantity: 1,
// 			maxQuantity: 10000,
// 			multipleQuantities: 5
// 		}
// 	},
// 	"#qsbtn3"
// );
// new AddToCartButton(
// 	{
// 		editMode: true,
// 		settings: {
// 			allowedOptions: [5, 10, 100, 1000, 2500]
// 		}
// 	},
// 	"#qsbtn4"
// );

// new MiniumSearchBar(
// 	{
// 		placeholder: "Search Product Name, SKU, Client…",
// 		id: "commerce-search-input"
// 	},
// 	"#commerce-search"
// );

function getItems(category) {
	return [
		{
			type: "label",
			value: category
		},
		...new Array(3).fill({
			type: "item",
			image: "http://placehold.it/100",
			title: "Vestibulum Marmitta commodo urna",
			subtitle: "AR385672"
		}),
		{
			type: "category",
			value: category
		}
	];
}

new MiniumSearchResults(
	{
		results: [
			{
				type: "category",
				value: "Pending Orders"
			},
			{
				type: "category",
				value: "All Contents"
			},
			...getItems("Pending Orders"),
			...getItems("Catalog")
		]
	},
	"#commerce-search-results"
);

function handleKeyDownForSearch(e) {
	if (e.key === "Escape") {
		toggleMiniumSearch();
	}
}

// const userInvitation = new UserInvitation({
// 	usersAPI: 'api/users',
// 	invitationAPI: 'api/invitation'
// }, '.minium-content')

function toggleMiniumSearch() {
	const status = document
		.getElementById("minium")
		.classList.toggle("has-search");
	document.querySelectorAll(".js-toggle-search").forEach(el => {
		el.classList.toggle("is-active", status);
	});

	if (status) {
		document.addEventListener("keydown", handleKeyDownForSearch);
	} else {
		document.removeEventListener("keydown", handleKeyDownForSearch);
	}
}

document.querySelectorAll(".js-toggle-search").forEach(el => {
	el.addEventListener("click", toggleMiniumSearch);
});

const images = [
	{
		color: "#1abc9c",
		width: 1600,
		height: 1200
	},
	{
		color: "#9b59b6",
		width: 1200,
		height: 1600
	},
	{
		color: "#e67e22",
		width: 1200,
		height: 1200
	},
	{
		color: "#95a5a6",
		width: 1600,
		height: 1600
	},
	{
		color: "#3498db",
		width: 1600,
		height: 800
	},
	{
		color: "#e74c3c",
		width: 800,
		height: 1600
	},
	{
		color: "#2ecc71",
		width: 800,
		height: 600
	},
	{
		color: "#f1c40f",
		width: 480,
		height: 320
	},
	{
		color: "#34495e",
		width: 1600,
		height: 1200
	}
]

new MiniumProductGallery(
	{
		images: images.map(img => ({
			thumb: `//placehold.it/${img.width/20}x${img.height/20}/${img.color.substr(1)}/fff`,
			preview: `//placehold.it/${img.width/2}x${img.height/2}/${img.color.substr(1)}/fff`,
			full: `//placehold.it/${img.width}x${img.height}/${img.color.substr(1)}/fff`,
			description: 'Alt text here'
		}))
	},
	"#minium-product-gallery"
);