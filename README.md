# metadata-preconfig-rest
Metadata preconfigured REST based application to get started.

## Prerequisites
* Java 17
* Maven
* Docker

## Repository layout
Repository consists of two independent applications:
* metadata-deployer - database schema deployment application
* metadata-app - main application

## How to start locally
Under directory _docker_ is located a _docker-compose.yaml_ which runs both applications as a Docker Swarm Cluster.

Depending on environment (meaning Windows, UNIX) there are located to _startup_ files _startup.bat_ and _startup.sh_.

Each of them contains a set of parameters:
* all - executes Maven build of the whole repository, builds Docker image and runs Swarm Cluster
* app - executes Maven build of the _metadata-app_ main application, rebuilds Docker image and re-runs it in Swarm Cluster
* deployer - executes Maven build of the _metadata-deployer_ Liquibase deployment application, rebuilds Docker image and re-runs it in Swarm Cluster
* images - stops applications in Swarm Cluster, destroys cluster by removing all containers, rebuilds images and starts them again in Swarm mode

## Working with database
This particular solution supposes that all database modifications including table creation, data population
and all other manipulations should be performed using _metadata-deployer_ application and not a Liquibase Maven plugin.

This approach was chosen to allow a possibility to run Liquibase in Docker container and Cloud environment.

After each database adjustment whether it is a new data or table a full Maven rebuild of _metadata-deployer_
as well as application re-run are required.
Note that as soon as Liquibase execution finishes container should go down with exit code 0.

## Reference
Detailed guidelines of how to use Metadata Framework can be found
under the following [link](https://github.com/sergeivisotsky/metadata/blob/master/README.adoc).