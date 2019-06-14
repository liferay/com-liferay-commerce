<#if BumperImage.getData()?? && BumperImage.getData() != "">
<div class="bumper--container" style="background-image: url('${BumperImage.getData()}')">
<div class="bumper--text">
<span class="subtitle">
				${SecondaryGreetings.getData()}
			</span>
<span class="title">
				${Greetings.getData()}
			</span>
<span class="call-to-action">
				<button><a href="">${LinkToPage8ilp.ButtonLabel.getData()}</a></button>
			</span>
			<#if ProTitle.getSiblings()?has_content>
<div class="bumper--pros-list">
					<table>
						<tr>
							<#list ProTitle.getSiblings() as cur_ProTitle>
<td class="pro-element">
<div class="pro-element--wrapper">
										<div
											class="pro-element--icon"
											 style="background-image: url('${cur_ProTitle.ProIcon.getData()}')">
										</div>

<div class="pro-element--text">
											<h4>${cur_ProTitle.getData()}</h4>

											<p>${cur_ProTitle.ProDescription.getData()}</p>
										</div>
									</div>
								</td>
							</#list>
						</tr>
					</table>
				</div>
			</#if>
		</div>
	</div>
</#if>