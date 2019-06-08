UPDATE
	CommercePriceList
SET
	commerceCurrencyCode = CommerceCurrency.code_
FROM
	CommerceCurrency
WHERE
	CommercePriceList.commerceCurrencyId = CommerceCurrency.commerceCurrencyId;
