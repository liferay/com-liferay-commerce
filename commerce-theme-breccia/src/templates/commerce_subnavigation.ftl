<div class="commerce-subnavigation commerce-subnavigation-dark navbar navbar-collapse-absolute navbar-expand-md navigation-bar">
	<div class="container-fluid container-fluid-max-xl">
		<a class="navbar-brand navbar-breakpoint-d-none" href="tel:+1-888-888-8888">
			<span class="inline-item inline-item-before"><svg aria-hidden="true" class="lexicon-icon lexicon-icon-phone" focusable="false"><use xlink:href="${images_folder}/lexicon/icons.svg#phone" /></svg></span><span class="navbar-text-truncate">1.888.888.8888</span>
		</a>

		<a aria-controls="commerceSubnavigation" aria-expanded="false" aria-label="Toggle Subnavigation" class="collapsed navbar-toggler navbar-toggler-link" data-toggle="collapse" href="#commerceSubnavigation" role="button">
			<svg aria-hidden="true" class="lexicon-icon lexicon-icon-bars" focusable="false"><use xlink:href="${images_folder}/lexicon/icons.svg#bars" /></svg>
		</a>

		<div class="collapse navbar-collapse" id="commerceSubnavigation">
			<div class="container-fluid container-fluid-max-xl">
				<ul class="navbar-nav navbar-nav-expand">
					<#if company_phone_number?has_content>
						<li class="nav-item nav-item-expand navbar-breakpoint-down-d-none">
							<a class="navbar-brand" href="tel:${company_phone_number}">
								<span class="inline-item inline-item-before"><svg aria-hidden="true" class="lexicon-icon lexicon-icon-phone" focusable="false"><use xlink:href="${images_folder}/lexicon/icons.svg#phone" /></svg></span><span class="navbar-text-truncate">${company_phone_number}</span>
							</a>
						</li>
					</#if>

					<#if header_column_1?has_content>
						<li class="nav-item">
							<a class="nav-link" href="#1">
								<span class="inline-item inline-item-before"><svg aria-hidden="true" class="lexicon-icon lexicon-icon-geolocation" focusable="false"><use xlink:href="${images_folder}/lexicon/icons.svg#globe" /></svg></span><span class="navbar-text-truncate">${header_column_1}</span>
							</a>
						</li>
					</#if>

					<#if header_column_2?has_content>
						<li class="nav-item">
							<a class="nav-link" href="#1">
								<span class="inline-item inline-item-before"><svg aria-hidden="true" class="lexicon-icon lexicon-icon-time" focusable="false"><use xlink:href="${images_folder}/lexicon/icons.svg#time" /></svg></span><span class="navbar-text-truncate">${header_column_2}</span>
							</a>
						</li>
					</#if>

					<#if header_column_3?has_content>
						<li class="nav-item">
							<a class="nav-link" href="#1">
								<span class="inline-item inline-item-before"><svg aria-hidden="true" class="lexicon-icon lexicon-icon-sun" focusable="false"><use xlink:href="${images_folder}/lexicon/icons.svg#sun" /></svg></span><span class="navbar-text-truncate">${header_column_3}</span>
							</a>
						</li>
					</#if>
				</ul>
			</div>
		</div>
	</div>
</div>