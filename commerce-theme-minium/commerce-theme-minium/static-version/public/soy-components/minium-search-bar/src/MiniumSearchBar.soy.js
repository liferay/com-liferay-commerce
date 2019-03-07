/* jshint ignore:start */
import Component from 'metal-component';
import Soy from 'metal-soy';

var templates;
goog.loadModule(function(exports) {
var soy = goog.require('soy');
var soydata = goog.require('soydata');
// This file was automatically generated from MiniumSearchBar.soy.
// Please don't edit this file by hand.

/**
 * @fileoverview Templates in namespace MiniumSearchBar.
 * @public
 */

goog.module('MiniumSearchBar.incrementaldom');

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
  /** @type {!goog.soy.data.SanitizedContent|string} */
  var placeholder = soy.asserts.assertType(goog.isString(opt_data.placeholder) || opt_data.placeholder instanceof goog.soy.data.SanitizedContent, 'placeholder', opt_data.placeholder, '!goog.soy.data.SanitizedContent|string');
  /** @type {!goog.soy.data.SanitizedContent|string} */
  var id = soy.asserts.assertType(goog.isString(opt_data.id) || opt_data.id instanceof goog.soy.data.SanitizedContent, 'id', opt_data.id, '!goog.soy.data.SanitizedContent|string');
  incrementalDom.elementOpenStart('form');
      incrementalDom.attr('class', 'commerce-search');
      incrementalDom.attr('data-onsubmit', 'handleSubmit');
  incrementalDom.elementOpenEnd();
  incrementalDom.elementOpenStart('div');
      incrementalDom.attr('class', 'commerce-search__input');
  incrementalDom.elementOpenEnd();
  incrementalDom.elementOpenStart('input');
      incrementalDom.attr('type', 'text');
      incrementalDom.attr('placeholder', placeholder);
      incrementalDom.attr('id', id);
      incrementalDom.attr('name', 'queryString');
      incrementalDom.attr('data-onkeyup', 'handleKeyUp');
  incrementalDom.elementOpenEnd();
  incrementalDom.elementClose('input');
  incrementalDom.elementClose('div');
  incrementalDom.elementOpenStart('button');
      incrementalDom.attr('type', 'submit');
      incrementalDom.attr('class', 'commerce-search__button');
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
  incrementalDom.elementClose('button');
  incrementalDom.elementClose('form');
};
exports.render = $render;
/**
 * @typedef {{
 *  placeholder: (!goog.soy.data.SanitizedContent|string),
 *  id: (!goog.soy.data.SanitizedContent|string),
 * }}
 */
$render.Params;
if (goog.DEBUG) {
  $render.soyTemplateName = 'MiniumSearchBar.render';
}

exports.render.params = ["placeholder","id"];
exports.render.types = {"placeholder":"string ","id":"string "};
templates = exports;
return exports;

});

class MiniumSearchBar extends Component {}
Soy.register(MiniumSearchBar, templates);
export { MiniumSearchBar, templates };
export default templates;
/* jshint ignore:end */
