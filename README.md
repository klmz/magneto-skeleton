Magneto project skeleton.
================

Clone/Fork this repository to get started with your own Magneto distribution. It provides the Maven skeleton to add your own plugins to to magneto. 

# Set-up

## Set up your configuration
To run the bot on XMMP, you need to fill in the configuration file in `distribution/src/main/config/config.properties`

## Change the artifact ids (optional)
You can change the artifact and group id in the poms to reflect your company name.

You also need to replace the following line in `distribution/src/main/assembly` to reflect your groupId and artifactId.

	<exclude>com.yourcompany:yourcompany-magneto-skeleton</exclude>

## Writing a plugin.

You can add your own plugins to this skeleton by adding code to this repository. Add your new plugin as a Maven module the the parent pom and list it as a dependency in the distribution pom. We have added the *politeness* plugin as an example.

Magneto will scan all plugins on the classpath automatically, so once you package this project, the distribution module will contain magneto and all the dependencies you have added. 

## Importing a plug-in.

We of course want you to share your plug-ins with others. To do so, create you plug-in in a separate git module. That module can be added to this repository by adding it as a git submodule. Once it is imported, add it to the partent pom for cmopilation and to the distribution pom as a dependency. After that Magneto will pick it up automatically.

# Deploying it
In the distribution module, you will find a distribution folder in target. In there you will find a start/stop script that can be called to run magneto.

# Testing and development.

Magneto ships with a Command Line Interface to test if your plugin works. To enable it, run Magneto or the distribution jar with command line arguments "CLI". 

Run the the jar using `java -jar trolo.java CLI`. Or from Maven use `mvn exec:java -Dexec.args=CLI`.