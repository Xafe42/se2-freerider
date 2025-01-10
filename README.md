# E1: *se2-freerider* Spring *initializr* &nbsp; (<span style="color:red"> 9 Pts </span>)

Goal of the *se2-freerider* application is to provide access to the data managed in the *db-freerider*
[database](https://github.com/sgra64/db-freerider)
and provide REST endpoints for a car sharing reservation system.

Spring Boot is a modular framework suitable to build Java applications that expose REST endpoints
and require access to databases.

Creating a Spring Boot application requires using a build-tool (*maven* or *gradle*) and
follows certain steps. A Spring Boot project also has a certain structure.

### Challenges

1. [Challenge 1:](#1-initialize-new-spring-boot-project) Initialize new Spring Boot Project - (2 Pts)

2. [Challenge 2:](#2-build-and-run-the-application) Build and Run the Application - (2 Pts)

3. [Challenge 3:](#3-build-and-run-tests) Build and Run Tests - (2 Pts)
<!-- 3. [Challenge 3:](#3-create-application-bean) Create Application Bean - (2 Pts) -->

4. [Challenge 4:](#4-build-and-run-test-reports) Build and Run Test Reports - (1 Pts)

5. [Challenge 5:](#5-implement-spring-component) Implement Spring *@Component* - (1 Pt)

6. [Challenge 6:](#6-check-project-into-your-remote-repository) Check Project into Your Remote Repository - (1 Pt)


&nbsp;

---
## 1. Initialize new Spring Boot Project

Configure the Spring Boot project at [*Spring Initializr*](https://start.spring.io)
following the dialog and download the project.

For Visual Studio Code, extension *Spring Boot Tools* can be installed from the
market place (although not required). If installed, search for *initialzr* in the
Command Palette (Ctrl + Shift + P) and follow the dialog.

In both dialogs, select:
- Maven project
- Java
- Version: 3.3.0
- GAV:
    - **G**roup: `de.freerider` -- turns into Java package path 
    - **A**rtifact: `application` -- turns into name of project directory
    - **V**ersion: `0.0.1-SNAPSHOT`
    - name: se2-freerider
    - description: *Software-Engineering-2 freerider car sharing project*
    - package name: 0.0.1-SNAPSHOT
    - packaging: `jar`
    - java: `21` (depending on your installed Java version, chose 11, 17 or 21)

Button `GENERATE` will download a .zip: *application.zip*.
Button `EXPLORE` opens the generated `pom.xml` and allows exploring files included in the archive.

Unpack files from the .zip archive in a local project directory: `se2-freerider`
and show files obtained from *initializr*:

```sh
find *.* .mvn/ src/         # show content obtained from Spring-initializr
```
```
HELP.md
mvnw.cmd
pom.xml
README.md
.mvn/
.mvn/wrapper
.mvn/wrapper/maven-wrapper.properties
src/
src/main
src/main/java
src/main/java/de
src/main/java/de/freerider
src/main/java/de/freerider/application
src/main/java/de/freerider/application/Application.java
src/main/resources
src/main/resources/application.properties
src/test
src/test/java
src/test/java/de
src/test/java/de/freerider
src/test/java/de/freerider/application
src/test/java/de/freerider/application/ApplicationTests.java
```

Some files can be removed and renamed:

```sh
# remove unused files
rm -rf HELP.md mvnw* .mvn
```

The project structure matches the default *Spring Boot* scaffold.

```sh
--<se2-freerider>:
 +--<.git>                      # local git repository
 +--.gitignore
 +-- ...
 +-- pom.xml                    # maven's central project file
 |
 +--<src/main/java>:            # project sources (resource and .java files)
 |        |    |
 |        |    +--<de/freerider>:           # artefact package path
 |        |            +--> <application>   # local package path
 |        |                   |
 |        |                   +--> Application.java
 |        +--<resources>:
 |             +--> application.properties  # application configuration file
 |
 +--<src/test/java>:            # test sources (.java files)
 |             |
 |             +--<de/freerider>:           # artefact package path
 |                     +--> <application>   # local package path
 |                            |
 |                            +--> ApplicationTest.java
 |
 +--<target>                    # directory with all results (not initially present)
 |    +--> application-0.0.1-SNAPSHOT.jar           # main executable artefact with dependencies
 |    +--> application-0.0.1-SNAPSHOT.jar.original  # .jar without dependencies
 |    +--<classes>              # compiled sources (.class files)
 |    +--<generated-sources>
 |    +--<generated-test-sources>
 |    +--<maven-archiver>
 |    +--<maven-status>
 |    +--<site>                 # test reports as site, index.html
 |    +--<surefire-report>      # test reports
 |    +--<test-classes>         # compiled test classes
 |
```

Java application packages begin at: `src/java/main`, followed by the group package path:
`de.freerider`.

File `application.properties`) contains configuration information for the Spring Boot application.

`pom.xml` is the central file for the Maven build-process.

Verify configurations in `pom.xml` (adjust if needed):

```xml
<!-- Use Spring Boot as parent project. -->
<parent>
    <groupId>org.springframework.boot</groupId>
    <artifactId>spring-boot-starter-parent</artifactId>
    <version>3.3.0</version>
    <relativePath/> <!-- lookup parent from repository -->
</parent>

<!-- Project GAV with groupId (G), artifactId (A) and version (V). -->
<groupId>de.freerider</groupId>
<artifactId>application</artifactId>
<version>0.0.1-SNAPSHOT</version>

<!-- The properties section defines variables used in pom.xml. -->
<properties>
    <java.version>21</java.version>
    <skipTests>true</skipTests>     <!-- skip running tests -->
</properties>
```

Spring Boot is a modular framework. Only components of the framework
are loaded into the application that are defined as dependencies in
`pom.xml`. In the minimal configuration, only two components are loaded
from the
[*Maven Repository*](https://mvnrepository.com/artifact/org.springframework.boot):
[spring-boot-starter](https://mvnrepository.com/artifact/org.springframework.boot/spring-boot-starter) and
[spring-boot-starter-test](https://mvnrepository.com/artifact/org.springframework.boot/spring-boot-starter-test).

```xml
<!-- 
    The dependencies section defines project dependenencies with Spring packages
    chosen from:
        https://mvnrepository.com/artifact/org.springframework.boot
-->
<dependencies>
    <!-- https://mvnrepository.com/artifact/org.springframework.boot/spring-boot-starter -->
    <dependency>
        <groupId>org.springframework.boot</groupId>
        <artifactId>spring-boot-starter</artifactId>
    </dependency>

    <!-- https://mvnrepository.com/artifact/org.springframework.boot/spring-boot-starter-test -->
    <dependency>
        <groupId>org.springframework.boot</groupId>
        <artifactId>spring-boot-starter-test</artifactId>
        <scope>test</scope>
    </dependency>
</dependencies>
```

Maven is used to build the application with following commands:

```perl
# maven commands to build project:
mvn validate                        # validate correctness of pom.xml
mvn compile                         # compile sources to: target/classes
mvn test-compile                    # compile tests to: target/test-classes
mvn test                            # run tests, create: target/surefire-reports
mvn package                         # create: target/se2-freerider-0.0.1-SNAPSHOT.jar
mvn package -DskipTests=true        # create .jar with skipping running tests
mvn site -DgenerateReports=false    # create css and images for: target/site
mvn surefire-report:report          # run tests, create: target/site/index.html
mvn site -DgenerateReports=false surefire-report:report     # do both

mvn spring-boot:run                 # run application

mvn clean                           # remove target directory

mvn dependency:tree                 # show dependency tree
mvn dependency:build-classpath      # show classpath
```

Perform following commands and explore their results:

```perl
mvn validate            # validate the correctness of pom.xml
```

Output:

```
[INFO] Scanning for projects...
[INFO]
[INFO] ----------------------< de.freerider:application >----------------------
[INFO] Building application 0.0.1-SNAPSHOT
[INFO] --------------------------------[ jar ]---------------------------------
[INFO] ------------------------------------------------------------------------
[INFO] BUILD SUCCESS
[INFO] ------------------------------------------------------------------------
[INFO] Total time:  0.583 s
[INFO] Finished at: 2024-06-13T23:32:18+02:00
[INFO] ------------------------------------------------------------------------
```

Show dependency tree (all imported packages with their included imports):

```perl
mvn dependency:tree     # show dependency tree
```

Output:

```
[INFO] Scanning for projects...
[INFO]
[INFO] ----------------------< de.freerider:application >----------------------
[INFO] Building application 0.0.1-SNAPSHOT
[INFO] --------------------------------[ jar ]---------------------------------
[INFO]
[INFO] --- maven-dependency-plugin:3.6.1:tree (default-cli) @ application ---
[INFO] de.freerider:application:jar:0.0.1-SNAPSHOT
[INFO] +- org.springframework.boot:spring-boot-starter:jar:3.3.0:compile
[INFO] |  +- org.springframework.boot:spring-boot:jar:3.3.0:compile
[INFO] |  |  \- org.springframework:spring-context:jar:6.1.8:compile
[INFO] |  |     +- org.springframework:spring-aop:jar:6.1.8:compile
[INFO] |  |     +- org.springframework:spring-beans:jar:6.1.8:compile
[INFO] |  |     +- org.springframework:spring-expression:jar:6.1.8:compile
[INFO] |  |     \- io.micrometer:micrometer-observation:jar:1.13.0:compile
[INFO] |  |        \- io.micrometer:micrometer-commons:jar:1.13.0:compile
[INFO] |  +- org.springframework.boot:spring-boot-autoconfigure:jar:3.3.0:compile
[INFO] |  +- org.springframework.boot:spring-boot-starter-logging:jar:3.3.0:compile
[INFO] |  |  +- ch.qos.logback:logback-classic:jar:1.5.6:compile
[INFO] |  |  |  \- ch.qos.logback:logback-core:jar:1.5.6:compile
[INFO] |  |  +- org.apache.logging.log4j:log4j-to-slf4j:jar:2.23.1:compile
[INFO] |  |  |  \- org.apache.logging.log4j:log4j-api:jar:2.23.1:compile
[INFO] |  |  \- org.slf4j:jul-to-slf4j:jar:2.0.13:compile
[INFO] |  +- jakarta.annotation:jakarta.annotation-api:jar:2.1.1:compile
[INFO] |  +- org.springframework:spring-core:jar:6.1.8:compile
[INFO] |  |  \- org.springframework:spring-jcl:jar:6.1.8:compile
[INFO] |  \- org.yaml:snakeyaml:jar:2.2:compile
[INFO] \- org.springframework.boot:spring-boot-starter-test:jar:3.3.0:test
[INFO]    +- org.springframework.boot:spring-boot-test:jar:3.3.0:test
[INFO]    +- org.springframework.boot:spring-boot-test-autoconfigure:jar:3.3.0:test
[INFO]    +- com.jayway.jsonpath:json-path:jar:2.9.0:test
[INFO]    |  \- org.slf4j:slf4j-api:jar:2.0.13:compile
[INFO]    +- jakarta.xml.bind:jakarta.xml.bind-api:jar:4.0.2:test
[INFO]    |  \- jakarta.activation:jakarta.activation-api:jar:2.1.3:test
[INFO]    +- net.minidev:json-smart:jar:2.5.1:test
[INFO]    |  \- net.minidev:accessors-smart:jar:2.5.1:test
[INFO]    |     \- org.ow2.asm:asm:jar:9.6:test
[INFO]    +- org.assertj:assertj-core:jar:3.25.3:test
[INFO]    |  \- net.bytebuddy:byte-buddy:jar:1.14.16:test
[INFO]    +- org.awaitility:awaitility:jar:4.2.1:test
[INFO]    +- org.hamcrest:hamcrest:jar:2.2:test
[INFO]    +- org.junit.jupiter:junit-jupiter:jar:5.10.2:test
[INFO]    |  +- org.junit.jupiter:junit-jupiter-api:jar:5.10.2:test
[INFO]    |  |  +- org.opentest4j:opentest4j:jar:1.3.0:test
[INFO]    |  |  +- org.junit.platform:junit-platform-commons:jar:1.10.2:test
[INFO]    |  |  \- org.apiguardian:apiguardian-api:jar:1.1.2:test
[INFO]    |  +- org.junit.jupiter:junit-jupiter-params:jar:5.10.2:test
[INFO]    |  \- org.junit.jupiter:junit-jupiter-engine:jar:5.10.2:test
[INFO]    |     \- org.junit.platform:junit-platform-engine:jar:1.10.2:test
[INFO]    +- org.mockito:mockito-core:jar:5.11.0:test
[INFO]    |  +- net.bytebuddy:byte-buddy-agent:jar:1.14.16:test
[INFO]    |  \- org.objenesis:objenesis:jar:3.3:test
[INFO]    +- org.mockito:mockito-junit-jupiter:jar:5.11.0:test
[INFO]    +- org.skyscreamer:jsonassert:jar:1.5.1:test
[INFO]    |  \- com.vaadin.external.google:android-json:jar:0.0.20131108.vaadin1:test
[INFO]    +- org.springframework:spring-test:jar:6.1.8:test
[INFO]    \- org.xmlunit:xmlunit-core:jar:2.9.1:test
[INFO] ------------------------------------------------------------------------
[INFO] BUILD SUCCESS
[INFO] ------------------------------------------------------------------------
[INFO] Total time:  3.171 s
[INFO] Finished at: 2024-06-13T23:32:51+02:00
[INFO] ------------------------------------------------------------------------
```

Count .jar files needed and aquired by maven to build the project. The output
of `mvn dependency:tree` is filtered for .jar files with the `grep` command.
Its output, lines that contain .jar, are then counted with the `wc` (word count)
command:

```
mvn dependency:tree | grep .jar | wc
```

`wc`'s output (lines, words, bytes) shows 48 lines:

```
    51     203    3396
```

It means that 51 .jar files are needed to build a basic Spring Boot application.


&nbsp;

---
## 2. Build and Run the Application

Compile source .java files to .class files:

```perl
mvn compile             # compile sources to: target/classes
find target/classes
```

Output:

```
target/classes/
target/classes/application.properties
target/classes/de
target/classes/de/freerider
target/classes/de/freerider/application
target/classes/de/freerider/application/Application.class
```

Package .jar as final artefact:

```py
mvn package                     # create: target/se2-freerider-0.0.1-SNAPSHOT.jar
mvn package -DskipTests=true    # create with skipping running tests
ls -la target
```

Output:

```
total 9196
drwxr-xr-x 1 svgr2 Kein        0 Jun 13 23:36 .
drwxr-xr-x 1 svgr2 Kein        0 Jun 13 23:24 ..
-rw-r--r-- 1 svgr2 Kein 10459567 Jun 13 23:36 application-0.0.1-SNAPSHOT.jar
-rw-r--r-- 1 svgr2 Kein     2767 Jun 13 23:36 application-0.0.1-SNAPSHOT.jar.original
drwxr-xr-x 1 svgr2 Kein        0 Jun 13 23:24 classes
drwxr-xr-x 1 svgr2 Kein        0 Jun 13 23:24 generated-sources
drwxr-xr-x 1 svgr2 Kein        0 Jun 13 23:24 generated-test-sources
drwxr-xr-x 1 svgr2 Kein        0 Jun 13 23:24 maven-archiver
drwxr-xr-x 1 svgr2 Kein        0 Jun 13 23:24 maven-status
drwxr-xr-x 1 svgr2 Kein        0 Jun 13 23:35 surefire-reports
drwxr-xr-x 1 svgr2 Kein        0 Jun 13 23:24 test-classes
```

Spring Boot has multiple ways to run an application after it is built.

<!-- 
[ "$(uname | grep 'CYGWIN\|MINGW')" ] && local sep=';' || local sep=':'
 -->
```perl
mvn spring-boot:run             # Spring's full build and run
mvn spring-boot:run --quiet     # run in quiet mode without maven INFO logs

# run the packaged artefact
java -jar target/application-0.0.1-SNAPSHOT.jar


# build classpath file from maven
mvn -q dependency:build-classpath -Dmdep.outputFile=classpath

# set CLASSPATH from classpath file (Mac: use ':' as separator, not ';')
export CLASSPATH="target/classes;$(cat classpath)"

# run the program
java de.freerider.application.Application
```

Output:

```
  .   ____          _            __ _ _
 /\\ / ___'_ __ _ _(_)_ __  __ _ \ \ \ \
( ( )\___ | '_ | '_| | '_ \/ _` | \ \ \ \
 \\/  ___)| |_)| | | | | || (_| |  ) ) ) )
  '  |____| .__|_| |_|_| |_\__, | / / / /
 =========|_|==============|___/=/_/_/_/

 :: Spring Boot ::                (v3.3.0)

2024-06-13T23:40:23.507+02:00  INFO 10296 --- [application] [           main] de.freerider.application.Application     : Starting Application v0.0.1-SNAPSHOT using Java 21 with PID 10296 (C:\Sven1\svgr\workspaces\2-SE\se2-freerider\target\application-0.0.1-SNAPSHOT.jar started by svgr2 in C:\Sven1\svgr\workspaces\2-SE\se2-freerider)
2024-06-13T23:40:23.514+02:00  INFO 10296 --- [application] [           main] de.freerider.application.Application     : No active profile set, falling back to1 default profile: "default"
2024-06-13T23:40:24.771+02:00  INFO 10296 --- [application] [           main] de.freerider.application.Application     : Started Application in 1.999 seconds (process running for 2.857)
```

*Spring Boot* runs the appliction, but only prints internal log lines.

[Application.java](src/main/java/de/freerider/application/Application.java) has no content yet:

```java
package de.freerider.application;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class Application {

    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }
}
```

The *main(String[] args)* function calls the *Spring Boot* runtime system (*IoC* container).

Add a constructor and an Event-Listener to the class:

```java
package de.freerider.application;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.event.EventListener;

@SpringBootApplication
public class Application {

    /**
     * Constructor executes when the Spring runtime system creates the Application Bean.
     */
    Application() {
        System.out.println("\n-<2>--> Application constructor is called\n");
    }

    public static void main(String[] args) {
        System.out.println("\n-<1>--> before Spring runtime system is called\n");
        SpringApplication.run(Application.class, args);
        System.out.println("\n-<4>--> after Spring runtime system has exited\n");
    }

    /**
     * Called from Spring runtime system after IoC container has been initialized.
     */
    @EventListener(ApplicationReadyEvent.class)
    public void doSomethingAfterStartup() {
        System.out.println("\n-<3>--> doSomethingAfterStartup()");
    }
}
```

Rebuild and re-run the application.

```sh
mvn package -q -DskipTests=true

# re-run the application (as class or .jar)
java de.freerider.application.Application
java -jar target/application-0.0.1-SNAPSHOT.jar
```

Output for all variations:

```
-<1>--> before Spring runtime system is called

  .   ____          _            __ _ _
 /\\ / ___'_ __ _ _(_)_ __  __ _ \ \ \ \
( ( )\___ | '_ | '_| | '_ \/ _` | \ \ \ \
 \\/  ___)| |_)| | | | | || (_| |  ) ) ) )
  '  |____| .__|_| |_|_| |_\__, | / / / /
 =========|_|==============|___/=/_/_/_/

 :: Spring Boot ::                (v3.3.0)

2024-06-14T00:22:34.036+02:00  INFO 7424 --- [application] [           main] de.freerider.application.Application     : Starting Application using Java 21 with PID 7424 (C:\Sven1\svgr\workspaces\2-SE\se2-freerider\target\classes started by svgr2 in C:\Sven1\svgr\workspaces\2-SE\se2-freerider)
2024-06-14T00:22:34.040+02:00  INFO 7424 --- [application] [           main] de.freerider.application.Application     : No active profile set, falling back to 1 default profile: "default"

-<2>--> Application constructor is called

2024-06-14T00:22:35.217+02:00  INFO 7424 --- [application] [           main] de.freerider.application.Application     : Started Application in 1.892 seconds (process running for 2.752)

-<3>--> doSomethingAfterStartup()

-<4>--> after Spring runtime system has exited
```

Explain the order of the `-<n>-` print lines.

Add *run(String... args)* implementing the
[CommandLineRunner](https://docs.spring.io/spring-boot/api/java/org/springframework/boot/CommandLineRunner.html)
interface:

```java
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.event.EventListener;


@SpringBootApplication
public class Application implements CommandLineRunner {

    @Override
    public void run(String... args) {
        System.out.println("\n-<5>--> run(String... args) called from CommandLineRunner Bean");
        //
        Arrays.stream(args)
            .map(arg -> String.format(" - arg: %s", arg))
            // .filter(msg -> false)   // disable printing
            .forEach(System.out::println);
    }
}
```

Turn off Spring logging `src/resources/application.properties`, add:

```sh
spring.application.name=application
logging.level.root=OFF
logging.level.org.springframework.boot=OFF
# spring.main.banner-mode=OFF
```

and re-run the application passing some arguments:

```sh
java de.freerider.application.Application A=1 B=2 C=3
```

```
-<1>--> before Spring runtime system is called

  .   ____          _            __ _ _
 /\\ / ___'_ __ _ _(_)_ __  __ _ \ \ \ \
( ( )\___ | '_ | '_| | '_ \/ _` | \ \ \ \
 \\/  ___)| |_)| | | | | || (_| |  ) ) ) )
  '  |____| .__|_| |_|_| |_\__, | / / / /
 =========|_|==============|___/=/_/_/_/

 :: Spring Boot ::                (v3.3.0)

-<2>--> Application constructor is called

-<5>--> run(String... args) called from CommandLineRunner Bean
 - arg: A=1
 - arg: B=2
 - arg: C=3

-<3>--> doSomethingAfterStartup()

-<4>--> after Spring runtime system has exited
```

Add *Bean* factory method that prints all beans created by the
Spring IoC Container:

```java
    @Bean
    public CommandLineRunner commandLineRunner(ApplicationContext context) {
        return args -> {
            System.out.println("\n-<6>--> Bean objects created by Spring Boot in ApplicationContext:");
            String[] beanNames = context.getBeanDefinitionNames();
            Arrays.stream(beanNames)
                // .filter(beanName -> beanName.matches("^[a-f].*"))
                .filter(beanName -> ! beanName.matches("^org.*"))
                .sorted()
                .map(beanName -> String.format(" - bean: %s", beanName))
                // .filter(msg -> false)   // disable printing
                .forEach(System.out::println);
        };
    }
```

Output:

```
-<1>--> before Spring runtime system is called

  .   ____          _            __ _ _
 /\\ / ___'_ __ _ _(_)_ __  __ _ \ \ \ \
( ( )\___ | '_ | '_| | '_ \/ _` | \ \ \ \
 \\/  ___)| |_)| | | | | || (_| |  ) ) ) )
  '  |____| .__|_| |_|_| |_\__, | / / / /
 =========|_|==============|___/=/_/_/_/

 :: Spring Boot ::                (v3.3.0)


-<2>--> Application constructor is called


-<5>--> run(String... args) called from CommandLineRunner Bean
 - arg: A=1
 - arg: B=2
 - arg: C=3

-<6>--> Bean objects created by Spring Boot in ApplicationContext:
 - bean: application                <-- Application bean
 - bean: applicationAvailability
 - bean: applicationTaskExecutor
 - bean: commandLineRunner          <-- CommandLineRunner bean
 - bean: fileWatcher
 - bean: forceAutoProxyCreatorToUseClassProxying
 - bean: lifecycleProcessor
 - bean: propertySourcesPlaceholderConfigurer
 - bean: simpleAsyncTaskExecutorBuilder
 - bean: simpleAsyncTaskSchedulerBuilder
 - bean: spring.info-org.springframework.boot.autoconfigure.info.ProjectInfoProperties
 - bean: spring.lifecycle-org.springframework.boot.autoconfigure.context.LifecycleProperties
 - bean: spring.sql.init-org.springframework.boot.autoconfigure.sql.init.SqlInitializationProperties
 - bean: spring.ssl-org.springframework.boot.autoconfigure.ssl.SslProperties
 - bean: spring.task.execution-org.springframework.boot.autoconfigure.task.TaskExecutionProperties
 - bean: spring.task.scheduling-org.springframework.boot.autoconfigure.task.TaskSchedulingProperties
 - bean: sslBundleRegistry
 - bean: sslPropertiesSslBundleRegistrar
 - bean: taskExecutorBuilder
 - bean: taskSchedulerBuilder
 - bean: threadPoolTaskExecutorBuilder
 - bean: threadPoolTaskSchedulerBuilder

-<3>--> doSomethingAfterStartup()

-<4>--> after Spring runtime system has exited
```


&nbsp;

---
## 3. Build and Run Tests

Next, build and run Tests. All three test-methods in `ApplicationTest.java`
should succeed:

```
mvn test
```

Output:

```
...
[INFO] -------------------------------------------------------
[INFO]  T E S T S
[INFO] -------------------------------------------------------
[INFO] Running de.freerider.application.FreeriderApplicationTest
23:30:50,141 |-WARN in Logger[org.springframework.core.env.PropertySourcesProper
tyResolver] - No appenders present in context [default] for logger [org.springfr
amework.core.env.PropertySourcesPropertyResolver].
...
  .   ____          _            __ _ _
 /\\ / ___'_ __ _ _(_)_ __  __ _ \ \ \ \
( ( )\___ | '_ | '_| | '_ \/ _` | \ \ \ \
 \\/  ___)| |_)| | | | | || (_| |  ) ) ) )
  '  |____| .__|_| |_|_| |_\__, | / / / /
 =========|_|==============|___/=/_/_/_/
 :: Spring Boot ::                (v3.0.1)

(1.) FreeriderApplication instance created.
(2.) Spring Container ready.
Hello FreeriderApplication!
performing test: test_100()
performing test: test_200()
performing test: test_300()
[INFO] Tests run: 3, Failures: 0, Errors: 0, Skipped: 0, Time elapsed: 2.651 s -
 in de.freerider.application.FreeriderApplicationTest
[INFO]
[INFO] Results:
[INFO] Tests run: 3, Failures: 0, Errors: 0, Skipped: 0
[INFO]
[INFO] ------------------------------------------------------------------------
[INFO] BUILD SUCCESS
[INFO] ------------------------------------------------------------------------
...
```


&nbsp;

---
## 4. Build and Run Test Reports

Tests-Reports are created to summarize results of complex test runs that are usually
performed on dedicated test servers overnight. Test-Reports are rendered in form of a
generated web-site that can be viewed with a browser.


```perl
mvn site -DgenerateReports=false    # create css and images for: target/site
mvn surefire-report:report          # run tests, create: target/site/surefire-report.html
```

Or perform both combined:

```
mvn site -DgenerateReports=false surefire-report:report     # do both
```

The test report is in: `target/site/surefire-report.html`.
Open file in browser:

<!-- ![surefire-report.html](./img/img_E1_01.png) -->
<img src="https://raw.githubusercontent.com/sgra64/se2-freerider/markup/e1-initializr/img/initializr_surefire_report.png" alt="surefire-report.html" width="600"/>


&nbsp;

---
## 5. Implement Spring *@Component*

Implement a Spring *@Component: Calculator* that implements a method:

```java
int add(int x, int y);
```

Use component in `Application.java`:

```java
@Autowired
Calculator calculator;      // Component


public void run(String... args) {
    int sum = calculator.add(10, 2);
    // print sum
}
```


&nbsp;

---
## 6. Check Project into Your Remote Repository

Put the project under *git* control and check the project into your
[*BHT GitLab*](https://gitlab.bht-berlin.de) or
[*GitHub*](https://github.com) (*"remote"*) repository.

- Name the project: *"se2-freerider"* in the remote repository.

- Put the content of this *"Spring initializr"* assignment under branch: `e1-initializr`
    in the remote repository.

- Put the content of the previous assignment
    [MB1](https://github.com/sgra64/se2-freerider/tree/maven-jdbc)
    ("*Maven Build & JDBC Database Access*") under the branch: `maven-jdbc`
    in the remote repository.

Briefly describe steps.


