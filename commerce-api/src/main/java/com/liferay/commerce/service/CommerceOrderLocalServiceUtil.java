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

import org.osgi.framework.Bundle;
import org.osgi.framework.FrameworkUtil;

import org.osgi.util.tracker.ServiceTracker;

/**
 * Provides the local service utility for CommerceOrder. This utility wraps
 * {@link com.liferay.commerce.service.impl.CommerceOrderLocalServiceImpl} and is the
 * primary access point for service operations in application layer code running
 * on the local server. Methods of this service will not have security checks
 * based on the propagated JAAS credentials because this service can only be
 * accessed from within the same VM.
 *
 * @author Alessio Antonio Rendina
 * @see CommerceOrderLocalService
 * @see com.liferay.commerce.service.base.CommerceOrderLocalServiceBaseImpl
 * @see com.liferay.commerce.service.impl.CommerceOrderLocalServiceImpl
 * @generated
 */
@ProviderType
public class CommerceOrderLocalServiceUtil {
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify this class directly. Add custom service methods to {@link com.liferay.commerce.service.impl.CommerceOrderLocalServiceImpl} and rerun ServiceBuilder to regenerate this class.
	 */

	/**
	* Adds the commerce order to the database. Also notifies the appropriate model listeners.
	*
	* @param commerceOrder the commerce order
	* @return the commerce order that was added
	*/
	public static com.liferay.commerce.model.CommerceOrder addCommerceOrder(
		com.liferay.commerce.model.CommerceOrder commerceOrder) {
		return getService().addCommerceOrder(commerceOrder);
	}

	public static com.liferay.commerce.model.CommerceOrder addCommerceOrder(
		long groupId, long userId, long commerceAccountId)
		throws com.liferay.portal.kernel.exception.PortalException {
		return getService().addCommerceOrder(groupId, userId, commerceAccountId);
	}

	public static com.liferay.commerce.model.CommerceOrder addCommerceOrder(
		long groupId, long userId, long commerceAccountId,
		long commerceCurrencyId)
		throws com.liferay.portal.kernel.exception.PortalException {
		return getService()
				   .addCommerceOrder(groupId, userId, commerceAccountId,
			commerceCurrencyId);
	}

	public static com.liferay.commerce.model.CommerceOrder addCommerceOrder(
		long groupId, long userId, long commerceAccountId,
		long commerceCurrencyId, long shippingAddressId,
		String purchaseOrderNumber)
		throws com.liferay.portal.kernel.exception.PortalException {
		return getService()
				   .addCommerceOrder(groupId, userId, commerceAccountId,
			commerceCurrencyId, shippingAddressId, purchaseOrderNumber);
	}

	public static com.liferay.commerce.model.CommerceOrder addCommerceOrder(
		long groupId, long userId, long commerceAccountId,
		long shippingAddressId, String purchaseOrderNumber)
		throws com.liferay.portal.kernel.exception.PortalException {
		return getService()
				   .addCommerceOrder(groupId, userId, commerceAccountId,
			shippingAddressId, purchaseOrderNumber);
	}

	public static com.liferay.commerce.model.CommerceOrder addCommerceOrder(
		long commerceAccountId, long commerceCurrencyId, long billingAddressId,
		long shippingAddressId, String commercePaymentMethodKey,
		long commerceShippingMethodId, String shippingOptionName,
		String purchaseOrderNumber, java.math.BigDecimal subtotal,
		java.math.BigDecimal shippingAmount, java.math.BigDecimal total,
		int paymentStatus, int orderStatus,
		com.liferay.portal.kernel.service.ServiceContext serviceContext)
		throws com.liferay.portal.kernel.exception.PortalException {
		return getService()
				   .addCommerceOrder(commerceAccountId, commerceCurrencyId,
			billingAddressId, shippingAddressId, commercePaymentMethodKey,
			commerceShippingMethodId, shippingOptionName, purchaseOrderNumber,
			subtotal, shippingAmount, total, paymentStatus, orderStatus,
			serviceContext);
	}

	public static com.liferay.commerce.model.CommerceOrder approveCommerceOrder(
		long userId, long commerceOrderId)
		throws com.liferay.portal.kernel.exception.PortalException {
		return getService().approveCommerceOrder(userId, commerceOrderId);
	}

	public static com.liferay.commerce.model.CommerceOrder checkoutCommerceOrder(
		long commerceOrderId,
		com.liferay.commerce.context.CommerceContext commerceContext,
		com.liferay.portal.kernel.service.ServiceContext serviceContext)
		throws com.liferay.portal.kernel.exception.PortalException {
		return getService()
				   .checkoutCommerceOrder(commerceOrderId, commerceContext,
			serviceContext);
	}

	/**
	* Creates a new commerce order with the primary key. Does not add the commerce order to the database.
	*
	* @param commerceOrderId the primary key for the new commerce order
	* @return the new commerce order
	*/
	public static com.liferay.commerce.model.CommerceOrder createCommerceOrder(
		long commerceOrderId) {
		return getService().createCommerceOrder(commerceOrderId);
	}

	/**
	* Deletes the commerce order from the database. Also notifies the appropriate model listeners.
	*
	* @param commerceOrder the commerce order
	* @return the commerce order that was removed
	* @throws PortalException
	*/
	public static com.liferay.commerce.model.CommerceOrder deleteCommerceOrder(
		com.liferay.commerce.model.CommerceOrder commerceOrder)
		throws com.liferay.portal.kernel.exception.PortalException {
		return getService().deleteCommerceOrder(commerceOrder);
	}

	/**
	* Deletes the commerce order with the primary key from the database. Also notifies the appropriate model listeners.
	*
	* @param commerceOrderId the primary key of the commerce order
	* @return the commerce order that was removed
	* @throws PortalException if a commerce order with the primary key could not be found
	*/
	public static com.liferay.commerce.model.CommerceOrder deleteCommerceOrder(
		long commerceOrderId)
		throws com.liferay.portal.kernel.exception.PortalException {
		return getService().deleteCommerceOrder(commerceOrderId);
	}

	public static void deleteCommerceOrders(long groupId)
		throws com.liferay.portal.kernel.exception.PortalException {
		getService().deleteCommerceOrders(groupId);
	}

	public static void deleteCommerceOrders(long userId, java.util.Date date,
		int status) {
		getService().deleteCommerceOrders(userId, date, status);
	}

	/**
	* @throws PortalException
	*/
	public static com.liferay.portal.kernel.model.PersistedModel deletePersistedModel(
		com.liferay.portal.kernel.model.PersistedModel persistedModel)
		throws com.liferay.portal.kernel.exception.PortalException {
		return getService().deletePersistedModel(persistedModel);
	}

	public static com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery() {
		return getService().dynamicQuery();
	}

	/**
	* Performs a dynamic query on the database and returns the matching rows.
	*
	* @param dynamicQuery the dynamic query
	* @return the matching rows
	*/
	public static <T> java.util.List<T> dynamicQuery(
		com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery) {
		return getService().dynamicQuery(dynamicQuery);
	}

	/**
	* Performs a dynamic query on the database and returns a range of the matching rows.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.liferay.commerce.model.impl.CommerceOrderModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param dynamicQuery the dynamic query
	* @param start the lower bound of the range of model instances
	* @param end the upper bound of the range of model instances (not inclusive)
	* @return the range of matching rows
	*/
	public static <T> java.util.List<T> dynamicQuery(
		com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery, int start,
		int end) {
		return getService().dynamicQuery(dynamicQuery, start, end);
	}

	/**
	* Performs a dynamic query on the database and returns an ordered range of the matching rows.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.liferay.commerce.model.impl.CommerceOrderModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param dynamicQuery the dynamic query
	* @param start the lower bound of the range of model instances
	* @param end the upper bound of the range of model instances (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of matching rows
	*/
	public static <T> java.util.List<T> dynamicQuery(
		com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery, int start,
		int end,
		com.liferay.portal.kernel.util.OrderByComparator<T> orderByComparator) {
		return getService()
				   .dynamicQuery(dynamicQuery, start, end, orderByComparator);
	}

	/**
	* Returns the number of rows matching the dynamic query.
	*
	* @param dynamicQuery the dynamic query
	* @return the number of rows matching the dynamic query
	*/
	public static long dynamicQueryCount(
		com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery) {
		return getService().dynamicQueryCount(dynamicQuery);
	}

	/**
	* Returns the number of rows matching the dynamic query.
	*
	* @param dynamicQuery the dynamic query
	* @param projection the projection to apply to the query
	* @return the number of rows matching the dynamic query
	*/
	public static long dynamicQueryCount(
		com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery,
		com.liferay.portal.kernel.dao.orm.Projection projection) {
		return getService().dynamicQueryCount(dynamicQuery, projection);
	}

	public static com.liferay.commerce.model.CommerceOrder executeWorkflowTransition(
		long userId, long commerceOrderId, long workflowTaskId,
		String transitionName, String comment)
		throws com.liferay.portal.kernel.exception.PortalException {
		return getService()
				   .executeWorkflowTransition(userId, commerceOrderId,
			workflowTaskId, transitionName, comment);
	}

	public static com.liferay.commerce.model.CommerceOrder fetchByExternalReferenceCode(
		long companyId, String externalReferenceCode) {
		return getService()
				   .fetchByExternalReferenceCode(companyId,
			externalReferenceCode);
	}

	public static com.liferay.commerce.model.CommerceOrder fetchCommerceOrder(
		long commerceOrderId) {
		return getService().fetchCommerceOrder(commerceOrderId);
	}

	public static com.liferay.commerce.model.CommerceOrder fetchCommerceOrder(
		long commerceAccountId, long groupId, int orderStatus) {
		return getService()
				   .fetchCommerceOrder(commerceAccountId, groupId, orderStatus);
	}

	/**
	* Returns the commerce order with the matching external reference code and company.
	*
	* @param companyId the primary key of the company
	* @param externalReferenceCode the commerce order's external reference code
	* @return the matching commerce order, or <code>null</code> if a matching commerce order could not be found
	*/
	public static com.liferay.commerce.model.CommerceOrder fetchCommerceOrderByReferenceCode(
		long companyId, String externalReferenceCode) {
		return getService()
				   .fetchCommerceOrderByReferenceCode(companyId,
			externalReferenceCode);
	}

	/**
	* Returns the commerce order matching the UUID and group.
	*
	* @param uuid the commerce order's UUID
	* @param groupId the primary key of the group
	* @return the matching commerce order, or <code>null</code> if a matching commerce order could not be found
	*/
	public static com.liferay.commerce.model.CommerceOrder fetchCommerceOrderByUuidAndGroupId(
		String uuid, long groupId) {
		return getService().fetchCommerceOrderByUuidAndGroupId(uuid, groupId);
	}

	public static com.liferay.portal.kernel.dao.orm.ActionableDynamicQuery getActionableDynamicQuery() {
		return getService().getActionableDynamicQuery();
	}

	public static int[] getAvailableOrderStatuses(long commerceOrderId)
		throws com.liferay.portal.kernel.exception.PortalException {
		return getService().getAvailableOrderStatuses(commerceOrderId);
	}

	/**
	* Returns the commerce order with the primary key.
	*
	* @param commerceOrderId the primary key of the commerce order
	* @return the commerce order
	* @throws PortalException if a commerce order with the primary key could not be found
	*/
	public static com.liferay.commerce.model.CommerceOrder getCommerceOrder(
		long commerceOrderId)
		throws com.liferay.portal.kernel.exception.PortalException {
		return getService().getCommerceOrder(commerceOrderId);
	}

	/**
	* Returns the commerce order matching the UUID and group.
	*
	* @param uuid the commerce order's UUID
	* @param groupId the primary key of the group
	* @return the matching commerce order
	* @throws PortalException if a matching commerce order could not be found
	*/
	public static com.liferay.commerce.model.CommerceOrder getCommerceOrderByUuidAndGroupId(
		String uuid, long groupId)
		throws com.liferay.portal.kernel.exception.PortalException {
		return getService().getCommerceOrderByUuidAndGroupId(uuid, groupId);
	}

	/**
	* Returns a range of all the commerce orders.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.liferay.commerce.model.impl.CommerceOrderModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param start the lower bound of the range of commerce orders
	* @param end the upper bound of the range of commerce orders (not inclusive)
	* @return the range of commerce orders
	*/
	public static java.util.List<com.liferay.commerce.model.CommerceOrder> getCommerceOrders(
		int start, int end) {
		return getService().getCommerceOrders(start, end);
	}

	public static java.util.List<com.liferay.commerce.model.CommerceOrder> getCommerceOrders(
		long groupId, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<com.liferay.commerce.model.CommerceOrder> orderByComparator) {
		return getService()
				   .getCommerceOrders(groupId, start, end, orderByComparator);
	}

	public static java.util.List<com.liferay.commerce.model.CommerceOrder> getCommerceOrders(
		long groupId, int[] orderStatuses) {
		return getService().getCommerceOrders(groupId, orderStatuses);
	}

	public static java.util.List<com.liferay.commerce.model.CommerceOrder> getCommerceOrders(
		long groupId, long commerceAccountId, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<com.liferay.commerce.model.CommerceOrder> orderByComparator) {
		return getService()
				   .getCommerceOrders(groupId, commerceAccountId, start, end,
			orderByComparator);
	}

	public static java.util.List<com.liferay.commerce.model.CommerceOrder> getCommerceOrdersByBillingAddress(
		long billingAddressId) {
		return getService().getCommerceOrdersByBillingAddress(billingAddressId);
	}

	public static java.util.List<com.liferay.commerce.model.CommerceOrder> getCommerceOrdersByShippingAddress(
		long shippingAddressId) {
		return getService().getCommerceOrdersByShippingAddress(shippingAddressId);
	}

	/**
	* Returns all the commerce orders matching the UUID and company.
	*
	* @param uuid the UUID of the commerce orders
	* @param companyId the primary key of the company
	* @return the matching commerce orders, or an empty list if no matches were found
	*/
	public static java.util.List<com.liferay.commerce.model.CommerceOrder> getCommerceOrdersByUuidAndCompanyId(
		String uuid, long companyId) {
		return getService().getCommerceOrdersByUuidAndCompanyId(uuid, companyId);
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
	public static java.util.List<com.liferay.commerce.model.CommerceOrder> getCommerceOrdersByUuidAndCompanyId(
		String uuid, long companyId, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<com.liferay.commerce.model.CommerceOrder> orderByComparator) {
		return getService()
				   .getCommerceOrdersByUuidAndCompanyId(uuid, companyId, start,
			end, orderByComparator);
	}

	/**
	* Returns the number of commerce orders.
	*
	* @return the number of commerce orders
	*/
	public static int getCommerceOrdersCount() {
		return getService().getCommerceOrdersCount();
	}

	public static int getCommerceOrdersCount(long groupId) {
		return getService().getCommerceOrdersCount(groupId);
	}

	public static int getCommerceOrdersCount(long groupId,
		long commerceAccountId) {
		return getService().getCommerceOrdersCount(groupId, commerceAccountId);
	}

	public static com.liferay.portal.kernel.dao.orm.ExportActionableDynamicQuery getExportActionableDynamicQuery(
		com.liferay.exportimport.kernel.lar.PortletDataContext portletDataContext) {
		return getService().getExportActionableDynamicQuery(portletDataContext);
	}

	public static com.liferay.portal.kernel.dao.orm.IndexableActionableDynamicQuery getIndexableActionableDynamicQuery() {
		return getService().getIndexableActionableDynamicQuery();
	}

	/**
	* Returns the OSGi service identifier.
	*
	* @return the OSGi service identifier
	*/
	public static String getOSGiServiceIdentifier() {
		return getService().getOSGiServiceIdentifier();
	}

	public static com.liferay.portal.kernel.model.PersistedModel getPersistedModel(
		java.io.Serializable primaryKeyObj)
		throws com.liferay.portal.kernel.exception.PortalException {
		return getService().getPersistedModel(primaryKeyObj);
	}

	public static java.util.List<com.liferay.commerce.model.CommerceOrder> getUserCommerceOrders(
		long groupId, long userId, long commerceAccountId, Integer orderStatus,
		boolean excludeOrderStatus, String keywords, int start, int end) {
		return getService()
				   .getUserCommerceOrders(groupId, userId, commerceAccountId,
			orderStatus, excludeOrderStatus, keywords, start, end);
	}

	public static int getUserCommerceOrdersCount(long groupId, long userId,
		long commerceAccountId, Integer orderStatus,
		boolean excludeOrderStatus, String keywords) {
		return getService()
				   .getUserCommerceOrdersCount(groupId, userId,
			commerceAccountId, orderStatus, excludeOrderStatus, keywords);
	}

	public static void mergeGuestCommerceOrder(long guestCommerceOrderId,
		long userCommerceOrderId,
		com.liferay.commerce.context.CommerceContext commerceContext,
		com.liferay.portal.kernel.service.ServiceContext serviceContext)
		throws com.liferay.portal.kernel.exception.PortalException {
		getService()
			.mergeGuestCommerceOrder(guestCommerceOrderId, userCommerceOrderId,
			commerceContext, serviceContext);
	}

	public static com.liferay.commerce.model.CommerceOrder recalculatePrice(
		long commerceOrderId,
		com.liferay.commerce.context.CommerceContext commerceContext)
		throws com.liferay.portal.kernel.exception.PortalException {
		return getService().recalculatePrice(commerceOrderId, commerceContext);
	}

	public static com.liferay.commerce.model.CommerceOrder reorderCommerceOrder(
		long userId, long commerceOrderId,
		com.liferay.commerce.context.CommerceContext commerceContext)
		throws com.liferay.portal.kernel.exception.PortalException {
		return getService()
				   .reorderCommerceOrder(userId, commerceOrderId,
			commerceContext);
	}

	public static com.liferay.commerce.model.CommerceOrder resetCommerceOrderShipping(
		long commerceOrderId)
		throws com.liferay.portal.kernel.exception.PortalException {
		return getService().resetCommerceOrderShipping(commerceOrderId);
	}

	public static com.liferay.portal.kernel.search.BaseModelSearchResult<com.liferay.commerce.model.CommerceOrder> searchCommerceOrders(
		com.liferay.portal.kernel.search.SearchContext searchContext)
		throws com.liferay.portal.kernel.exception.PortalException {
		return getService().searchCommerceOrders(searchContext);
	}

	public static long searchCommerceOrdersCount(
		com.liferay.portal.kernel.search.SearchContext searchContext)
		throws com.liferay.portal.kernel.exception.PortalException {
		return getService().searchCommerceOrdersCount(searchContext);
	}

	public static com.liferay.commerce.model.CommerceOrder submitCommerceOrder(
		long userId, long commerceOrderId)
		throws com.liferay.portal.kernel.exception.PortalException {
		return getService().submitCommerceOrder(userId, commerceOrderId);
	}

	public static com.liferay.commerce.model.CommerceOrder updateBillingAddress(
		long commerceOrderId, String name, String description, String street1,
		String street2, String street3, String city, String zip,
		long commerceRegionId, long commerceCountryId, String phoneNumber,
		com.liferay.portal.kernel.service.ServiceContext serviceContext)
		throws com.liferay.portal.kernel.exception.PortalException {
		return getService()
				   .updateBillingAddress(commerceOrderId, name, description,
			street1, street2, street3, city, zip, commerceRegionId,
			commerceCountryId, phoneNumber, serviceContext);
	}

	/**
	* Updates the commerce order in the database or adds it if it does not yet exist. Also notifies the appropriate model listeners.
	*
	* @param commerceOrder the commerce order
	* @return the commerce order that was updated
	*/
	public static com.liferay.commerce.model.CommerceOrder updateCommerceOrder(
		com.liferay.commerce.model.CommerceOrder commerceOrder) {
		return getService().updateCommerceOrder(commerceOrder);
	}

	public static com.liferay.commerce.model.CommerceOrder updateCommerceOrder(
		long commerceOrderId, long billingAddressId, long shippingAddressId,
		String commercePaymentMethodKey, long commerceShippingMethodId,
		String shippingOptionName, String purchaseOrderNumber,
		java.math.BigDecimal subtotal, java.math.BigDecimal shippingAmount,
		java.math.BigDecimal total, String advanceStatus,
		com.liferay.commerce.context.CommerceContext commerceContext)
		throws com.liferay.portal.kernel.exception.PortalException {
		return getService()
				   .updateCommerceOrder(commerceOrderId, billingAddressId,
			shippingAddressId, commercePaymentMethodKey,
			commerceShippingMethodId, shippingOptionName, purchaseOrderNumber,
			subtotal, shippingAmount, total, advanceStatus, commerceContext);
	}

	public static com.liferay.commerce.model.CommerceOrder updateCommerceOrder(
		long commerceOrderId, long billingAddressId, long shippingAddressId,
		String commercePaymentMethodKey, long commerceShippingMethodId,
		String shippingOptionName, String purchaseOrderNumber,
		java.math.BigDecimal subtotal, java.math.BigDecimal shippingAmount,
		java.math.BigDecimal total, String advanceStatus,
		String externalReferenceCode,
		com.liferay.commerce.context.CommerceContext commerceContext)
		throws com.liferay.portal.kernel.exception.PortalException {
		return getService()
				   .updateCommerceOrder(commerceOrderId, billingAddressId,
			shippingAddressId, commercePaymentMethodKey,
			commerceShippingMethodId, shippingOptionName, purchaseOrderNumber,
			subtotal, shippingAmount, total, advanceStatus,
			externalReferenceCode, commerceContext);
	}

	public static com.liferay.commerce.model.CommerceOrder updateOrderStatus(
		long commerceOrderId, int orderStatus)
		throws com.liferay.portal.kernel.exception.PortalException {
		return getService().updateOrderStatus(commerceOrderId, orderStatus);
	}

	public static com.liferay.commerce.model.CommerceOrder updatePaymentStatus(
		long userId, long commerceOrderId, int paymentStatus)
		throws com.liferay.portal.kernel.exception.PortalException {
		return getService()
				   .updatePaymentStatus(userId, commerceOrderId, paymentStatus);
	}

	public static com.liferay.commerce.model.CommerceOrder updatePaymentStatusAndTransactionId(
		long userId, long commerceOrderId, int paymentStatus,
		String transactionId)
		throws com.liferay.portal.kernel.exception.PortalException {
		return getService()
				   .updatePaymentStatusAndTransactionId(userId,
			commerceOrderId, paymentStatus, transactionId);
	}

	public static com.liferay.commerce.model.CommerceOrder updatePurchaseOrderNumber(
		long commerceOrderId, String purchaseOrderNumber)
		throws com.liferay.portal.kernel.exception.PortalException {
		return getService()
				   .updatePurchaseOrderNumber(commerceOrderId,
			purchaseOrderNumber);
	}

	public static com.liferay.commerce.model.CommerceOrder updateShippingAddress(
		long commerceOrderId, String name, String description, String street1,
		String street2, String street3, String city, String zip,
		long commerceRegionId, long commerceCountryId, String phoneNumber,
		com.liferay.portal.kernel.service.ServiceContext serviceContext)
		throws com.liferay.portal.kernel.exception.PortalException {
		return getService()
				   .updateShippingAddress(commerceOrderId, name, description,
			street1, street2, street3, city, zip, commerceRegionId,
			commerceCountryId, phoneNumber, serviceContext);
	}

	public static com.liferay.commerce.model.CommerceOrder updateShippingMethod(
		long commerceOrderId, long commerceShippingMethodId,
		String shippingOptionName, java.math.BigDecimal shippingAmount,
		com.liferay.commerce.context.CommerceContext commerceContext)
		throws com.liferay.portal.kernel.exception.PortalException {
		return getService()
				   .updateShippingMethod(commerceOrderId,
			commerceShippingMethodId, shippingOptionName, shippingAmount,
			commerceContext);
	}

	public static com.liferay.commerce.model.CommerceOrder updateStatus(
		long userId, long commerceOrderId, int status,
		com.liferay.portal.kernel.service.ServiceContext serviceContext,
		java.util.Map<String, java.io.Serializable> workflowContext)
		throws com.liferay.portal.kernel.exception.PortalException {
		return getService()
				   .updateStatus(userId, commerceOrderId, status,
			serviceContext, workflowContext);
	}

	public static com.liferay.commerce.model.CommerceOrder updateTransactionId(
		long commerceOrderId, String transactionId)
		throws com.liferay.portal.kernel.exception.PortalException {
		return getService().updateTransactionId(commerceOrderId, transactionId);
	}

	public static com.liferay.commerce.model.CommerceOrder updateUser(
		long commerceOrderId, long userId)
		throws com.liferay.portal.kernel.exception.PortalException {
		return getService().updateUser(commerceOrderId, userId);
	}

	public static com.liferay.commerce.model.CommerceOrder upsertCommerceOrder(
		long commerceAccountId, long commerceCurrencyId, long billingAddressId,
		long shippingAddressId, String commercePaymentMethodKey,
		long commerceShippingMethodId, String shippingOptionName,
		String purchaseOrderNumber, java.math.BigDecimal subtotal,
		java.math.BigDecimal shippingAmount, java.math.BigDecimal total,
		int paymentStatus, int orderStatus, String advanceStatus,
		String externalReferenceCode,
		com.liferay.commerce.context.CommerceContext commerceContext,
		com.liferay.portal.kernel.service.ServiceContext serviceContext)
		throws com.liferay.portal.kernel.exception.PortalException {
		return getService()
				   .upsertCommerceOrder(commerceAccountId, commerceCurrencyId,
			billingAddressId, shippingAddressId, commercePaymentMethodKey,
			commerceShippingMethodId, shippingOptionName, purchaseOrderNumber,
			subtotal, shippingAmount, total, paymentStatus, orderStatus,
			advanceStatus, externalReferenceCode, commerceContext,
			serviceContext);
	}

	public static CommerceOrderLocalService getService() {
		return _serviceTracker.getService();
	}

	private static ServiceTracker<CommerceOrderLocalService, CommerceOrderLocalService> _serviceTracker;

	static {
		Bundle bundle = FrameworkUtil.getBundle(CommerceOrderLocalService.class);

		ServiceTracker<CommerceOrderLocalService, CommerceOrderLocalService> serviceTracker =
			new ServiceTracker<CommerceOrderLocalService, CommerceOrderLocalService>(bundle.getBundleContext(),
				CommerceOrderLocalService.class, null);

		serviceTracker.open();

		_serviceTracker = serviceTracker;
	}
}