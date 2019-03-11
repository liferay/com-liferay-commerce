import template from './ItemOverview.soy';
import Component from 'metal-component';
import Soy, {Config} from 'metal-soy';

import '../input_utils/CommerceInputText.es';
import '../accordion_panel/AccordionPanel.es';

const checkObjectEquality = (firstObj, secondObj) => {
	const properties = Object.keys(firstObj);
	const comparisonResult = properties.reduce(
		(changed, key) => {
			return changed && firstObj[key] == secondObj[key]
		}, true
	)
	return comparisonResult;
}

class ItemOverview extends Component {

	syncDataChanged(){
		this.emit('handleChanges', this.dataChanged);
	}

	syncItemData(){
		return this.itemData && this._setInitialData();
	}

	_handleCloseModal(){
		console.log('close modal');
	}

	_handleFormSubmit(e){
		console.log('submit', e);
		e.preventDefault();
		this._submitUpdates();
	}

	_handleSaveClick(e){
		e.preventDefault();
		this._submitUpdates();
	}

	_submitUpdates(){
		if(!this.dataChanged){
			return null
		}
		const updatedData = Object.assign(
			{},
			this.itemData,
			this.formData
		);
		return this.emit('submitChanges', updatedData);
	}
	
	_handleCancelClick(e){
		this._setInitialData()
	}
	
	_handleDescriptionStatusChange(newState){
		this._descriptionVisibilityStatus = newState;
	}
	
	_handleTierPricingStatusChange(newState){
		this._tierPricingVisibilityStatus = newState;
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

ItemOverview.STATE = {
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
	_descriptionVisibilityStatus: Config.oneOf(
		[
			'closed',
			'closing',
			'opened',
			'opening'
		]
	)
		.value('closed')
		.internal(),
	_tierPricingVisibilityStatus: Config.oneOf(
		[
			'closed',
			'closing',
			'opened',
			'opening'
		]
	)
		.value('closed')
		.internal(),
	stage: Config.string().oneOf(
		[
			'overview',
			'edit'
		]
	).value('overview')
};

Soy.register(ItemOverview, template);

export {ItemOverview};
export default ItemOverview;