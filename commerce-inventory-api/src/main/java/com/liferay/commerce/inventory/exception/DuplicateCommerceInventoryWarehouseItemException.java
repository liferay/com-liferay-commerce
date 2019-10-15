package com.liferay.commerce.inventory.exception;

import com.liferay.portal.kernel.exception.PortalException;

/**
 * @author Andrea Sbarra
 */
public class DuplicateCommerceInventoryWarehouseItemException
	extends PortalException {

	public DuplicateCommerceInventoryWarehouseItemException() {
	}

	public DuplicateCommerceInventoryWarehouseItemException(String msg) {
		super(msg);
	}

	public DuplicateCommerceInventoryWarehouseItemException(
		String msg, Throwable cause) {

		super(msg, cause);
	}

	public DuplicateCommerceInventoryWarehouseItemException(Throwable cause) {
		super(cause);
	}

}