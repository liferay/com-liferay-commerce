import React from 'react';

export default class Upload extends React.Component {
	constructor(props) {
		super(props);

		this.state = {
			hightlight: false
		};

		this.fileInputRef = React.createRef();

		window.addEventListener('dragover', this.preventWindowDrop);
		window.addEventListener('drop', this.preventWindowDrop);
	}

	componentWillUnmount() {
		window.removeEventListener('dragover', this.preventWindowDrop);
		window.removeEventListener('drop', this.preventWindowDrop);
	}

	preventWindowDrop(e) {
		e = e || event;
		e.preventDefault();
	}

	onDragEnter(evt) {
		evt.preventDefault();
		this.setState({hightlight: true});
	}

	onDragLeave() {
		this.setState({hightlight: false});
	}

	onDrop(event) {
		event.preventDefault();

		this.uploadFiles(event.dataTransfer.files);
		this.setState({hightlight: false});
	}

	onFilesAdded(evt) {
		this.uploadFiles(evt.target.files);
	}

	uploadFiles(files) {
		console.log(files);
	}

	openFileDialog() {
		this.fileInputRef.current.click();
	}

	render() {
		return (
			<div
				className={`upload-image ${
					this.state.hightlight ? 'is-dragging' : ''
				}`}
				onDragEnter={e => this.onDragEnter(e)}
				onDragLeave={e => this.onDragLeave(e)}
				onDrop={e => this.onDrop(e)}
				onClick={() => this.openFileDialog()}
			>
				Upload
				<input
					ref={this.fileInputRef}
					className="file-input"
					type="file"
					multiple
					onChange={e => this.onFilesAdded(e)}
				/>
			</div>
		);
	}
}
