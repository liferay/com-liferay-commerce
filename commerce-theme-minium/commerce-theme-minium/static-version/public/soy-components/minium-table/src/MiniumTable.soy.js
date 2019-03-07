/* jshint ignore:start */
import Component from 'metal-component';
import Soy from 'metal-soy';

var templates;
goog.loadModule(function(exports) {
var soy = goog.require('soy');
var soydata = goog.require('soydata');
// This file was automatically generated from MiniumTable.soy.
// Please don't edit this file by hand.

/**
 * @fileoverview Templates in namespace MiniumTable.
 * @hassoydeltemplate {ClayTable.Cell.idom}
 * @public
 */

goog.module('MiniumTable.incrementaldom');

goog.require('goog.soy.data.SanitizedContent');
var incrementalDom = goog.require('incrementaldom');
goog.require('soy');
goog.require('soy.asserts');
var soyIdom = goog.require('soy.idom');


/**
 * @param {__deltemplate__ClayTable_Cell_miniumImage.Params} opt_data
 * @param {Object<string, *>=} opt_ijData
 * @param {Object<string, *>=} opt_ijData_deprecated
 * @return {void}
 * @suppress {checkTypes|uselessCode}
 */
var __deltemplate__ClayTable_Cell_miniumImage = function(opt_data, opt_ijData, opt_ijData_deprecated) {
  opt_ijData = opt_ijData_deprecated || opt_ijData;
  /** @type {?} */
  var fieldSchema = opt_data.fieldSchema;
  /** @type {?} */
  var item = opt_data.item;
  /** @type {?} */
  var value = opt_data.value;
  /** @type {*|null|undefined} */
  var _handleCellContentClick = opt_data._handleCellContentClick;
  /** @type {!goog.soy.data.SanitizedContent|null|string|undefined} */
  var spritemap = soy.asserts.assertType(opt_data.spritemap == null || (goog.isString(opt_data.spritemap) || opt_data.spritemap instanceof goog.soy.data.SanitizedContent), 'spritemap', opt_data.spritemap, '!goog.soy.data.SanitizedContent|null|string|undefined');
  incrementalDom.elementOpenStart('td');
      incrementalDom.attr('class', 'cell');
  incrementalDom.elementOpenEnd();
  incrementalDom.elementOpenStart('img');
      incrementalDom.attr('src', value);
      incrementalDom.attr('alt', item[fieldSchema.altField]);
  incrementalDom.elementOpenEnd();
  incrementalDom.elementClose('img');
  incrementalDom.elementClose('td');
};
exports.__deltemplate__ClayTable_Cell_miniumImage = __deltemplate__ClayTable_Cell_miniumImage;
/**
 * @typedef {{
 *  fieldSchema: ?,
 *  item: ?,
 *  value: ?,
 *  _handleCellContentClick: (*|null|undefined),
 *  spritemap: (!goog.soy.data.SanitizedContent|null|string|undefined),
 * }}
 */
__deltemplate__ClayTable_Cell_miniumImage.Params;
if (goog.DEBUG) {
  __deltemplate__ClayTable_Cell_miniumImage.soyTemplateName = 'MiniumTable.__deltemplate__ClayTable_Cell_miniumImage';
}
soy.$$registerDelegateFn(soy.$$getDelTemplateId('ClayTable.Cell.idom'), 'miniumImage', 0, __deltemplate__ClayTable_Cell_miniumImage);


/**
 * @param {__deltemplate__ClayTable_Cell_miniumCell.Params} opt_data
 * @param {Object<string, *>=} opt_ijData
 * @param {Object<string, *>=} opt_ijData_deprecated
 * @return {void}
 * @suppress {checkTypes|uselessCode}
 */
var __deltemplate__ClayTable_Cell_miniumCell = function(opt_data, opt_ijData, opt_ijData_deprecated) {
  opt_ijData = opt_ijData_deprecated || opt_ijData;
  /** @type {?} */
  var fieldSchema = opt_data.fieldSchema;
  /** @type {?} */
  var item = opt_data.item;
  /** @type {?} */
  var value = opt_data.value;
  /** @type {*|null|undefined} */
  var _handleCellContentClick = opt_data._handleCellContentClick;
  /** @type {!goog.soy.data.SanitizedContent|null|string|undefined} */
  var spritemap = soy.asserts.assertType(opt_data.spritemap == null || (goog.isString(opt_data.spritemap) || opt_data.spritemap instanceof goog.soy.data.SanitizedContent), 'spritemap', opt_data.spritemap, '!goog.soy.data.SanitizedContent|null|string|undefined');
  incrementalDom.elementOpenStart('td');
      incrementalDom.attr('class', 'cell');
  incrementalDom.elementOpenEnd();
  soyIdom.print(value);
  if (item[fieldSchema.subTitleField]) {
    incrementalDom.elementOpen('small');
    soyIdom.print(item[fieldSchema.subTitleField]);
    incrementalDom.elementClose('small');
  }
  incrementalDom.elementClose('td');
};
exports.__deltemplate__ClayTable_Cell_miniumCell = __deltemplate__ClayTable_Cell_miniumCell;
/**
 * @typedef {{
 *  fieldSchema: ?,
 *  item: ?,
 *  value: ?,
 *  _handleCellContentClick: (*|null|undefined),
 *  spritemap: (!goog.soy.data.SanitizedContent|null|string|undefined),
 * }}
 */
__deltemplate__ClayTable_Cell_miniumCell.Params;
if (goog.DEBUG) {
  __deltemplate__ClayTable_Cell_miniumCell.soyTemplateName = 'MiniumTable.__deltemplate__ClayTable_Cell_miniumCell';
}
soy.$$registerDelegateFn(soy.$$getDelTemplateId('ClayTable.Cell.idom'), 'miniumCell', 0, __deltemplate__ClayTable_Cell_miniumCell);


/**
 * @param {__deltemplate__ClayTable_Cell_miniumLink.Params} opt_data
 * @param {Object<string, *>=} opt_ijData
 * @param {Object<string, *>=} opt_ijData_deprecated
 * @return {void}
 * @suppress {checkTypes|uselessCode}
 */
var __deltemplate__ClayTable_Cell_miniumLink = function(opt_data, opt_ijData, opt_ijData_deprecated) {
  opt_ijData = opt_ijData_deprecated || opt_ijData;
  /** @type {?} */
  var fieldSchema = opt_data.fieldSchema;
  /** @type {?} */
  var item = opt_data.item;
  /** @type {?} */
  var value = opt_data.value;
  incrementalDom.elementOpenStart('td');
      incrementalDom.attr('class', 'cell');
  incrementalDom.elementOpenEnd();
  incrementalDom.elementOpenStart('a');
      incrementalDom.attr('href', item[fieldSchema.fieldLink]);
  incrementalDom.elementOpenEnd();
  soyIdom.print(value);
  incrementalDom.elementClose('a');
  incrementalDom.elementClose('td');
};
exports.__deltemplate__ClayTable_Cell_miniumLink = __deltemplate__ClayTable_Cell_miniumLink;
/**
 * @typedef {{
 *  fieldSchema: ?,
 *  item: ?,
 *  value: ?,
 * }}
 */
__deltemplate__ClayTable_Cell_miniumLink.Params;
if (goog.DEBUG) {
  __deltemplate__ClayTable_Cell_miniumLink.soyTemplateName = 'MiniumTable.__deltemplate__ClayTable_Cell_miniumLink';
}
soy.$$registerDelegateFn(soy.$$getDelTemplateId('ClayTable.Cell.idom'), 'miniumLink', 0, __deltemplate__ClayTable_Cell_miniumLink);


/**
 * @param {__deltemplate__ClayTable_Cell_miniumStatus.Params} opt_data
 * @param {Object<string, *>=} opt_ijData
 * @param {Object<string, *>=} opt_ijData_deprecated
 * @return {void}
 * @suppress {checkTypes|uselessCode}
 */
var __deltemplate__ClayTable_Cell_miniumStatus = function(opt_data, opt_ijData, opt_ijData_deprecated) {
  opt_ijData = opt_ijData_deprecated || opt_ijData;
  /** @type {?} */
  var fieldSchema = opt_data.fieldSchema;
  /** @type {?} */
  var item = opt_data.item;
  /** @type {?} */
  var value = opt_data.value;
  /** @type {*|null|undefined} */
  var _handleCellContentClick = opt_data._handleCellContentClick;
  /** @type {!goog.soy.data.SanitizedContent|null|string|undefined} */
  var spritemap = soy.asserts.assertType(opt_data.spritemap == null || (goog.isString(opt_data.spritemap) || opt_data.spritemap instanceof goog.soy.data.SanitizedContent), 'spritemap', opt_data.spritemap, '!goog.soy.data.SanitizedContent|null|string|undefined');
  incrementalDom.elementOpenStart('td');
      incrementalDom.attr('class', 'cell');
  incrementalDom.elementOpenEnd();
  incrementalDom.elementOpenStart('div');
      incrementalDom.attr('class', 'commerce-dot commerce-dot--' + value.badge);
  incrementalDom.elementOpenEnd();
  soyIdom.print(value.label);
  incrementalDom.elementClose('div');
  incrementalDom.elementClose('td');
};
exports.__deltemplate__ClayTable_Cell_miniumStatus = __deltemplate__ClayTable_Cell_miniumStatus;
/**
 * @typedef {{
 *  fieldSchema: ?,
 *  item: ?,
 *  value: ?,
 *  _handleCellContentClick: (*|null|undefined),
 *  spritemap: (!goog.soy.data.SanitizedContent|null|string|undefined),
 * }}
 */
__deltemplate__ClayTable_Cell_miniumStatus.Params;
if (goog.DEBUG) {
  __deltemplate__ClayTable_Cell_miniumStatus.soyTemplateName = 'MiniumTable.__deltemplate__ClayTable_Cell_miniumStatus';
}
soy.$$registerDelegateFn(soy.$$getDelTemplateId('ClayTable.Cell.idom'), 'miniumStatus', 0, __deltemplate__ClayTable_Cell_miniumStatus);


/**
 * @param {__deltemplate__ClayTable_Cell_miniumActions.Params} opt_data
 * @param {Object<string, *>=} opt_ijData
 * @param {Object<string, *>=} opt_ijData_deprecated
 * @return {void}
 * @suppress {checkTypes|uselessCode}
 */
var __deltemplate__ClayTable_Cell_miniumActions = function(opt_data, opt_ijData, opt_ijData_deprecated) {
  opt_ijData = opt_ijData_deprecated || opt_ijData;
  /** @type {?} */
  var fieldSchema = opt_data.fieldSchema;
  /** @type {?} */
  var item = opt_data.item;
  /** @type {?} */
  var value = opt_data.value;
  /** @type {*|null|undefined} */
  var _handleCellContentClick = opt_data._handleCellContentClick;
  /** @type {!goog.soy.data.SanitizedContent|null|string|undefined} */
  var spritemap = soy.asserts.assertType(opt_data.spritemap == null || (goog.isString(opt_data.spritemap) || opt_data.spritemap instanceof goog.soy.data.SanitizedContent), 'spritemap', opt_data.spritemap, '!goog.soy.data.SanitizedContent|null|string|undefined');
  incrementalDom.elementOpenStart('td');
      incrementalDom.attr('class', 'cell');
  incrementalDom.elementOpenEnd();
  incrementalDom.elementOpenStart('div');
      incrementalDom.attr('class', 'toggle-wrapper');
  incrementalDom.elementOpenEnd();
  incrementalDom.elementOpenStart('div');
      incrementalDom.attr('class', 'toggle');
      incrementalDom.attr('data-onclick', 'handleToggle');
  incrementalDom.elementOpenEnd();
  incrementalDom.elementOpenStart('svg');
      incrementalDom.attr('viewBox', '0 0 4 16');
      incrementalDom.attr('xmlns', 'http://www.w3.org/2000/svg');
      incrementalDom.attr('class', 'commerce-icon toggle__open');
  incrementalDom.elementOpenEnd();
  incrementalDom.elementOpenStart('path');
      incrementalDom.attr('d', 'M0 2a2 2 0 1 1 4 0 2 2 0 0 1-4 0zm0 6a2 2 0 1 1 4 0 2 2 0 0 1-4 0zm0 6a2 2 0 1 1 4 0 2 2 0 0 1-4 0z');
      incrementalDom.attr('fill', 'currentColor');
      incrementalDom.attr('fill-rule', 'evenodd');
  incrementalDom.elementOpenEnd();
  incrementalDom.elementClose('path');
  incrementalDom.elementClose('svg');
  incrementalDom.elementOpenStart('svg');
      incrementalDom.attr('viewBox', '0 0 13 8');
      incrementalDom.attr('xmlns', 'http://www.w3.org/2000/svg');
      incrementalDom.attr('class', 'commerce-icon toggle__close');
  incrementalDom.elementOpenEnd();
  incrementalDom.elementOpenStart('path');
      incrementalDom.attr('d', 'M8.002 3V.986A.986.986 0 0 1 9.685.289l3.027 3.015a.986.986 0 0 1 0 1.394L9.685 7.711a.984.984 0 0 1-1.683-.697V5H1a1 1 0 0 1 0-2h7.002z');
      incrementalDom.attr('fill', 'currentColor');
      incrementalDom.attr('fill-rule', 'evenodd');
  incrementalDom.elementOpenEnd();
  incrementalDom.elementClose('path');
  incrementalDom.elementClose('svg');
  incrementalDom.elementClose('div');
  incrementalDom.elementOpenStart('div');
      incrementalDom.attr('class', 'actions');
  incrementalDom.elementOpenEnd();
  incrementalDom.elementOpenStart('div');
      incrementalDom.attr('class', 'close actions__close');
      incrementalDom.attr('data-onclick', 'handleToggle');
  incrementalDom.elementOpenEnd();
  incrementalDom.elementOpenStart('svg');
      incrementalDom.attr('viewBox', '0 0 13 8');
      incrementalDom.attr('xmlns', 'http://www.w3.org/2000/svg');
      incrementalDom.attr('class', 'commerce-icon');
  incrementalDom.elementOpenEnd();
  incrementalDom.elementOpenStart('path');
      incrementalDom.attr('d', 'M8.002 3V.986A.986.986 0 0 1 9.685.289l3.027 3.015a.986.986 0 0 1 0 1.394L9.685 7.711a.984.984 0 0 1-1.683-.697V5H1a1 1 0 0 1 0-2h7.002z');
      incrementalDom.attr('fill', 'currentColor');
      incrementalDom.attr('fill-rule', 'evenodd');
  incrementalDom.elementOpenEnd();
  incrementalDom.elementClose('path');
  incrementalDom.elementClose('svg');
  incrementalDom.elementClose('div');
  incrementalDom.elementOpenStart('a');
      incrementalDom.attr('href', item[fieldSchema.linkField]);
      incrementalDom.attr('class', 'actions__link');
  incrementalDom.elementOpenEnd();
  soyIdom.print(item[fieldSchema.linkLabelField]);
  incrementalDom.elementClose('a');
  incrementalDom.elementOpenStart('div');
      incrementalDom.attr('class', 'actions__buttons');
  incrementalDom.elementOpenEnd();
  var actionItem64List = item.actions;
  var actionItem64ListLen = actionItem64List.length;
  for (var actionItem64Index = 0; actionItem64Index < actionItem64ListLen; actionItem64Index++) {
    var actionItem64Data = actionItem64List[actionItem64Index];
    incrementalDom.elementOpenStart('a');
        var $tmp = actionItem64Data.type;
        incrementalDom.attr('class', 'commerce-button commerce-button--small commerce-button--' + ($tmp != null ? $tmp : 'outline'));
        incrementalDom.attr('href', actionItem64Data.href);
    incrementalDom.elementOpenEnd();
    soyIdom.print(actionItem64Data.label);
    incrementalDom.elementClose('a');
  }
  incrementalDom.elementClose('div');
  incrementalDom.elementOpenStart('a');
      incrementalDom.attr('href', 'delete');
  incrementalDom.elementOpenEnd();
  incrementalDom.text('delete');
  incrementalDom.elementClose('a');
  incrementalDom.elementClose('div');
  incrementalDom.elementClose('div');
  incrementalDom.elementClose('td');
};
exports.__deltemplate__ClayTable_Cell_miniumActions = __deltemplate__ClayTable_Cell_miniumActions;
/**
 * @typedef {{
 *  fieldSchema: ?,
 *  item: ?,
 *  value: ?,
 *  _handleCellContentClick: (*|null|undefined),
 *  spritemap: (!goog.soy.data.SanitizedContent|null|string|undefined),
 * }}
 */
__deltemplate__ClayTable_Cell_miniumActions.Params;
if (goog.DEBUG) {
  __deltemplate__ClayTable_Cell_miniumActions.soyTemplateName = 'MiniumTable.__deltemplate__ClayTable_Cell_miniumActions';
}
soy.$$registerDelegateFn(soy.$$getDelTemplateId('ClayTable.Cell.idom'), 'miniumActions', 0, __deltemplate__ClayTable_Cell_miniumActions);

templates = exports;
return exports;

});

export { templates };
export default templates;
/* jshint ignore:end */
