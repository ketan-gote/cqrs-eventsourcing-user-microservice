package com.ws.user.core.configuration;

import java.util.Collections;

import org.axonframework.eventhandling.tokenstore.TokenStore;
import org.axonframework.eventsourcing.eventstore.EmbeddedEventStore;
import org.axonframework.eventsourcing.eventstore.EventStorageEngine;
import org.axonframework.eventsourcing.eventstore.EventStore;
import org.axonframework.extensions.mongo.DefaultMongoTemplate;
import org.axonframework.extensions.mongo.MongoTemplate;
import org.axonframework.extensions.mongo.eventsourcing.eventstore.MongoEventStorageEngine;
import org.axonframework.extensions.mongo.eventsourcing.eventstore.MongoFactory;
import org.axonframework.extensions.mongo.eventsourcing.eventstore.MongoSettingsFactory;
import org.axonframework.extensions.mongo.eventsourcing.tokenstore.MongoTokenStore;
import org.axonframework.serialization.Serializer;
import org.axonframework.spring.config.AxonConfiguration;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;

import com.mongodb.ServerAddress;
import com.mongodb.client.MongoClient;

@org.springframework.context.annotation.Configuration
public class Configuration {

	@Value("${spring.data.mongodb.host}")
	private String dbHost;

	@Value("${spring.data.mongodb.port}")
	private int dbPoort;

	@Value("${spring.data.mongodb.database}")
	private String dbName;

	@Bean
	public MongoClient mongoClient() {
		MongoSettingsFactory msf = new MongoSettingsFactory();
		msf.setMongoAddresses(Collections.singletonList(new ServerAddress(dbHost, dbPoort)));

		MongoFactory mf = new MongoFactory();
		mf.setMongoClientSettings(msf.createMongoClientSettings());

		return mf.createMongo();
	}

	@Bean
	public MongoTemplate axonMongoTemplate() {
		return DefaultMongoTemplate.builder(
				).mongoDatabase(mongoClient(), dbName)
				.build();
	}

	@Bean
	public TokenStore tokenStore(Serializer serializer) {
		return MongoTokenStore.builder()
				.mongoTemplate(axonMongoTemplate())
				.serializer(serializer)
				.build();
	}

	@Bean
	public EventStorageEngine storageEngine(MongoClient mongoClient) {
		return MongoEventStorageEngine.builder()
				.mongoTemplate(DefaultMongoTemplate.builder().mongoDatabase(mongoClient(), dbName).build())
				.build();
	}

	@Bean
	public EmbeddedEventStore embeddedEventStore(EventStorageEngine eventStorageEngine,
			AxonConfiguration axonConfiguration) {
		return EmbeddedEventStore.builder()
				.storageEngine(eventStorageEngine)
				.messageMonitor(axonConfiguration.messageMonitor(EventStore.class, "eventstore"))
				.build();
	}
}
