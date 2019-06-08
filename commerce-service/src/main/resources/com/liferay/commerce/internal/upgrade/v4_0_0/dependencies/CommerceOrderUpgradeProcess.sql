UPDATE
	CommerceOrder
SET
	commerceCurrencyCode = CommerceCurrency.code_
FROM
	CommerceCurrency
WHERE
	CommerceOrder.commerceCurrencyId = CommerceCurrency.commerceCurrencyId;
