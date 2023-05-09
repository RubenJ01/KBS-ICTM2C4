# HMI-application

Before working on the application please read this document in its entirety.
This document describes how this application is build and how each of its components are programmed.

## Code Conventions

The following code conventions should be followed at all times:

- Classes should be written in upper camel case: ExampleClass.
- Methods and variables should be written in camel case: exampleVariable.
- Constants should be written in screaming snake case: EXAMPLE_CONSTANT.
- Packages and property files should be written in lower dot case: example.yml

## Configuration

To save specific configuration data we use yaml. To get started with this create a config.yml file in the root folder.
Check [config.yml.example](config.yml.example) to see what this file should be structured like.
The config.yml file should never be committed as it contains private configuration data.
All configuration data will be loaded in the [Config.java](src/main/java/utility/Config.java) file.
When adding new data to the configuration file make sure to add it to the example file and create appropriate variables
and getters for the data.
Make sure to also inform your fellow group members.

### Accessing configuration data

The Config.java class is a [singleton](https://www.baeldung.com/java-singleton), and thus we can retrieve the instance
of this class by calling its getInstance() method:

```java
Config config = Config.getInstance();
config.getPassword();
```

## Database

Please read [this](src/main/java/database/database.md) document for all database related information.

