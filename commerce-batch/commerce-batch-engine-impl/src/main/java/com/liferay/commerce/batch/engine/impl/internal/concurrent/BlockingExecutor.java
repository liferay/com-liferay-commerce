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

package com.liferay.commerce.batch.engine.impl.internal.concurrent;

import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;

import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.RejectedExecutionException;
import java.util.concurrent.Semaphore;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 * An executor which blocks and prevents further tasks from
 * being submitted to the pool when the queue is full.
 * <p>
 * Based on the BoundedExecutor example in:
 * Brian Goetz, 2006. Java Concurrency in Practice. (Listing 8.4)
 *
 * @author Ivica Cardic
 */
public class BlockingExecutor extends ThreadPoolExecutor {

	/**
	 * Creates a BlockingExecutor which will block and prevent further
	 * submission to the pool when the specified queue size has been reached.
	 *
	 * @param poolSize the number of the threads in the pool
	 * @param queueSize the size of the queue
	 */
	public BlockingExecutor(final int poolSize, final int queueSize) {
		super(
			poolSize, poolSize, 0L, TimeUnit.MILLISECONDS,
			new LinkedBlockingQueue<>());

		// The semaphore is bounding both the number of tasks currently
		// executing and those queued up

		_semaphore = new Semaphore(poolSize + queueSize);
	}

	public void destroy() {
		shutdown();
	}

	/**
	 * Executes the given task.
	 * This method will block when the semaphore has no permits
	 * i.e. when the queue has reached its capacity.
	 */
	@Override
	public void execute(Runnable runnable) {
		boolean acquired = false;

		do {
			try {
				_semaphore.acquire();

				acquired = true;
			}
			catch (InterruptedException ie) {
				if (_log.isWarnEnabled()) {
					_log.warn(
						"Interrupted exception while acquiring semaphore", ie);
				}
			}
		}
		while (!acquired);

		try {
			super.execute(runnable);
		}
		catch (RejectedExecutionException ree) {
			_semaphore.release();

			throw ree;
		}
		finally {
			_semaphore.release();
		}
	}

	private static final Log _log = LogFactoryUtil.getLog(
		BlockingExecutor.class);

	private final Semaphore _semaphore;

}