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

package com.liferay.commerce.order.content.web.internal.render;

import com.liferay.commerce.order.content.render.CommerceOrderDetailRenderer;
import com.liferay.commerce.order.content.render.CommerceOrderDetailRendererRegistry;
import com.liferay.commerce.order.content.web.internal.render.util.comparator.CommerceOrderDetailRendererServiceWrapperOrderComparator;
import com.liferay.osgi.service.tracker.collections.map.ServiceTrackerCustomizerFactory;
import com.liferay.osgi.service.tracker.collections.map.ServiceTrackerCustomizerFactory.ServiceWrapper;
import com.liferay.osgi.service.tracker.collections.map.ServiceTrackerMap;
import com.liferay.osgi.service.tracker.collections.map.ServiceTrackerMapFactory;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.util.ArrayUtil;
import com.liferay.portal.kernel.util.GetterUtil;
import com.liferay.portal.kernel.util.ListUtil;
import com.liferay.portal.kernel.util.Validator;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Map;

import org.osgi.framework.BundleContext;
import org.osgi.service.component.annotations.Activate;
import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Deactivate;

/**
 * @author Alessio Antonio Rendina
 */
@Component(
	immediate = true, service = CommerceOrderDetailRendererRegistry.class
)
public class CommerceOrderDetailRendererRegistryImpl
	implements CommerceOrderDetailRendererRegistry {

	@Override
	public CommerceOrderDetailRenderer getCommerceOrderDetailRenderer(
		String key) {

		if (Validator.isNull(key)) {
			return null;
		}

		ServiceWrapper<CommerceOrderDetailRenderer>
			commerceOrderDetailRendererServiceWrapper =
				_commerceOrderDetailRendererServiceTrackerMap.getService(key);

		if (commerceOrderDetailRendererServiceWrapper == null) {
			if (_log.isDebugEnabled()) {
				_log.debug(
					"No CommerceOrderDetailRenderer registered with key " +
						key);
			}

			return null;
		}

		return commerceOrderDetailRendererServiceWrapper.getService();
	}

	@Override
	public List<CommerceOrderDetailRenderer> getCommerceOrderDetailRenderers(
		String orderDetailStatus) {

		List<CommerceOrderDetailRenderer> commerceOrderDetailRenderers =
			new ArrayList<>();

		List<ServiceWrapper<CommerceOrderDetailRenderer>>
			commerceOrderDetailRendererServiceWrappers =
				ListUtil.fromCollection(
					_commerceOrderDetailRendererServiceTrackerMap.values());

		Collections.sort(
			commerceOrderDetailRendererServiceWrappers,
			_cpContentRendererServiceWrapperOrderComparator);

		for (ServiceWrapper<CommerceOrderDetailRenderer>
				commerceOrderDetailRendererServiceWrapper :
					commerceOrderDetailRendererServiceWrappers) {

			if (Validator.isNotNull(orderDetailStatus)) {
				Map<String, Object>
					commerceOrderDetailRendererServiceWrapperProperties =
						commerceOrderDetailRendererServiceWrapper.
							getProperties();

				Object orderDetailStatusObject =
					commerceOrderDetailRendererServiceWrapperProperties.get(
						"commerce.order.detail.renderer.status");

				if (orderDetailStatusObject instanceof String[]) {
					String[] orderDetailStatuses = GetterUtil.getStringValues(
						orderDetailStatusObject);

					if (ArrayUtil.contains(
							orderDetailStatuses, orderDetailStatus)) {

						commerceOrderDetailRenderers.add(
							commerceOrderDetailRendererServiceWrapper.
								getService());
					}
				}
				else if ((orderDetailStatusObject instanceof String) &&
						 orderDetailStatus.equals(
							 GetterUtil.getString(orderDetailStatusObject))) {

					commerceOrderDetailRenderers.add(
						commerceOrderDetailRendererServiceWrapper.getService());
				}
			}
		}

		return Collections.unmodifiableList(commerceOrderDetailRenderers);
	}

	@Activate
	protected void activate(BundleContext bundleContext) {
		_commerceOrderDetailRendererServiceTrackerMap =
			ServiceTrackerMapFactory.openSingleValueMap(
				bundleContext, CommerceOrderDetailRenderer.class,
				"commerce.order.detail.renderer.key",
				ServiceTrackerCustomizerFactory.
					<CommerceOrderDetailRenderer>serviceWrapper(bundleContext));
	}

	@Deactivate
	protected void deactivate() {
		_commerceOrderDetailRendererServiceTrackerMap.close();
	}

	private static final Log _log = LogFactoryUtil.getLog(
		CommerceOrderDetailRendererRegistryImpl.class);

	private ServiceTrackerMap
		<String, ServiceWrapper<CommerceOrderDetailRenderer>>
			_commerceOrderDetailRendererServiceTrackerMap;
	private final Comparator<ServiceWrapper<CommerceOrderDetailRenderer>>
		_cpContentRendererServiceWrapperOrderComparator =
			new CommerceOrderDetailRendererServiceWrapperOrderComparator();

}