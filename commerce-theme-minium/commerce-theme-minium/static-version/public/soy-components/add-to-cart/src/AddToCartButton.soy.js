/* jshint ignore:start */
import Component from 'metal-component';
import Soy from 'metal-soy';

var templates;
goog.loadModule(function(exports) {
var soy = goog.require('soy');
var soydata = goog.require('soydata');
// This file was automatically generated from AddToCartButton.soy.
// Please don't edit this file by hand.

/**
 * @fileoverview Templates in namespace AddToCartButton.
 * @public
 */

goog.module('AddToCartButton.incrementaldom');

var incrementalDom = goog.require('incrementaldom');
goog.require('soy.asserts');
var soyIdom = goog.require('soy.idom');

var $templateAlias1 = Soy.getTemplate('QuantitySelector.incrementaldom', 'render');


/**
 * @param {$render.Params} opt_data
 * @param {Object<string, *>=} opt_ijData
 * @param {Object<string, *>=} opt_ijData_deprecated
 * @return {void}
 * @suppress {checkTypes|uselessCode}
 */
var $render = function(opt_data, opt_ijData, opt_ijData_deprecated) {
  opt_ijData = opt_ijData_deprecated || opt_ijData;
  /** @type {number} */
  var quantity = soy.asserts.assertType(goog.isNumber(opt_data.quantity), 'quantity', opt_data.quantity, 'number');
  /** @type {boolean} */
  var editMode = soy.asserts.assertType(goog.isBoolean(opt_data.editMode) || opt_data.editMode === 1 || opt_data.editMode === 0, 'editMode', opt_data.editMode, 'boolean');
  /** @type {?} */
  var settings = opt_data.settings;
  /** @type {?} */
  var updateQuantity = opt_data.updateQuantity;
  var btnClass__soy13 = '';
  btnClass__soy13 += 'commerce-button add-to-cart-button';
  btnClass__soy13 += quantity > 0 && editMode == false ? ' commerce-button--good' : '';
  incrementalDom.elementOpenStart('div');
      incrementalDom.attr('class', btnClass__soy13);
      incrementalDom.attr('data-onclick', 'handleBtnClick');
  incrementalDom.elementOpenEnd();
  if (editMode == false) {
    if (quantity == 0) {
      incrementalDom.text('Add to cart');
    } else {
      soyIdom.print(quantity);
      incrementalDom.text(' added');
      incrementalDom.elementOpenStart('div');
          incrementalDom.attr('class', 'add-to-cart-button__ok');
      incrementalDom.elementOpenEnd();
      incrementalDom.elementClose('div');
    }
  } else {
    $templateAlias1({events: {updateQuantity: updateQuantity}, quantity: quantity, minQuantity: settings.minQuantity, maxQuantity: settings.maxQuantity, multipleQuantities: settings.multipleQuantities, allowedOptions: settings.allowedOptions}, opt_ijData);
    incrementalDom.elementOpenStart('div');
        incrementalDom.attr('class', 'add-to-cart-button__fwd');
        incrementalDom.attr('data-onclick', 'handleSubmitClick');
    incrementalDom.elementOpenEnd();
    incrementalDom.elementClose('div');
  }
  incrementalDom.elementClose('div');
};
exports.render = $render;
/**
 * @typedef {{
 *  quantity: number,
 *  editMode: boolean,
 *  settings: ?,
 *  updateQuantity: (?|undefined),
 * }}
 */
$render.Params;
if (goog.DEBUG) {
  $render.soyTemplateName = 'AddToCartButton.render';
}

exports.render.params = ["quantity","editMode","settings","updateQuantity"];
exports.render.types = {"quantity":"int ","editMode":"bool ","settings":"? ","updateQuantity":"? "};
templates = exports;
return exports;

});

class AddToCartButton extends Component {}
Soy.register(AddToCartButton, templates);
export { AddToCartButton, templates };
export default templates;
/* jshint ignore:end */
