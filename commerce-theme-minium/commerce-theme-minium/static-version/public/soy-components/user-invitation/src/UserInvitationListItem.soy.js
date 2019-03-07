/* jshint ignore:start */
import Component from 'metal-component';
import Soy from 'metal-soy';

var templates;
goog.loadModule(function(exports) {
var soy = goog.require('soy');
var soydata = goog.require('soydata');
// This file was automatically generated from UserInvitationListItem.soy.
// Please don't edit this file by hand.

/**
 * @fileoverview Templates in namespace UserInvitationListItem.
 * @public
 */

goog.module('UserInvitationListItem.incrementaldom');

goog.require('goog.soy.data.SanitizedContent');
var incrementalDom = goog.require('incrementaldom');
goog.require('soy.asserts');
var soyIdom = goog.require('soy.idom');

var $templateAlias1 = Soy.getTemplate('AutocompleteItem.incrementaldom', 'render');


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
  var thumbnail = soy.asserts.assertType(opt_data.thumbnail == null || (goog.isString(opt_data.thumbnail) || opt_data.thumbnail instanceof goog.soy.data.SanitizedContent), 'thumbnail', opt_data.thumbnail, '!goog.soy.data.SanitizedContent|null|string|undefined');
  /** @type {!goog.soy.data.SanitizedContent|null|string|undefined} */
  var name = soy.asserts.assertType(opt_data.name == null || (goog.isString(opt_data.name) || opt_data.name instanceof goog.soy.data.SanitizedContent), 'name', opt_data.name, '!goog.soy.data.SanitizedContent|null|string|undefined');
  /** @type {!goog.soy.data.SanitizedContent|string} */
  var email = soy.asserts.assertType(goog.isString(opt_data.email) || opt_data.email instanceof goog.soy.data.SanitizedContent, 'email', opt_data.email, '!goog.soy.data.SanitizedContent|string');
  /** @type {!goog.soy.data.SanitizedContent|null|string|undefined} */
  var _status = soy.asserts.assertType(opt_data._status == null || (goog.isString(opt_data._status) || opt_data._status instanceof goog.soy.data.SanitizedContent), '_status', opt_data._status, '!goog.soy.data.SanitizedContent|null|string|undefined');
  /** @type {boolean|null|undefined} */
  var _invited = soy.asserts.assertType(opt_data._invited == null || (goog.isBoolean(opt_data._invited) || opt_data._invited === 1 || opt_data._invited === 0), '_invited', opt_data._invited, 'boolean|null|undefined');
  /** @type {!goog.soy.data.SanitizedContent|null|string|undefined} */
  var query = soy.asserts.assertType(opt_data.query == null || (goog.isString(opt_data.query) || opt_data.query instanceof goog.soy.data.SanitizedContent), 'query', opt_data.query, '!goog.soy.data.SanitizedContent|null|string|undefined');
  incrementalDom.elementOpenStart('div');
      incrementalDom.attr('class', 'user-item');
  incrementalDom.elementOpenEnd();
  incrementalDom.elementOpenStart('div');
      incrementalDom.attr('class', 'user-item__content');
  incrementalDom.elementOpenEnd();
  if ((thumbnail != null)) {
    incrementalDom.elementOpenStart('div');
        incrementalDom.attr('class', 'user-item__thumbnail');
    incrementalDom.elementOpenEnd();
    incrementalDom.elementOpenStart('img');
        incrementalDom.attr('src', thumbnail);
    incrementalDom.elementOpenEnd();
    incrementalDom.elementClose('img');
    incrementalDom.elementClose('div');
  }
  if ((name != null)) {
    incrementalDom.elementOpenStart('span');
        incrementalDom.attr('class', 'user-item__primary-text');
    incrementalDom.elementOpenEnd();
    $templateAlias1({text: name, query: query}, opt_ijData);
    incrementalDom.elementClose('span');
    incrementalDom.elementOpenStart('span');
        incrementalDom.attr('class', 'user-item__secondary-text');
    incrementalDom.elementOpenEnd();
    $templateAlias1({text: email, query: query}, opt_ijData);
    incrementalDom.elementClose('span');
  } else {
    incrementalDom.elementOpenStart('span');
        incrementalDom.attr('class', 'user-item__primary-text');
    incrementalDom.elementOpenEnd();
    $templateAlias1({text: email, query: query}, opt_ijData);
    incrementalDom.elementClose('span');
    incrementalDom.elementOpenStart('span');
        incrementalDom.attr('class', 'user-item__secondary-text');
    incrementalDom.elementOpenEnd();
    var $tmp = _status;
    switch (goog.isObject($tmp) ? $tmp.toString() : $tmp) {
      case 'user-not-found':
        incrementalDom.text('User not found. You could invite a new user typing his email address.');
        break;
      case 'email-not-valid':
        incrementalDom.text('Please add a valid email address.');
        break;
      case 'valid':
        incrementalDom.text('Click to add to your Organization');
        break;
      default:
    }
    incrementalDom.elementClose('span');
  }
  incrementalDom.elementClose('div');
  if (_status == 'valid') {
    incrementalDom.elementOpenStart('button');
        incrementalDom.attr('class', 'user-item__action');
        incrementalDom.attr('data-onclick', '_handleToggleInvitation');
    incrementalDom.elementOpenEnd();
    if (_invited) {
      incrementalDom.text('del');
    } else {
      incrementalDom.text('add');
    }
    incrementalDom.elementClose('button');
  }
  incrementalDom.elementClose('div');
};
exports.render = $render;
/**
 * @typedef {{
 *  thumbnail: (!goog.soy.data.SanitizedContent|null|string|undefined),
 *  name: (!goog.soy.data.SanitizedContent|null|string|undefined),
 *  email: (!goog.soy.data.SanitizedContent|string),
 *  _status: (!goog.soy.data.SanitizedContent|null|string|undefined),
 *  _invited: (boolean|null|undefined),
 *  query: (!goog.soy.data.SanitizedContent|null|string|undefined),
 * }}
 */
$render.Params;
if (goog.DEBUG) {
  $render.soyTemplateName = 'UserInvitationListItem.render';
}

exports.render.params = ["thumbnail","name","email","_status","_invited","query"];
exports.render.types = {"thumbnail":"string ","name":"string ","email":"string ","_status":"string","_invited":"bool ","query":"string "};
templates = exports;
return exports;

});

class UserInvitationListItem extends Component {}
Soy.register(UserInvitationListItem, templates);
export { UserInvitationListItem, templates };
export default templates;
/* jshint ignore:end */
