/* jshint ignore:start */
import Component from 'metal-component';
import Soy from 'metal-soy';

var templates;
goog.loadModule(function(exports) {
var soy = goog.require('soy');
var soydata = goog.require('soydata');
// This file was automatically generated from AccountsTable.soy.
// Please don't edit this file by hand.

/**
 * @fileoverview Templates in namespace AccountsTable.
 * @public
 */

goog.module('AccountsTable.incrementaldom');

goog.require('goog.soy.data.SanitizedContent');
var incrementalDom = goog.require('incrementaldom');
goog.require('soy.asserts');
var soyIdom = goog.require('soy.idom');

var $templateAlias1 = Soy.getTemplate('AccountsTableItem.incrementaldom', 'render');


/**
 * @param {{
 *  accounts: (!Array<{id: (!goog.soy.data.SanitizedContent|number|string), name: (!goog.soy.data.SanitizedContent|string), thumbnail: (!goog.soy.data.SanitizedContent|string)}>|null|undefined),
 *  filterString: (!goog.soy.data.SanitizedContent|null|string|undefined),
 *  isVisible: (boolean|null|undefined),
 *  viewAllAccountsLink: (!goog.soy.data.SanitizedContent|null|string|undefined),
 *  createNewAccountLink: (!goog.soy.data.SanitizedContent|null|string|undefined),
 *  handleSelectAccount: (?)
 * }} opt_data
 * @param {Object<string, *>=} opt_ijData
 * @param {Object<string, *>=} opt_ijData_deprecated
 * @return {void}
 * @suppress {checkTypes}
 */
function $render(opt_data, opt_ijData, opt_ijData_deprecated) {
  opt_ijData = opt_ijData_deprecated || opt_ijData;
  opt_data = opt_data || {};
  /** @type {!Array<{id: (!goog.soy.data.SanitizedContent|number|string), name: (!goog.soy.data.SanitizedContent|string), thumbnail: (!goog.soy.data.SanitizedContent|string)}>|null|undefined} */
  var accounts = soy.asserts.assertType(opt_data.accounts == null || goog.isArray(opt_data.accounts), 'accounts', opt_data.accounts, '!Array<{id: (!goog.soy.data.SanitizedContent|number|string), name: (!goog.soy.data.SanitizedContent|string), thumbnail: (!goog.soy.data.SanitizedContent|string)}>|null|undefined');
  /** @type {!goog.soy.data.SanitizedContent|null|string|undefined} */
  var filterString = soy.asserts.assertType(opt_data.filterString == null || (goog.isString(opt_data.filterString) || opt_data.filterString instanceof goog.soy.data.SanitizedContent), 'filterString', opt_data.filterString, '!goog.soy.data.SanitizedContent|null|string|undefined');
  /** @type {boolean|null|undefined} */
  var isVisible = soy.asserts.assertType(opt_data.isVisible == null || (goog.isBoolean(opt_data.isVisible) || opt_data.isVisible === 1 || opt_data.isVisible === 0), 'isVisible', opt_data.isVisible, 'boolean|null|undefined');
  /** @type {!goog.soy.data.SanitizedContent|null|string|undefined} */
  var viewAllAccountsLink = soy.asserts.assertType(opt_data.viewAllAccountsLink == null || (goog.isString(opt_data.viewAllAccountsLink) || opt_data.viewAllAccountsLink instanceof goog.soy.data.SanitizedContent), 'viewAllAccountsLink', opt_data.viewAllAccountsLink, '!goog.soy.data.SanitizedContent|null|string|undefined');
  /** @type {!goog.soy.data.SanitizedContent|null|string|undefined} */
  var createNewAccountLink = soy.asserts.assertType(opt_data.createNewAccountLink == null || (goog.isString(opt_data.createNewAccountLink) || opt_data.createNewAccountLink instanceof goog.soy.data.SanitizedContent), 'createNewAccountLink', opt_data.createNewAccountLink, '!goog.soy.data.SanitizedContent|null|string|undefined');
  /** @type {?} */
  var handleSelectAccount = opt_data.handleSelectAccount;
  var accountSwitcherClasses__soy231 = '';
  accountSwitcherClasses__soy231 += 'account-switcher';
  accountSwitcherClasses__soy231 += isVisible ? ' is-visible' : '';
  incrementalDom.elementOpenStart('div');
      incrementalDom.attr('class', accountSwitcherClasses__soy231);
  incrementalDom.elementOpenEnd();
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
              incrementalDom.attr('placeholder', 'Search Accounts\u2026');
              incrementalDom.attr('data-onkeyup', '_handleFilterChange');
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
        incrementalDom.attr('class', 'account-switcher__section account-switcher__section--fill');
    incrementalDom.elementOpenEnd();
      incrementalDom.elementOpenStart('div');
          incrementalDom.attr('class', 'commerce-account-list');
      incrementalDom.elementOpenEnd();
        incrementalDom.elementOpenStart('div');
            incrementalDom.attr('class', 'commerce-account-list__title');
        incrementalDom.elementOpenEnd();
          incrementalDom.text('Select Account...');
        incrementalDom.elementClose('div');
        if ((accounts != null)) {
          var account251List = accounts;
          var account251ListLen = account251List.length;
          for (var account251Index = 0; account251Index < account251ListLen; account251Index++) {
              var account251Data = account251List[account251Index];
              $templateAlias1({events: {selectAccount: handleSelectAccount}, id: account251Data.id, thumbnail: account251Data.thumbnail, name: account251Data.name, query: filterString}, null, opt_ijData);
            }
        }
      incrementalDom.elementClose('div');
    incrementalDom.elementClose('div');
    if ((viewAllAccountsLink != null)) {
      incrementalDom.elementOpenStart('div');
          incrementalDom.attr('class', 'account-switcher__section');
      incrementalDom.elementOpenEnd();
        incrementalDom.elementOpenStart('a');
            incrementalDom.attr('href', viewAllAccountsLink);
            incrementalDom.attr('class', 'commerce-button commerce-button--block commerce-button--outline');
        incrementalDom.elementOpenEnd();
          incrementalDom.text('View all accounts');
        incrementalDom.elementClose('a');
      incrementalDom.elementClose('div');
    }
    if ((createNewAccountLink != null)) {
      incrementalDom.elementOpenStart('div');
          incrementalDom.attr('class', 'account-switcher__section');
      incrementalDom.elementOpenEnd();
        incrementalDom.elementOpenStart('a');
            incrementalDom.attr('href', createNewAccountLink);
            incrementalDom.attr('class', 'commerce-button commerce-button--block');
        incrementalDom.elementOpenEnd();
          incrementalDom.text('Create new account');
        incrementalDom.elementClose('a');
      incrementalDom.elementClose('div');
    }
  incrementalDom.elementClose('div');
}
exports.render = $render;
/**
 * @typedef {{
 *  accounts: (!Array<{id: (!goog.soy.data.SanitizedContent|number|string), name: (!goog.soy.data.SanitizedContent|string), thumbnail: (!goog.soy.data.SanitizedContent|string)}>|null|undefined),
 *  filterString: (!goog.soy.data.SanitizedContent|null|string|undefined),
 *  isVisible: (boolean|null|undefined),
 *  viewAllAccountsLink: (!goog.soy.data.SanitizedContent|null|string|undefined),
 *  createNewAccountLink: (!goog.soy.data.SanitizedContent|null|string|undefined),
 *  handleSelectAccount: (?)
 * }}
 */
$render.Params;
if (goog.DEBUG) {
  $render.soyTemplateName = 'AccountsTable.render';
}

exports.render.params = ["accounts","filterString","isVisible","viewAllAccountsLink","createNewAccountLink","handleSelectAccount"];
exports.render.types = {"accounts":"list<[\n        name: string,\n        id: string|int,\n        thumbnail: string\n    ]>","filterString":"string ","isVisible":"bool","viewAllAccountsLink":"string","createNewAccountLink":"string","handleSelectAccount":"?"};
templates = exports;
return exports;

});

class AccountsTable extends Component {}
Soy.register(AccountsTable, templates);
export { AccountsTable, templates };
export default templates;
/* jshint ignore:end */
