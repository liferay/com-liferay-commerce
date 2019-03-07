/* jshint ignore:start */
import Component from 'metal-component';
import Soy from 'metal-soy';

var templates;
goog.loadModule(function(exports) {
var soy = goog.require('soy');
var soydata = goog.require('soydata');
// This file was automatically generated from MiniumProductGallery.soy.
// Please don't edit this file by hand.

/**
 * @fileoverview Templates in namespace MiniumProductGallery.
 * @public
 */

goog.module('MiniumProductGallery.incrementaldom');

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
  /** @type {boolean} */
  var fullscreen = soy.asserts.assertType(goog.isBoolean(opt_data.fullscreen) || opt_data.fullscreen === 1 || opt_data.fullscreen === 0, 'fullscreen', opt_data.fullscreen, 'boolean');
  /** @type {?} */
  var images = opt_data.images;
  /** @type {boolean|null|undefined} */
  var loading = soy.asserts.assertType(opt_data.loading == null || (goog.isBoolean(opt_data.loading) || opt_data.loading === 1 || opt_data.loading === 0), 'loading', opt_data.loading, 'boolean|null|undefined');
  /** @type {number} */
  var selected = soy.asserts.assertType(goog.isNumber(opt_data.selected), 'selected', opt_data.selected, 'number');
  incrementalDom.elementOpenStart('div');
      incrementalDom.attr('class', 'minium-product-gallery');
  incrementalDom.elementOpenEnd();
  incrementalDom.elementOpenStart('figure', images[selected].preview);
      incrementalDom.attr('class', 'minium-product-gallery__main ' + (loading ? 'is-loading' : ''));
      incrementalDom.attr('data-onclick', 'openFullscreen');
      incrementalDom.attr('key', images[selected].preview);
  incrementalDom.elementOpenEnd();
  incrementalDom.elementOpenStart('img');
      incrementalDom.attr('src', images[selected].preview);
      incrementalDom.attr('alt', images[selected].description);
  incrementalDom.elementOpenEnd();
  incrementalDom.elementClose('img');
  incrementalDom.elementClose('figure');
  if (fullscreen) {
    incrementalDom.elementOpenStart('div');
        incrementalDom.attr('class', 'minium-product-gallery__fullscreen');
        incrementalDom.attr('data-onclick', 'closeFullscreen');
    incrementalDom.elementOpenEnd();
    incrementalDom.elementOpenStart('img');
        incrementalDom.attr('src', images[selected].full);
        incrementalDom.attr('alt', images[selected].description);
    incrementalDom.elementOpenEnd();
    incrementalDom.elementClose('img');
    incrementalDom.elementOpenStart('div');
        incrementalDom.attr('class', 'minium-product-gallery__prev minium-product-gallery__prev--big');
        incrementalDom.attr('data-onclick', 'goToPrev');
    incrementalDom.elementOpenEnd();
    incrementalDom.elementClose('div');
    incrementalDom.elementOpenStart('div');
        incrementalDom.attr('class', 'minium-product-gallery__next minium-product-gallery__next--big');
        incrementalDom.attr('data-onclick', 'goToNext');
    incrementalDom.elementOpenEnd();
    incrementalDom.elementClose('div');
    incrementalDom.elementClose('div');
  }
  incrementalDom.elementOpenStart('div');
      incrementalDom.attr('class', 'minium-product-gallery__thumbs');
  incrementalDom.elementOpenEnd();
  var image27List = images;
  var image27ListLen = image27List.length;
  for (var image27Index = 0; image27Index < image27ListLen; image27Index++) {
    var image27Data = image27List[image27Index];
    incrementalDom.elementOpenStart('div');
        incrementalDom.attr('class', 'minium-product-gallery__thumb ' + (image27Index == selected ? 'is-active' : ''));
        incrementalDom.attr('data-index', image27Index);
        incrementalDom.attr('data-onclick', 'handleThumbClick');
    incrementalDom.elementOpenEnd();
    incrementalDom.elementOpenStart('img');
        incrementalDom.attr('src', image27Data.thumb);
        incrementalDom.attr('alt', image27Data.description);
    incrementalDom.elementOpenEnd();
    incrementalDom.elementClose('img');
    incrementalDom.elementClose('div');
  }
  incrementalDom.elementClose('div');
  incrementalDom.elementClose('div');
};
exports.render = $render;
/**
 * @typedef {{
 *  fullscreen: boolean,
 *  images: ?,
 *  loading: (boolean|null|undefined),
 *  selected: number,
 * }}
 */
$render.Params;
if (goog.DEBUG) {
  $render.soyTemplateName = 'MiniumProductGallery.render';
}

exports.render.params = ["fullscreen","images","loading","selected"];
exports.render.types = {"fullscreen":"bool ","images":"? ","loading":"bool ","selected":"int "};
templates = exports;
return exports;

});

class MiniumProductGallery extends Component {}
Soy.register(MiniumProductGallery, templates);
export { MiniumProductGallery, templates };
export default templates;
/* jshint ignore:end */
