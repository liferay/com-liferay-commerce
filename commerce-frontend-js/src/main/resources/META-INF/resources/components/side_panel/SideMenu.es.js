import React from 'react';
import ClayIcon from '@clayui/icon';
import ClayLink from '@clayui/link';
import classNames from 'classnames';
import ClayButton from '@clayui/button/lib/Button';

export default function SideMenu(props) {
	return (
		<ul className="nav side-menu bg-dark" role="tablist">
			{props.items.map(item => (
				<li className="nav-item" key={item.slug}>
					<ClayButton
						className={
							classNames(
								"mx-3 my-2 btn-link",
								props.active === item.slug && 'active'
							)
						}
						onClick={(e) => {
							e.preventDefault();
							props.open(item.href, item.slug)
						}}
						displayType="unstyled"
						monospaced
					>
						<ClayIcon symbol={item.icon} />
					</ClayButton>
					{/* <ClayLink 
						className={
							classNames(
								"mx-3 my-2",
								props.active === item.slug && 'active'
							)
						}
						displayType="primary"
						href="#"
						monospaced
						onClick={(e) => {
							e.preventDefault();
							props.open(item.href, item.slug)
						}}
						outline
					>
						<ClayIcon symbol={item.icon} />
					</ClayLink> */}
				</li>
			))}
		</ul>
	);
}
