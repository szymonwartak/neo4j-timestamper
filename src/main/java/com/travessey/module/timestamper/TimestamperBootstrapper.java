package com.travessey.module.timestamper;

import java.util.Map;

import com.graphaware.runtime.module.BaseRuntimeModuleBootstrapper;
import com.graphaware.runtime.module.RuntimeModule;
import com.graphaware.runtime.module.RuntimeModuleBootstrapper;
import org.neo4j.graphdb.GraphDatabaseService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class TimestamperBootstrapper extends BaseRuntimeModuleBootstrapper<TimestamperConfiguration> implements RuntimeModuleBootstrapper {

    private static final Logger LOG = LoggerFactory.getLogger(TimestamperBootstrapper.class);

    private static final String UPDATED_TIME_PROPERTY = "updatedProperty";
    private static final String CREATED_TIME_PROPERTY = "createdProperty";

    /**
     * {@inheritDoc}
     */
    @Override
    protected TimestamperConfiguration defaultConfiguration() {
        return TimestamperConfiguration.defaultConfiguration();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    protected RuntimeModule doBootstrapModule(String moduleId, Map<String, String> config, GraphDatabaseService database, TimestamperConfiguration configuration) {
        if (config.get(UPDATED_TIME_PROPERTY) != null && config.get(UPDATED_TIME_PROPERTY).length() > 0) {
            configuration = configuration.withUpdatedProperty(config.get(UPDATED_TIME_PROPERTY));
            LOG.info("updatedProperty set to {}", configuration.getUpdatedProperty());
        }

        if (config.get(CREATED_TIME_PROPERTY) != null && config.get(CREATED_TIME_PROPERTY).length() > 0) {
            configuration = configuration.withCreatedProperty(config.get(CREATED_TIME_PROPERTY));
            LOG.info("createdProperty set to {}", configuration.getCreatedProperty());
        }

        return new TimestamperModule(moduleId, configuration, database);
    }
}
