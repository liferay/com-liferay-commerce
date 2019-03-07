/* jshint ignore:start */
import Component from 'metal-component';
import Soy from 'metal-soy';

var templates;
goog.loadModule(function(exports) {
var soy = goog.require('soy');
var soydata = goog.require('soydata');
// This file was automatically generated from AccountSelector.soy.
// Please don't edit this file by hand.

/**
 * @fileoverview Templates in namespace AccountSelector.
 * @public
 */

goog.module('AccountSelector.incrementaldom');

goog.require('goog.soy.data.SanitizedContent');
var incrementalDom = goog.require('incrementaldom');
goog.require('soy.asserts');
var soyIdom = goog.require('soy.idom');

var $templateAlias2 = Soy.getTemplate('AccountsTable.incrementaldom', 'render');

var $templateAlias1 = Soy.getTemplate('OrdersTable.incrementaldom', 'render');


/**
 * @param {{
 *  openingState: (!goog.soy.data.SanitizedContent|string),
 *  currentView: (!goog.soy.data.SanitizedContent|string),
 *  accounts: (!Array<{id: (!goog.soy.data.SanitizedContent|number|string), name: (!goog.soy.data.SanitizedContent|string), thumbnail: (!goog.soy.data.SanitizedContent|string)}>|null|undefined),
 *  orders: (!Array<{addOrderLink: (!goog.soy.data.SanitizedContent|string), id: (!goog.soy.data.SanitizedContent|number|string), lastEdit: (!goog.soy.data.SanitizedContent|string), status: (!goog.soy.data.SanitizedContent|string)}>|null|undefined),
 *  viewAllAccountsLink: (!goog.soy.data.SanitizedContent|string),
 *  createNewAccountLink: (!goog.soy.data.SanitizedContent|string),
 *  viewAllOrdersLink: (!goog.soy.data.SanitizedContent|string),
 *  createNewOrderLink: (!goog.soy.data.SanitizedContent|string),
 *  currentAccount: (?),
 *  currentOrder: (?),
 *  handleChangeSelectedView: (?),
 *  handleAccountSelected: (?),
 *  handleOrderSelected: (?),
 *  handleGetOrders: (?),
 *  handleGetAccounts: (?)
 * }} opt_data
 * @param {Object<string, *>=} opt_ijData
 * @param {Object<string, *>=} opt_ijData_deprecated
 * @return {void}
 * @suppress {checkTypes}
 */
function $render(opt_data, opt_ijData, opt_ijData_deprecated) {
  opt_ijData = opt_ijData_deprecated || opt_ijData;
  /** @type {!goog.soy.data.SanitizedContent|string} */
  var openingState = soy.asserts.assertType(goog.isString(opt_data.openingState) || opt_data.openingState instanceof goog.soy.data.SanitizedContent, 'openingState', opt_data.openingState, '!goog.soy.data.SanitizedContent|string');
  /** @type {!goog.soy.data.SanitizedContent|string} */
  var currentView = soy.asserts.assertType(goog.isString(opt_data.currentView) || opt_data.currentView instanceof goog.soy.data.SanitizedContent, 'currentView', opt_data.currentView, '!goog.soy.data.SanitizedContent|string');
  /** @type {!Array<{id: (!goog.soy.data.SanitizedContent|number|string), name: (!goog.soy.data.SanitizedContent|string), thumbnail: (!goog.soy.data.SanitizedContent|string)}>|null|undefined} */
  var accounts = soy.asserts.assertType(opt_data.accounts == null || goog.isArray(opt_data.accounts), 'accounts', opt_data.accounts, '!Array<{id: (!goog.soy.data.SanitizedContent|number|string), name: (!goog.soy.data.SanitizedContent|string), thumbnail: (!goog.soy.data.SanitizedContent|string)}>|null|undefined');
  /** @type {!Array<{addOrderLink: (!goog.soy.data.SanitizedContent|string), id: (!goog.soy.data.SanitizedContent|number|string), lastEdit: (!goog.soy.data.SanitizedContent|string), status: (!goog.soy.data.SanitizedContent|string)}>|null|undefined} */
  var orders = soy.asserts.assertType(opt_data.orders == null || goog.isArray(opt_data.orders), 'orders', opt_data.orders, '!Array<{addOrderLink: (!goog.soy.data.SanitizedContent|string), id: (!goog.soy.data.SanitizedContent|number|string), lastEdit: (!goog.soy.data.SanitizedContent|string), status: (!goog.soy.data.SanitizedContent|string)}>|null|undefined');
  /** @type {!goog.soy.data.SanitizedContent|string} */
  var viewAllAccountsLink = soy.asserts.assertType(goog.isString(opt_data.viewAllAccountsLink) || opt_data.viewAllAccountsLink instanceof goog.soy.data.SanitizedContent, 'viewAllAccountsLink', opt_data.viewAllAccountsLink, '!goog.soy.data.SanitizedContent|string');
  /** @type {!goog.soy.data.SanitizedContent|string} */
  var createNewAccountLink = soy.asserts.assertType(goog.isString(opt_data.createNewAccountLink) || opt_data.createNewAccountLink instanceof goog.soy.data.SanitizedContent, 'createNewAccountLink', opt_data.createNewAccountLink, '!goog.soy.data.SanitizedContent|string');
  /** @type {!goog.soy.data.SanitizedContent|string} */
  var viewAllOrdersLink = soy.asserts.assertType(goog.isString(opt_data.viewAllOrdersLink) || opt_data.viewAllOrdersLink instanceof goog.soy.data.SanitizedContent, 'viewAllOrdersLink', opt_data.viewAllOrdersLink, '!goog.soy.data.SanitizedContent|string');
  /** @type {!goog.soy.data.SanitizedContent|string} */
  var createNewOrderLink = soy.asserts.assertType(goog.isString(opt_data.createNewOrderLink) || opt_data.createNewOrderLink instanceof goog.soy.data.SanitizedContent, 'createNewOrderLink', opt_data.createNewOrderLink, '!goog.soy.data.SanitizedContent|string');
  /** @type {?} */
  var currentAccount = opt_data.currentAccount;
  /** @type {?} */
  var currentOrder = opt_data.currentOrder;
  /** @type {?} */
  var handleChangeSelectedView = opt_data.handleChangeSelectedView;
  /** @type {?} */
  var handleAccountSelected = opt_data.handleAccountSelected;
  /** @type {?} */
  var handleOrderSelected = opt_data.handleOrderSelected;
  /** @type {?} */
  var handleGetOrders = opt_data.handleGetOrders;
  /** @type {?} */
  var handleGetAccounts = opt_data.handleGetAccounts;
  var curtainClasses__soy19 = '';
  curtainClasses__soy19 += openingState == 'open' ? ' is-open' : '';
  curtainClasses__soy19 += openingState == 'closing' ? ' is-closing' : '';
  incrementalDom.elementOpenStart('div');
      incrementalDom.attr('class', 'commerce-dropdown');
  incrementalDom.elementOpenEnd();
    incrementalDom.elementOpenStart('button');
        incrementalDom.attr('class', 'commerce-topbar-button');
        incrementalDom.attr('data-onclick', 'toggleAccountSelector');
    incrementalDom.elementOpenEnd();
      $currentState(opt_data, null, opt_ijData);
      incrementalDom.elementOpenStart('svg');
          incrementalDom.attr('xmlns', 'http://www.w3.org/2000/svg');
          incrementalDom.attr('viewBox', '0 0 100 100');
          incrementalDom.attr('class', 'commerce-icon');
      incrementalDom.elementOpenEnd();
        incrementalDom.elementOpenStart('rect');
            incrementalDom.attr('fill', 'currentColor');
            incrementalDom.attr('x', '5');
            incrementalDom.attr('y', '5');
            incrementalDom.attr('width', '90');
            incrementalDom.attr('height', '90');
            incrementalDom.attr('rx', '10');
            incrementalDom.attr('ry', '10');
        incrementalDom.elementOpenEnd();
        incrementalDom.elementClose('rect');
      incrementalDom.elementClose('svg');
    incrementalDom.elementClose('button');
    incrementalDom.elementOpenStart('div');
        incrementalDom.attr('class', 'commerce-dropdown__curtain' + curtainClasses__soy19);
    incrementalDom.elementOpenEnd();
      if ((currentAccount != null)) {
        var isOrdersTableVisible__soy39 = currentView == 'orders';
        $templateAlias1({events: {changeSelectedView: handleChangeSelectedView, orderSelected: handleOrderSelected, getOrders: handleGetOrders}, currentAccountName: currentAccount.name, orders: orders, currentOrder: currentOrder, isVisible: isOrdersTableVisible__soy39, createNewOrderLink: createNewOrderLink, viewAllOrdersLink: viewAllOrdersLink}, null, opt_ijData);
      }
      var isAccountsTableVisible__soy51 = currentView == 'accounts';
      $templateAlias2({events: {accountSelected: handleAccountSelected, getAccounts: handleGetAccounts}, accounts: accounts, currentAccount: currentAccount, isVisible: isAccountsTableVisible__soy51, createNewAccountLink: createNewAccountLink, viewAllAccountsLink: viewAllAccountsLink}, null, opt_ijData);
    incrementalDom.elementClose('div');
  incrementalDom.elementClose('div');
}
exports.render = $render;
/**
 * @typedef {{
 *  openingState: (!goog.soy.data.SanitizedContent|string),
 *  currentView: (!goog.soy.data.SanitizedContent|string),
 *  accounts: (!Array<{id: (!goog.soy.data.SanitizedContent|number|string), name: (!goog.soy.data.SanitizedContent|string), thumbnail: (!goog.soy.data.SanitizedContent|string)}>|null|undefined),
 *  orders: (!Array<{addOrderLink: (!goog.soy.data.SanitizedContent|string), id: (!goog.soy.data.SanitizedContent|number|string), lastEdit: (!goog.soy.data.SanitizedContent|string), status: (!goog.soy.data.SanitizedContent|string)}>|null|undefined),
 *  viewAllAccountsLink: (!goog.soy.data.SanitizedContent|string),
 *  createNewAccountLink: (!goog.soy.data.SanitizedContent|string),
 *  viewAllOrdersLink: (!goog.soy.data.SanitizedContent|string),
 *  createNewOrderLink: (!goog.soy.data.SanitizedContent|string),
 *  currentAccount: (?),
 *  currentOrder: (?),
 *  handleChangeSelectedView: (?),
 *  handleAccountSelected: (?),
 *  handleOrderSelected: (?),
 *  handleGetOrders: (?),
 *  handleGetAccounts: (?)
 * }}
 */
$render.Params;
if (goog.DEBUG) {
  $render.soyTemplateName = 'AccountSelector.render';
}


/**
 * @param {{
 *  currentAccount: (?),
 *  currentOrder: (?)
 * }} opt_data
 * @param {Object<string, *>=} opt_ijData
 * @param {Object<string, *>=} opt_ijData_deprecated
 * @return {void}
 * @suppress {checkTypes}
 */
function $currentState(opt_data, opt_ijData, opt_ijData_deprecated) {
  opt_ijData = opt_ijData_deprecated || opt_ijData;
  /** @type {?} */
  var currentAccount = opt_data.currentAccount;
  /** @type {?} */
  var currentOrder = opt_data.currentOrder;
  if ((currentAccount != null)) {
    incrementalDom.elementOpenStart('div');
        incrementalDom.attr('class', 'commerce-account-selector');
    incrementalDom.elementOpenEnd();
      incrementalDom.elementOpenStart('div');
          incrementalDom.attr('class', 'commerce-account-selector__image');
      incrementalDom.elementOpenEnd();
        incrementalDom.elementOpenStart('img');
            incrementalDom.attr('src', currentAccount.thumbnail);
            incrementalDom.attr('alt', currentAccount.name);
        incrementalDom.elementOpenEnd();
        incrementalDom.elementClose('img');
      incrementalDom.elementClose('div');
      incrementalDom.elementOpenStart('div');
          incrementalDom.attr('class', 'commerce-account-selector__title');
      incrementalDom.elementOpenEnd();
        soyIdom.print(currentAccount.name);
      incrementalDom.elementClose('div');
      incrementalDom.elementOpenStart('div');
          incrementalDom.attr('class', 'commerce-account-selector__info');
      incrementalDom.elementOpenEnd();
        if ((currentOrder != null)) {
          soyIdom.print(currentOrder.id);
          incrementalDom.text(' | ');
          soyIdom.print(currentOrder.status);
        } else {
          incrementalDom.text('No order selected');
        }
      incrementalDom.elementClose('div');
    incrementalDom.elementClose('div');
  } else {
    incrementalDom.elementOpenStart('div');
        incrementalDom.attr('class', 'commerce-account-selector');
    incrementalDom.elementOpenEnd();
      incrementalDom.elementOpenStart('div');
          incrementalDom.attr('class', 'commerce-account-selector__title');
      incrementalDom.elementOpenEnd();
        incrementalDom.text('Select Account & Order');
      incrementalDom.elementClose('div');
    incrementalDom.elementClose('div');
  }
}
exports.currentState = $currentState;
/**
 * @typedef {{
 *  currentAccount: (?),
 *  currentOrder: (?)
 * }}
 */
$currentState.Params;
if (goog.DEBUG) {
  $currentState.soyTemplateName = 'AccountSelector.currentState';
}

exports.render.params = ["openingState","currentView","accounts","orders","viewAllAccountsLink","createNewAccountLink","viewAllOrdersLink","createNewOrderLink","currentAccount","currentOrder","handleChangeSelectedView","handleAccountSelected","handleOrderSelected","handleGetOrders","handleGetAccounts"];
exports.render.types = {"openingState":"string","currentView":"string","accounts":"list<[\n        name: string,\n        id: string|int,\n        thumbnail: string\n    ]>","orders":"list<[\n        id: string|int,\n        status: string,\n        lastEdit: string,\n        addOrderLink: string\n    ]>","viewAllAccountsLink":"string","createNewAccountLink":"string","viewAllOrdersLink":"string","createNewOrderLink":"string","currentAccount":"?","currentOrder":"?","handleChangeSelectedView":"?","handleAccountSelected":"?","handleOrderSelected":"?","handleGetOrders":"?","handleGetAccounts":"?"};
exports.currentState.params = ["currentAccount","currentOrder"];
exports.currentState.types = {"currentAccount":"? ","currentOrder":"? "};
templates = exports;
return exports;

});

class AccountSelector extends Component {}
Soy.register(AccountSelector, templates);
export { AccountSelector, templates };
export default templates;
/* jshint ignore:end */
