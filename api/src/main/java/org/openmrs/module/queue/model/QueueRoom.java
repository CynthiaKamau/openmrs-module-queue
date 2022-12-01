/*
 * This Source Code Form is subject to the terms of the Mozilla Public License,
 * v. 2.0. If a copy of the MPL was not distributed with this file, You can
 * obtain one at http://mozilla.org/MPL/2.0/. OpenMRS is also distributed under
 * the terms of the Healthcare Disclaimer located at http://openmrs.org/license.
 *
 * Copyright (C) OpenMRS Inc. OpenMRS is a registered trademark and the OpenMRS
 * graphic logo is a trademark of OpenMRS Inc.
 */
package org.openmrs.module.queue.model;

<<<<<<< HEAD
<<<<<<< HEAD
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Data;
import lombok.EqualsAndHashCode;
=======
import javax.persistence.CascadeType;
=======
>>>>>>> 20804b0 (work in progress)
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Data;
import lombok.EqualsAndHashCode;
<<<<<<< HEAD
import org.hibernate.annotations.Where;
>>>>>>> 92f473e (work in progress)
=======
>>>>>>> 20804b0 (work in progress)
import org.openmrs.BaseChangeableOpenmrsMetadata;

@EqualsAndHashCode(callSuper = true)
@Data
@Entity
@Table(name = "queue_room")
public class QueueRoom extends BaseChangeableOpenmrsMetadata {
	
	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "queue_room_id")
	private Integer queueRoomId;
	
<<<<<<< HEAD
<<<<<<< HEAD
	@Override
=======
	@OneToMany(mappedBy = "queue_room", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
	@Where(clause = "voided = 0 and (started_at <= current_timestamp() and ended_at is null)")
	private List<QueueRoom> queueRooms;
	
>>>>>>> 92f473e (work in progress)
=======
	@Override
>>>>>>> 20804b0 (work in progress)
	public Integer getId() {
		return getQueueRoomId();
	}
	
<<<<<<< HEAD
<<<<<<< HEAD
	@Override
=======
>>>>>>> 92f473e (work in progress)
=======
	@Override
>>>>>>> 20804b0 (work in progress)
	public void setId(Integer id) {
		this.setQueueRoomId(id);
	}
}
