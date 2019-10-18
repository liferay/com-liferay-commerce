import React from 'react';

function EmptyResultMessage() {
	return (
		<div className="sheet taglib-empty-result-message border-0 pt-0">
			<div className="taglib-empty-result-message-header"></div>
			<div className="sheet-text text-center">
				{Liferay.Language.get('no-order-items-were-found.')}
			</div>
		</div>
	);
}

export default EmptyResultMessage;
