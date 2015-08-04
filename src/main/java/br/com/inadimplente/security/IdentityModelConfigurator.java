package br.com.inadimplente.security;

import javax.enterprise.inject.Produces;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.transaction.Transactional;

import org.picketlink.idm.config.IdentityConfiguration;
import org.picketlink.idm.config.IdentityConfigurationBuilder;
import org.picketlink.idm.jpa.internal.JPAIdentityStore;
import org.picketlink.idm.jpa.model.sample.simple.IdentityTypeEntity;
import org.picketlink.idm.jpa.model.sample.simple.PartitionTypeEntity;
import org.picketlink.idm.model.basic.User;
import org.picketlink.idm.spi.ContextInitializer;
import org.picketlink.idm.spi.IdentityContext;
import org.picketlink.idm.spi.IdentityStore;

public class IdentityModelConfigurator {
	
	@Inject
	private EntityManager entityManager;

	@Produces
	@Transactional
	public IdentityConfiguration produceJPAConfiguration() {
		IdentityConfigurationBuilder builder = new IdentityConfigurationBuilder();

		// builder.named("jpa.config")
		// .stores()
		// .jpa()
		// .supportType(User.class, Role.class, Group.class, Realm.class)
		// .addContextInitializer(this.contextInitializer)
		// .supportGlobalRelationship(Grant.class, GroupMembership.class)
		// .supportCredentials(true)
		// .mappedEntity(GrantTypeEntity.class,
		// GroupMembershipTypeEntity.class, GroupTypeEntity.class,
		// PartitionTypeEntity.class,
		// RelationshipIdentityTypeEntity.class,
		// RelationshipTypeEntity.class, RoleTypeEntity.class,
		// PasswordCredentialTypeEntity.class, UserTypeEntity.class,
		// RealmTypeEntity.class).supportAllFeatures();
		//
		// return builder.build();

		builder.named("jpa.config").stores().jpa().supportType(User.class).supportCredentials(false)
				.mappedEntity(IdentityTypeEntity.class, PartitionTypeEntity.class).addContextInitializer(new ContextInitializer() {
                    @Override
                    public void initContextForStore(IdentityContext context, IdentityStore<?> store) {
                        if (store instanceof JPAIdentityStore) {
                            if (!context.isParameterSet(JPAIdentityStore.INVOCATION_CTX_ENTITY_MANAGER)) {
                                context.setParameter(JPAIdentityStore.INVOCATION_CTX_ENTITY_MANAGER, entityManager);
                            }
                        }
                    }
                });

		return builder.build();
	}

}
