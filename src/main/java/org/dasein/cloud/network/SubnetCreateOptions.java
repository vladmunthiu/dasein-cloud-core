package org.dasein.cloud.network;

import org.dasein.cloud.Tag;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import java.util.HashMap;
import java.util.Map;

/**
 * Options for creating subnets of in a network.
 * <p>Created by George Reese: 1/29/13 9:32 AM</p>
 * @author George Reese
 * @version 2013.04 initial version
 * @since 2013.04
 */
public class SubnetCreateOptions {
    /**
     * Creation options for the most basic approach to creating a subnet. Because no data center is specified, either the
     * cloud or the Dasein Cloud implementation will randomly pick a data center (if appropriate).
     * @param inVlanId the VLAN in which the subnet will be created
     * @param cidr a CIDR that is a subnet the addressing of the parent VLAN indicating the address space for the subnet
     * @param name a user-friendly name for the new subnet
     * @param description a description of the subnet's purpose
     * @return options for creating a subnet with the specified parameters
     */
    static public @Nonnull SubnetCreateOptions getInstance(@Nonnull String inVlanId, @Nonnull String cidr, @Nonnull String name, @Nonnull String description) {
        SubnetCreateOptions options = new SubnetCreateOptions();

        options.providerVlanId = inVlanId;
        options.cidr = cidr;
        options.name = name;
        options.description = description;
        return options;
    }

    /**
     * Creation options for creating a subnet in a specific data center.
     * @param inVlanId the VLAN in which the subnet will be created
     * @param inDcId the data center in which the subnet will be created
     * @param cidr a CIDR that is a subnet the addressing of the parent VLAN indicating the address space for the subnet
     * @param name a user-friendly name for the new subnet
     * @param description a description of the subnet's purpose
     * @return options for creating a subnet with the specified parameters
     */
    static public @Nonnull SubnetCreateOptions getInstance(@Nonnull String inVlanId, @Nonnull String inDcId, @Nonnull String cidr, @Nonnull String name, @Nonnull String description) {
        SubnetCreateOptions options = new SubnetCreateOptions();

        options.providerVlanId = inVlanId;
        options.providerDataCenterId = inDcId;
        options.cidr = cidr;
        options.name = name;
        options.description = description;
        return options;
    }

    /**
     * Creation options for creating a subnet in a specific data center with pre-defined meta-data.
     * @param inVlanId the VLAN in which the subnet will be created
     * @param inDcId the data center in which the subnet will be created
     * @param cidr a CIDR that is a subnet the addressing of the parent VLAN indicating the address space for the subnet
     * @param name a user-friendly name for the new subnet
     * @param description a description of the subnet's purpose
     * @param tags any meta-data to assign to the subnet
     * @return options for creating a subnet with the specified parameters
     */
    static public @Nonnull SubnetCreateOptions getInstance(@Nonnull String inVlanId, @Nonnull String inDcId, @Nonnull String cidr, @Nonnull String name, @Nonnull String description, @Nonnull Tag ... tags) {
        SubnetCreateOptions options = new SubnetCreateOptions();

        options.providerVlanId = inVlanId;
        options.providerDataCenterId = inDcId;
        options.cidr = cidr;
        options.name = name;
        options.description = description;
        options.metaData = new HashMap<String, Object>();
        for( Tag t : tags ) {
            options.metaData.put(t.getKey(), t.getValue());
        }
        return options;
    }

    private String             cidr;
    private String             description;
    private Map<String,Object> metaData;
    private String             name;
    private String             providerDataCenterId;
    private String             providerVlanId;

    private SubnetCreateOptions() { }

    /**
     * @return the address space into which resources for the subnet to be created will be placed
     */
    public @Nonnull String getCidr() {
        return cidr;
    }

    /**
     * @return a description of the purpose of the subnet to be created
     */
    public @Nonnull String getDescription() {
        return description;
    }

    /**
     * @return any custom meta-data associated with the subnet to be created
     */
    public @Nonnull Map<String,Object> getMetaData() {
        return (metaData == null ? new HashMap<String, Object>() : metaData);
    }

    /**
     * @return the name of the subnet to be created
     */
    public @Nonnull String getName() {
        return name;
    }

    /**
     * @return the data center into which the subnet to be created will be placed (or any if <code>null</code>)
     */
    public @Nullable String getProviderDataCenterId() {
        return providerDataCenterId;
    }

    /**
     * @return the VLAN to which the new subnet will belong
     */
    public @Nonnull String getProviderVlanId() {
        return providerVlanId;
    }

    /**
     * Adds meta-data to associate with the subnet to be created. This method is additive; it will not wipe existing
     * meta-data.
     * @param metaData the meta-data to add
     * @return this
     */
    public @Nonnull SubnetCreateOptions withMetaData(@Nonnull Map<String,Object> metaData) {
        if( this.metaData == null ) {
            this.metaData = new HashMap<String, Object>();
        }
        this.metaData.putAll(metaData);
        return this;
    }
}