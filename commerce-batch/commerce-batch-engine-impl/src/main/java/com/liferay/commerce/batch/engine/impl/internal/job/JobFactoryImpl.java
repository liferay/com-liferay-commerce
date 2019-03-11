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

package com.liferay.commerce.batch.engine.impl.internal.job;

import com.liferay.commerce.batch.engine.api.item.ItemReader;
import com.liferay.commerce.batch.engine.api.item.ItemRegistry;
import com.liferay.commerce.batch.engine.api.item.ItemWriter;
import com.liferay.commerce.batch.engine.api.job.Job;
import com.liferay.commerce.batch.engine.api.job.JobFactory;

import java.util.Dictionary;
import java.util.Hashtable;
import java.util.Map;
import java.util.Objects;
import java.util.UUID;
import java.util.concurrent.ConcurrentHashMap;

import org.osgi.service.component.ComponentFactory;
import org.osgi.service.component.ComponentInstance;
import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;

/**
 * @author Ivica Cardic
 */
@Component(immediate = true, service = JobFactory.class)
public class JobFactoryImpl implements JobFactory {

	@Override
	public Job create(
		String name, ItemReader itemReader, ItemWriter itemWriter) {

		return _createJob(
			_generateJobKey(), Objects.requireNonNull(name),
			Objects.requireNonNull(itemReader),
			Objects.requireNonNull(itemWriter));
	}

	@Override
	public Job create(
		String key, String name, ItemReader itemReader, ItemWriter itemWriter) {

		return _createJob(
			Objects.requireNonNull(key), Objects.requireNonNull(name),
			Objects.requireNonNull(itemReader),
			Objects.requireNonNull(itemWriter));
	}

	@Override
	public void dispose(Job job) {
		job.dispose();

		ComponentInstance componentInstance = _componentInstanceMap.get(job);

		if (componentInstance != null) {
			componentInstance.dispose();
		}
	}

	private JobImpl _createJob(
		String key, String name, ItemReader itemReader, ItemWriter itemWriter) {

		Dictionary<String, String> properties = new Hashtable<>();

		ComponentInstance componentInstance = _jobComponentFactory.newInstance(
			properties);

		JobImpl jobImpl = (JobImpl)componentInstance.getInstance();

		_componentInstanceMap.put(jobImpl, componentInstance);

		jobImpl.setItemReader(itemReader);
		jobImpl.setItemWriter(itemWriter);
		jobImpl.setKey(key);
		jobImpl.setName(name);

		return jobImpl;
	}

	private String _generateJobKey() {
		UUID uuid = UUID.randomUUID();

		return uuid.toString();
	}

	private final Map<Job, ComponentInstance> _componentInstanceMap =
		new ConcurrentHashMap<>();

	@Reference
	private ItemRegistry _itemRegistry;

	@Reference(target = "(component.factory=JobImpl)")
	private ComponentFactory _jobComponentFactory;

}