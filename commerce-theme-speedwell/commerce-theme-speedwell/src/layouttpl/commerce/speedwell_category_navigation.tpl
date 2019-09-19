<div class="commerce-1-1-2-columns category-page commerce-layouttpl"
     id="main-content"
     role="main">

    <div class="portlet-layout category-page__head">
        <div class="portlet-layout row">
            <div class="portlet-column portlet-column-only col-12" id="column-1">
                ${processor.processColumn("column-1", "portlet-column-content portlet-column-content-only")}
            </div>
        </div>
    </div>

    <div class="portlet-layout container">
        <div class="portlet-layout row">
            <div class="portlet-column portlet-column-only col-12" id="column-2">
                ${processor.processColumn("column-2", "portlet-column-content portlet-column-content-only")}
            </div>
        </div>
    </div>

    <div class="portlet-layout container">
        <div class="portlet-layout row">
            <div class="portlet-column portlet-column-first col-md-3 hidden-sm" id="column-3">
                ${processor.processColumn("column-3", "portlet-column-content portlet-column-content-first")}
            </div>

            <div class="portlet-column portlet-column-last col-md-9 col-sm-12" id="column-4">
                ${processor.processColumn("column-4", "portlet-column-content portlet-column-content-last")}
            </div>
        </div>
    </div>
</div>
