import React from 'react';
import ClayIcon from '@clayui/icon';
import ClayLink from '@clayui/link';
import ClayList from '@clayui/list';
import ClaySticker from '@clayui/sticker';

import components from '../index';

function getCurrentPage() {
	return window.location.hash && components.find(c => c.folder === window.location.hash.slice(1)).page;
}

function Example({spritemap}) {
	const [current, setComponent] = React.useState(getCurrentPage());
	
	return (
		<div className="container-fluid">
			<div className="row">
				<div className="col-3">
					<ClayList>
						<ClayList.Header>Components</ClayList.Header>

						{components.filter(c => c.page).map(component => (
							<ClayList.Item flex key={component.folder}>
								<ClayList.ItemField>
									<ClaySticker displayType="light" size="sm">
										<ClayIcon spritemap={spritemap} symbol="caret-right" />
									</ClaySticker>
								</ClayList.ItemField>
								<ClayList.ItemField>
									<ClayLink href={`#${component.folder}`} onClick={() => setComponent(component.page)}>
										{component.name}
									</ClayLink>
								</ClayList.ItemField>
							</ClayList.Item>
						))}
					</ClayList>
				</div>
				<div className="col-9">
					{current && <iframe src={current} frameBorder="0"></iframe>}
				</div>
			</div>
		</div>
	);
}

export default Example;
