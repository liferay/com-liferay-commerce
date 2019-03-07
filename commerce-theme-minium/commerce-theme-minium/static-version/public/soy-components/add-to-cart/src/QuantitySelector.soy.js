/* jshint ignore:start */
import Component from 'metal-component';
import Soy from 'metal-soy';

var templates;
goog.loadModule(function(exports) {
var soy = goog.require('soy');
var soydata = goog.require('soydata');
// This file was automatically generated from QuantitySelector.soy.
// Please don't edit this file by hand.

/**
 * @fileoverview Templates in namespace QuantitySelector.
 * @public
 */

goog.module('QuantitySelector.incrementaldom');

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
  /** @type {number} */
  var quantity = soy.asserts.assertType(goog.isNumber(opt_data.quantity), 'quantity', opt_data.quantity, 'number');
  /** @type {?} */
  var allowedOptions = opt_data.allowedOptions;
  /** @type {boolean|null|undefined} */
  var isPrevAvailable = soy.asserts.assertType(opt_data.isPrevAvailable == null || (goog.isBoolean(opt_data.isPrevAvailable) || opt_data.isPrevAvailable === 1 || opt_data.isPrevAvailable === 0), 'isPrevAvailable', opt_data.isPrevAvailable, 'boolean|null|undefined');
  /** @type {boolean|null|undefined} */
  var isNextAvailable = soy.asserts.assertType(opt_data.isNextAvailable == null || (goog.isBoolean(opt_data.isNextAvailable) || opt_data.isNextAvailable === 1 || opt_data.isNextAvailable === 0), 'isNextAvailable', opt_data.isNextAvailable, 'boolean|null|undefined');
  var prevClass__soy84 = '';
  prevClass__soy84 += isPrevAvailable == false ? ' is-disabled' : '';
  var nextClass__soy91 = '';
  nextClass__soy91 += isNextAvailable == false ? ' is-disabled' : '';
  incrementalDom.elementOpenStart('div');
      incrementalDom.attr('class', 'commerce-quantity-selector');
  incrementalDom.elementOpenEnd();
  if ((allowedOptions != null)) {
    incrementalDom.elementOpenStart('form');
        incrementalDom.attr('data-onsubmit', 'handleFormSubmit');
    incrementalDom.elementOpenEnd();
    incrementalDom.elementOpenStart('select');
        incrementalDom.attr('class', 'commerce-quantity-selector__select');
        incrementalDom.attr('data-onchange', 'handleSelectOption');
    incrementalDom.elementOpenEnd();
    var option96List = allowedOptions;
    var option96ListLen = option96List.length;
    for (var option96Index = 0; option96Index < option96ListLen; option96Index++) {
      var option96Data = option96List[option96Index];
      var optionAttributes__soy106 = function() {
        incrementalDom.attr('value', option96Data);
        if (option96Data == quantity) {
          incrementalDom.attr('selected', 'selected');
        }
      };
      incrementalDom.elementOpenStart('option');
          optionAttributes__soy106();
      incrementalDom.elementOpenEnd();
      soyIdom.print(option96Data);
      incrementalDom.elementClose('option');
    }
    incrementalDom.elementClose('select');
    incrementalDom.elementClose('form');
  } else {
    incrementalDom.elementOpenStart('a');
        incrementalDom.attr('href', '#');
        incrementalDom.attr('class', 'commerce-quantity-selector__btn' + prevClass__soy84);
        incrementalDom.attr('data-onclick', 'handlePrevQuantity');
    incrementalDom.elementOpenEnd();
    incrementalDom.text('-');
    incrementalDom.elementClose('a');
    incrementalDom.elementOpenStart('form');
        incrementalDom.attr('data-onsubmit', 'handleFormSubmit');
    incrementalDom.elementOpenEnd();
    incrementalDom.elementOpenStart('input');
        incrementalDom.attr('name', 'quantity');
        incrementalDom.attr('type', 'number');
        incrementalDom.attr('class', 'commerce-quantity-selector__input');
        incrementalDom.attr('data-onkeyup', 'handleInputKeyUp');
        incrementalDom.attr('value', quantity);
    incrementalDom.elementOpenEnd();
    incrementalDom.elementClose('input');
    incrementalDom.elementClose('form');
    incrementalDom.elementOpenStart('a');
        incrementalDom.attr('href', '#');
        incrementalDom.attr('class', 'commerce-quantity-selector__btn' + nextClass__soy91);
        incrementalDom.attr('data-onclick', 'handleNextQuantity');
    incrementalDom.elementOpenEnd();
    incrementalDom.text('+');
    incrementalDom.elementClose('a');
  }
  incrementalDom.elementClose('div');
};
exports.render = $render;
/**
 * @typedef {{
 *  quantity: number,
 *  allowedOptions: (?|undefined),
 *  isPrevAvailable: (boolean|null|undefined),
 *  isNextAvailable: (boolean|null|undefined),
 * }}
 */
$render.Params;
if (goog.DEBUG) {
  $render.soyTemplateName = 'QuantitySelector.render';
}

exports.render.params = ["quantity","allowedOptions","isPrevAvailable","isNextAvailable"];
exports.render.types = {"quantity":"int","allowedOptions":"?","isPrevAvailable":"bool","isNextAvailable":"bool"};
templates = exports;
return exports;

});

class QuantitySelector extends Component {}
Soy.register(QuantitySelector, templates);
export { QuantitySelector, templates };
export default templates;
/* jshint ignore:end */
