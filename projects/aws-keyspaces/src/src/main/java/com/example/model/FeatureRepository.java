package com.example.model;

import java.util.List;
import org.springframework.data.cassandra.repository.CassandraRepository;
import org.springframework.data.cassandra.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface FeatureRepository extends CassandraRepository<Feature, Feature.Key> {

    @Query("select * from aws." + Feature.TABLE_NAME + " where tenant_id = ?0 and company_id = ?1 and country_code = ?2 and is_active = ?3")
    List<Feature> findByTenantIdAndCompanyIdAndCountryCodeAndIsActive(
        String tenantId,
        String companyId,
        String countryCode,
        Boolean isActive
    );

}
