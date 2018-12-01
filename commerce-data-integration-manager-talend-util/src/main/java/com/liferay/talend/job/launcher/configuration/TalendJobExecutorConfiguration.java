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

package com.liferay.talend.job.launcher.configuration;

import aQute.bnd.annotation.metatype.Meta;

import com.liferay.portal.configuration.metatype.annotations.ExtendedObjectClassDefinition;

/**
 * @author guywandji
 */
@ExtendedObjectClassDefinition(
	category = "data-integration",
	scope = ExtendedObjectClassDefinition.Scope.SYSTEM
)
@Meta.OCD(
	id = "com.liferay.talend.job.launcher.configuration.TalendJobExecutorConfiguration",
	localization = "content/Language",
	name = "talend-job-launcher-configuration-name"
)
public interface TalendJobExecutorConfiguration {

	@Meta.AD(deflt = "-Xms256M", name = "xms-arg", required = false)
	public String xms();

	@Meta.AD(deflt = "-Xmx1024M", name = "xmx-arg", required = false)
	public String xmx();

	@Meta.AD(deflt = "/lib", name = "lib-folder-name", required = false)
	public String libFolderName();

	@Meta.AD(deflt = "\"$@\"", name = "end-command-string", required = false)
	public String endCommandChar();

	@Meta.AD(deflt = "Default", name = "context", required = false)
	public String context();

}