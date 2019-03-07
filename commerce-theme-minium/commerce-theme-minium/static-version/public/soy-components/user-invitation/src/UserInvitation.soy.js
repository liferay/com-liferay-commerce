/* jshint ignore:start */
import Component from 'metal-component';
import Soy from 'metal-soy';

var templates;
goog.loadModule(function(exports) {
var soy = goog.require('soy');
var soydata = goog.require('soydata');
// This file was automatically generated from UserInvitation.soy.
// Please don't edit this file by hand.

/**
 * @fileoverview Templates in namespace UserInvitation.
 * @public
 */

goog.module('UserInvitation.incrementaldom');

goog.require('goog.soy.data.SanitizedContent');
var incrementalDom = goog.require('incrementaldom');
goog.require('soy.asserts');
var soyIdom = goog.require('soy.idom');

var $templateAlias1 = Soy.getTemplate('UserInvitationInputItem.incrementaldom', 'render');

var $templateAlias2 = Soy.getTemplate('UserInvitationListItem.incrementaldom', 'render');


/**
 * @param {$render.Params} opt_data
 * @param {Object<string, *>=} opt_ijData
 * @param {Object<string, *>=} opt_ijData_deprecated
 * @return {void}
 * @suppress {checkTypes|uselessCode}
 */
var $render = function(opt_data, opt_ijData, opt_ijData_deprecated) {
  opt_ijData = opt_ijData_deprecated || opt_ijData;
  /** @type {!Array<{email: (!goog.soy.data.SanitizedContent|string), id: (!goog.soy.data.SanitizedContent|number|string), name: (!goog.soy.data.SanitizedContent|string), thumbnail: (!goog.soy.data.SanitizedContent|string),}>} */
  var users = soy.asserts.assertType(goog.isArray(opt_data.users), 'users', opt_data.users, '!Array<{email: (!goog.soy.data.SanitizedContent|string), id: (!goog.soy.data.SanitizedContent|number|string), name: (!goog.soy.data.SanitizedContent|string), thumbnail: (!goog.soy.data.SanitizedContent|string),}>');
  /** @type {!Array<{email: (!goog.soy.data.SanitizedContent|string), id: (!goog.soy.data.SanitizedContent|number|string), name: (!goog.soy.data.SanitizedContent|string), thumbnail: (!goog.soy.data.SanitizedContent|string),}>} */
  var addedUsers = soy.asserts.assertType(goog.isArray(opt_data.addedUsers), 'addedUsers', opt_data.addedUsers, '!Array<{email: (!goog.soy.data.SanitizedContent|string), id: (!goog.soy.data.SanitizedContent|number|string), name: (!goog.soy.data.SanitizedContent|string), thumbnail: (!goog.soy.data.SanitizedContent|string),}>');
  /** @type {!goog.soy.data.SanitizedContent|string} */
  var query = soy.asserts.assertType(goog.isString(opt_data.query) || opt_data.query instanceof goog.soy.data.SanitizedContent, 'query', opt_data.query, '!goog.soy.data.SanitizedContent|string');
  /** @type {?} */
  var _toggleInvitation = opt_data._toggleInvitation;
  incrementalDom.elementOpenStart('div');
      incrementalDom.attr('class', 'user-invitation');
  incrementalDom.elementOpenEnd();
  incrementalDom.elementOpenStart('h3');
      incrementalDom.attr('class', 'user-invitation__title');
  incrementalDom.elementOpenEnd();
  incrementalDom.text('Invite user to this account');
  incrementalDom.elementClose('h3');
  incrementalDom.elementOpenStart('div');
      incrementalDom.attr('class', 'user-invitation__input autocomplete-input');
  incrementalDom.elementOpenEnd();
  incrementalDom.elementOpenStart('div');
      incrementalDom.attr('class', 'autocomplete-input__icon');
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
  incrementalDom.elementClose('div');
  incrementalDom.elementOpenStart('div');
      incrementalDom.attr('class', 'autocomplete-input__wrapper');
  incrementalDom.elementOpenEnd();
  incrementalDom.elementOpenStart('div');
      incrementalDom.attr('class', 'autocomplete-input__content');
  incrementalDom.elementOpenEnd();
  var addedUser42List = addedUsers;
  var addedUser42ListLen = addedUser42List.length;
  for (var addedUser42Index = 0; addedUser42Index < addedUser42ListLen; addedUser42Index++) {
    var addedUser42Data = addedUser42List[addedUser42Index];
    $templateAlias1({events: {removeItem: _toggleInvitation}, id: addedUser42Data.id, email: addedUser42Data.email, name: addedUser42Data.name, thumbnail: addedUser42Data.thumbnail}, opt_ijData);
  }
  incrementalDom.elementOpenStart('form');
      incrementalDom.attr('data-onsubmit', '_handleFormSubmit');
  incrementalDom.elementOpenEnd();
  incrementalDom.elementOpenStart('input');
      incrementalDom.attr('class', 'autocomplete-input__box');
      incrementalDom.attr('type', 'text');
      incrementalDom.attr('value', query);
      incrementalDom.attr('data-onkeyup', '_handleInputBox');
  incrementalDom.elementOpenEnd();
  incrementalDom.elementClose('input');
  incrementalDom.elementClose('form');
  incrementalDom.elementClose('div');
  incrementalDom.elementClose('div');
  incrementalDom.elementClose('div');
  incrementalDom.elementOpenStart('div');
      incrementalDom.attr('class', 'user-invitation__list');
  incrementalDom.elementOpenEnd();
  if ((users.length) > 0) {
    var user58List = users;
    var user58ListLen = user58List.length;
    for (var user58Index = 0; user58Index < user58ListLen; user58Index++) {
      var user58Data = user58List[user58Index];
      $templateAlias2({events: {toggleInvitation: _toggleInvitation}, id: user58Data.id, name: user58Data.name, email: user58Data.email, thumbnail: user58Data.thumbnail, addedUsers: addedUsers, query: query}, opt_ijData);
    }
  }
  if ((users.length) == 0 && query != '') {
    $templateAlias2({events: {toggleInvitation: _toggleInvitation}, email: query, addedUsers: addedUsers}, opt_ijData);
  }
  incrementalDom.elementClose('div');
  var inviteButtonClasses__soy86 = '';
  inviteButtonClasses__soy86 += 'commerce-button commerce-button--big';
  inviteButtonClasses__soy86 += (addedUsers.length) == 0 ? ' commerce-button--disabled' : '';
  incrementalDom.elementOpenStart('div');
      incrementalDom.attr('class', 'user-invitation__invite');
  incrementalDom.elementOpenEnd();
  incrementalDom.elementOpenStart('button');
      incrementalDom.attr('class', inviteButtonClasses__soy86);
      incrementalDom.attr('data-onclick', '_sendInvitations');
  incrementalDom.elementOpenEnd();
  incrementalDom.text('Send invite');
  incrementalDom.elementClose('button');
  incrementalDom.elementClose('div');
  incrementalDom.elementClose('div');
};
exports.render = $render;
/**
 * @typedef {{
 *  users: !Array<{email: (!goog.soy.data.SanitizedContent|string), id: (!goog.soy.data.SanitizedContent|number|string), name: (!goog.soy.data.SanitizedContent|string), thumbnail: (!goog.soy.data.SanitizedContent|string),}>,
 *  addedUsers: !Array<{email: (!goog.soy.data.SanitizedContent|string), id: (!goog.soy.data.SanitizedContent|number|string), name: (!goog.soy.data.SanitizedContent|string), thumbnail: (!goog.soy.data.SanitizedContent|string),}>,
 *  query: (!goog.soy.data.SanitizedContent|string),
 *  _toggleInvitation: ?,
 * }}
 */
$render.Params;
if (goog.DEBUG) {
  $render.soyTemplateName = 'UserInvitation.render';
}

exports.render.params = ["users","addedUsers","query","_toggleInvitation"];
exports.render.types = {"users":"list<[\n\t\tid: string | number,\n\t\tthumbnail: string,\n\t\tname: string,\n\t\temail: string\n\t]>","addedUsers":"list<[\n\t\tid: string | number,\n\t\tthumbnail: string,\n\t\tname: string,\n\t\temail: string\n\t]>","query":"string ","_toggleInvitation":"? "};
templates = exports;
return exports;

});

class UserInvitation extends Component {}
Soy.register(UserInvitation, templates);
export { UserInvitation, templates };
export default templates;
/* jshint ignore:end */
