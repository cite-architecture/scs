# Scala CITE Services

CITE service suite based on [finch](https://github.com/finagle/finch), loading data from files in the local file system.

## Version: 0.1.0

Status:  **experimental**.  No binary releases yet.

## Configuration

Copy `src/main/config.properties-template` to `src/main/config.properties`, and edit `config.properties` with appropriate values for your service.


## Running

The `build.sbt` file includes a task alias so that `sbt scs` starts a service running at `http://localhost:8080/scs`.


## Available microservices

The following services are in an initial state of implementation with no testing or meaningfuly error handling.

### Text services


- `/texts` => lists all distinct work-components appearing in cited text nodes
- `/texts/`**CTS URN** => (possibly empty) list of citable nodes matching **CTS URN**
- `/texts/first/`**CTS URN** => 0 or 1 citable node; if 1, the first node matching **CTS URN**
- `/texts/reff/`**CTS URN** => (possibly empty) list of CTS URNs matching **CTS URN**


### Text catalog services

- `/textcatalog` => lists catalog entries for all cataloged texts
- `/textcatalog`**CTS URN** =>  (possibly empty) list of catalog entries matching **CTS URN**
