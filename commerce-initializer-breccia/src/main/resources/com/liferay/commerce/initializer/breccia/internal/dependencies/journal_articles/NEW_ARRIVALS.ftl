<style type="text/css">
	.newestDescription{
		font-size:20px;
		font-weight:400;
		margin:30px 100px 30px 100px;
	}

	.newestImage{
		text-align:center;
	}

	.newestTitle{
		font-size:26px;
		font-weight:400;
		margin:30px 30px 30px 30px;
		text-align:center;
	}
</style>

<div class="newestTitle">
	${TitleNewest.getData()}
</div>

<div class="newestImage">
	<#if ImageNewest.getData()?? && ImageNewest.getData() != "">
		<img alt="${ImageNewest.getAttribute("alt")}" data-fileentryid="${ImageNewest.getAttribute("fileEntryId")}" src="${ImageNewest.getData()}" />
	</#if>
</div>

<div class="newestDescription">
	${HTMLNewest.getData()}
</div>