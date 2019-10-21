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

import com.liferay.portal.kernel.service.ServiceWrapper;

/**
 * Provides a wrapper for {@link CommerceOrderLocalService}.
 *
 * @author Alessio Antonio Rendina
 * @see CommerceOrderLocalService
 * @generated
 */
public class CommerceOrderLocalServiceWrapper
	implements CommerceOrderLocalService,
			   ServiceWrapper<CommerceOrderLocalService> {

	public CommerceOrderLocalServiceWrapper(
		CommerceOrderLocalService commerceOrderLocalService) {

		_commerceOrderLocalService = commerceOrderLocalService;
	}

	/**
	 * Adds the commerce order to the database. Also notifies the appropriate model listeners.
	 *
	 * @param commerceOrder the commerce order
	 * @return the commerce order that was added
	 */
	@Override
	public com.liferay.commerce.model.CommerceOrder addCommerceOrder(
		com.liferay.commerce.model.CommerceOrder commerceOrder) {

		return _commerceOrderLocalService.addCommerceOrder(commerceOrder);
	}

	@Override
	public com.liferay.commerce.model.CommerceOrder addCommerceOrder(
			long userId, long groupId, long commerceAccountId)
		throws com.liferay.portal.kernel.exception.PortalException {

		return _commerceOrderLocalService.addCommerceOrder(
			userId, groupId, commerceAccountId);
	}

	@Override
	public com.liferay.commerce.model.CommerceOrder addCommerceOrder(
			long userId, long groupId, long commerceAccountId,
			long commerceCurrencyId)
		throws com.liferay.portal.kernel.exception.PortalException {

		return _commerceOrderLocalService.addCommerceOrder(
			userId, groupId, commerceAccountId, commerceCurrencyId);
	}

	@Override
	public com.liferay.commerce.model.CommerceOrder addCommerceOrder(
			long userId, long groupId, long commerceAccountId,
			long commerceCurrencyId, long billingAddressId,
			long shippingAddressId, String commercePaymentMethodKey,
			long commerceShippingMethodId, String shippingOptionName,
			String purchaseOrderNumber, java.math.BigDecimal subtotal,
			java.math.BigDecimal shippingAmount, java.math.BigDecimal total,
			int paymentStatus, int orderStatus,
			com.liferay.portal.kernel.service.ServiceContext serviceContext)
		throws com.liferay.portal.kernel.exception.PortalException {

		return _commerceOrderLocalService.addCommerceOrder(
			userId, groupId, commerceAccountId, commerceCurrencyId,
			billingAddressId, shippingAddressId, commercePaymentMethodKey,
			commerceShippingMethodId, shippingOptionName, purchaseOrderNumber,
			subtotal, shippingAmount, total, paymentStatus, orderStatus,
			serviceContext);
	}

	@Override
	public com.liferay.commerce.model.CommerceOrder addCommerceOrder(
			long userId, long groupId, long commerceAccountId,
			long commerceCurrencyId, long shippingAddressId,
			String purchaseOrderNumber)
		throws com.liferay.portal.kernel.exception.PortalException {

		return _commerceOrderLocalService.addCommerceOrder(
			userId, groupId, commerceAccountId, commerceCurrencyId,
			shippingAddressId, purchaseOrderNumber);
	}

	@Override
	public com.liferay.commerce.model.CommerceOrder addCommerceOrder(
			long userId, long groupId, long commerceAccountId,
			long shippingAddressId, String purchaseOrderNumber)
		throws com.liferay.portal.kernel.exception.PortalException {

		return _commerceOrderLocalService.addCommerceOrder(
			userId, groupId, commerceAccountId, shippingAddressId,
			purchaseOrderNumber);
	}

	@Override
	public com.liferay.commerce.model.CommerceOrder applyCouponCode(
			long commerceOrderId, String couponCode,
			com.liferay.commerce.context.CommerceContext commerceContext)
		throws com.liferay.portal.kernel.exception.PortalException {

		return _commerceOrderLocalService.applyCouponCode(
			commerceOrderId, couponCode, commerceContext);
	}

	@Override
	public com.liferay.commerce.model.CommerceOrder approveCommerceOrder(
			long userId, long commerceOrderId)
		throws com.liferay.portal.kernel.exception.PortalException {

		return _commerceOrderLocalService.approveCommerceOrder(
			userId, commerceOrderId);
	}

	@Override
	public com.liferay.commerce.model.CommerceOrder checkoutCommerceOrder(
			long commerceOrderId,
			com.liferay.commerce.context.CommerceContext commerceContext,
			com.liferay.portal.kernel.service.ServiceContext serviceContext)
		throws com.liferay.portal.kernel.exception.PortalException {

		return _commerceOrderLocalService.checkoutCommerceOrder(
			commerceOrderId, commerceContext, serviceContext);
	}

	/**
	 * Creates a new commerce order with the primary key. Does not add the commerce order to the database.
	 *
	 * @param commerceOrderId the primary key for the new commerce order
	 * @return the new commerce order
	 */
	@Override
	public com.liferay.commerce.model.CommerceOrder createCommerceOrder(
		long commerceOrderId) {

		return _commerceOrderLocalService.createCommerceOrder(commerceOrderId);
	}

	/**
	 * Deletes the commerce order from the database. Also notifies the appropriate model listeners.
	 *
	 * @param commerceOrder the commerce order
	 * @return the commerce order that was removed
	 * @throws PortalException
	 */
	@Override
	public com.liferay.commerce.model.CommerceOrder deleteCommerceOrder(
			com.liferay.commerce.model.CommerceOrder commerceOrder)
		throws com.liferay.portal.kernel.exception.PortalException {

		return _commerceOrderLocalService.deleteCommerceOrder(commerceOrder);
	}

	/**
	 * Deletes the commerce order with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * @param commerceOrderId the primary key of the commerce order
	 * @return the commerce order that was removed
	 * @throws PortalException if a commerce order with the primary key could not be found
	 */
	@Override
	public com.liferay.commerce.model.CommerceOrder deleteCommerceOrder(
			long commerceOrderId)
		throws com.liferay.portal.kernel.exception.PortalException {

		return _commerceOrderLocalService.deleteCommerceOrder(commerceOrderId);
	}

	@Override
	public void deleteCommerceOrders(long groupId)
		throws com.liferay.portal.kernel.exception.PortalException {

		_commerceOrderLocalService.deleteCommerceOrders(groupId);
	}

	@Override
	public void deleteCommerceOrders(
		long userId, java.util.Date date, int status) {

		_commerceOrderLocalService.deleteCommerceOrders(userId, date, status);
	}

	/**
	 * @throws PortalException
	 */
	@Override
	public com.liferay.portal.kernel.model.PersistedModel deletePersistedModel(
			com.liferay.portal.kernel.model.PersistedModel persistedModel)
		throws com.liferay.portal.kernel.exception.PortalException {

		return _commerceOrderLocalService.deletePersistedModel(persistedModel);
	}

	@Override
	public com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery() {
		return _commerceOrderLocalService.dynamicQuery();
	}

	/**
	 * Performs a dynamic query on the database and returns the matching rows.
	 *
	 * @param dynamicQuery the dynamic query
	 * @return the matching rows
	 */
	@Override
	public <T> java.util.List<T> dynamicQuery(
		com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery) {

		return _commerceOrderLocalService.dynamicQuery(dynamicQuery);
	}

	/**
	 * Performs a dynamic query on the database and returns a range of the matching rows.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>com.liferay.commerce.model.impl.CommerceOrderModelImpl</code>.
	 * </p>
	 *
	 * @param dynamicQuery the dynamic query
	 * @param start the lower bound of the range of model instances
	 * @param end the upper bound of the range of model instances (not inclusive)
	 * @return the range of matching rows
	 */
	@Override
	public <T> java.util.List<T> dynamicQuery(
		com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery, int start,
		int end) {

		return _commerceOrderLocalService.dynamicQuery(
			dynamicQuery, start, end);
	}

	/**
	 * Performs a dynamic query on the database and returns an ordered range of the matching rows.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>com.liferay.commerce.model.impl.CommerceOrderModelImpl</code>.
	 * </p>
	 *
	 * @param dynamicQuery the dynamic query
	 * @param start the lower bound of the range of model instances
	 * @param end the upper bound of the range of model instances (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of matching rows
	 */
	@Override
	public <T> java.util.List<T> dynamicQuery(
		com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery, int start,
		int end,
		com.liferay.portal.kernel.util.OrderByComparator<T> orderByComparator) {

		return _commerceOrderLocalService.dynamicQuery(
			dynamicQuery, start, end, orderByComparator);
	}

	/**
	 * Returns the number of rows matching the dynamic query.
	 *
	 * @param dynamicQuery the dynamic query
	 * @return the number of rows matching the dynamic query
	 */
	@Override
	public long dynamicQueryCount(
		com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery) {

		return _commerceOrderLocalService.dynamicQueryCount(dynamicQuery);
	}

	/**
	 * Returns the number of rows matching the dynamic query.
	 *
	 * @param dynamicQuery the dynamic query
	 * @param projection the projection to apply to the query
	 * @return the number of rows matching the dynamic query
	 */
	@Override
	public long dynamicQueryCount(
		com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery,
		com.liferay.portal.kernel.dao.orm.Projection projection) {

		return _commerceOrderLocalService.dynamicQueryCount(
			dynamicQuery, projection);
	}

	@Override
	public com.liferay.commerce.model.CommerceOrder executeWorkflowTransition(
			long userId, long commerceOrderId, long workflowTaskId,
			String transitionName, String comment)
		throws com.liferay.portal.kernel.exception.PortalException {

		return _commerceOrderLocalService.executeWorkflowTransition(
			userId, commerceOrderId, workflowTaskId, transitionName, comment);
	}

	@Override
	public com.liferay.commerce.model.CommerceOrder
		fetchByExternalReferenceCode(
			long companyId, String externalReferenceCode) {

		return _commerceOrderLocalService.fetchByExternalReferenceCode(
			companyId, externalReferenceCode);
	}

	@Override
	public com.liferay.commerce.model.CommerceOrder fetchCommerceOrder(
		long commerceOrderId) {

		return _commerceOrderLocalService.fetchCommerceOrder(commerceOrderId);
	}

	@Override
	public com.liferay.commerce.model.CommerceOrder fetchCommerceOrder(
		long commerceAccountId, long groupId, int orderStatus) {

		return _commerceOrderLocalService.fetchCommerceOrder(
			commerceAccountId, groupId, orderStatus);
	}

	/**
	 * Returns the commerce order with the matching external reference code and company.
	 *
	 * @param companyId the primary key of the company
	 * @param externalReferenceCode the commerce order's external reference code
	 * @return the matching commerce order, or <code>null</code> if a matching commerce order could not be found
	 */
	@Override
	public com.liferay.commerce.model.CommerceOrder
		fetchCommerceOrderByReferenceCode(
			long companyId, String externalReferenceCode) {

		return _commerceOrderLocalService.fetchCommerceOrderByReferenceCode(
			companyId, externalReferenceCode);
	}

	/**
	 * Returns the commerce order matching the UUID and group.
	 *
	 * @param uuid the commerce order's UUID
	 * @param groupId the primary key of the group
	 * @return the matching commerce order, or <code>null</code> if a matching commerce order could not be found
	 */
	@Override
	public com.liferay.commerce.model.CommerceOrder
		fetchCommerceOrderByUuidAndGroupId(String uuid, long groupId) {

		return _commerceOrderLocalService.fetchCommerceOrderByUuidAndGroupId(
			uuid, groupId);
	}

	@Override
	public com.liferay.portal.kernel.dao.orm.ActionableDynamicQuery
		getActionableDynamicQuery() {

		return _commerceOrderLocalService.getActionableDynamicQuery();
	}

	@Override
	public int[] getAvailableOrderStatuses(long commerceOrderId)
		throws com.liferay.portal.kernel.exception.PortalException {

		return _commerceOrderLocalService.getAvailableOrderStatuses(
			commerceOrderId);
	}

	/**
	 * Returns the commerce order with the primary key.
	 *
	 * @param commerceOrderId the primary key of the commerce order
	 * @return the commerce order
	 * @throws PortalException if a commerce order with the primary key could not be found
	 */
	@Override
	public com.liferay.commerce.model.CommerceOrder getCommerceOrder(
			long commerceOrderId)
		throws com.liferay.portal.kernel.exception.PortalException {

		return _commerceOrderLocalService.getCommerceOrder(commerceOrderId);
	}

	/**
	 * Returns the commerce order matching the UUID and group.
	 *
	 * @param uuid the commerce order's UUID
	 * @param groupId the primary key of the group
	 * @return the matching commerce order
	 * @throws PortalException if a matching commerce order could not be found
	 */
	@Override
	public com.liferay.commerce.model.CommerceOrder
			getCommerceOrderByUuidAndGroupId(String uuid, long groupId)
		throws com.liferay.portal.kernel.exception.PortalException {

		return _commerceOrderLocalService.getCommerceOrderByUuidAndGroupId(
			uuid, groupId);
	}

	/**
	 * Returns a range of all the commerce orders.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>com.liferay.commerce.model.impl.CommerceOrderModelImpl</code>.
	 * </p>
	 *
	 * @param start the lower bound of the range of commerce orders
	 * @param end the upper bound of the range of commerce orders (not inclusive)
	 * @return the range of commerce orders
	 */
	@Override
	public java.util.List<com.liferay.commerce.model.CommerceOrder>
		getCommerceOrders(int start, int end) {

		return _commerceOrderLocalService.getCommerceOrders(start, end);
	}

	@Override
	public java.util.List<com.liferay.commerce.model.CommerceOrder>
		getCommerceOrders(
			long groupId, int start, int end,
			com.liferay.portal.kernel.util.OrderByComparator
				<com.liferay.commerce.model.CommerceOrder> orderByComparator) {

		return _commerceOrderLocalService.getCommerceOrders(
			groupId, start, end, orderByComparator);
	}

	@Override
	public java.util.List<com.liferay.commerce.model.CommerceOrder>
		getCommerceOrders(long groupId, int[] orderStatuses) {

		return _commerceOrderLocalService.getCommerceOrders(
			groupId, orderStatuses);
	}

	@Override
	public java.util.List<com.liferay.commerce.model.CommerceOrder>
		getCommerceOrders(
			long groupId, int[] orderStatuses, int start, int end) {

		return _commerceOrderLocalService.getCommerceOrders(
			groupId, orderStatuses, start, end);
	}

	@Override
	public java.util.List<com.liferay.commerce.model.CommerceOrder>
		getCommerceOrders(
			long groupId, long commerceAccountId, int start, int end,
			com.liferay.portal.kernel.util.OrderByComparator
				<com.liferay.commerce.model.CommerceOrder> orderByComparator) {

		return _commerceOrderLocalService.getCommerceOrders(
			groupId, commerceAccountId, start, end, orderByComparator);
	}

	@Override
	public java.util.List<com.liferay.commerce.model.CommerceOrder>
			getCommerceOrders(
				long companyId, long groupId, long[] commerceAccountIds,
				int[] orderStatuses, boolean excludeOrderStatus, int start,
				int end)
		throws com.liferay.portal.kernel.exception.PortalException {

		return _commerceOrderLocalService.getCommerceOrders(
			companyId, groupId, commerceAccountIds, orderStatuses,
			excludeOrderStatus, start, end);
	}

	@Override
	public java.util.List<com.liferay.commerce.model.CommerceOrder>
		getCommerceOrdersByBillingAddress(long billingAddressId) {

		return _commerceOrderLocalService.getCommerceOrdersByBillingAddress(
			billingAddressId);
	}

	@Override
	public java.util.List<com.liferay.commerce.model.CommerceOrder>
		getCommerceOrdersByCommerceAccountId(
			long commerceAccountId, int start, int end,
			com.liferay.portal.kernel.util.OrderByComparator
				<com.liferay.commerce.model.CommerceOrder> orderByComparator) {

		return _commerceOrderLocalService.getCommerceOrdersByCommerceAccountId(
			commerceAccountId, start, end, orderByComparator);
	}

	@Override
	public java.util.List<com.liferay.commerce.model.CommerceOrder>
		getCommerceOrdersByShippingAddress(long shippingAddressId) {

		return _commerceOrderLocalService.getCommerceOrdersByShippingAddress(
			shippingAddressId);
	}

	/**
	 * Returns all the commerce orders matching the UUID and company.
	 *
	 * @param uuid the UUID of the commerce orders
	 * @param companyId the primary key of the company
	 * @return the matching commerce orders, or an empty list if no matches were found
	 */
	@Override
	public java.util.List<com.liferay.commerce.model.CommerceOrder>
		getCommerceOrdersByUuidAndCompanyId(String uuid, long companyId) {

		return _commerceOrderLocalService.getCommerceOrdersByUuidAndCompanyId(
			uuid, companyId);
	}

	/**
	 * Returns a range of commerce orders matching the UUID and company.
	 *
	 * @param uuid the UUID of the commerce orders
	 * @param companyId the primary key of the company
	 * @param start the lower bound of the range of commerce orders
	 * @param end the upper bound of the range of commerce orders (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the range of matching commerce orders, or an empty list if no matches were found
	 */
	@Override
	public java.util.List<com.liferay.commerce.model.CommerceOrder>
		getCommerceOrdersByUuidAndCompanyId(
			String uuid, long companyId, int start, int end,
			com.liferay.portal.kernel.util.OrderByComparator
				<com.liferay.commerce.model.CommerceOrder> orderByComparator) {

		return _commerceOrderLocalService.getCommerceOrdersByUuidAndCompanyId(
			uuid, companyId, start, end, orderByComparator);
	}

	/**
	 * Returns the number of commerce orders.
	 *
	 * @return the number of commerce orders
	 */
	@Override
	public int getCommerceOrdersCount() {
		return _commerceOrderLocalService.getCommerceOrdersCount();
	}

	@Override
	public int getCommerceOrdersCount(long groupId) {
		return _commerceOrderLocalService.getCommerceOrdersCount(groupId);
	}

	@Override
	public int getCommerceOrdersCount(long groupId, long commerceAccountId) {
		return _commerceOrderLocalService.getCommerceOrdersCount(
			groupId, commerceAccountId);
	}

	@Override
	public long getCommerceOrdersCount(
			long companyId, long groupId, long[] commerceAccountIds,
			int[] orderStatuses, boolean excludeOrderStatus)
		throws com.liferay.portal.kernel.exception.PortalException {

		return _commerceOrderLocalService.getCommerceOrdersCount(
			companyId, groupId, commerceAccountIds, orderStatuses,
			excludeOrderStatus);
	}

	@Override
	public int getCommerceOrdersCountByCommerceAccountId(
		long commerceAccountId) {

		return _commerceOrderLocalService.
			getCommerceOrdersCountByCommerceAccountId(commerceAccountId);
	}

	@Override
	public com.liferay.portal.kernel.dao.orm.ExportActionableDynamicQuery
		getExportActionableDynamicQuery(
			com.liferay.exportimport.kernel.lar.PortletDataContext
				portletDataContext) {

		return _commerceOrderLocalService.getExportActionableDynamicQuery(
			portletDataContext);
	}

	@Override
	public com.liferay.portal.kernel.dao.orm.IndexableActionableDynamicQuery
		getIndexableActionableDynamicQuery() {

		return _commerceOrderLocalService.getIndexableActionableDynamicQuery();
	}

	/**
	 * Returns the OSGi service identifier.
	 *
	 * @return the OSGi service identifier
	 */
	@Override
	public String getOSGiServiceIdentifier() {
		return _commerceOrderLocalService.getOSGiServiceIdentifier();
	}

	@Override
	public com.liferay.portal.kernel.model.PersistedModel getPersistedModel(
			java.io.Serializable primaryKeyObj)
		throws com.liferay.portal.kernel.exception.PortalException {

		return _commerceOrderLocalService.getPersistedModel(primaryKeyObj);
	}

	/**
	 * @deprecated As of Mueller (7.2.x)
	 */
	@Deprecated
	@Override
	public java.util.List<com.liferay.commerce.model.CommerceOrder>
		getUserCommerceOrders(
			long groupId, long userId, long commerceAccountId,
			Integer orderStatus, boolean excludeOrderStatus, String keywords,
			int start, int end) {

		return _commerceOrderLocalService.getUserCommerceOrders(
			groupId, userId, commerceAccountId, orderStatus, excludeOrderStatus,
			keywords, start, end);
	}

	/**
	 * @deprecated As of Mueller (7.2.x)
	 */
	@Deprecated
	@Override
	public int getUserCommerceOrdersCount(
		long groupId, long userId, long commerceAccountId, Integer orderStatus,
		boolean excludeOrderStatus, String keywords) {

		return _commerceOrderLocalService.getUserCommerceOrdersCount(
			groupId, userId, commerceAccountId, orderStatus, excludeOrderStatus,
			keywords);
	}

	@Override
	public void mergeGuestCommerceOrder(
			long guestCommerceOrderId, long userCommerceOrderId,
			com.liferay.commerce.context.CommerceContext commerceContext,
			com.liferay.portal.kernel.service.ServiceContext serviceContext)
		throws com.liferay.portal.kernel.exception.PortalException {

		_commerceOrderLocalService.mergeGuestCommerceOrder(
			guestCommerceOrderId, userCommerceOrderId, commerceContext,
			serviceContext);
	}

	@Override
	public com.liferay.commerce.model.CommerceOrder recalculatePrice(
			long commerceOrderId,
			com.liferay.commerce.context.CommerceContext commerceContext)
		throws com.liferay.portal.kernel.exception.PortalException {

		return _commerceOrderLocalService.recalculatePrice(
			commerceOrderId, commerceContext);
	}

	@Override
	public com.liferay.commerce.model.CommerceOrder reorderCommerceOrder(
			long userId, long commerceOrderId,
			com.liferay.commerce.context.CommerceContext commerceContext)
		throws com.liferay.portal.kernel.exception.PortalException {

		return _commerceOrderLocalService.reorderCommerceOrder(
			userId, commerceOrderId, commerceContext);
	}

	@Override
	public com.liferay.commerce.model.CommerceOrder resetCommerceOrderShipping(
			long commerceOrderId)
		throws com.liferay.portal.kernel.exception.PortalException {

		return _commerceOrderLocalService.resetCommerceOrderShipping(
			commerceOrderId);
	}

	@Override
	public com.liferay.portal.kernel.search.BaseModelSearchResult
		<com.liferay.commerce.model.CommerceOrder> searchCommerceOrders(
				com.liferay.portal.kernel.search.SearchContext searchContext)
			throws com.liferay.portal.kernel.exception.PortalException {

		return _commerceOrderLocalService.searchCommerceOrders(searchContext);
	}

	@Override
	public long searchCommerceOrdersCount(
			com.liferay.portal.kernel.search.SearchContext searchContext)
		throws com.liferay.portal.kernel.exception.PortalException {

		return _commerceOrderLocalService.searchCommerceOrdersCount(
			searchContext);
	}

	@Override
	public com.liferay.commerce.model.CommerceOrder setCommerceOrderToTransmit(
			long userId, com.liferay.commerce.model.CommerceOrder commerceOrder)
		throws com.liferay.portal.kernel.exception.PortalException {

		return _commerceOrderLocalService.setCommerceOrderToTransmit(
			userId, commerceOrder);
	}

	@Override
	public com.liferay.commerce.model.CommerceOrder submitCommerceOrder(
			long userId, long commerceOrderId)
		throws com.liferay.portal.kernel.exception.PortalException {

		return _commerceOrderLocalService.submitCommerceOrder(
			userId, commerceOrderId);
	}

	@Override
	public com.liferay.commerce.model.CommerceOrder updateAccount(
			long commerceOrderId, long userId, long commerceAccountId)
		throws com.liferay.portal.kernel.exception.PortalException {

		return _commerceOrderLocalService.updateAccount(
			commerceOrderId, userId, commerceAccountId);
	}

	@Override
	public com.liferay.commerce.model.CommerceOrder updateBillingAddress(
			long commerceOrderId, String name, String description,
			String street1, String street2, String street3, String city,
			String zip, long commerceRegionId, long commerceCountryId,
			String phoneNumber,
			com.liferay.portal.kernel.service.ServiceContext serviceContext)
		throws com.liferay.portal.kernel.exception.PortalException {

		return _commerceOrderLocalService.updateBillingAddress(
			commerceOrderId, name, description, street1, street2, street3, city,
			zip, commerceRegionId, commerceCountryId, phoneNumber,
			serviceContext);
	}

	/**
	 * Updates the commerce order in the database or adds it if it does not yet exist. Also notifies the appropriate model listeners.
	 *
	 * @param commerceOrder the commerce order
	 * @return the commerce order that was updated
	 */
	@Override
	public com.liferay.commerce.model.CommerceOrder updateCommerceOrder(
		com.liferay.commerce.model.CommerceOrder commerceOrder) {

		return _commerceOrderLocalService.updateCommerceOrder(commerceOrder);
	}

	@Override
	public com.liferay.commerce.model.CommerceOrder updateCommerceOrder(
			long commerceOrderId, long billingAddressId, long shippingAddressId,
			String commercePaymentMethodKey, long commerceShippingMethodId,
			String shippingOptionName, String purchaseOrderNumber,
			java.math.BigDecimal subtotal, java.math.BigDecimal shippingAmount,
			java.math.BigDecimal total, String advanceStatus,
			com.liferay.commerce.context.CommerceContext commerceContext)
		throws com.liferay.portal.kernel.exception.PortalException {

		return _commerceOrderLocalService.updateCommerceOrder(
			commerceOrderId, billingAddressId, shippingAddressId,
			commercePaymentMethodKey, commerceShippingMethodId,
			shippingOptionName, purchaseOrderNumber, subtotal, shippingAmount,
			total, advanceStatus, commerceContext);
	}

	@Override
	public com.liferay.commerce.model.CommerceOrder updateCommerceOrder(
			long commerceOrderId, long billingAddressId, long shippingAddressId,
			String commercePaymentMethodKey, long commerceShippingMethodId,
			String shippingOptionName, String purchaseOrderNumber,
			java.math.BigDecimal subtotal, java.math.BigDecimal shippingAmount,
			java.math.BigDecimal total, String advanceStatus,
			String externalReferenceCode,
			com.liferay.commerce.context.CommerceContext commerceContext)
		throws com.liferay.portal.kernel.exception.PortalException {

		return _commerceOrderLocalService.updateCommerceOrder(
			commerceOrderId, billingAddressId, shippingAddressId,
			commercePaymentMethodKey, commerceShippingMethodId,
			shippingOptionName, purchaseOrderNumber, subtotal, shippingAmount,
			total, advanceStatus, externalReferenceCode, commerceContext);
	}

	@Override
	public com.liferay.commerce.model.CommerceOrder updateCommerceOrderPrices(
			long commerceOrderId, java.math.BigDecimal subtotal,
			java.math.BigDecimal subtotalDiscountAmount,
			java.math.BigDecimal subtotalDiscountPercentageLevel1,
			java.math.BigDecimal subtotalDiscountPercentageLevel2,
			java.math.BigDecimal subtotalDiscountPercentageLevel3,
			java.math.BigDecimal subtotalDiscountPercentageLevel4,
			java.math.BigDecimal shippingAmount,
			java.math.BigDecimal shippingDiscountAmount,
			java.math.BigDecimal shippingDiscountPercentageLevel1,
			java.math.BigDecimal shippingDiscountPercentageLevel2,
			java.math.BigDecimal shippingDiscountPercentageLevel3,
			java.math.BigDecimal shippingDiscountPercentageLevel4,
			java.math.BigDecimal taxAmount, java.math.BigDecimal total,
			java.math.BigDecimal totalDiscountAmount,
			java.math.BigDecimal totalDiscountPercentageLevel1,
			java.math.BigDecimal totalDiscountPercentageLevel2,
			java.math.BigDecimal totalDiscountPercentageLevel3,
			java.math.BigDecimal totalDiscountPercentageLevel4)
		throws com.liferay.portal.kernel.exception.PortalException {

		return _commerceOrderLocalService.updateCommerceOrderPrices(
			commerceOrderId, subtotal, subtotalDiscountAmount,
			subtotalDiscountPercentageLevel1, subtotalDiscountPercentageLevel2,
			subtotalDiscountPercentageLevel3, subtotalDiscountPercentageLevel4,
			shippingAmount, shippingDiscountAmount,
			shippingDiscountPercentageLevel1, shippingDiscountPercentageLevel2,
			shippingDiscountPercentageLevel3, shippingDiscountPercentageLevel4,
			taxAmount, total, totalDiscountAmount,
			totalDiscountPercentageLevel1, totalDiscountPercentageLevel2,
			totalDiscountPercentageLevel3, totalDiscountPercentageLevel4);
	}

	@Override
	public com.liferay.commerce.model.CommerceOrder
			updateCommercePaymentMethodKey(
				long commerceOrderId, String commercePaymentMethodKey)
		throws com.liferay.portal.kernel.exception.PortalException {

		return _commerceOrderLocalService.updateCommercePaymentMethodKey(
			commerceOrderId, commercePaymentMethodKey);
	}

	@Override
	public com.liferay.commerce.model.CommerceOrder updateCustomFields(
			long commerceOrderId,
			com.liferay.portal.kernel.service.ServiceContext serviceContext)
		throws com.liferay.portal.kernel.exception.PortalException {

		return _commerceOrderLocalService.updateCustomFields(
			commerceOrderId, serviceContext);
	}

	@Override
	public com.liferay.commerce.model.CommerceOrder updateInfo(
			long commerceOrderId, String printedNote,
			int requestedDeliveryDateMonth, int requestedDeliveryDateDay,
			int requestedDeliveryDateYear, int requestedDeliveryDateHour,
			int requestedDeliveryDateMinute,
			com.liferay.portal.kernel.service.ServiceContext serviceContext)
		throws com.liferay.portal.kernel.exception.PortalException {

		return _commerceOrderLocalService.updateInfo(
			commerceOrderId, printedNote, requestedDeliveryDateMonth,
			requestedDeliveryDateDay, requestedDeliveryDateYear,
			requestedDeliveryDateHour, requestedDeliveryDateMinute,
			serviceContext);
	}

	@Override
	public com.liferay.commerce.model.CommerceOrder updateOrderStatus(
			long commerceOrderId, int orderStatus)
		throws com.liferay.portal.kernel.exception.PortalException {

		return _commerceOrderLocalService.updateOrderStatus(
			commerceOrderId, orderStatus);
	}

	@Override
	public com.liferay.commerce.model.CommerceOrder updatePaymentStatus(
			long userId, long commerceOrderId, int paymentStatus)
		throws com.liferay.portal.kernel.exception.PortalException {

		return _commerceOrderLocalService.updatePaymentStatus(
			userId, commerceOrderId, paymentStatus);
	}

	@Override
	public com.liferay.commerce.model.CommerceOrder
			updatePaymentStatusAndTransactionId(
				long userId, long commerceOrderId, int paymentStatus,
				String transactionId)
		throws com.liferay.portal.kernel.exception.PortalException {

		return _commerceOrderLocalService.updatePaymentStatusAndTransactionId(
			userId, commerceOrderId, paymentStatus, transactionId);
	}

	@Override
	public com.liferay.commerce.model.CommerceOrder updatePurchaseOrderNumber(
			long commerceOrderId, String purchaseOrderNumber)
		throws com.liferay.portal.kernel.exception.PortalException {

		return _commerceOrderLocalService.updatePurchaseOrderNumber(
			commerceOrderId, purchaseOrderNumber);
	}

	@Override
	public com.liferay.commerce.model.CommerceOrder updateShippingAddress(
			long commerceOrderId, String name, String description,
			String street1, String street2, String street3, String city,
			String zip, long commerceRegionId, long commerceCountryId,
			String phoneNumber,
			com.liferay.portal.kernel.service.ServiceContext serviceContext)
		throws com.liferay.portal.kernel.exception.PortalException {

		return _commerceOrderLocalService.updateShippingAddress(
			commerceOrderId, name, description, street1, street2, street3, city,
			zip, commerceRegionId, commerceCountryId, phoneNumber,
			serviceContext);
	}

	@Override
	public com.liferay.commerce.model.CommerceOrder updateShippingMethod(
			long commerceOrderId, long commerceShippingMethodId,
			String shippingOptionName, java.math.BigDecimal shippingAmount,
			com.liferay.commerce.context.CommerceContext commerceContext)
		throws com.liferay.portal.kernel.exception.PortalException {

		return _commerceOrderLocalService.updateShippingMethod(
			commerceOrderId, commerceShippingMethodId, shippingOptionName,
			shippingAmount, commerceContext);
	}

	@Override
	public com.liferay.commerce.model.CommerceOrder updateStatus(
			long userId, long commerceOrderId, int status,
			com.liferay.portal.kernel.service.ServiceContext serviceContext,
			java.util.Map<String, java.io.Serializable> workflowContext)
		throws com.liferay.portal.kernel.exception.PortalException {

		return _commerceOrderLocalService.updateStatus(
			userId, commerceOrderId, status, serviceContext, workflowContext);
	}

	@Override
	public com.liferay.commerce.model.CommerceOrder updateTransactionId(
			long commerceOrderId, String transactionId)
		throws com.liferay.portal.kernel.exception.PortalException {

		return _commerceOrderLocalService.updateTransactionId(
			commerceOrderId, transactionId);
	}

	@Override
	public com.liferay.commerce.model.CommerceOrder updateUser(
			long commerceOrderId, long userId)
		throws com.liferay.portal.kernel.exception.PortalException {

		return _commerceOrderLocalService.updateUser(commerceOrderId, userId);
	}

	@Override
	public com.liferay.commerce.model.CommerceOrder upsertCommerceOrder(
			long userId, long groupId, long commerceAccountId,
			long commerceCurrencyId, long billingAddressId,
			long shippingAddressId, String commercePaymentMethodKey,
			long commerceShippingMethodId, String shippingOptionName,
			String purchaseOrderNumber, java.math.BigDecimal subtotal,
			java.math.BigDecimal shippingAmount, java.math.BigDecimal total,
			int paymentStatus, int orderStatus, String advanceStatus,
			String externalReferenceCode,
			com.liferay.commerce.context.CommerceContext commerceContext,
			com.liferay.portal.kernel.service.ServiceContext serviceContext)
		throws com.liferay.portal.kernel.exception.PortalException {

		return _commerceOrderLocalService.upsertCommerceOrder(
			userId, groupId, commerceAccountId, commerceCurrencyId,
			billingAddressId, shippingAddressId, commercePaymentMethodKey,
			commerceShippingMethodId, shippingOptionName, purchaseOrderNumber,
			subtotal, shippingAmount, total, paymentStatus, orderStatus,
			advanceStatus, externalReferenceCode, commerceContext,
			serviceContext);
	}

	@Override
	public CommerceOrderLocalService getWrappedService() {
		return _commerceOrderLocalService;
	}

	@Override
	public void setWrappedService(
		CommerceOrderLocalService commerceOrderLocalService) {

		_commerceOrderLocalService = commerceOrderLocalService;
	}

	private CommerceOrderLocalService _commerceOrderLocalService;

}