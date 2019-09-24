import React from 'react';
// import './images.scss';

export default class Images extends React.Component {
	render() {
		return (
			<div className={`commerce-gallery`}>
				{this.props.images.map(img => (
					<img
						src={img.url}
						key={img.url}
						alt={img.url}
						onMouseEnter={() => this.props.onMouseEnter(img.url)}
						onMouseLeave={() => this.props.onMouseLeave(img.url)}
						onClick={() =>
							this.props.onClick([
								{
									url: `/iframe.html?${Math.random()}`,
									pageName: 'Edit',
									slug: 'edit'
								}
								// {
								// 	url: `/api/fake-img/${encodeURIComponent(img.url)}`,
								// 	pageName: 'Details',
								// 	slug: 'details'
								// },
							])
						}
					/>
				))}
			</div>
		);
	}
}
