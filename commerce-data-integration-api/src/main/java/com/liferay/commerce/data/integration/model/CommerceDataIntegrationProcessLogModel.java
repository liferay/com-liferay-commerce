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

package com.liferay.commerce.data.integration.model;

import aQute.bnd.annotation.ProviderType;

import com.liferay.expando.kernel.model.ExpandoBridge;
import com.liferay.portal.kernel.bean.AutoEscape;
import com.liferay.portal.kernel.model.AuditedModel;
import com.liferay.portal.kernel.model.BaseModel;
import com.liferay.portal.kernel.model.CacheModel;
import com.liferay.portal.kernel.model.ShardedModel;
import com.liferay.portal.kernel.service.ServiceContext;

import java.io.Serializable;

import java.util.Date;

/**
 * The base model interface for the CommerceDataIntegrationProcessLog service. Represents a row in the &quot;CDataIntegrationProcessLog&quot; database table, with each column mapped to a property of this class.
 *
 * <p>
 * This interface and its corresponding implementation <code>com.liferay.commerce.data.integration.model.impl.CommerceDataIntegrationProcessLogModelImpl</code> exist only as a container for the default property accessors generated by ServiceBuilder. Helper methods and all application logic should be put in <code>com.liferay.commerce.data.integration.model.impl.CommerceDataIntegrationProcessLogImpl</code>.
 * </p>
 *
 * @author Alessio Antonio Rendina
 * @see CommerceDataIntegrationProcessLog
 * @generated
 */
@ProviderType
public interface CommerceDataIntegrationProcessLogModel
	extends AuditedModel, BaseModel<CommerceDataIntegrationProcessLog>,
			ShardedModel {

	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this interface directly. All methods that expect a commerce data integration process log model instance should use the {@link CommerceDataIntegrationProcessLog} interface instead.
	 */

	/**
	 * Returns the primary key of this commerce data integration process log.
	 *
	 * @return the primary key of this commerce data integration process log
	 */
	public long getPrimaryKey();

	/**
	 * Sets the primary key of this commerce data integration process log.
	 *
	 * @param primaryKey the primary key of this commerce data integration process log
	 */
	public void setPrimaryKey(long primaryKey);

	/**
	 * Returns the commerce data integration process log ID of this commerce data integration process log.
	 *
	 * @return the commerce data integration process log ID of this commerce data integration process log
	 */
	public long getCommerceDataIntegrationProcessLogId();

	/**
	 * Sets the commerce data integration process log ID of this commerce data integration process log.
	 *
	 * @param commerceDataIntegrationProcessLogId the commerce data integration process log ID of this commerce data integration process log
	 */
	public void setCommerceDataIntegrationProcessLogId(
		long commerceDataIntegrationProcessLogId);

	/**
	 * Returns the company ID of this commerce data integration process log.
	 *
	 * @return the company ID of this commerce data integration process log
	 */
	@Override
	public long getCompanyId();

	/**
	 * Sets the company ID of this commerce data integration process log.
	 *
	 * @param companyId the company ID of this commerce data integration process log
	 */
	@Override
	public void setCompanyId(long companyId);

	/**
	 * Returns the user ID of this commerce data integration process log.
	 *
	 * @return the user ID of this commerce data integration process log
	 */
	@Override
	public long getUserId();

	/**
	 * Sets the user ID of this commerce data integration process log.
	 *
	 * @param userId the user ID of this commerce data integration process log
	 */
	@Override
	public void setUserId(long userId);

	/**
	 * Returns the user uuid of this commerce data integration process log.
	 *
	 * @return the user uuid of this commerce data integration process log
	 */
	@Override
	public String getUserUuid();

	/**
	 * Sets the user uuid of this commerce data integration process log.
	 *
	 * @param userUuid the user uuid of this commerce data integration process log
	 */
	@Override
	public void setUserUuid(String userUuid);

	/**
	 * Returns the user name of this commerce data integration process log.
	 *
	 * @return the user name of this commerce data integration process log
	 */
	@AutoEscape
	@Override
	public String getUserName();

	/**
	 * Sets the user name of this commerce data integration process log.
	 *
	 * @param userName the user name of this commerce data integration process log
	 */
	@Override
	public void setUserName(String userName);

	/**
	 * Returns the create date of this commerce data integration process log.
	 *
	 * @return the create date of this commerce data integration process log
	 */
	@Override
	public Date getCreateDate();

	/**
	 * Sets the create date of this commerce data integration process log.
	 *
	 * @param createDate the create date of this commerce data integration process log
	 */
	@Override
	public void setCreateDate(Date createDate);

	/**
	 * Returns the modified date of this commerce data integration process log.
	 *
	 * @return the modified date of this commerce data integration process log
	 */
	@Override
	public Date getModifiedDate();

	/**
	 * Sets the modified date of this commerce data integration process log.
	 *
	 * @param modifiedDate the modified date of this commerce data integration process log
	 */
	@Override
	public void setModifiedDate(Date modifiedDate);

	/**
	 * Returns the c data integration process ID of this commerce data integration process log.
	 *
	 * @return the c data integration process ID of this commerce data integration process log
	 */
	public long getCDataIntegrationProcessId();

	/**
	 * Sets the c data integration process ID of this commerce data integration process log.
	 *
	 * @param CDataIntegrationProcessId the c data integration process ID of this commerce data integration process log
	 */
	public void setCDataIntegrationProcessId(long CDataIntegrationProcessId);

	/**
	 * Returns the error of this commerce data integration process log.
	 *
	 * @return the error of this commerce data integration process log
	 */
	@AutoEscape
	public String getError();

	/**
	 * Sets the error of this commerce data integration process log.
	 *
	 * @param error the error of this commerce data integration process log
	 */
	public void setError(String error);

	/**
	 * Returns the output of this commerce data integration process log.
	 *
	 * @return the output of this commerce data integration process log
	 */
	@AutoEscape
	public String getOutput();

	/**
	 * Sets the output of this commerce data integration process log.
	 *
	 * @param output the output of this commerce data integration process log
	 */
	public void setOutput(String output);

	/**
	 * Returns the status of this commerce data integration process log.
	 *
	 * @return the status of this commerce data integration process log
	 */
	public int getStatus();

	/**
	 * Sets the status of this commerce data integration process log.
	 *
	 * @param status the status of this commerce data integration process log
	 */
	public void setStatus(int status);

	/**
	 * Returns the start date of this commerce data integration process log.
	 *
	 * @return the start date of this commerce data integration process log
	 */
	public Date getStartDate();

	/**
	 * Sets the start date of this commerce data integration process log.
	 *
	 * @param startDate the start date of this commerce data integration process log
	 */
	public void setStartDate(Date startDate);

	/**
	 * Returns the end date of this commerce data integration process log.
	 *
	 * @return the end date of this commerce data integration process log
	 */
	public Date getEndDate();

	/**
	 * Sets the end date of this commerce data integration process log.
	 *
	 * @param endDate the end date of this commerce data integration process log
	 */
	public void setEndDate(Date endDate);

	@Override
	public boolean isNew();

	@Override
	public void setNew(boolean n);

	@Override
	public boolean isCachedModel();

	@Override
	public void setCachedModel(boolean cachedModel);

	@Override
	public boolean isEscapedModel();

	@Override
	public Serializable getPrimaryKeyObj();

	@Override
	public void setPrimaryKeyObj(Serializable primaryKeyObj);

	@Override
	public ExpandoBridge getExpandoBridge();

	@Override
	public void setExpandoBridgeAttributes(BaseModel<?> baseModel);

	@Override
	public void setExpandoBridgeAttributes(ExpandoBridge expandoBridge);

	@Override
	public void setExpandoBridgeAttributes(ServiceContext serviceContext);

	@Override
	public Object clone();

	@Override
	public int compareTo(
		CommerceDataIntegrationProcessLog commerceDataIntegrationProcessLog);

	@Override
	public int hashCode();

	@Override
	public CacheModel<CommerceDataIntegrationProcessLog> toCacheModel();

	@Override
	public CommerceDataIntegrationProcessLog toEscapedModel();

	@Override
	public CommerceDataIntegrationProcessLog toUnescapedModel();

	@Override
	public String toString();

	@Override
	public String toXmlString();

}