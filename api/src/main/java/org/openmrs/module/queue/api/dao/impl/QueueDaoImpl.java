/*
 * This Source Code Form is subject to the terms of the Mozilla Public License,
 * v. 2.0. If a copy of the MPL was not distributed with this file, You can
 * obtain one at http://mozilla.org/MPL/2.0/. OpenMRS is also distributed under
 * the terms of the Healthcare Disclaimer located at http://openmrs.org/license.
 *
 * Copyright (C) OpenMRS Inc. OpenMRS is a registered trademark and the OpenMRS
 * graphic logo is a trademark of OpenMRS Inc.
 */
package org.openmrs.module.queue.api.dao.impl;

import javax.validation.constraints.NotNull;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Restrictions;
import org.openmrs.module.queue.api.dao.QueueDao;
import org.openmrs.module.queue.model.Queue;
import org.springframework.beans.factory.annotation.Qualifier;

@SuppressWarnings("unchecked")
public class QueueDaoImpl extends AbstractBaseQueueDaoImpl<Queue> implements QueueDao<Queue> {
	
	public QueueDaoImpl(@Qualifier(value = "sessionFactory") SessionFactory sessionFactory) {
		super(sessionFactory);
	}
	
	@Override
	public List<Queue> getAllQueuesByLocation(@NotNull String locationUuid) {
		return this.getAllQueuesByLocation(locationUuid, false);
	}
	
	@Override
	public List<Queue> getAllQueuesByLocationAndQueueRoom(String locationUuid, String queueRoomUuid) {
		Criteria criteria = getCurrentSession().createCriteria(Queue.class, "q");
		//Include/exclude retired queues
		includeVoidedObjects(criteria, false);
		//		if (locationUuid != null) {
		//			criteria.add(Restrictions.eq("q.locationUuid", locationUuid));
		//		}
		//		if (queueRoomUuid != null) {
		//			criteria.add(Restrictions.eq("q.queueRoomUuid", queueRoomUuid));
		//		}
		
		//		Criteria locationCriteria = criteria.createCriteria("location", "ql");
		//		locationCriteria.add(Restrictions.eq("ql.uuid", locationUuid));
		Criteria queueRoomCriteria = criteria.createCriteria("queueRoom", "qr");
		queueRoomCriteria.add(Restrictions.eq("qr.uuid", queueRoomUuid));
		
		//		CriteriaBuilder builder = entityManager.getCriteriaBuilder();
		//		CriteriaQuery<Queue> criteria = builder.createQuery(Queue.class);
		//		Root<Queue> queueRoot = criteria.from(Queue.class);
		//		criteria.where(builder.and(builder.equal(queueRoot.get("location").get("uuid"), locationUuid),
		//		    builder.equal(queueRoot.get("queueRoom").get("uuid"), queueRoomUuid)));
		
		return (List<Queue>) criteria.list();
	}
	
	@Override
	public List<Queue> getAllQueuesByLocation(@NotNull String locationUuid, boolean includeVoided) {
		Criteria criteria = getCurrentSession().createCriteria(Queue.class);
		//Include/exclude retired queues
		includeVoidedObjects(criteria, includeVoided);
		Criteria locationCriteria = criteria.createCriteria("location", "ql");
		locationCriteria.add(Restrictions.eq("ql.uuid", locationUuid));
		return (List<Queue>) locationCriteria.list();
	}
}
