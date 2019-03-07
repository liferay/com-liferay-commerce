<template>
  <div id="minium" class="minium minium-frame (has-search)">
    <div class="minium-frame__sidebar">
      <minium-sidebar/>
    </div>

    <div class="minium-frame__topbar">
      <minium-topbar/>
    </div>

    <div class="minium-frame__content js-scroll-area">
      <a name="minium-top"></a>
      <div class="minium-content">
        <div class="container-fluid product-detail">
          <div class="row">
            <div class="col">
              <span class="commerce-typo commerce-typo__section-title">Selected vehicle:</span>
              <strong>BMW X3 sDrive 18i - 125KW/170HP - froam 2014</strong>
            </div>
          </div>
          <div class="row">
            <div class="col-6" id="minium-product-gallery"></div>
            <article class="col-6">
              <header class="minium-product-header">
                <div class="commerce-dot commerce-dot--good">In Stock</div>
                <h3 class="minium-product-header__tagline">AR385672</h3>
                <h2
                  class="minium-product-header__title"
                >Throttle Body Integer euismod tristique posuere</h2>
                <h4 class="minium-product-header__subtitle">Engine</h4>
              </header>
              <p>Nam vestibulum ex eget purus posuere suscipit. Donec interdum egestas felis rutrum condimentum. Phasellus varius suscipit ornare. Nam vestibulum ex eget purus posuere suscipit. Donec interdum egestas felis rutrum condimentum. Phasellus varius suscipit ornare.</p>
              <div id="test-table-mini"></div>
              <a class="commerce-link" href="#">View More</a>
              <div class="product-detail__actions">
                <button class="commerce-button">Add to order</button>
              </div>
            </article>
          </div>
          <div class="row">
            <div class="col">
              <div class="commerce-panel">
                <div class="commerce-panel__tabs">
                  <minium-tabs></minium-tabs>
                </div>
              </div>
            </div>
          </div>
          <div class="row">
            <div class="col">
              <div class="commerce-panel">
                <div class="commerce-panel__title">Technical Information</div>
                <div class="commerce-panel__content">
                  <ul>
                    <li>Ø: 64 mm</li>
                    <li>Operating Mode: EL</li>
                    <li>Supplementary Article / Supplementary Info: 35</li>
                    <li>Voltage: 12 V</li>
                  </ul>
                </div>
              </div>
            </div>
          </div>
          <div class="row">
            <div class="col">
              <div class="commerce-panel">
                <div class="commerce-panel__title">Technical Information</div>
                <div class="commerce-panel__content">
                  <p>Lorem ipsum dolor sit amet consectetur adipisicing elit. Itaque excepturi sapiente totam, voluptatem reprehenderit vitae illum, porro est, distinctio numquam quas voluptas modi consequatur obcaecati molestias expedita vel odit impedit magnam tempora cumque. Repudiandae non similique porro, mollitia molestias adipisci, ullam, modi eligendi quae quo sed laboriosam saepe sequi cumque repellat autem corporis. Ab ipsa officiis, corporis qui doloremque earum consequuntur dicta, nam cupiditate necessitatibus repellat, quibusdam quaerat quae consequatur ullam. Vero, tempora animi? Sed temporibus esse qui accusantium explicabo. Tempora eum blanditiis deserunt perspiciatis provident fugiat deleniti nesciunt earum iste impedit quasi architecto fugit, dolorum sed aperiam? Dicta, qui.</p>
                </div>
              </div>
            </div>
          </div>
          <div class="row">
            <div class="col">
              <div id="test-table"></div>
            </div>
          </div>
          <div class="row">
            <div class="col">
              <div class="commerce-typo commerce-typo__section-title">Related products</div>
            </div>
          </div>
          <div class="row">
            <div class="col">Product Card</div>
            <div class="col">Product Card</div>
            <div class="col">Product Card</div>
            <div class="col">Product Card</div>
          </div>
        </div>

        <!-- <div
          style="height: 200px; background: white; padding-top: 40px; text-align: center; border-radius: 8px; color: #9D9E9E; font-size: 11px; text-transform: uppercase; letter-spacing: 1.1px;"
        >👉 Page content HERE! 👈</div>-->
        <br>
        <br>
        <div style="display: inline-grid; grid-template-columns: repeat(4,auto); grid-gap: 20px">
          <div id="qsbtn1"></div>
          <div id="qsbtn3"></div>
          <div id="qsbtn4"></div>
          <div id="qsbtn2"></div>
        </div>
        <br>
        <br>
        <details-header></details-header>
        <br>
        <br>
        <div id="test-table"></div>
      </div>


    </div>

    <div class="minium-frame__toolbar (is-visible)"></div>

    <div class="minium-frame__cta is-visible">
      <commerce-button big outline>Cancel</commerce-button>
      <commerce-button big>Done</commerce-button>
    </div>

    <div class="minium-frame__dock (is-visible)">
      Products
      <a href="#" class="commerce-button">Compare</a>
    </div>

    <div class="minium-frame__overlay (is-visible)" id="commerce-search-results">
      <!-- <commerce-suggestions/> -->
    </div>

    <div class="minium-frame__tray">
      <minium-toast>Toast message message
        <br>
        <a href="#" class="commerce-link">Go to link</a>
      </minium-toast>
    </div>

    <div class="minium-frame__modal (is-visible)">
      <div class="minium-modal">
        <a href="#" class="minium-modal__close">
          <commerce-icon/>
        </a>
        <div class="minium-modal__content">
          <div class="minium-modal__title">Modal title</div>
          <div
            style="color: #9D9E9E; font-size: 11px; text-transform: uppercase; letter-spacing: 1.1px;"
          >👉 Modal content HERE! 👈</div>
        </div>
      </div>
    </div>
  </div>
</template>

<script>
import MiniumButton from "./components/MiniumButton";
import MiniumIcon from "./components/MiniumIcon";
import MiniumSidebar from "./components/MiniumSidebar";
import MiniumSuggestions from "./components/MiniumSuggestions";
import MiniumTabs from "./components/MiniumTabs";
import MiniumToast from "./components/MiniumToast";
import MiniumTopbar from "./components/MiniumTopbar";
import DetailsHeader from "./components/DetailsHeader";

export default {
  components: {
    MiniumButton,
    MiniumIcon,
    MiniumSidebar,
    MiniumSuggestions,
    MiniumTabs,
    MiniumToast,
    MiniumTopbar,
    DetailsHeader
  }
};
</script>
