# Scala CITE Services

CITE service suite based on [finch](https://github.com/finagle/finch), loading data from files in the local file system.


## Configuration

Copy `src/main/config.properties-template` to `src/main/config.properties`, and edit `config.properties` with appropriate values for your service.


## Running

The `build.sbt` file includes a task alias so that `sbt scs` starts a service running at `http://localhost:8080/scs`.


## Available microservices

The following services are in an initial state of implementation with no testing or meaningfuly error handling.

### Text Services


- `scs/texts` => lists all distinct work-components appearing in cited text nodes
- `scs/texts/`**CTS URN** => (possibly empty) list of citable nodes matching **CTS URN**
- `scs/texts/first/`**CTS URN** => 0 or 1 citable node; if 1, the first node matching **CTS URN**
- `scs/texts/reff/`**CTS URN** => (possibly empty) list of CTS URNs matching **CTS URN**
