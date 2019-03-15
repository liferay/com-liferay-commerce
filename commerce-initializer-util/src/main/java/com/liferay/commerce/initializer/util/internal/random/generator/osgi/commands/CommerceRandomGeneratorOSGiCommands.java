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

package com.liferay.commerce.initializer.util.internal.random.generator.osgi.commands;

import com.liferay.commerce.initializer.util.random.generator.CommerceOrderRandomGenerator;
import com.liferay.commerce.initializer.util.random.generator.CommerceShipmentRandomGenerator;

import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;

/**
 * @author Alessio Antonio Rendina
 */
@Component(
	immediate = true,
	property = {
		"osgi.command.function=generateRandomOrders",
		"osgi.command.function=generateRandomShipments",
		"osgi.command.scope=commerce"
	},
	service = CommerceRandomGeneratorOSGiCommands.class
)
public class CommerceRandomGeneratorOSGiCommands {

	public void generateRandomOrders(long groupId, int ordersCount)
		throws Exception {

		_commerceOrderRandomGenerator.generate(groupId, ordersCount);
	}

	public void generateRandomShipments(long groupId, int shipmentsCount)
		throws Exception {

		_commerceShipmentRandomGenerator.generate(groupId, shipmentsCount);
	}

	@Reference
	private CommerceOrderRandomGenerator _commerceOrderRandomGenerator;

	@Reference
	private CommerceShipmentRandomGenerator _commerceShipmentRandomGenerator;

}