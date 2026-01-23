package com.example.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.cassandra.core.cql.PrimaryKeyType;
import org.springframework.data.cassandra.core.mapping.Column;
import org.springframework.data.cassandra.core.mapping.PrimaryKey;
import org.springframework.data.cassandra.core.mapping.PrimaryKeyClass;
import org.springframework.data.cassandra.core.mapping.PrimaryKeyColumn;
import org.springframework.data.cassandra.core.mapping.Table;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder(toBuilder = true)
@Table(value = Feature.TABLE_NAME)
public class Feature {

    public static final String TABLE_NAME = "features";

    @Data
    @AllArgsConstructor
    @NoArgsConstructor
    @Builder(toBuilder = true)
    @PrimaryKeyClass
    public static class Key {
        @PrimaryKeyColumn(name = "tenant_id", ordinal = 0, type = PrimaryKeyType.PARTITIONED)
        private String tenantId;

        @PrimaryKeyColumn(name = "company_id", ordinal = 1, type = PrimaryKeyType.PARTITIONED)
        private String companyId;

        @PrimaryKeyColumn(name = "country_code", ordinal = 2, type = PrimaryKeyType.PARTITIONED)
        private String countryCode;

        @PrimaryKeyColumn(name = "feature_id", ordinal = 3, type = PrimaryKeyType.CLUSTERED)
        private String featureId;
    }

    @PrimaryKey
    private Feature.Key key;

    @Column("is_active")
    private Boolean isActive;

}
