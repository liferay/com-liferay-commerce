import template from './ItemRecap.soy';
import Component from 'metal-component';
import Soy, {Config} from 'metal-soy';

import '../input_utils/CommerceInputText';

const checkObjectEquality = (firstObj, secondObj) => {
	const properties = Object.keys(firstObj);
	const comparisonResult = properties.reduce(
		(changed, key) => {
			return changed && firstObj[key] == secondObj[key]
		}, true
	)
	return comparisonResult;
}

class ItemRecap extends Component {

	syncItemData(){
		this._setInitialData();
	}

	_handleFormSubmit(e){
		console.log('submit', e);
		e.preventDefault();
		this._sendUpdates();
	}

	_handleSaveClick(e){
		e.preventDefault();
		this._sendUpdates();
	}

	_sendUpdates(){
		if(!this.dataChanged){
			return null
		}
		const updatedData = Object.assign(
			{},
			this.itemData,
			this.formData
		);
		return this.emit('productDataUpdated', updatedData);
	}
	
	_handleCancelClick(e){
		this._setInitialData()
	}

	_setInitialData(){
		this.formData = {
			deliveryDate: this.itemData.deliveryDate,
			note: this.itemData.note,
			price: this.itemData.price,
			discount: this.itemData.discount,
			giftQuantity: this.itemData.giftQuantity,
			quantity: this.itemData.quantity
		};
	}
	
    _handleTabChange(e){
        this.stage = e.target.dataset.element;
	}
	
    _handleFormUpdate(e){
		console.log(e);
		const key = e.target.name;
		this.formData = Object.assign(
			{},
			this.formData,
			{
				[key]: e.target.value
			}
		);
		this.dataChanged = !checkObjectEquality(this.formData, this.itemData);
		return this.formData;
	}

}

ItemRecap.STATE = {
	_confirmModalVisible: Config.bool().value(false),
	adminPrivileges: Config.bool().value(false),
	dataChanged: Config.bool().value(false),
	formData: Config.shapeOf(
		{
			discount: Config.string(),
			deliveryDate: Config.string(),
				giftQuantity: Config.oneOfType(
					[
						Config.number(),
						Config.string()
					]
				),
			note: Config.string(),
			price: Config.string(),
			quantity: Config.oneOfType(
				[
					Config.number(),
					Config.string()
				]
			)
		}
	),
    itemData: Config.shapeOf(
        {
            thumbnail: Config.string(),
			sku: Config.string().required(),
			name: Config.string().required(),
			note: Config.string().required(),
			description: Config.string(),
			deliveryDate: Config.string().required(),
			tierPricing: Config.array(
				Config.shapeOf(
					{
						quantity: Config.number(),
						price: Config.string()
					}
				)
			),
			price: Config.string(),
			discount: Config.string(),
			giftQuantity: Config.oneOfType(
				[
					Config.number(),
					Config.string()
				]
			),
			quantity: Config.oneOfType(
				[
					Config.number(),
					Config.string()
				]
			)
        }
	),
	stage: Config.string().oneOf(
		[
			'empty',
			'overview',
			'edit'
		]
	)
};

Soy.register(ItemRecap, template);

export {ItemRecap};
export default ItemRecap;