/**
 * Copyright (c) 2015 Bosch Software Innovations GmbH and others.
 *
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 */
package org.eclipse.hawkbit.repository.jpa;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.eclipse.hawkbit.cache.TenantAwareCacheManager;
import org.eclipse.hawkbit.repository.util.AbstractIntegrationTest;
import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.data.mongodb.gridfs.GridFsOperations;

@SpringApplicationConfiguration(classes = { org.eclipse.hawkbit.RepositoryApplicationConfiguration.class,
        TestConfiguration.class })
public abstract class AbstractJpaIntegrationTest extends AbstractIntegrationTest {

    @PersistenceContext
    protected EntityManager entityManager;

    @Autowired
    protected TargetRepository targetRepository;

    @Autowired
    protected ActionRepository actionRepository;

    @Autowired
    protected DistributionSetRepository distributionSetRepository;

    @Autowired
    protected SoftwareModuleRepository softwareModuleRepository;

    @Autowired
    protected TenantMetaDataRepository tenantMetaDataRepository;

    @Autowired
    protected DistributionSetTypeRepository distributionSetTypeRepository;

    @Autowired
    protected SoftwareModuleTypeRepository softwareModuleTypeRepository;

    @Autowired
    protected TargetTagRepository targetTagRepository;

    @Autowired
    protected DistributionSetTagRepository distributionSetTagRepository;

    @Autowired
    protected SoftwareModuleMetadataRepository softwareModuleMetadataRepository;

    @Autowired
    protected ActionStatusRepository actionStatusRepository;

    @Autowired
    protected ExternalArtifactRepository externalArtifactRepository;

    @Autowired
    protected LocalArtifactRepository artifactRepository;

    @Autowired
    protected TargetInfoRepository targetInfoRepository;

    @Autowired
    protected GridFsOperations operations;

    @Autowired
    protected RolloutGroupRepository rolloutGroupRepository;

    @Autowired
    protected RolloutRepository rolloutRepository;

    @Autowired
    protected TenantAwareCacheManager cacheManager;

    private static CIMySqlTestDatabase tesdatabase;

    @BeforeClass
    public static void beforeClass() {
        createTestdatabaseAndStart();
    }

    private static void createTestdatabaseAndStart() {
        if ("MYSQL".equals(System.getProperty("spring.jpa.database"))) {
            tesdatabase = new CIMySqlTestDatabase();
            tesdatabase.before();
        }
    }

    @AfterClass
    public static void afterClass() {
        if (tesdatabase != null) {
            tesdatabase.after();
        }
    }

}
