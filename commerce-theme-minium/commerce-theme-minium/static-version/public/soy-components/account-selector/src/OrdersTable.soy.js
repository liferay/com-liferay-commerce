/* jshint ignore:start */
import Component from 'metal-component';
import Soy from 'metal-soy';

var templates;
goog.loadModule(function(exports) {
var soy = goog.require('soy');
var soydata = goog.require('soydata');
// This file was automatically generated from OrdersTable.soy.
// Please don't edit this file by hand.

/**
 * @fileoverview Templates in namespace OrdersTable.
 * @public
 */

goog.module('OrdersTable.incrementaldom');

goog.require('goog.soy.data.SanitizedContent');
var incrementalDom = goog.require('incrementaldom');
goog.require('soy.asserts');
var soyIdom = goog.require('soy.idom');

var $templateAlias1 = Soy.getTemplate('AutocompleteItem.incrementaldom', 'render');


/**
 * @param {{
 *  currentAccountName: (!goog.soy.data.SanitizedContent|string),
 *  filterString: (!goog.soy.data.SanitizedContent|null|string|undefined),
 *  isVisible: (boolean|null|undefined),
 *  createNewOrderLink: (!goog.soy.data.SanitizedContent|null|string|undefined),
 *  viewAllOrdersLink: (!goog.soy.data.SanitizedContent|null|string|undefined),
 *  orders: (!Array<{addOrderLink: (!goog.soy.data.SanitizedContent|string), id: (!goog.soy.data.SanitizedContent|number|string), lastEdit: (!goog.soy.data.SanitizedContent|string), status: (!goog.soy.data.SanitizedContent|string)}>|null|undefined)
 * }} opt_data
 * @param {Object<string, *>=} opt_ijData
 * @param {Object<string, *>=} opt_ijData_deprecated
 * @return {void}
 * @suppress {checkTypes}
 */
function $render(opt_data, opt_ijData, opt_ijData_deprecated) {
  opt_ijData = opt_ijData_deprecated || opt_ijData;
  /** @type {!goog.soy.data.SanitizedContent|string} */
  var currentAccountName = soy.asserts.assertType(goog.isString(opt_data.currentAccountName) || opt_data.currentAccountName instanceof goog.soy.data.SanitizedContent, 'currentAccountName', opt_data.currentAccountName, '!goog.soy.data.SanitizedContent|string');
  /** @type {!goog.soy.data.SanitizedContent|null|string|undefined} */
  var filterString = soy.asserts.assertType(opt_data.filterString == null || (goog.isString(opt_data.filterString) || opt_data.filterString instanceof goog.soy.data.SanitizedContent), 'filterString', opt_data.filterString, '!goog.soy.data.SanitizedContent|null|string|undefined');
  /** @type {boolean|null|undefined} */
  var isVisible = soy.asserts.assertType(opt_data.isVisible == null || (goog.isBoolean(opt_data.isVisible) || opt_data.isVisible === 1 || opt_data.isVisible === 0), 'isVisible', opt_data.isVisible, 'boolean|null|undefined');
  /** @type {!goog.soy.data.SanitizedContent|null|string|undefined} */
  var createNewOrderLink = soy.asserts.assertType(opt_data.createNewOrderLink == null || (goog.isString(opt_data.createNewOrderLink) || opt_data.createNewOrderLink instanceof goog.soy.data.SanitizedContent), 'createNewOrderLink', opt_data.createNewOrderLink, '!goog.soy.data.SanitizedContent|null|string|undefined');
  /** @type {!goog.soy.data.SanitizedContent|null|string|undefined} */
  var viewAllOrdersLink = soy.asserts.assertType(opt_data.viewAllOrdersLink == null || (goog.isString(opt_data.viewAllOrdersLink) || opt_data.viewAllOrdersLink instanceof goog.soy.data.SanitizedContent), 'viewAllOrdersLink', opt_data.viewAllOrdersLink, '!goog.soy.data.SanitizedContent|null|string|undefined');
  /** @type {!Array<{addOrderLink: (!goog.soy.data.SanitizedContent|string), id: (!goog.soy.data.SanitizedContent|number|string), lastEdit: (!goog.soy.data.SanitizedContent|string), status: (!goog.soy.data.SanitizedContent|string)}>|null|undefined} */
  var orders = soy.asserts.assertType(opt_data.orders == null || goog.isArray(opt_data.orders), 'orders', opt_data.orders, '!Array<{addOrderLink: (!goog.soy.data.SanitizedContent|string), id: (!goog.soy.data.SanitizedContent|number|string), lastEdit: (!goog.soy.data.SanitizedContent|string), status: (!goog.soy.data.SanitizedContent|string)}>|null|undefined');
  var orderSwitcherClasses__soy518 = '';
  orderSwitcherClasses__soy518 += 'account-switcher';
  orderSwitcherClasses__soy518 += isVisible ? ' is-visible' : '';
  incrementalDom.elementOpenStart('div');
      incrementalDom.attr('class', orderSwitcherClasses__soy518);
  incrementalDom.elementOpenEnd();
    incrementalDom.elementOpenStart('button');
        incrementalDom.attr('data-onclick', '_handleBackButtonClick');
        incrementalDom.attr('class', 'account-switcher__back');
    incrementalDom.elementOpenEnd();
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
      incrementalDom.text('Back');
    incrementalDom.elementClose('button');
    incrementalDom.elementOpenStart('div');
        incrementalDom.attr('class', 'account-switcher__section');
    incrementalDom.elementOpenEnd();
      incrementalDom.elementOpenStart('div');
          incrementalDom.attr('class', 'account-switcher__title');
      incrementalDom.elementOpenEnd();
        soyIdom.print(currentAccountName);
      incrementalDom.elementClose('div');
    incrementalDom.elementClose('div');
    incrementalDom.elementOpenStart('div');
        incrementalDom.attr('class', 'account-switcher__section');
    incrementalDom.elementOpenEnd();
      incrementalDom.elementOpenStart('form');
          incrementalDom.attr('class', 'commerce-search');
          incrementalDom.attr('data-onsubmit', '_handleSubmitFilter');
      incrementalDom.elementOpenEnd();
        incrementalDom.elementOpenStart('div');
            incrementalDom.attr('class', 'commerce-search__input');
        incrementalDom.elementOpenEnd();
          incrementalDom.elementOpenStart('input');
              incrementalDom.attr('type', 'text');
              incrementalDom.attr('data-onkeyup', '_handleFilterChange');
              incrementalDom.attr('placeholder', 'Search Order\u2026');
          incrementalDom.elementOpenEnd();
          incrementalDom.elementClose('input');
        incrementalDom.elementClose('div');
        incrementalDom.elementOpenStart('button');
            incrementalDom.attr('class', 'commerce-search__button');
            incrementalDom.attr('type', 'submit');
        incrementalDom.elementOpenEnd();
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
      incrementalDom.elementClose('form');
    incrementalDom.elementClose('div');
    incrementalDom.elementOpenStart('div');
        incrementalDom.attr('class', 'account-switcher__section account-switcher__section--fill account-switcher__section--padded');
    incrementalDom.elementOpenEnd();
      incrementalDom.elementOpenStart('div');
          incrementalDom.attr('class', 'd-table commerce-small-table');
      incrementalDom.elementOpenEnd();
        incrementalDom.elementOpenStart('div');
            incrementalDom.attr('class', 'd-table-head-group');
        incrementalDom.elementOpenEnd();
          incrementalDom.elementOpenStart('div');
              incrementalDom.attr('class', 'd-table-row');
          incrementalDom.elementOpenEnd();
            incrementalDom.elementOpenStart('div');
                incrementalDom.attr('class', 'd-table-cell');
            incrementalDom.elementOpenEnd();
              incrementalDom.text('Select Order');
            incrementalDom.elementClose('div');
            incrementalDom.elementOpenStart('div');
                incrementalDom.attr('class', 'd-table-cell u-tac');
            incrementalDom.elementOpenEnd();
              incrementalDom.text('Status');
            incrementalDom.elementClose('div');
            incrementalDom.elementOpenStart('div');
                incrementalDom.attr('class', 'd-table-cell u-tar');
            incrementalDom.elementOpenEnd();
              incrementalDom.text('Last Modified');
            incrementalDom.elementClose('div');
          incrementalDom.elementClose('div');
        incrementalDom.elementClose('div');
        incrementalDom.elementOpenStart('div');
            incrementalDom.attr('class', 'd-table-row-group');
        incrementalDom.elementOpenEnd();
          if ((orders != null)) {
            var order540List = orders;
            var order540ListLen = order540List.length;
            for (var order540Index = 0; order540Index < order540ListLen; order540Index++) {
                var order540Data = order540List[order540Index];
                $order({id: order540Data.id, status: order540Data.status, lastEdit: order540Data.lastEdit, addOrderLink: order540Data.addOrderLink, query: filterString}, null, opt_ijData);
              }
          }
        incrementalDom.elementClose('div');
      incrementalDom.elementClose('div');
    incrementalDom.elementClose('div');
    if ((createNewOrderLink != null)) {
      incrementalDom.elementOpenStart('div');
          incrementalDom.attr('class', 'account-switcher__section');
      incrementalDom.elementOpenEnd();
        incrementalDom.elementOpenStart('a');
            incrementalDom.attr('href', createNewOrderLink);
            incrementalDom.attr('class', 'commerce-button commerce-button--block commerce-button--outline');
        incrementalDom.elementOpenEnd();
          incrementalDom.text('Create new order');
        incrementalDom.elementClose('a');
      incrementalDom.elementClose('div');
    }
    if ((viewAllOrdersLink != null)) {
      incrementalDom.elementOpenStart('div');
          incrementalDom.attr('class', 'account-switcher__section');
      incrementalDom.elementOpenEnd();
        incrementalDom.elementOpenStart('a');
            incrementalDom.attr('href', viewAllOrdersLink);
            incrementalDom.attr('class', 'commerce-button commerce-button--block');
        incrementalDom.elementOpenEnd();
          incrementalDom.text('View all orders');
        incrementalDom.elementClose('a');
      incrementalDom.elementClose('div');
    }
  incrementalDom.elementClose('div');
}
exports.render = $render;
/**
 * @typedef {{
 *  currentAccountName: (!goog.soy.data.SanitizedContent|string),
 *  filterString: (!goog.soy.data.SanitizedContent|null|string|undefined),
 *  isVisible: (boolean|null|undefined),
 *  createNewOrderLink: (!goog.soy.data.SanitizedContent|null|string|undefined),
 *  viewAllOrdersLink: (!goog.soy.data.SanitizedContent|null|string|undefined),
 *  orders: (!Array<{addOrderLink: (!goog.soy.data.SanitizedContent|string), id: (!goog.soy.data.SanitizedContent|number|string), lastEdit: (!goog.soy.data.SanitizedContent|string), status: (!goog.soy.data.SanitizedContent|string)}>|null|undefined)
 * }}
 */
$render.Params;
if (goog.DEBUG) {
  $render.soyTemplateName = 'OrdersTable.render';
}


/**
 * @param {{
 *  id: (!goog.soy.data.SanitizedContent|number|string),
 *  status: (!goog.soy.data.SanitizedContent|string),
 *  lastEdit: (!goog.soy.data.SanitizedContent|string),
 *  addOrderLink: (!goog.soy.data.SanitizedContent|string),
 *  query: (!goog.soy.data.SanitizedContent|null|string|undefined)
 * }} opt_data
 * @param {Object<string, *>=} opt_ijData
 * @param {Object<string, *>=} opt_ijData_deprecated
 * @return {void}
 * @suppress {checkTypes}
 */
function $order(opt_data, opt_ijData, opt_ijData_deprecated) {
  opt_ijData = opt_ijData_deprecated || opt_ijData;
  var $$temp;
  /** @type {!goog.soy.data.SanitizedContent|number|string} */
  var id = soy.asserts.assertType(goog.isNumber(opt_data.id) || (goog.isString(opt_data.id) || opt_data.id instanceof goog.soy.data.SanitizedContent), 'id', opt_data.id, '!goog.soy.data.SanitizedContent|number|string');
  /** @type {!goog.soy.data.SanitizedContent|string} */
  var status = soy.asserts.assertType(goog.isString(opt_data.status) || opt_data.status instanceof goog.soy.data.SanitizedContent, 'status', opt_data.status, '!goog.soy.data.SanitizedContent|string');
  /** @type {!goog.soy.data.SanitizedContent|string} */
  var lastEdit = soy.asserts.assertType(goog.isString(opt_data.lastEdit) || opt_data.lastEdit instanceof goog.soy.data.SanitizedContent, 'lastEdit', opt_data.lastEdit, '!goog.soy.data.SanitizedContent|string');
  /** @type {!goog.soy.data.SanitizedContent|string} */
  var addOrderLink = soy.asserts.assertType(goog.isString(opt_data.addOrderLink) || opt_data.addOrderLink instanceof goog.soy.data.SanitizedContent, 'addOrderLink', opt_data.addOrderLink, '!goog.soy.data.SanitizedContent|string');
  /** @type {!goog.soy.data.SanitizedContent|null|string|undefined} */
  var query = soy.asserts.assertType(opt_data.query == null || (goog.isString(opt_data.query) || opt_data.query instanceof goog.soy.data.SanitizedContent), 'query', opt_data.query, '!goog.soy.data.SanitizedContent|null|string|undefined');
  var statusClasses__soy563 = '';
  statusClasses__soy563 += 'd-inline-block commerce-dot commerce-dot--';
  var $tmp = status;
  switch (goog.isObject($tmp) ? $tmp.toString() : $tmp) {
    case 'approved':
      statusClasses__soy563 += 'good';
      break;
    case 'pending':
      statusClasses__soy563 += 'neutral';
      break;
    default:
      statusClasses__soy563 += 'bad';
  }
  incrementalDom.elementOpenStart('a');
      incrementalDom.attr('class', 'd-table-row');
      incrementalDom.attr('href', addOrderLink);
  incrementalDom.elementOpenEnd();
    incrementalDom.elementOpenStart('div');
        incrementalDom.attr('class', 'd-table-cell');
    incrementalDom.elementOpenEnd();
      $templateAlias1({text: id, query: query}, null, opt_ijData);
    incrementalDom.elementClose('div');
    incrementalDom.elementOpenStart('div');
        incrementalDom.attr('class', 'd-table-cell u-tac');
    incrementalDom.elementOpenEnd();
      incrementalDom.elementOpenStart('div');
          incrementalDom.attr('class', statusClasses__soy563);
      incrementalDom.elementOpenEnd();
      incrementalDom.elementClose('div');
    incrementalDom.elementClose('div');
    incrementalDom.elementOpenStart('div');
        incrementalDom.attr('class', 'd-table-cell u-tar');
    incrementalDom.elementOpenEnd();
      soyIdom.print(lastEdit);
    incrementalDom.elementClose('div');
  incrementalDom.elementClose('a');
}
exports.order = $order;
/**
 * @typedef {{
 *  id: (!goog.soy.data.SanitizedContent|number|string),
 *  status: (!goog.soy.data.SanitizedContent|string),
 *  lastEdit: (!goog.soy.data.SanitizedContent|string),
 *  addOrderLink: (!goog.soy.data.SanitizedContent|string),
 *  query: (!goog.soy.data.SanitizedContent|null|string|undefined)
 * }}
 */
$order.Params;
if (goog.DEBUG) {
  $order.soyTemplateName = 'OrdersTable.order';
}

exports.render.params = ["currentAccountName","filterString","isVisible","createNewOrderLink","viewAllOrdersLink","orders"];
exports.render.types = {"currentAccountName":"string","filterString":"string","isVisible":"bool","createNewOrderLink":"string","viewAllOrdersLink":"string","orders":"list<[\n        id: string|int,\n        status: string,\n        lastEdit: string,\n        addOrderLink: string\n    ]>"};
exports.order.params = ["id","status","lastEdit","addOrderLink","query"];
exports.order.types = {"id":"int|string","status":"string","lastEdit":"string","addOrderLink":"string","query":"string"};
templates = exports;
return exports;

});

class OrdersTable extends Component {}
Soy.register(OrdersTable, templates);
export { OrdersTable, templates };
export default templates;
/* jshint ignore:end */
