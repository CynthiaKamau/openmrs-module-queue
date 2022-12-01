/*
 * This Source Code Form is subject to the terms of the Mozilla Public License,
 * v. 2.0. If a copy of the MPL was not distributed with this file, You can
 * obtain one at http://mozilla.org/MPL/2.0/. OpenMRS is also distributed under
 * the terms of the Healthcare Disclaimer located at http://openmrs.org/license.
 *
 * Copyright (C) OpenMRS Inc. OpenMRS is a registered trademark and the OpenMRS
 * graphic logo is a trademark of OpenMRS Inc.
 */
package org.openmrs.module.queue.api;

import javax.validation.constraints.NotNull;

import java.util.Collection;
import java.util.Optional;

import org.openmrs.api.APIException;
import org.openmrs.module.queue.model.QueueRoom;

/**
 * This interface defines methods for Queue objects
 */
public interface QueueRoomService {
	
	/**
	 * Gets a queue given UUID.
	 *
	 * @param uuid uuid of the queue to be returned.
	 * @return {@link org.openmrs.module.queue.model.Queue}
	 */
	Optional<QueueRoom> getQueueRoomByUuid(@NotNull String uuid);
	
	/**
	 * Gets a queue by id.
	 *
	 * @param id queueId - the id of the queue to retrieve.
	 * @return {@link org.openmrs.module.queue.model.Queue}
	 */
	Optional<QueueRoom> getQueueRoomById(@NotNull Integer id);
	
	/**
	 * Saves a queue
	 *
	 * @param queue the queue to be saved
	 * @return saved {@link org.openmrs.module.queue.model.Queue}
	 */
	QueueRoom createQueueRoom(@NotNull QueueRoom queueRoom);
	
	/**
	 * Gets all queues
	 *
	 * @return all queues
	 */
	Collection<QueueRoom> getAllQueueRooms();
	
	/**
	 * Voids a queue
	 *
	 * @param queueUuid uuid of the queue to be voided
	 * @param voidReason the reason for voiding the queue
	 */
	void voidQueueRoom(@NotNull String queueRoomUuid, String voidReason);
	
	/**
	 * Completely remove a queue from the database
	 *
	 * @param queue queue to be deleted
	 * @throws APIException <strong>Should</strong> delete the given queue from the database
	 */
	void purgeQueueRoom(@NotNull QueueRoom queueRoom) throws APIException;
	
}
