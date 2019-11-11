### Nested Fields Support to Our APIs
- **Date: 2019-Oct-19**
- **JIRA Ticket: COMMERCE-2270**

#### What changed?

The following entities *Products*, *Accounts*, *Orders*, *Warehouses* are now supporting the **nested fields**, which means that request to the **GET endpoints** of these entities will return just the *root object*, instead of the root object and the *nested entities*.

For example the endpoint
```
../o/headless-commerce-admin-inventory/v1.0/warehouses/
```
would have produced a response like this:
```
{
  "items": [
    {
      "active": true,
      "city": "Borgorose",
      "countryISOCode": "IT",
      "externalReferenceCode": "380RI2021",
      "id": 36504,
      "latitude": 42.214601,
      "longitude": 12.796434,
      "name": "Italy",
      "regionISOCode": "RI",
      "street1": "Via delle Coste 24",
      "zip": "2021",
      "items": [
        {
          "id": 36527,
          "quantity": 20,
          "reservedQuantity": 0,
          "sku": "MIN93015",
          "warehouseExternalReferenceCode": "380RI2021",
          "warehouseId": 36504
        },
        {
          "id": 36551,
          "quantity": 100,
          "reservedQuantity": 0,
          "sku": "MIN93016A",
          "warehouseExternalReferenceCode": "380RI2021",
          "warehouseId": 36504
        }
      ]
    }
  ]
}
while now it produce a response like this:

{
  "items": [
    {
      "active": true,
      "city": "Borgorose",
      "countryISOCode": "IT",
      "externalReferenceCode": "380RI2021",
      "id": 36504,
      "latitude": 42.214601,
      "longitude": 12.796434,
      "name": "Italy",
      "regionISOCode": "RI",
      "street1": "Via delle Coste 24",
      "zip": "2021"
    }
  ]
}
```

and it is now possible to make the endpoint load the nested fields with the query parameter nestedFields valorized with the name of the desired nested property.

For example the endpoint
```
../o/headless-commerce-admin-inventory/v1.0/warehouses/?nestedFields=items
```
will produce a response like this:
```
{
  "items": [
    {
      "active": true,
      "city": "Borgorose",
      "countryISOCode": "IT",
      "externalReferenceCode": "380RI2021",
      "id": 36504,
      "latitude": 42.214601,
      "longitude": 12.796434,
      "name": "Italy",
      "regionISOCode": "RI",
      "street1": "Via delle Coste 24",
      "zip": "2021",
      "items": [
        {
          "id": 36527,
          "quantity": 20,
          "reservedQuantity": 0,
          "sku": "MIN93015",
          "warehouseExternalReferenceCode": "380RI2021",
          "warehouseId": 36504
        },
        {
          "id": 36551,
          "quantity": 100,
          "reservedQuantity": 0,
          "sku": "MIN93016A",
          "warehouseExternalReferenceCode": "380RI2021",
          "warehouseId": 36504
        }
      ]
    }
  ]
}
```
Finally it is also possible to request a list of desired nested properties to be loaded within the same request.

For example a request like this:
```
../o/headless-commerce-admin-catalog/v1.0/products/?nestedFields=images,categories
```
would load both images and categories for the products.

The following are the supported nested properties for each entity that supports this mechanism.

#### Who is affected?

- **Product**: [attachments, images, categories, configuration, productOptions, shippingConfiguration, productSpecifications, subscriptionConfiguration, taxConfiguration, relatedProducts, skus]
- **Account**: [addresses, users, organizations]
- **Order**: [items, shippingAddress]
- **Warehouse**: [items]

#### How should I update my code?

Changes are required if some nested fields is used in the client side. For example if *categories* of product is needed, the call to that endpoint must now use the query parameter: 
```
../o/headless-commerce-admin-catalog/v1.0/products/?nestedFields=categories
```

#### Why was this change made?

This change was made to make the payload of the response a bit thinner.