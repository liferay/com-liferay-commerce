### Nested Fields Support in Our APIs
- **Date: 2019-Oct-19**
- **JIRA Ticket: COMMERCE-2270**

#### What changed?

The following entities *Products*, *Accounts*, *Orders*, *Warehouses* now support **nested fields**, which means that requests to the **GET endpoints** of these entities will return just the *root object*, instead of the root object and the *nested entities*.

For example, the endpoint
```
../o/headless-commerce-admin-inventory/v1.0/warehouses/
```
used to produce a response like this:
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
while now it produces a response like this:
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
      "zip": "2021"
    }
  ]
}
```

It is still possible to request the nested fields by passing the query parameter `nestedFields` with the name of the desired nested property.

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
Finally, it is also possible to request a list of desired nested properties to be provided within the same request.

For example, a request like this:
```
../o/headless-commerce-admin-catalog/v1.0/products/?nestedFields=images,categories
```
will include both images and categories for the products.

The valid nested properties for each entity that supports this mechanism are listed below.

#### What is affected?

- **Product**: [attachments, images, categories, configuration, productOptions, shippingConfiguration, productSpecifications, subscriptionConfiguration, taxConfiguration, relatedProducts, skus]
- **Account**: [addresses, users, organizations]
- **Order**: [items, shippingAddress]
- **Warehouse**: [items]

#### How should I update my code?

Changes are required if some nested fields are used on the client side. For example if the *categories* of a product are needed, the call to that endpoint must now use the query parameter:
```
../o/headless-commerce-admin-catalog/v1.0/products/?nestedFields=categories
```

#### Why was this change made?

This change was made to make the response payload more lightweight by default.