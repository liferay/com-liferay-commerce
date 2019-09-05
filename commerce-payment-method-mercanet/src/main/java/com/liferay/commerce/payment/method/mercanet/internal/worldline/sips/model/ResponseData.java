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

package com.liferay.commerce.payment.method.mercanet.internal.worldline.sips.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;

import com.liferay.commerce.payment.method.mercanet.internal.worldline.sips.helper.BooleanDeserializer;
import com.liferay.commerce.payment.method.mercanet.internal.worldline.sips.helper.RuleResultListDeserializer;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.YearMonth;

import java.util.List;

/**
 * @author Luca Pellizzon
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public class ResponseData {

	public AcquirerResponseCode getAcquirerResponseCode() {
		return acquirerResponseCode;
	}

	public int getAmount() {
		return amount;
	}

	public String getAuthorisationId() {
		return authorisationId;
	}

	public int getCaptureDay() {
		return captureDay;
	}

	public LocalDate getCaptureLimitDate() {
		return captureLimitDate;
	}

	public CaptureMode getCaptureMode() {
		return captureMode;
	}

	public CardCSCResultCode getCardCSCResultCode() {
		return cardCSCResultCode;
	}

	public Currency getCurrencyCode() {
		return currencyCode;
	}

	public String getCustomerIpAddress() {
		return customerIpAddress;
	}

	public GuaranteeIndicator getGuaranteeIndicator() {
		return guaranteeIndicator;
	}

	public HolderAuthentMethod getHolderAuthentMethod() {
		return holderAuthentMethod;
	}

	public HolderAuthentProgram getHolderAuthentProgram() {
		return holderAuthentProgram;
	}

	public HolderAuthentStatus getHolderAuthentStatus() {
		return holderAuthentStatus;
	}

	public String getKeyVersion() {
		return keyVersion;
	}

	public String getMaskedPan() {
		return maskedPan;
	}

	public String getMerchantId() {
		return merchantId;
	}

	public OrderChannel getOrderChannel() {
		return orderChannel;
	}

	public PanEntryMode getPanEntryMode() {
		return panEntryMode;
	}

	public YearMonth getPanExpiryDate() {
		return panExpiryDate;
	}

	public PaymentMeanBrand getPaymentMeanBrand() {
		return paymentMeanBrand;
	}

	public PaymentMeanType getPaymentMeanType() {
		return paymentMeanType;
	}

	public PaymentPattern getPaymentPattern() {
		return paymentPattern;
	}

	public List<RuleResult> getPreAuthorisationRuleResultList() {
		return preAuthorisationRuleResultList;
	}

	public ResponseCode getResponseCode() {
		return responseCode;
	}

	public ScoreColor getScoreColor() {
		return scoreColor;
	}

	public String getStatementReference() {
		return statementReference;
	}

	public String getTokenPan() {
		return tokenPan;
	}

	public LocalDateTime getTransactionDateTime() {
		return transactionDateTime;
	}

	public String getTransactionOrigin() {
		return transactionOrigin;
	}

	public String getTransactionReference() {
		return transactionReference;
	}

	public WalletType getWalletType() {
		return walletType;
	}

	public boolean isHolderAuthentRelegation() {
		return holderAuthentRelegation;
	}

	protected AcquirerResponseCode acquirerResponseCode;
	protected int amount;
	protected String authorisationId;
	protected int captureDay;
	protected LocalDate captureLimitDate;
	protected CaptureMode captureMode;
	protected CardCSCResultCode cardCSCResultCode;
	protected Currency currencyCode;
	protected String customerIpAddress;
	protected GuaranteeIndicator guaranteeIndicator;
	protected HolderAuthentMethod holderAuthentMethod;
	protected HolderAuthentProgram holderAuthentProgram;

	@JsonDeserialize(using = BooleanDeserializer.class)
	protected boolean holderAuthentRelegation;

	protected HolderAuthentStatus holderAuthentStatus;
	protected String keyVersion;
	protected String maskedPan;
	protected String merchantId;
	protected OrderChannel orderChannel;
	protected PanEntryMode panEntryMode;
	protected YearMonth panExpiryDate;
	protected PaymentMeanBrand paymentMeanBrand;
	protected PaymentMeanType paymentMeanType;
	protected PaymentPattern paymentPattern;

	@JsonDeserialize(using = RuleResultListDeserializer.class)
	protected List<RuleResult> preAuthorisationRuleResultList;

	protected ResponseCode responseCode;
	protected ScoreColor scoreColor;
	protected String statementReference;
	protected String tokenPan;
	protected LocalDateTime transactionDateTime;
	protected String transactionOrigin;
	protected String transactionReference;
	protected WalletType walletType;

}