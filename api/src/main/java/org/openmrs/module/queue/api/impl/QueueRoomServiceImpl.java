/*
 * This Source Code Form is subject to the terms of the Mozilla Public License,
 * v. 2.0. If a copy of the MPL was not distributed with this file, You can
 * obtain one at http://mozilla.org/MPL/2.0/. OpenMRS is also distributed under
 * the terms of the Healthcare Disclaimer located at http://openmrs.org/license.
 *
 * Copyright (C) OpenMRS Inc. OpenMRS is a registered trademark and the OpenMRS
 * graphic logo is a trademark of OpenMRS Inc.
 */
package org.openmrs.module.queue.api.impl;

import javax.validation.constraints.NotNull;

import java.util.Collection;
import java.util.Date;
import java.util.Optional;

import lombok.AccessLevel;
import lombok.Setter;
<<<<<<< HEAD
<<<<<<< HEAD
import lombok.extern.slf4j.Slf4j;
=======
>>>>>>> 92f473e (work in progress)
=======
import lombok.extern.slf4j.Slf4j;
>>>>>>> 20804b0 (work in progress)
import org.openmrs.api.APIException;
import org.openmrs.api.context.Context;
import org.openmrs.api.impl.BaseOpenmrsService;
import org.openmrs.module.queue.api.QueueRoomService;
import org.openmrs.module.queue.api.dao.QueueRoomDao;
import org.openmrs.module.queue.model.QueueRoom;
import org.springframework.transaction.annotation.Transactional;

<<<<<<< HEAD
<<<<<<< HEAD
@Slf4j
=======
>>>>>>> 92f473e (work in progress)
=======
@Slf4j
>>>>>>> 20804b0 (work in progress)
@Transactional
@Setter(AccessLevel.MODULE)
public class QueueRoomServiceImpl extends BaseOpenmrsService implements QueueRoomService {
	
	private QueueRoomDao<QueueRoom> dao;
	
	public void setDao(QueueRoomDao<QueueRoom> dao) {
		this.dao = dao;
	}
	
	/**
	 * @see org.openmrs.module.queue.api.QueueService#getQueueByUuid(String)
	 */
	@Override
	@Transactional(readOnly = true)
	public Optional<QueueRoom> getQueueRoomByUuid(@NotNull String queueRoomUuid) {
		return this.dao.get(queueRoomUuid);
	}
	
	/**
	 * @see org.openmrs.module.queue.api.QueueService#getQueueById(Integer)
	 */
	@Override
	@Transactional(readOnly = true)
	public Optional<QueueRoom> getQueueRoomById(@NotNull Integer queueRoomId) {
		return this.dao.get(queueRoomId);
	}
	
	/**
	 * @see org.openmrs.module.queue.api.QueueService#createQueue(org.openmrs.module.queue.model.Queue)
	 */
	@Override
	public QueueRoom createQueueRoom(@NotNull QueueRoom queueRoom) {
		return this.dao.createOrUpdate(queueRoom);
	}
	
	/**
	 * @see org.openmrs.module.queue.api.QueueService#getAllQueues()
	 */
	@Override
	@Transactional(readOnly = true)
	public Collection<QueueRoom> getAllQueueRooms() {
		return this.dao.findAll();
	}
	
	/**
	 * @see org.openmrs.module.queue.api.QueueService#voidQueue(String, String)
	 */
	@Override
	public void voidQueueRoom(@NotNull String queueRoomUuid, String voidReason) {
		this.dao.get(queueRoomUuid).ifPresent(queueRoom -> {
			queueRoom.setRetired(true);
			queueRoom.setDateRetired(new Date());
			queueRoom.setRetireReason(voidReason);
			queueRoom.setRetiredBy(Context.getAuthenticatedUser());
			//Effect the change
			this.dao.createOrUpdate(queueRoom);
		});
	}
	
	/**
	 * @see org.openmrs.module.queue.api.QueueService#purgeQueue(org.openmrs.module.queue.model.Queue)
	 */
	@Override
	public void purgeQueueRoom(QueueRoom queueRoom) throws APIException {
		this.dao.delete(queueRoom);
	}
}
