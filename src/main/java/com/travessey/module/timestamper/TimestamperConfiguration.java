package com.travessey.module.timestamper;

import com.graphaware.common.policy.InclusionPolicies;
import com.graphaware.common.policy.none.IncludeNoRelationships;
import com.graphaware.runtime.config.BaseTxDrivenModuleConfiguration;
import com.graphaware.runtime.policy.InclusionPoliciesFactory;


public class TimestamperConfiguration extends BaseTxDrivenModuleConfiguration<TimestamperConfiguration> {

    private static final String DEFAULT_CREATED_PROPERTY = Properties.CREATED;
    private static final String DEFAULT_UPDATED_PROPERTY = Properties.UPDATED;

    private final String updatedProperty;
    private final String createdProperty;


    private TimestamperConfiguration(InclusionPolicies inclusionPolicies, long initializeUntil, String updatedProperty, String createdProperty) {
        super(inclusionPolicies, initializeUntil);
        this.createdProperty = createdProperty;
        this.updatedProperty = updatedProperty;
    }

    public static TimestamperConfiguration defaultConfiguration() {
        return new TimestamperConfiguration(InclusionPoliciesFactory
                .allBusiness()
                .with(IncludeNoRelationships.getInstance())
                , ALWAYS, DEFAULT_UPDATED_PROPERTY, DEFAULT_CREATED_PROPERTY);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    protected TimestamperConfiguration newInstance(InclusionPolicies inclusionPolicies, long initializeUntil) {
        return new TimestamperConfiguration(inclusionPolicies, initializeUntil, getUpdatedProperty(), getCreatedProperty());
    }

    public String getUpdatedProperty() {
        return updatedProperty;
    }

    public String getCreatedProperty() {
        return createdProperty;
    }

    /**
     * Create a new instance of this {@link TimestamperConfiguration} with different updated property.
     *
     * @param updatedProperty of the new instance.
     * @return new instance.
     */
    public TimestamperConfiguration withUpdatedProperty(String updatedProperty) {
        return new TimestamperConfiguration(getInclusionPolicies(), initializeUntil(), updatedProperty, getCreatedProperty());
    }

    /**
     * Create a new instance of this {@link TimestamperConfiguration} with different created property.
     *
     * @param createdProperty of the new instance.
     * @return new instance.
     */
    public TimestamperConfiguration withCreatedProperty(String createdProperty) {
        return new TimestamperConfiguration(getInclusionPolicies(), initializeUntil(), getUpdatedProperty(), createdProperty);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        if (!super.equals(o)) {
            return false;
        }

        TimestamperConfiguration that = (TimestamperConfiguration) o;

        if (!createdProperty.equals(that.createdProperty)) {
            return false;
        }
        if (!updatedProperty.equals(that.updatedProperty)) {
            return false;
        }

        return true;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public int hashCode() {
        int result = super.hashCode();
        result = 31 * result + createdProperty.hashCode();
        result = 31 * result + updatedProperty.hashCode();
        return result;
    }
  }
