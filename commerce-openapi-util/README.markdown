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

Any configuration property prefixed with ``osgi.module`` can be overridden by gradle project property. Pass `-Posgi.module.generator.overwrite.gradle=true` to `generateRestModule` task to override default from config file.  
### Run OpenAPI Generator
Example 1 - generate module based on configuration in config file

`../gradlew generateRestModule`

Example 2 - generate module at location other than defined in config file

`../gradlew generateRestModule -Posgi.module.root.path=/Volumes/dev`

Example 3 - generate module at location other than defined in config file and overwrite existing implementation files

`../gradlew generateRestModule -Posgi.module.root.path=/Volumes/dev -Posgi.module.generator.overwrite.implementation=true`

### Run with Gradle `runClass` task

`../gradlew -PmainClass=com.liferay.commerce.openapi.util.generator.OSGiRESTModuleGenerator runClass`