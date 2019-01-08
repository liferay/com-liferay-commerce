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
| `osgi.module.generator.overwrite.implementation` | `false` | avoid overwriting resource implementation file |

**Run with Gradle runClass task**

`../gradlew -PmainClass=com.liferay.commerce.openapi.util.generator.OSGiRESTModuleGenerator runClass`