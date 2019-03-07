/* jshint ignore:start */
import Component from 'metal-component';
import Soy from 'metal-soy';

var templates;
goog.loadModule(function(exports) {
var soy = goog.require('soy');
var soydata = goog.require('soydata');
// This file was automatically generated from AccountsTableItem.soy.
// Please don't edit this file by hand.

/**
 * @fileoverview Templates in namespace AccountsTableItem.
 * @public
 */

goog.module('AccountsTableItem.incrementaldom');

goog.require('goog.soy.data.SanitizedContent');
var incrementalDom = goog.require('incrementaldom');
goog.require('soy.asserts');
var soyIdom = goog.require('soy.idom');

var $templateAlias1 = Soy.getTemplate('AutocompleteItem.incrementaldom', 'render');


/**
 * @param {{
 *  thumbnail: (!goog.soy.data.SanitizedContent|string),
 *  name: (!goog.soy.data.SanitizedContent|string),
 *  query: (!goog.soy.data.SanitizedContent|null|string|undefined)
 * }} opt_data
 * @param {Object<string, *>=} opt_ijData
 * @param {Object<string, *>=} opt_ijData_deprecated
 * @return {void}
 * @suppress {checkTypes}
 */
function $render(opt_data, opt_ijData, opt_ijData_deprecated) {
  opt_ijData = opt_ijData_deprecated || opt_ijData;
  /** @type {!goog.soy.data.SanitizedContent|string} */
  var thumbnail = soy.asserts.assertType(goog.isString(opt_data.thumbnail) || opt_data.thumbnail instanceof goog.soy.data.SanitizedContent, 'thumbnail', opt_data.thumbnail, '!goog.soy.data.SanitizedContent|string');
  /** @type {!goog.soy.data.SanitizedContent|string} */
  var name = soy.asserts.assertType(goog.isString(opt_data.name) || opt_data.name instanceof goog.soy.data.SanitizedContent, 'name', opt_data.name, '!goog.soy.data.SanitizedContent|string');
  /** @type {!goog.soy.data.SanitizedContent|null|string|undefined} */
  var query = soy.asserts.assertType(opt_data.query == null || (goog.isString(opt_data.query) || opt_data.query instanceof goog.soy.data.SanitizedContent), 'query', opt_data.query, '!goog.soy.data.SanitizedContent|null|string|undefined');
  incrementalDom.elementOpenStart('span');
      incrementalDom.attr('class', 'commerce-account-list__item u-hoverable');
      incrementalDom.attr('data-onclick', '_handleItemClick');
  incrementalDom.elementOpenEnd();
    incrementalDom.elementOpenStart('img');
        incrementalDom.attr('src', thumbnail);
        incrementalDom.attr('alt', name);
    incrementalDom.elementOpenEnd();
    incrementalDom.elementClose('img');
    $templateAlias1({text: name, query: query}, null, opt_ijData);
  incrementalDom.elementClose('span');
}
exports.render = $render;
/**
 * @typedef {{
 *  thumbnail: (!goog.soy.data.SanitizedContent|string),
 *  name: (!goog.soy.data.SanitizedContent|string),
 *  query: (!goog.soy.data.SanitizedContent|null|string|undefined)
 * }}
 */
$render.Params;
if (goog.DEBUG) {
  $render.soyTemplateName = 'AccountsTableItem.render';
}

exports.render.params = ["thumbnail","name","query"];
exports.render.types = {"thumbnail":"string ","name":"string ","query":"string "};
templates = exports;
return exports;

});

class AccountsTableItem extends Component {}
Soy.register(AccountsTableItem, templates);
export { AccountsTableItem, templates };
export default templates;
/* jshint ignore:end */
