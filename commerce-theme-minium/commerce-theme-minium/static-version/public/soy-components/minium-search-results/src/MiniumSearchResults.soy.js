/* jshint ignore:start */
import Component from 'metal-component';
import Soy from 'metal-soy';

var templates;
goog.loadModule(function(exports) {
var soy = goog.require('soy');
var soydata = goog.require('soydata');
// This file was automatically generated from MiniumSearchResults.soy.
// Please don't edit this file by hand.

/**
 * @fileoverview Templates in namespace MiniumSearchResults.
 * @hassoydeltemplate {MiniumSearchResults.Item.idom}
 * @hassoydelcall {MiniumSearchResults.Item.idom}
 * @public
 */

goog.module('MiniumSearchResults.incrementaldom');

goog.require('goog.soy.data.SanitizedContent');
var incrementalDom = goog.require('incrementaldom');
goog.require('soy');
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
  /** @type {!goog.soy.data.SanitizedContent|string} */
  var queryString = soy.asserts.assertType(goog.isString(opt_data.queryString) || opt_data.queryString instanceof goog.soy.data.SanitizedContent, 'queryString', opt_data.queryString, '!goog.soy.data.SanitizedContent|string');
  /** @type {?} */
  var results = opt_data.results;
  /** @type {boolean} */
  var visible = soy.asserts.assertType(goog.isBoolean(opt_data.visible) || opt_data.visible === 1 || opt_data.visible === 0, 'visible', opt_data.visible, 'boolean');
  if (visible) {
    incrementalDom.elementOpenStart('div');
        incrementalDom.attr('class', 'commerce-suggestions');
    incrementalDom.elementOpenEnd();
    incrementalDom.elementOpenStart('div');
        incrementalDom.attr('class', 'commerce-suggestions__wrapper');
    incrementalDom.elementOpenEnd();
    var item10List = results;
    var item10ListLen = item10List.length;
    for (var item10Index = 0; item10Index < item10ListLen; item10Index++) {
      var item10Data = item10List[item10Index];
      soy.$$getDelegateFn(soy.$$getDelTemplateId('MiniumSearchResults.Item.idom'), item10Data.type, false)({queryString: queryString, item: item10Data}, opt_ijData);
    }
    incrementalDom.elementClose('div');
    incrementalDom.elementOpenStart('div');
        incrementalDom.attr('class', 'commerce-suggestions__hints');
    incrementalDom.elementOpenEnd();
    incrementalDom.elementOpenStart('svg');
        incrementalDom.attr('class', 'commerce-icon');
        incrementalDom.attr('xmlns', 'http://www.w3.org/2000/svg');
        incrementalDom.attr('viewBox', '0 0 100 100');
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
    incrementalDom.text(' to navigate');
    incrementalDom.elementOpenStart('svg');
        incrementalDom.attr('class', 'commerce-icon');
        incrementalDom.attr('xmlns', 'http://www.w3.org/2000/svg');
        incrementalDom.attr('viewBox', '0 0 100 100');
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
    incrementalDom.text(' to select');
    incrementalDom.elementOpenStart('svg');
        incrementalDom.attr('class', 'commerce-icon');
        incrementalDom.attr('xmlns', 'http://www.w3.org/2000/svg');
        incrementalDom.attr('viewBox', '0 0 100 100');
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
    incrementalDom.text(' to dismiss');
    incrementalDom.elementClose('div');
    incrementalDom.elementClose('div');
  }
};
exports.render = $render;
/**
 * @typedef {{
 *  queryString: (!goog.soy.data.SanitizedContent|string),
 *  results: ?,
 *  visible: boolean,
 * }}
 */
$render.Params;
if (goog.DEBUG) {
  $render.soyTemplateName = 'MiniumSearchResults.render';
}


/**
 * @param {__deltemplate__MiniumSearchResults_Item_.Params} opt_data
 * @param {Object<string, *>=} opt_ijData
 * @param {Object<string, *>=} opt_ijData_deprecated
 * @return {void}
 * @suppress {checkTypes|uselessCode}
 */
var __deltemplate__MiniumSearchResults_Item_ = function(opt_data, opt_ijData, opt_ijData_deprecated) {
  opt_ijData = opt_ijData_deprecated || opt_ijData;
  /** @type {!goog.soy.data.SanitizedContent|string} */
  var queryString = soy.asserts.assertType(goog.isString(opt_data.queryString) || opt_data.queryString instanceof goog.soy.data.SanitizedContent, 'queryString', opt_data.queryString, '!goog.soy.data.SanitizedContent|string');
  /** @type {?} */
  var item = opt_data.item;
  incrementalDom.elementOpenStart('div');
      incrementalDom.attr('class', 'commerce-suggestions__item ' + (item.selected ? 'is-selected' : ''));
      incrementalDom.attr('data-onmouseenter', 'handleMouseEnter');
      incrementalDom.attr('data-onmouseleave', 'handleMouseLeave');
      incrementalDom.attr('data-pos', item.pos);
  incrementalDom.elementOpenEnd();
  incrementalDom.elementOpenStart('div');
      incrementalDom.attr('class', 'commerce-item commerce-item--search');
  incrementalDom.elementOpenEnd();
  incrementalDom.elementOpenStart('img');
      incrementalDom.attr('class', 'commerce-item__image');
      incrementalDom.attr('src', item.image);
      incrementalDom.attr('alt', '');
  incrementalDom.elementOpenEnd();
  incrementalDom.elementClose('img');
  incrementalDom.elementOpenStart('div');
      incrementalDom.attr('class', 'commerce-item__content');
  incrementalDom.elementOpenEnd();
  soyIdom.print(item.title);
  incrementalDom.elementOpen('br');
  incrementalDom.elementClose('br');
  soyIdom.print(item.subtitle);
  incrementalDom.elementClose('div');
  incrementalDom.elementClose('div');
  incrementalDom.elementClose('div');
};
exports.__deltemplate__MiniumSearchResults_Item_ = __deltemplate__MiniumSearchResults_Item_;
/**
 * @typedef {{
 *  queryString: (!goog.soy.data.SanitizedContent|string),
 *  item: ?,
 * }}
 */
__deltemplate__MiniumSearchResults_Item_.Params;
if (goog.DEBUG) {
  __deltemplate__MiniumSearchResults_Item_.soyTemplateName = 'MiniumSearchResults.__deltemplate__MiniumSearchResults_Item_';
}
soy.$$registerDelegateFn(soy.$$getDelTemplateId('MiniumSearchResults.Item.idom'), '', 0, __deltemplate__MiniumSearchResults_Item_);


/**
 * @param {__deltemplate__MiniumSearchResults_Item_category.Params} opt_data
 * @param {Object<string, *>=} opt_ijData
 * @param {Object<string, *>=} opt_ijData_deprecated
 * @return {void}
 * @suppress {checkTypes|uselessCode}
 */
var __deltemplate__MiniumSearchResults_Item_category = function(opt_data, opt_ijData, opt_ijData_deprecated) {
  opt_ijData = opt_ijData_deprecated || opt_ijData;
  /** @type {!goog.soy.data.SanitizedContent|string} */
  var queryString = soy.asserts.assertType(goog.isString(opt_data.queryString) || opt_data.queryString instanceof goog.soy.data.SanitizedContent, 'queryString', opt_data.queryString, '!goog.soy.data.SanitizedContent|string');
  /** @type {?} */
  var item = opt_data.item;
  incrementalDom.elementOpenStart('div');
      incrementalDom.attr('class', 'commerce-suggestions__item ' + (item.selected ? 'is-selected' : ''));
      incrementalDom.attr('data-onmouseenter', 'handleMouseEnter');
      incrementalDom.attr('data-onmouseleave', 'handleMouseLeave');
      incrementalDom.attr('data-pos', item.pos);
  incrementalDom.elementOpenEnd();
  incrementalDom.text('Search "');
  soyIdom.print(queryString);
  incrementalDom.text('" in ');
  soyIdom.print(item.value);
  incrementalDom.elementClose('div');
};
exports.__deltemplate__MiniumSearchResults_Item_category = __deltemplate__MiniumSearchResults_Item_category;
/**
 * @typedef {{
 *  queryString: (!goog.soy.data.SanitizedContent|string),
 *  item: ?,
 * }}
 */
__deltemplate__MiniumSearchResults_Item_category.Params;
if (goog.DEBUG) {
  __deltemplate__MiniumSearchResults_Item_category.soyTemplateName = 'MiniumSearchResults.__deltemplate__MiniumSearchResults_Item_category';
}
soy.$$registerDelegateFn(soy.$$getDelTemplateId('MiniumSearchResults.Item.idom'), 'category', 0, __deltemplate__MiniumSearchResults_Item_category);


/**
 * @param {__deltemplate__MiniumSearchResults_Item_label.Params} opt_data
 * @param {Object<string, *>=} opt_ijData
 * @param {Object<string, *>=} opt_ijData_deprecated
 * @return {void}
 * @suppress {checkTypes|uselessCode}
 */
var __deltemplate__MiniumSearchResults_Item_label = function(opt_data, opt_ijData, opt_ijData_deprecated) {
  opt_ijData = opt_ijData_deprecated || opt_ijData;
  /** @type {!goog.soy.data.SanitizedContent|string} */
  var queryString = soy.asserts.assertType(goog.isString(opt_data.queryString) || opt_data.queryString instanceof goog.soy.data.SanitizedContent, 'queryString', opt_data.queryString, '!goog.soy.data.SanitizedContent|string');
  /** @type {?} */
  var item = opt_data.item;
  incrementalDom.elementOpenStart('div');
      incrementalDom.attr('class', 'commerce-suggestions__label');
  incrementalDom.elementOpenEnd();
  soyIdom.print(item.value);
  incrementalDom.elementClose('div');
};
exports.__deltemplate__MiniumSearchResults_Item_label = __deltemplate__MiniumSearchResults_Item_label;
/**
 * @typedef {{
 *  queryString: (!goog.soy.data.SanitizedContent|string),
 *  item: ?,
 * }}
 */
__deltemplate__MiniumSearchResults_Item_label.Params;
if (goog.DEBUG) {
  __deltemplate__MiniumSearchResults_Item_label.soyTemplateName = 'MiniumSearchResults.__deltemplate__MiniumSearchResults_Item_label';
}
soy.$$registerDelegateFn(soy.$$getDelTemplateId('MiniumSearchResults.Item.idom'), 'label', 0, __deltemplate__MiniumSearchResults_Item_label);

exports.render.params = ["queryString","results","visible"];
exports.render.types = {"queryString":"string ","results":"? ","visible":"bool "};
templates = exports;
return exports;

});

class MiniumSearchResults extends Component {}
Soy.register(MiniumSearchResults, templates);
export { MiniumSearchResults, templates };
export default templates;
/* jshint ignore:end */
