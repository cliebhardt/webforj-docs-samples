# Testing with Playwright

## Playwright Setup

To use Playwright in the project it needs to
be added to the pom.xml.
```xml
...
<dependency>
  <groupId>com.microsoft.playwright</groupId>
  <artifactId>playwright</artifactId>
  <version>1.42.0</version>
</dependency>
...
<build>
<plugins>
  <plugin>
    <groupId>org.apache.maven.plugins</groupId>
    <artifactId>maven-compiler-plugin</artifactId>
    <version>3.10.1</version>
    <configuration>
      <source>1.8</source>
      <target>1.8</target>
    </configuration>
  </plugin>
</plugins>
</build>
```
After including it in the pom.xml the following command needs to 
be executed

`mvn exec:java -e -D exec.mainClass=com.microsoft.playwright.CLI -D exec.args="install --with-deps"`

To write and execute tests a separate test runner 
must be used like JUnit.

## Test Setup

The test class can be setup as shown below.

```java
import com.microsoft.playwright.Browser;
import com.microsoft.playwright.BrowserContext;
import com.microsoft.playwright.Page;
import com.microsoft.playwright.Playwright;
import org.junit.jupiter.api.*;

class Test {
  static Playwright playwright;
  static Browser browser;
  BrowserContext context;
  Page page;

  @BeforeAll
  static void launchBrowser() {
    playwright = Playwright.create();
    browser = playwright.chromium().launch();
  }

  @AfterAll
  static void closeBrowser() {
    playwright.close();
  }

  @BeforeEach
  void setUp() {
    context = browser.newContext();
    page = context.newPage();
  }

  @AfterEach
  void breakDown() {
    context.close();
  }
}
```
`Playwright` provides the engine and create the browser to test the
functionality on. The provided browser types are Chromium, Firefox and
WebKit.
`Browser` allows the creation of a `BrowserContext` that allows the
tests to interact with the web page. The `Browser` launch options
can be configurated to display the window by setting `setHeaderless`
to false and with `slowMo` the execution speed can be reduced to either
interact during the test or make following the executed code easier
during debugging. The test that are pushed to the repository should 
leave the `launch` method empty.
`Page` is the most important component of the test, as it is used to
interact and test the functionality of the site.

See [Guides](https://playwright.dev/java/docs/input) on how to interact 
with the website and how make assertions on elements.

To cut down on the time it requires to execute the tests, 
the following setup should be used instead.

```java
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
class TestFixtures {
  // shared by all instances
  Playwright playwright;
  Browser browser;
  // unique to each instance
  BrowserContext context;
  Page page;

  @BeforeAll
  void launchBrowser() {
    playwright = Playwright.create();
    browser = playwright.chromium().launch();
  }

  @AfterAll
  void closeBrowser() {
    playwright.close();
  }

  @BeforeEach
  void createContextAndPage() {
    context = browser.newContext();
    page = context.newPage();
  }

  @AfterEach
  void closeContext() {
    context.close();
  }
}
```
The following lines have to be added to the pom.xml
to enable JUnit to run multiple test in parallel.
```xml
<build>
    <plugins>
        <plugin>
            <artifactId>maven-surefire-plugin</artifactId>
            <version>3.2.5</version>
            <configuration>
                <properties>
                    <configurationParameters>
                      junit.jupiter.execution.parallel.enabled = true
                      junit.jupiter.execution.parallel.mode.default = same_thread
                      junit.jupiter.execution.parallel.mode.classes.default = concurrent
                      junit.jupiter.execution.parallel.config.strategy=dynamic
                      junit.jupiter.execution.parallel.config.dynamic.factor=0.5
                    </configurationParameters>
                </properties>
            </configuration>
        </plugin>
    </plugins>
</build>
```
A `DebugFixtures` is provided that can be temporarily extended by the
test class, its LaunchOptions can be configured as seen fit.
See [Parallel Execution](https://junit.org/junit5/docs/snapshot/user-guide/index.html#writing-tests-parallel-execution)
in the JUnit documentation for more information.

## Creating Tests

Playwright offers a tool to generate the code of the tests. By using
the following command 

`mvn exec:java -e -D exec.mainClass=com.microsoft.playwright.CLI -D exec.args=codegen`

a browser window and inspector are opened. 

A recording can be started during which interactions with the page 
are translated into the corresponding lines of code. In addition,
assertions and locators can be generated too.