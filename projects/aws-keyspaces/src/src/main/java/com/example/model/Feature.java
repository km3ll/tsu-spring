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
import java.util.Date;
import java.util.Map;
import java.util.Set;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder(toBuilder = true)
@Table(value = Feature.TABLE_NAME)
public class Feature {

    public static final String TABLE_NAME = "app_features";

    @Data
    @AllArgsConstructor
    @NoArgsConstructor
    @Builder(toBuilder = true)
    @PrimaryKeyClass
    public static class Key {
        @PrimaryKeyColumn(name = "company_id", ordinal = 0, type = PrimaryKeyType.PARTITIONED)
        private String companyId;

        @PrimaryKeyColumn(name = "country_code", ordinal = 1, type = PrimaryKeyType.PARTITIONED)
        private String countryCode;

        @PrimaryKeyColumn(name = "feature_id", ordinal = 2, type = PrimaryKeyType.CLUSTERED)
        private String featureId;
    }

    @PrimaryKey
    private Feature.Key key;

    @Column("is_active")
    private Boolean isActive;

    @Column("names")
    private Map<String, String> names;

    @Column("tags")
    private Map<String, String> tags;

    @Column("code")
    private Integer code;

    @Column("prices")
    private Set<Integer> prices;

    @Column("total")
    private Long total;

    @Column("alt_codes")
    private Set<String> altCodes;

    @Column("last_updated")
    private Date lastUpdated;

}
