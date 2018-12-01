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

package com.liferay.commerce.data.integration.manager.model;

import aQute.bnd.annotation.ProviderType;

import com.liferay.portal.kernel.annotation.ImplementationClassName;
import com.liferay.portal.kernel.model.PersistedModel;
import com.liferay.portal.kernel.util.Accessor;

/**
 * The extended model interface for the ScheduledTask service. Represents a row in the &quot;ScheduledTask&quot; database table, with each column mapped to a property of this class.
 *
 * @author Brian Wing Shun Chan
 * @see ScheduledTaskModel
 * @see com.liferay.data.integration.model.impl.ScheduledTaskImpl
 * @see com.liferay.data.integration.model.impl.ScheduledTaskModelImpl
 * @generated
 */
@ImplementationClassName("com.liferay.data.integration.model.impl.ScheduledTaskImpl")
@ProviderType
public interface ScheduledTask extends ScheduledTaskModel, PersistedModel {
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify this interface directly. Add methods to {@link com.liferay.data.integration.model.impl.ScheduledTaskImpl} and rerun ServiceBuilder to automatically copy the method declarations to this interface.
	 */
	public static final Accessor<ScheduledTask, Long> SCHEDULED_TASK_ID_ACCESSOR =
		new Accessor<ScheduledTask, Long>() {
			@Override
			public Long get(ScheduledTask scheduledTask) {
				return scheduledTask.getScheduledTaskId();
			}

			@Override
			public Class<Long> getAttributeClass() {
				return Long.class;
			}

			@Override
			public Class<ScheduledTask> getTypeClass() {
				return ScheduledTask.class;
			}
		};
}