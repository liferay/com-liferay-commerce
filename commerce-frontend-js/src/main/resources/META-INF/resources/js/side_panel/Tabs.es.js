import React from 'react';

export default function Tabs(props) {
	return (
		<ul className="nav nav-underline" role="tablist">
			{props.tabs.map(tab => (
				<li className="nav-item" key={tab.url}>
					<a
						aria-controls="navUnderlineFields"
						aria-expanded="true"
						className={`nav-link ${tab.url === props.current &&
							'active'}`}
						data-toggle="tab"
						id="navUnderlineFieldsTab"
						onClick={() => props.onChange(tab.url)}
						role="tab"
					>
						{tab.pageName}
					</a>
				</li>
			))}
		</ul>
	);
}
