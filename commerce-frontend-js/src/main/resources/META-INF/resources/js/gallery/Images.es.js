import React from 'react';
// import './images.scss';

export default class Images extends React.Component {
	render() {
		return (
			<div className={`commerce-gallery`}>
				{this.props.images.map(img => (
					<img
						alt={img.url}
						key={img.url}
						onClick={() =>
							this.props.onClick([
								{
									pageName: 'Edit',
									slug: 'edit',
									url: `/iframe.html?${Math.random()}`
								}
								// {
								// 	url: `/api/fake-img/${encodeURIComponent(img.url)}`,
								// 	pageName: 'Details',
								// 	slug: 'details'
								// },
							])
						}
						onMouseEnter={() => this.props.onMouseEnter(img.url)}
						onMouseLeave={() => this.props.onMouseLeave(img.url)}
						src={img.url}
					/>
				))}
			</div>
		);
	}
}
