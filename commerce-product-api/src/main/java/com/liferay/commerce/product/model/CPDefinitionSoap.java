/**
 * Copyright (c) 2000-present Liferay, Inc. All rights reserved.
 *
 * This library is free software; you can redistribute it and/or modify it under
 * the terms of the GNU Lesser General Public License as published by the Free
 * Software Foundation; either version 2.1 of the License, or (at your option)
 * any later version.
 *
 * This library is distributed in the hope that it will be useful, but WITHOUT
 * ANY WARRANTY; without even the implied warranty of MERCHANTABILITY or FITNESS
 * FOR A PARTICULAR PURPOSE. See the GNU Lesser General Public License for more
 * details.
 */

package com.liferay.commerce.product.model;

import java.io.Serializable;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * This class is used by SOAP remote services, specifically {@link com.liferay.commerce.product.service.http.CPDefinitionServiceSoap}.
 *
 * @author Marco Leo
 * @generated
 */
public class CPDefinitionSoap implements Serializable {

	public static CPDefinitionSoap toSoapModel(CPDefinition model) {
		CPDefinitionSoap soapModel = new CPDefinitionSoap();

		soapModel.setUuid(model.getUuid());
		soapModel.setDefaultLanguageId(model.getDefaultLanguageId());
		soapModel.setCPDefinitionId(model.getCPDefinitionId());
		soapModel.setGroupId(model.getGroupId());
		soapModel.setCompanyId(model.getCompanyId());
		soapModel.setUserId(model.getUserId());
		soapModel.setUserName(model.getUserName());
		soapModel.setCreateDate(model.getCreateDate());
		soapModel.setModifiedDate(model.getModifiedDate());
		soapModel.setCProductId(model.getCProductId());
		soapModel.setCPTaxCategoryId(model.getCPTaxCategoryId());
		soapModel.setProductTypeName(model.getProductTypeName());
		soapModel.setAvailableIndividually(model.isAvailableIndividually());
		soapModel.setIgnoreSKUCombinations(model.isIgnoreSKUCombinations());
		soapModel.setShippable(model.isShippable());
		soapModel.setFreeShipping(model.isFreeShipping());
		soapModel.setShipSeparately(model.isShipSeparately());
		soapModel.setShippingExtraPrice(model.getShippingExtraPrice());
		soapModel.setWidth(model.getWidth());
		soapModel.setHeight(model.getHeight());
		soapModel.setDepth(model.getDepth());
		soapModel.setWeight(model.getWeight());
		soapModel.setTaxExempt(model.isTaxExempt());
		soapModel.setTelcoOrElectronics(model.isTelcoOrElectronics());
		soapModel.setDDMStructureKey(model.getDDMStructureKey());
		soapModel.setPublished(model.isPublished());
		soapModel.setDisplayDate(model.getDisplayDate());
		soapModel.setExpirationDate(model.getExpirationDate());
		soapModel.setLastPublishDate(model.getLastPublishDate());
		soapModel.setSubscriptionEnabled(model.isSubscriptionEnabled());
		soapModel.setSubscriptionLength(model.getSubscriptionLength());
		soapModel.setSubscriptionType(model.getSubscriptionType());
		soapModel.setSubscriptionTypeSettings(
			model.getSubscriptionTypeSettings());
		soapModel.setMaxSubscriptionCycles(model.getMaxSubscriptionCycles());
		soapModel.setAccountGroupFilterEnabled(
			model.isAccountGroupFilterEnabled());
		soapModel.setChannelFilterEnabled(model.isChannelFilterEnabled());
		soapModel.setVersion(model.getVersion());
		soapModel.setStatus(model.getStatus());
		soapModel.setStatusByUserId(model.getStatusByUserId());
		soapModel.setStatusByUserName(model.getStatusByUserName());
		soapModel.setStatusDate(model.getStatusDate());

		return soapModel;
	}

	public static CPDefinitionSoap[] toSoapModels(CPDefinition[] models) {
		CPDefinitionSoap[] soapModels = new CPDefinitionSoap[models.length];

		for (int i = 0; i < models.length; i++) {
			soapModels[i] = toSoapModel(models[i]);
		}

		return soapModels;
	}

	public static CPDefinitionSoap[][] toSoapModels(CPDefinition[][] models) {
		CPDefinitionSoap[][] soapModels = null;

		if (models.length > 0) {
			soapModels = new CPDefinitionSoap[models.length][models[0].length];
		}
		else {
			soapModels = new CPDefinitionSoap[0][0];
		}

		for (int i = 0; i < models.length; i++) {
			soapModels[i] = toSoapModels(models[i]);
		}

		return soapModels;
	}

	public static CPDefinitionSoap[] toSoapModels(List<CPDefinition> models) {
		List<CPDefinitionSoap> soapModels = new ArrayList<CPDefinitionSoap>(
			models.size());

		for (CPDefinition model : models) {
			soapModels.add(toSoapModel(model));
		}

		return soapModels.toArray(new CPDefinitionSoap[soapModels.size()]);
	}

	public CPDefinitionSoap() {
	}

	public long getPrimaryKey() {
		return _CPDefinitionId;
	}

	public void setPrimaryKey(long pk) {
		setCPDefinitionId(pk);
	}

	public String getUuid() {
		return _uuid;
	}

	public void setUuid(String uuid) {
		_uuid = uuid;
	}

	public String getDefaultLanguageId() {
		return _defaultLanguageId;
	}

	public void setDefaultLanguageId(String defaultLanguageId) {
		_defaultLanguageId = defaultLanguageId;
	}

	public long getCPDefinitionId() {
		return _CPDefinitionId;
	}

	public void setCPDefinitionId(long CPDefinitionId) {
		_CPDefinitionId = CPDefinitionId;
	}

	public long getGroupId() {
		return _groupId;
	}

	public void setGroupId(long groupId) {
		_groupId = groupId;
	}

	public long getCompanyId() {
		return _companyId;
	}

	public void setCompanyId(long companyId) {
		_companyId = companyId;
	}

	public long getUserId() {
		return _userId;
	}

	public void setUserId(long userId) {
		_userId = userId;
	}

	public String getUserName() {
		return _userName;
	}

	public void setUserName(String userName) {
		_userName = userName;
	}

	public Date getCreateDate() {
		return _createDate;
	}

	public void setCreateDate(Date createDate) {
		_createDate = createDate;
	}

	public Date getModifiedDate() {
		return _modifiedDate;
	}

	public void setModifiedDate(Date modifiedDate) {
		_modifiedDate = modifiedDate;
	}

	public long getCProductId() {
		return _CProductId;
	}

	public void setCProductId(long CProductId) {
		_CProductId = CProductId;
	}

	public long getCPTaxCategoryId() {
		return _CPTaxCategoryId;
	}

	public void setCPTaxCategoryId(long CPTaxCategoryId) {
		_CPTaxCategoryId = CPTaxCategoryId;
	}

	public String getProductTypeName() {
		return _productTypeName;
	}

	public void setProductTypeName(String productTypeName) {
		_productTypeName = productTypeName;
	}

	public boolean getAvailableIndividually() {
		return _availableIndividually;
	}

	public boolean isAvailableIndividually() {
		return _availableIndividually;
	}

	public void setAvailableIndividually(boolean availableIndividually) {
		_availableIndividually = availableIndividually;
	}

	public boolean getIgnoreSKUCombinations() {
		return _ignoreSKUCombinations;
	}

	public boolean isIgnoreSKUCombinations() {
		return _ignoreSKUCombinations;
	}

	public void setIgnoreSKUCombinations(boolean ignoreSKUCombinations) {
		_ignoreSKUCombinations = ignoreSKUCombinations;
	}

	public boolean getShippable() {
		return _shippable;
	}

	public boolean isShippable() {
		return _shippable;
	}

	public void setShippable(boolean shippable) {
		_shippable = shippable;
	}

	public boolean getFreeShipping() {
		return _freeShipping;
	}

	public boolean isFreeShipping() {
		return _freeShipping;
	}

	public void setFreeShipping(boolean freeShipping) {
		_freeShipping = freeShipping;
	}

	public boolean getShipSeparately() {
		return _shipSeparately;
	}

	public boolean isShipSeparately() {
		return _shipSeparately;
	}

	public void setShipSeparately(boolean shipSeparately) {
		_shipSeparately = shipSeparately;
	}

	public double getShippingExtraPrice() {
		return _shippingExtraPrice;
	}

	public void setShippingExtraPrice(double shippingExtraPrice) {
		_shippingExtraPrice = shippingExtraPrice;
	}

	public double getWidth() {
		return _width;
	}

	public void setWidth(double width) {
		_width = width;
	}

	public double getHeight() {
		return _height;
	}

	public void setHeight(double height) {
		_height = height;
	}

	public double getDepth() {
		return _depth;
	}

	public void setDepth(double depth) {
		_depth = depth;
	}

	public double getWeight() {
		return _weight;
	}

	public void setWeight(double weight) {
		_weight = weight;
	}

	public boolean getTaxExempt() {
		return _taxExempt;
	}

	public boolean isTaxExempt() {
		return _taxExempt;
	}

	public void setTaxExempt(boolean taxExempt) {
		_taxExempt = taxExempt;
	}

	public boolean getTelcoOrElectronics() {
		return _telcoOrElectronics;
	}

	public boolean isTelcoOrElectronics() {
		return _telcoOrElectronics;
	}

	public void setTelcoOrElectronics(boolean telcoOrElectronics) {
		_telcoOrElectronics = telcoOrElectronics;
	}

	public String getDDMStructureKey() {
		return _DDMStructureKey;
	}

	public void setDDMStructureKey(String DDMStructureKey) {
		_DDMStructureKey = DDMStructureKey;
	}

	public boolean getPublished() {
		return _published;
	}

	public boolean isPublished() {
		return _published;
	}

	public void setPublished(boolean published) {
		_published = published;
	}

	public Date getDisplayDate() {
		return _displayDate;
	}

	public void setDisplayDate(Date displayDate) {
		_displayDate = displayDate;
	}

	public Date getExpirationDate() {
		return _expirationDate;
	}

	public void setExpirationDate(Date expirationDate) {
		_expirationDate = expirationDate;
	}

	public Date getLastPublishDate() {
		return _lastPublishDate;
	}

	public void setLastPublishDate(Date lastPublishDate) {
		_lastPublishDate = lastPublishDate;
	}

	public boolean getSubscriptionEnabled() {
		return _subscriptionEnabled;
	}

	public boolean isSubscriptionEnabled() {
		return _subscriptionEnabled;
	}

	public void setSubscriptionEnabled(boolean subscriptionEnabled) {
		_subscriptionEnabled = subscriptionEnabled;
	}

	public int getSubscriptionLength() {
		return _subscriptionLength;
	}

	public void setSubscriptionLength(int subscriptionLength) {
		_subscriptionLength = subscriptionLength;
	}

	public String getSubscriptionType() {
		return _subscriptionType;
	}

	public void setSubscriptionType(String subscriptionType) {
		_subscriptionType = subscriptionType;
	}

	public String getSubscriptionTypeSettings() {
		return _subscriptionTypeSettings;
	}

	public void setSubscriptionTypeSettings(String subscriptionTypeSettings) {
		_subscriptionTypeSettings = subscriptionTypeSettings;
	}

	public long getMaxSubscriptionCycles() {
		return _maxSubscriptionCycles;
	}

	public void setMaxSubscriptionCycles(long maxSubscriptionCycles) {
		_maxSubscriptionCycles = maxSubscriptionCycles;
	}

	public boolean getAccountGroupFilterEnabled() {
		return _accountGroupFilterEnabled;
	}

	public boolean isAccountGroupFilterEnabled() {
		return _accountGroupFilterEnabled;
	}

	public void setAccountGroupFilterEnabled(
		boolean accountGroupFilterEnabled) {

		_accountGroupFilterEnabled = accountGroupFilterEnabled;
	}

	public boolean getChannelFilterEnabled() {
		return _channelFilterEnabled;
	}

	public boolean isChannelFilterEnabled() {
		return _channelFilterEnabled;
	}

	public void setChannelFilterEnabled(boolean channelFilterEnabled) {
		_channelFilterEnabled = channelFilterEnabled;
	}

	public int getVersion() {
		return _version;
	}

	public void setVersion(int version) {
		_version = version;
	}

	public int getStatus() {
		return _status;
	}

	public void setStatus(int status) {
		_status = status;
	}

	public long getStatusByUserId() {
		return _statusByUserId;
	}

	public void setStatusByUserId(long statusByUserId) {
		_statusByUserId = statusByUserId;
	}

	public String getStatusByUserName() {
		return _statusByUserName;
	}

	public void setStatusByUserName(String statusByUserName) {
		_statusByUserName = statusByUserName;
	}

	public Date getStatusDate() {
		return _statusDate;
	}

	public void setStatusDate(Date statusDate) {
		_statusDate = statusDate;
	}

	private String _uuid;
	private String _defaultLanguageId;
	private long _CPDefinitionId;
	private long _groupId;
	private long _companyId;
	private long _userId;
	private String _userName;
	private Date _createDate;
	private Date _modifiedDate;
	private long _CProductId;
	private long _CPTaxCategoryId;
	private String _productTypeName;
	private boolean _availableIndividually;
	private boolean _ignoreSKUCombinations;
	private boolean _shippable;
	private boolean _freeShipping;
	private boolean _shipSeparately;
	private double _shippingExtraPrice;
	private double _width;
	private double _height;
	private double _depth;
	private double _weight;
	private boolean _taxExempt;
	private boolean _telcoOrElectronics;
	private String _DDMStructureKey;
	private boolean _published;
	private Date _displayDate;
	private Date _expirationDate;
	private Date _lastPublishDate;
	private boolean _subscriptionEnabled;
	private int _subscriptionLength;
	private String _subscriptionType;
	private String _subscriptionTypeSettings;
	private long _maxSubscriptionCycles;
	private boolean _accountGroupFilterEnabled;
	private boolean _channelFilterEnabled;
	private int _version;
	private int _status;
	private long _statusByUserId;
	private String _statusByUserName;
	private Date _statusDate;

}