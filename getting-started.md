# Getting started

## Configuration

### JDK version

The JDK for the project is initially set to JDK 11; this can be changed in [`build.gradle`](build.gradle), by changing the value passed to the `JavaLanguageVersion.of(int)` static factory method, in line 7.  

### Root project name

Initially, the root project is called `project`. This root project name will become part of the name of any JAR files built by the project; thus, it should be changed to something more relevant to your specific project. This is done by editing the value assigned to `rootProject.name` in the [`settings.gradle`](settings.gradle) file. (There is a `TODO` comment for this change in that file.) The value assigned must be in lower-case; conventionally, `spinal-case` (all lower-case, with dashes separating multiple words) is used.

### Maven coordinates

Most Gradle projects have Maven _coordinates,_ enabling the project's build artifacts to be cached automatically (for reference by other builds, among other things) and optionally deployed to local or remote Maven repositories. There are (at least) three components to Maven coordinates:

* `groupId`
* `artifactId`
* `version`

Setting the `rootProject.name` property in the build (see ["Root project name", above](#root-project-name)) also sets the `artifactId` portion of the Maven coordinates. 

This project starter is set up so that you can set the `groupId` by assigning a value to the `group` property in the [`gradle.properties`](gradle.properties) file. (There's a `TODO` comment for this change in that file). 

The `version` for the Maven coordinates is also set in [`gradle.properties`](gradle.properties). Here, it is set initially to `1.0.0-SNAPSHOT`, and should be updated according to your project's versioning scheme. (This value is also included as part of the name of JAR artifacts produced by the build.)

### Source and resource folders

Gradle has a default directory structure&mdash;shown below&mdash;for the source, test source, resource, and test resource folder. IntelliJ automatically uses this structure in a Gradle project; there's no need to set or change it.

* `src/`
    * `main/`
        * `java/` (source folder)
        * `resources/` (resource folder)
    * `test/`
        * `java/` (test source folder)
        * `resources/` (rest resource folder)

Since Git tracks only files and their paths relative to the repository root, but not directories themselves, each of these directories initially contains a `.keep` file, so that the directory structure is preserved in Git. If/when files are added to any given one of these directories, the `.keep` file in that directory can be deleted.

### Main class of JAR artifact

The project is configured to use the `com.github.johnrengelman.shadow` plug-in (see line 2 of [`build.gradle`](build.gradle)), to create a runnable JAR from the root project, with all dependencies included. This plug-in uses the `mainClass` property of the `application` plug-in to set the corresponding attribute of the JAR's manifest. You will need to set this property by assigning a value to the `mainClass` property in [`gradle.properties`](gradle.properties). (There's a `TODO` comment for this change in that file)       

### Dependencies

We do not recommend that dependencies be incorporated directly as JAR files in the project, unless those JARs are _not_ otherwise accessible in the Maven Central repository, or another accessible Maven repository. Instead, whenever possible, project dependencies should be declared in the `dependencies` task of [`build.gradle`](build.gradle) (initially starting at line 25), using the appropriate Gradle methods to specify each dependency's Maven coordinates. 

Note that JUnit5 is already included in the `dependencies` task. Here are just a few more frequently used libraries, with the Gradle statement to be added to `dependencies` for the latest release of each:

| Library | Statement in `dependency`                                             |
|:--------|:----------------------------------------------------------------------|
| Gson    | `implementation 'com.google.code.gson:gson:2.10'`                     |
| Jackson | `implementation 'com.fasterxml.jackson.core:jackson-databind:2.14.0'` |
| Jansi   | `implementation 'org.fusesource.jansi:jansi:2.4.0'`                   |

### Version control

The `.gitignore` file included in this project repository is specifically intended for use with either a Gradle or Maven project. 

The list of included/excluded files and directories is close to&mdash;but not exactly the same as&mdash;the corresponding list for a native IntelliJ project. If, at some moment, the project is converted to a native IntelliJ project, some files currently being ignored in the `.idea` directory should be tracked in version control. Do this by replacing the line (initially line 4) reading  

```gitignore
.idea/
```

with the following:

```gitignore
.idea/*
!.idea/artifacts/
!.idea/libraries/
!.idea/modules.xml
```

If only one developer will be contributing to the repository, or if the team members have standardized on JDK names configured in IntelliJ, then the following line should be included as well, immediately after those shown above:

```gitignore
!.idea/misc.xml
```

These changes should be made by one team member, on one branch of the repository, then propagated (to remotes, other branches, other team members' local clones, etc.) via the usual Git mechanisms.

## Build tasks

In a project it recognizes as using Gradle, IntelliJ automatically leverages Gradle for the operations found under the **Build** menu. However, a more complete set of build tasks is available under _Tasks_ in IntelliJ's **Gradle** tool window. Here are some of the tasks that can be executed in this project:

* _application_

    * _run_
  
        Loads the class specified (in [`build.gradle`](build.gradle)) in the `application.mainClass` property, and invokes its `main(String[])` method.

* _build_

    * _assemble_

        Compiles code in `src/main/java`, processes resources in `src/main/resources`, generates HTML from the bytecode and the Javadoc comments in the source code, and packages the results into JAR files in `build/libs`.

    * _build_

        Compiles code in `src/main/java` and processes resources in `src/main/resources`, with output in `build/classes/java`.

    * _clean_

        Deletes the `build` directory&mdash;including all previously compiled `.class` files, copied resource files, etc. 

    * _jar_

        Compiles code in `src/main/java`, processes resources in `src/main/resources`, and packages the results into a JAR file in `build/libs`.

    * _javadocJar_

        Compiles code in `src/main/java`, generates HTML from the bytecode and the Javadoc comments in the source code, and packages the results into a JAR file in `build/libs`.
    
* _documentation_

    * _javadoc_

        Compiles code in `src/main/java`, and generates HTML from the bytecode and the Javadoc comments in the source code, with results generated into `build/docs/javadoc`.

* _shadow_

    * _shadowJar_

        Compiles code in `src/main/java`, processes resources in `src/main/resources`, and packages the results (including dependencies) into a JAR file in `build/libs`.

* _verification_

    * _test_

       Compiles code in `src/main/java` and `src/test/java`, processes resources in `src/main/resources` and `src/test/resources`, with output in `build/classes/java`, and launches JUnit to run all test methods in `build/classes/java/test`.
