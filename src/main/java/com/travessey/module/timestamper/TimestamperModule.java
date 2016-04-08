package com.travessey.module.timestamper;

import org.neo4j.graphdb.GraphDatabaseService;
import org.neo4j.graphdb.Node;
import org.neo4j.graphdb.Relationship;
import org.neo4j.graphdb.PropertyContainer;
import com.graphaware.common.util.Change;
import com.graphaware.runtime.module.BaseTxDrivenModule;
import com.graphaware.runtime.module.DeliberateTransactionRollbackException;
import com.graphaware.tx.event.improved.api.ImprovedTransactionData;

/**
 * {@link com.graphaware.runtime.module.TxDrivenModule} that assigns UUID's to nodes in the graph.
 */
public class TimestamperModule extends BaseTxDrivenModule<Void> {

    public static final String DEFAULT_MODULE_ID = "TIMESTAMPER";
    private static final int BATCH_SIZE = 1000;

    private final TimestamperConfiguration configuration;

    /**
     * Construct a new UUID module.
     *
     * @param moduleId ID of the module.
     */
    public TimestamperModule(String moduleId, TimestamperConfiguration configuration, GraphDatabaseService database) {
        super(moduleId);
        this.configuration = configuration;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public TimestamperConfiguration getConfiguration() {
        return configuration;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Void beforeCommit(ImprovedTransactionData transactionData) throws DeliberateTransactionRollbackException {

        //Set the updated and created to all nodes
        for (Node node : transactionData.getAllCreatedNodes()) {
            assignCreated(node);
            assignUpdated(node);
        }

        for(Change<Node> change :transactionData.getAllChangedNodes()) {
          assignUpdated(change.getCurrent());
        }

        //Set the updated and created to relationships
        for (Relationship relationship : transactionData.getAllCreatedRelationships()) {
          assignCreated(relationship);
          assignUpdated(relationship);
        }

        for (Change<Relationship> change : transactionData.getAllChangedRelationships()) {
          assignUpdated(change.getCurrent());
        }

        return null;
    }

    private void assignCreated(PropertyContainer container) {
        container.setProperty(configuration.getCreatedProperty(), System.currentTimeMillis());
    }

    private void assignUpdated(PropertyContainer container) {
      container.setProperty(configuration.getUpdatedProperty(), System.currentTimeMillis());
    }
}
