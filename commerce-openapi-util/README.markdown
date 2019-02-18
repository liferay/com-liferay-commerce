# `com-liferay-commerce-openapi-util`

# Commerce OpenAPI specific tasks:

**runClass** - runs main method for class specified by mainClass parameter

*Example 1 - standard execution of class with main method:*

`../gradlew -PmainClass=com.my.package.ClassWithMainMethod runClass`

*Example 2 - debug execution:*

`../gradlew -PmainClass=com.my.package.ClassWithMainMethod runClass --debug-jvm`

# Commerce OpenAPI Generator

**Configuration**

***Importer configuration***

Configure importer to access Swagger API by changing default properties in
`com.liferay.commerce.openapi.util.importer.OpenAPIImporter.config`

***Generator configuration***

Configure generator output settings by changing default properties in
`com.liferay.commerce.openapi.util.generator.OSGiRESTModuleGenerator.config`

| Configuration | Default | Description |
|:---|:---|:---:|
| `osgi.module.generator.overwrite.bnd` | `false` | avoid overwriting BND file |
| `osgi.module.generator.overwrite.gradle` | `false` | avoid overwriting gradle build file |
| `osgi.module.generator.overwrite.implementation` | `false` | avoid overwriting resource implementation files |

Any configuration property prefixed with ``osgi.module`` can be overridden by
gradle project property. Pass `-Posgi.module.generator.overwrite.gradle=true` to
 `generateRestModule` task to override default from config file.  

### Run OpenAPI Generator
Example 1 - generate module based on configuration in config file

`../gradlew generateRestModule`

Example 2 - generate module at location other than defined in config file

`../gradlew generateRestModule -Posgi.module.root.path=/Volumes/dev`

Example 3 - generate module at location other than defined in config file and
overwrite existing implementation files

`../gradlew generateRestModule -Posgi.module.root.path=/Volumes/dev -Posgi.module.generator.overwrite.implementation=true`

### Run with Gradle `runClass` task

`../gradlew -PmainClass=com.liferay.commerce.openapi.util.generator.OSGiRESTModuleGenerator runClass`

# IDE Aid
### Code Analyse
As an aid to developers, generator marks all non required request parameters and
schema properties with ``@Nullable`` annotation. If code analyse tools are
enabled IDE will warn developer about possible NullPointerExceptions.
Very useful as generator do not generaty primitive java types, but wrapper
objects which may cause NPEs during autoboxing/unboxing process.

***How to perform code analyse in IntelliJ IDEA***

In IntelliJ IDEA ``Preferences`` go to ``Build, Execution, Deployment/Deployment -> Compiler``.
On the area on right locate option ``Add runtime assertions for notnull-annotated methods and parameters``
and make sure that it is enabled. Now click ``Configure annotations`` button.
If IntelliJ IDE hasn't already imported ``com.liferay.commerce.openapi.core.annotation.Nullable``
annotation, please do it by clicking ``+`` sign.
Once annotations are configured and settings are applied one can run code inspection.

Run inspection either via shortcut or by right click against module
``Analyze / Run Inspection by Name``. In popup window start typing
``Constant conditions & exceptions`` and choose it once you see it in the list.
Click ``Run``.
Inspections Results tab would appear and list all accesses of nullable fields.

Reference: https://www.jetbrains.com/help/idea/nullable-and-notnull-annotations.html

# OpenAPI examples

### Embedded Objects

Any component property may use $ref in its definition to refer to another
component. Such property is considered as an object and generator will generate
it as reference to DTO object.
Here is example with two components where ``WishList`` component's property
defaultItem refers to ``Item`` component:
```
 Item:
   required:
     - itemCode
   type: object
   properties:
     name:
       type: string
       example: 'Blue handle'
     itemCode:
       type: string
       example: 'AB-34098-789-N'
 WishList:
   required:
     - id
     - name
   type: object
   properties:
     id:
       type: integer
       example: 100023
     name:
       type: string
     defaultItem:
       $ref: '#/components/schema/Item'
     items:
       type: array
       items:
         $ref: '#/component/schema/Item'
```
WishList component generated as DTO will have this form:
```
public class WishListDTO {

		// ...
		// getters and setters  here
		// ...

		private Long _id;
		private String _name;
		private ItemDTO _defaultItem;
		private ItemDTO[] _items;

}
```

### Free Form Dictionary

Free form dictionary is special Open Api structure that supports mixed value types. As regular dictionaries, free form dictionaries only support string key values. Free form dictionary is advised way to store Liferay expando fields.
Here is `Item` object described by following schema:
```
 Item:
   required:
     - itemCode
   type: object
   properties:
     name:
       type: string
       example: 'Blue handle'
     active:
       type: boolean
       example: true
     itemCode:
       type: string
       example: 'AB-34098-789-N'
     itemParts:
       type: object
       additionalProperties: true
```
`itemParts` property is free form directory. It is optional and if has entries,
entry type can be arbitrary open api type.

Content body of an submitted Item has form:
```
{
    "name": "Blue handle",
    "active": "true",
    "itemCode": "I-002",
    "itemParts":
        {
            "structure": "solid",
            "color": "blue",
            "length": 45,
            "width": 12,
            "percetage": 99.9
        }
}
```
Generated REST module would convert free form directory structure into variable
of type Map<String, ?>. Underlying deserializer would load map with submitted
values.  Proper map handling is up to implementation developer.

Here is suggested example how to persist received map as expando field values:
```
protected void updateExpando(
		long companyId, Class<?> clazz, long classPK,
		Map<String, ?> itemParts) {

		ExpandoBridge expandoBridge = ExpandoBridgeFactoryUtil.getExpandoBridge(
			companyId, clazz.getName(), classPK);

		Enumeration<String> attributeNames = expandoBridge.getAttributeNames();

		while (attributeNames.hasMoreElements()) {
			String attributeName = attributeNames.nextElement();

			if (!itemParts.containsKey(attributeName)) {
				continue;
			}

			expandoBridge.setAttribute(
				attributeName,
				(Serializable)itemParts.get(attributeName));
		}
	}
```

### Nested support in generated applications

In order to use nested resource support, the following requirements have to be
satisfied:

Each resource entity DTO that wants to have nested DTOs has to explicitly
contain a field for each nested DTO/s, for example:

```
public class ProductDTO {
    ...

    public List<SkuDTO> getSkus() {
        return _skus;
    }

    public void setSkus(List<SkuDTO> skus) {
        _skus = skus;
    }

    ...

    private List<SkuDTO> _skus = new ArrayList<>();

}
```

Where "skus" can be a nested list of SkuDTOs.

A resource implementation method that will return nested DTOs has to be
annotated with the @Nested annotation, for example:

```
public class ProductResourceImpl implements ProductResource {
    ...

    @Nested("skus")
    @Override
    public CollectionDTO<SkuDTO> getSkus(String id, Pagination pagination)
    throws Exception {
        ...
    }

    ...
}
```

where "skus" is the name of the skus field inside the ProductDTO class.

The special query parameter "nested" has to be used in the request URL. It
defines a list of nested fields inside a resource entity DTO that should be
included, for example:

`{{baseUrl}}/product/1?groupId=36443&nested=skus`

where "skus"  is the name of the skus field inside ProductDTO class.

If a nested resource is a list of DTOs that can be paged then paging parameters
for that list can be passed as query parameters, for example:

`{{baseUrl}}/product/1?groupId=36443&nested=skus&skus.pageSize=1&skus.page=1`

where "pageSize" and "page" are page parameters used by the Product resource
method marked with the @Nested("skus") annotation.

Also, if a resource method that returns nested DTO/s accepts additional query
parameters, those parameters can also be passed as query parameters, for example:

`{{baseUrl}}/product/:id?groupId=36443&nested=skus&skus.keyword=test`

where "keyword" is the query parameter defined as an argument of the Product
resource method marked with the @Nested("skus") annotation.