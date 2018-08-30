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

package com.liferay.commerce.service;

import aQute.bnd.annotation.ProviderType;

import com.liferay.commerce.context.CommerceContext;
import com.liferay.commerce.model.CommerceOrderItem;

import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.exception.SystemException;
import com.liferay.portal.kernel.jsonwebservice.JSONWebService;
import com.liferay.portal.kernel.search.BaseModelSearchResult;
import com.liferay.portal.kernel.search.Sort;
import com.liferay.portal.kernel.security.access.control.AccessControlled;
import com.liferay.portal.kernel.service.BaseService;
import com.liferay.portal.kernel.service.ServiceContext;
import com.liferay.portal.kernel.spring.osgi.OSGiBeanProperties;
import com.liferay.portal.kernel.transaction.Isolation;
import com.liferay.portal.kernel.transaction.Propagation;
import com.liferay.portal.kernel.transaction.Transactional;

import java.util.List;

/**
 * Provides the remote service interface for CommerceOrderItem. Methods of this
 * service are expected to have security checks based on the propagated JAAS
 * credentials because this service can be accessed remotely.
 *
 * @author Alessio Antonio Rendina
 * @see CommerceOrderItemServiceUtil
 * @see com.liferay.commerce.service.base.CommerceOrderItemServiceBaseImpl
 * @see com.liferay.commerce.service.impl.CommerceOrderItemServiceImpl
 * @generated
 */
@AccessControlled
@JSONWebService
@OSGiBeanProperties(property =  {
	"json.web.service.context.name=commerce", "json.web.service.context.path=CommerceOrderItem"}, service = CommerceOrderItemService.class)
@ProviderType
@Transactional(isolation = Isolation.PORTAL, rollbackFor =  {
	PortalException.class, SystemException.class})
public interface CommerceOrderItemService extends BaseService {
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this interface directly. Always use {@link CommerceOrderItemServiceUtil} to access the commerce order item remote service. Add custom service methods to {@link com.liferay.commerce.service.impl.CommerceOrderItemServiceImpl} and rerun ServiceBuilder to automatically copy the method declarations to this interface.
	 */
	public CommerceOrderItem addCommerceOrderItem(long commerceOrderId,
		long cpInstanceId, int quantity, int shippedQuantity, String json,
		CommerceContext commerceContext, ServiceContext serviceContext)
		throws PortalException;

	public void deleteCommerceOrderItem(long commerceOrderItemId)
		throws PortalException;

	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public CommerceOrderItem fetchByExternalReferenceCode(long companyId,
		String externalReferenceCode) throws PortalException;

	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public CommerceOrderItem fetchCommerceOrderItem(long commerceOrderItemId)
		throws PortalException;

	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public List<CommerceOrderItem> getAvailableForShipmentCommerceOrderItems(
		long commerceOrderId) throws PortalException;

	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public CommerceOrderItem getCommerceOrderItem(long commerceOrderItemId)
		throws PortalException;

	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public List<CommerceOrderItem> getCommerceOrderItems(long commerceOrderId,
		int start, int end) throws PortalException;

	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public int getCommerceOrderItemsCount(long commerceOrderId)
		throws PortalException;

	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public int getCommerceOrderItemsQuantity(long commerceOrderId)
		throws PortalException;

	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public int getCommerceWarehouseItemQuantity(long commerceOrderItemId,
		long commerceWarehouseId) throws PortalException;

	/**
	* Returns the OSGi service identifier.
	*
	* @return the OSGi service identifier
	*/
	public String getOSGiServiceIdentifier();

	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public BaseModelSearchResult<CommerceOrderItem> search(
		long commerceOrderId, String keywords, int start, int end, Sort sort)
		throws PortalException;

	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public BaseModelSearchResult<CommerceOrderItem> search(
		long commerceOrderId, String sku, String name, boolean andOperator,
		int start, int end, Sort sort) throws PortalException;

	public CommerceOrderItem updateCommerceOrderItem(long commerceOrderItemId,
		int quantity, CommerceContext commerceContext)
		throws PortalException;

	public CommerceOrderItem updateCommerceOrderItem(long commerceOrderItemId,
		int quantity, String json, CommerceContext commerceContext)
		throws PortalException;

	public CommerceOrderItem upsertCommerceOrderItem(long commerceOrderId,
		long cpInstanceId, int quantity, int shippedQuantity, String json,
		CommerceContext commerceContext, ServiceContext serviceContext)
		throws PortalException;
}