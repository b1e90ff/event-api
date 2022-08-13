# Event-API

## About
This event API is there to react on certain events. This is especially useful if the program should work with additional user created mod's/plugins. This means that if for example a user connects to a server once the system itself can handle the whole thing and the user created mods/plugins too.

If you encounter any problems, have suggestions for improvement or if we can help
you in any other way, you are welcome to join our Discord https://discord.gg/6sBNTXTJn4

Here you will also be informed about future projects, updates and possible functions.

## Setup Maven
If you are using maven, just do the following:

1. Add a new repository url
```xml
<project>
    ...
    <repositories>
        <repository>
            <id>byteterm-repo</id>
            <name>ByteTerm Repository</name>
            <url>https://nexus.byteterm.de/repository/maven-public/</url>
        </repository>
        ...
    </repositories>
    ...
</project>
```
2. Add this dependency to your project. Important: change the version. You can see all available versions here https://nexus.byteterm.de/service/rest/repository/browse/maven-public/de/byteterm/event-api/
```xml
<project>
    ...
    <dependencies>
        <dependency>
            <groupId>de.byteterm</groupId>
            <artifactId>event-api</artifactId>
            <version>1.0</version>
        </dependency>
        ...
    </dependencies>
    ...
</project>
```

## Setup Gradle
If you are using gradle, just do the following:

1. Add a new repository url
```groovy
repositories {
    maven {
        url = 'https://nexus.byteterm.de/repository/maven-public/'
    }
}
```
2. Add this dependency to your project. Important: change the version. You can see all available versions here https://nexus.byteterm.de/service/rest/repository/browse/maven-public/de/byteterm/event-api/
```groovy
dependencies {
    implementation 'de.byteterm:event-api:1.0'
}
```
## Using in Java Program
1. To create your own Event you must create a Class that extends the Event class of this Library. Now you can create the methods or variable you need for this Event. In this Example we need the Application name and set it to final since this name does not change during the operation.
```java
import de.byteterm.event.Event;

public class ApplicationStartEvent extends Event {

    private final String applicationName;

    public ApplicationStartEvent(String applicationName) {
        this.applicationName = applicationName;
    }

    public String getApplicationName() {
        return applicationName;
    }
}
```
2. Next, we create the listener so that something also happens when the event is called. It doesn't matter how many listeners you write in a class.
In this example we print the Application name. 
```java
import de.byteterm.event.example.event.ApplicationStartEvent;
import de.byteterm.event.listener.Listener;
import de.byteterm.event.listener.annotation.HandleEvent;

public class ApplicationStartListener implements Listener {

    @HandleEvent
    public void onApplicationStart(ApplicationStartEvent event) {
        System.out.println(event.getApplicationName());
    }
}
```
3. Now we need to register and to call the Listener
```java
import de.byteterm.event.EventAPI;
import de.byteterm.event.example.event.ApplicationStartEvent;

public class Application {

    public static void main(String[] args) {
        // Register the listener
        EventAPI.registerListener(new ApplicationStartListener());

        // Call the listener
        EventAPI.callEvent(new ApplicationStartEvent("Example - Application"));
    }
}
```