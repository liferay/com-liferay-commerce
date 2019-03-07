/* jshint ignore:start */
import Component from 'metal-component';
import Soy from 'metal-soy';

var templates;
goog.loadModule(function(exports) {
var soy = goog.require('soy');
var soydata = goog.require('soydata');
// This file was automatically generated from AutocompleteItem.soy.
// Please don't edit this file by hand.

/**
 * @fileoverview Templates in namespace AutocompleteItem.
 * @public
 */

goog.module('AutocompleteItem.incrementaldom');

goog.require('goog.soy.data.SanitizedContent');
var incrementalDom = goog.require('incrementaldom');
goog.require('soy.asserts');
var soyIdom = goog.require('soy.idom');


/**
 * @param {$render.Params} opt_data
 * @param {Object<string, *>=} opt_ijData
 * @param {Object<string, *>=} opt_ijData_deprecated
 * @return {void}
 * @suppress {checkTypes|uselessCode}
 */
var $render = function(opt_data, opt_ijData, opt_ijData_deprecated) {
  opt_ijData = opt_ijData_deprecated || opt_ijData;
  opt_data = opt_data || {};
  /** @type {!goog.soy.data.SanitizedContent|null|string|undefined} */
  var firstGroup = soy.asserts.assertType(opt_data.firstGroup == null || (goog.isString(opt_data.firstGroup) || opt_data.firstGroup instanceof goog.soy.data.SanitizedContent), 'firstGroup', opt_data.firstGroup, '!goog.soy.data.SanitizedContent|null|string|undefined');
  /** @type {!goog.soy.data.SanitizedContent|null|string|undefined} */
  var secondGroup = soy.asserts.assertType(opt_data.secondGroup == null || (goog.isString(opt_data.secondGroup) || opt_data.secondGroup instanceof goog.soy.data.SanitizedContent), 'secondGroup', opt_data.secondGroup, '!goog.soy.data.SanitizedContent|null|string|undefined');
  /** @type {!goog.soy.data.SanitizedContent|null|string|undefined} */
  var thirdGroup = soy.asserts.assertType(opt_data.thirdGroup == null || (goog.isString(opt_data.thirdGroup) || opt_data.thirdGroup instanceof goog.soy.data.SanitizedContent), 'thirdGroup', opt_data.thirdGroup, '!goog.soy.data.SanitizedContent|null|string|undefined');
  incrementalDom.elementOpenStart('span');
      incrementalDom.attr('class', 'autocomplete-item');
  incrementalDom.elementOpenEnd();
  soyIdom.print(firstGroup);
  if ((secondGroup != null)) {
    incrementalDom.elementOpen('strong');
    soyIdom.print(secondGroup);
    incrementalDom.elementClose('strong');
  }
  if ((thirdGroup != null)) {
    soyIdom.print(thirdGroup);
  }
  incrementalDom.elementClose('span');
};
exports.render = $render;
/**
 * @typedef {{
 *  firstGroup: (!goog.soy.data.SanitizedContent|null|string|undefined),
 *  secondGroup: (!goog.soy.data.SanitizedContent|null|string|undefined),
 *  thirdGroup: (!goog.soy.data.SanitizedContent|null|string|undefined),
 * }}
 */
$render.Params;
if (goog.DEBUG) {
  $render.soyTemplateName = 'AutocompleteItem.render';
}

exports.render.params = ["firstGroup","secondGroup","thirdGroup"];
exports.render.types = {"firstGroup":"string ","secondGroup":"string ","thirdGroup":"string "};
templates = exports;
return exports;

});

class AutocompleteItem extends Component {}
Soy.register(AutocompleteItem, templates);
export { AutocompleteItem, templates };
export default templates;
/* jshint ignore:end */
