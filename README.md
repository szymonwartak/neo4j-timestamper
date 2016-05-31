neo4j-timestamper
===================
Neo4J Timestamper  is a simple plugin that adds timestamps to created/changed nodes and relationships

This plugin in bases on <a href="https://github.com/graphaware/neo4j-uuid">neo4j-uuid</a> plugin.
thats why you will need the <a href="https://github.com/graphaware/neo4j-framework" target="_blank">GraphAware Neo4j Framework</a> (which you can <a href="http://graphaware.com/downloads/" target="_blank">download here</a>).


Setup and Configuration
--------------------

### Server Mode
1. Just Download <a href="https://github.com/royipressburger/neo4j-timestamper/releases/download/1.0.0/timestamper-1.0.0.jar">timestamper.jar</a> and place him and the <a href="https://github.com/graphaware/neo4j-framework" target="_blank">GraphAware Neo4j Framework</a> at the plugins directory.

2. Edit neo4j.conf to register the Timestamper module:

```
com.graphaware.runtime.enabled=true

#TIMESTAMPER becomes the module ID:
com.graphaware.module.TIMESTAMPER.1=com.travessey.module.timestamper.TimestamperBootstrapper

#optional, defaults are displayed here:
com.graphaware.module.TIMESTAMPER.createdProperty=created
com.graphaware.module.TIMESTAMPER.updatedProperty=updated
```
`com.graphaware.module.TIMESTAMPER.createdProperty` and `com.graphaware.module.TIMESTAMPER.updatedProperty` are the properties names that will be used to store the assigned timestamps on nodes and relationships.


##### For more information You can take a look on the <a href="https://github.com/graphaware/neo4j-uuid">GraphAware Neo4j UUID Project</a>
