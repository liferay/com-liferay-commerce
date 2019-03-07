/* jshint ignore:start */
import Component from 'metal-component';
import Soy from 'metal-soy';

var templates;
goog.loadModule(function(exports) {
var soy = goog.require('soy');
var soydata = goog.require('soydata');
// This file was automatically generated from UserInvitationInputItem.soy.
// Please don't edit this file by hand.

/**
 * @fileoverview Templates in namespace UserInvitationInputItem.
 * @public
 */

goog.module('UserInvitationInputItem.incrementaldom');

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
  /** @type {!goog.soy.data.SanitizedContent|null|string|undefined} */
  var name = soy.asserts.assertType(opt_data.name == null || (goog.isString(opt_data.name) || opt_data.name instanceof goog.soy.data.SanitizedContent), 'name', opt_data.name, '!goog.soy.data.SanitizedContent|null|string|undefined');
  /** @type {!goog.soy.data.SanitizedContent|string} */
  var email = soy.asserts.assertType(goog.isString(opt_data.email) || opt_data.email instanceof goog.soy.data.SanitizedContent, 'email', opt_data.email, '!goog.soy.data.SanitizedContent|string');
  /** @type {!goog.soy.data.SanitizedContent|null|string|undefined} */
  var thumbnail = soy.asserts.assertType(opt_data.thumbnail == null || (goog.isString(opt_data.thumbnail) || opt_data.thumbnail instanceof goog.soy.data.SanitizedContent), 'thumbnail', opt_data.thumbnail, '!goog.soy.data.SanitizedContent|null|string|undefined');
  incrementalDom.elementOpenStart('span');
      incrementalDom.attr('class', 'added-input-item');
  incrementalDom.elementOpenEnd();
  if ((thumbnail != null)) {
    incrementalDom.elementOpenStart('span');
        incrementalDom.attr('class', 'added-input-item__thumbnail');
    incrementalDom.elementOpenEnd();
    incrementalDom.elementOpenStart('img');
        incrementalDom.attr('src', thumbnail);
    incrementalDom.elementOpenEnd();
    incrementalDom.elementClose('img');
    incrementalDom.elementClose('span');
  }
  incrementalDom.elementOpenStart('span');
      incrementalDom.attr('class', 'added-input-item__text');
  incrementalDom.elementOpenEnd();
  if ((name != null)) {
    soyIdom.print(name);
  } else {
    soyIdom.print(email);
  }
  incrementalDom.elementClose('span');
  incrementalDom.elementOpenStart('button');
      incrementalDom.attr('class', 'added-input-item__delete');
      incrementalDom.attr('data-onclick', '_handleRemoveItem');
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
  incrementalDom.elementClose('span');
};
exports.render = $render;
/**
 * @typedef {{
 *  name: (!goog.soy.data.SanitizedContent|null|string|undefined),
 *  email: (!goog.soy.data.SanitizedContent|string),
 *  thumbnail: (!goog.soy.data.SanitizedContent|null|string|undefined),
 * }}
 */
$render.Params;
if (goog.DEBUG) {
  $render.soyTemplateName = 'UserInvitationInputItem.render';
}

exports.render.params = ["name","email","thumbnail"];
exports.render.types = {"name":"string ","email":"string ","thumbnail":"string "};
templates = exports;
return exports;

});

class UserInvitationInputItem extends Component {}
Soy.register(UserInvitationInputItem, templates);
export { UserInvitationInputItem, templates };
export default templates;
/* jshint ignore:end */
