/**
 * Copyright (c) 2000-present Liferay, Inc. All rights reserved.
 *
 * The contents of this file are subject to the terms of the Liferay Enterprise
 * Subscription License ("License"). You may not use this file except in
 * compliance with the License. You can obtain a copy of the License by
 * contacting Liferay, Inc. See the License for the specific language governing
 * permissions and limitations under the License, including but not limited to
 * distribution rights of the Software.
 *
 *
 *
 */

package com.liferay.commerce.bom.rest.resource.v1_0;

import com.liferay.commerce.bom.rest.dto.v1_0.Spot;
import com.liferay.portal.kernel.model.Company;

import javax.annotation.Generated;

import javax.ws.rs.core.Response;

/**
 * To access this resource, run:
 *
 *     curl -u your@email.com:yourpassword -D - http://localhost:8080/o/commerce-bom/1.0
 *
 * @author Alessio Antonio Rendina
 * @generated
 */
@Generated("")
public interface SpotResource {

	public Spot postAreaIdSpot(Long id, Spot spot) throws Exception;

	public Response deleteAreaIdSpot(Long id, Long spotId) throws Exception;

	public Response putAreaIdSpot(Long id, Long spotId, Spot spot)
		throws Exception;

	public void setContextCompany(Company contextCompany);

}