

/* 
 * Copyright 2012-2015 the original author or authors. 
 * 
 * Licensed under the Apache License, Version 2.0 (the "License"); 
 * you may not use this file except in compliance with the License. 
 * You may obtain a copy of the License at 
 * 
 *      http://www.apache.org/licenses/LICENSE-2.0 
 * 
 * Unless required by applicable law or agreed to in writing, software 
 * distributed under the License is distributed on an "AS IS" BASIS, 
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied. 
 * See the License for the specific language governing permissions and 
 * limitations under the License. 
 */  
  
package com.bnrc.sdn.mongoClient;
  
import java.net.UnknownHostException;  
import java.util.Collection;  
import java.util.Collections;  
import java.util.HashSet;  
import java.util.Set;  
  


import org.springframework.beans.BeanUtils;  
import org.springframework.beans.factory.BeanClassLoaderAware;  
import org.springframework.beans.factory.BeanFactory;  
import org.springframework.beans.factory.NoSuchBeanDefinitionException;  
import org.springframework.beans.factory.annotation.Autowired;  
import org.springframework.beans.factory.config.BeanDefinition;  
import org.springframework.boot.autoconfigure.AutoConfigurationPackages;  
import org.springframework.boot.autoconfigure.AutoConfigureAfter;  
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;  
import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;  
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;  
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.context.properties.EnableConfigurationProperties;  
import org.springframework.context.annotation.Bean;  
import org.springframework.context.annotation.ClassPathScanningCandidateComponentProvider;  
import org.springframework.context.annotation.Configuration;  
import org.springframework.core.env.Environment;  
import org.springframework.core.io.ResourceLoader;  
import org.springframework.core.type.filter.AnnotationTypeFilter;  
import org.springframework.dao.DataAccessException;  
import org.springframework.dao.support.PersistenceExceptionTranslator;  
import org.springframework.data.annotation.Persistent;  
import org.springframework.data.mapping.model.FieldNamingStrategy;  
import org.springframework.data.mongodb.MongoDbFactory;  
import org.springframework.data.mongodb.core.MongoTemplate;  
import org.springframework.data.mongodb.core.SimpleMongoDbFactory;  
import org.springframework.data.mongodb.core.convert.CustomConversions;  
import org.springframework.data.mongodb.core.convert.DbRefResolver;  
import org.springframework.data.mongodb.core.convert.DefaultDbRefResolver;  
import org.springframework.data.mongodb.core.convert.MappingMongoConverter;  
import org.springframework.data.mongodb.core.convert.MongoConverter;  
import org.springframework.data.mongodb.core.mapping.Document;  
import org.springframework.data.mongodb.core.mapping.MongoMappingContext;  
import org.springframework.data.mongodb.gridfs.GridFsTemplate;  
import org.springframework.util.Assert;  
import org.springframework.util.ClassUtils;  
import org.springframework.util.StringUtils;  
  


import com.bnrc.sdn.properties.MongoProperties;

import com.mongodb.DB;  
import com.mongodb.Mongo;  
import com.mongodb.MongoClient;

/**
 * {@link EnableAutoConfiguration Auto-configuration} for Spring Data's mongo support.
 * <p> 
 * Registers a {@link MongoTemplate} and {@link GridFsTemplate} beans if no other beans of 
 * the same type are configured. 
 * <P> 
 * Honors the {@literal spring.data.mongodb.database} property if set, otherwise connects 
 * to the {@literal test} database. 
 * 
 * @author Dave Syer 
 * @author Oliver Gierke 
 * @author Josh Long 
 * @author Phillip Webb 
 * @author Eddú Meléndez 
 * @since 1.1.0 
 */  
@Configuration  
@ConditionalOnClass({ Mongo.class, MongoTemplate.class })  
@EnableConfigurationProperties(MongoProperties.class)  
@AutoConfigureAfter(MongoAutoConfiguration.class)  
@ConfigurationProperties
public class MongoDataAutoConfiguration implements BeanClassLoaderAware {  
  
    @Autowired  
    private MongoProperties properties;  
  
    @Autowired  
    private Environment environment;  
  
    @Autowired  
    private ResourceLoader resourceLoader;  
  
    private ClassLoader classLoader;  
  
    public void setBeanClassLoader(ClassLoader classLoader) {  
        this.classLoader = classLoader;  
    }  
  
    public void print(){
    	System.out.println(properties.getHost());
    	
    }

    @Bean
//    @ConditionalOnMissingBean(MongoDbFactory.class)
    public SimpleMongoDbFactory mongoDbFactory( MongoClient mongo) throws Exception {
        String database = this.properties.getDatabase();
        SimpleMongoDbFactory simpleMongoDbFactory = null;
//        System.out.println("==========="+mongo.getAddress());
        try{
        	simpleMongoDbFactory = new SimpleMongoDbFactory(mongo, database);
        }catch(Exception e){
        	e.printStackTrace();
//        	System.err.println("mongo is error");
        }
        return simpleMongoDbFactory;
    }  

    @Bean(name="mongoTemplate")
//  @ConditionalOnMissingBean
  public @Bean(name="mongoTemplate") MongoTemplate mongoTemplate(MongoDbFactory mongoDbFactory) throws UnknownHostException {  
  	MongoTemplate mongoTemplate = null;
//  	System.out.println("------------------------------"+mongoDbFactory.getDb().getName());
  	try{
  		mongoTemplate = new MongoTemplate(mongoDbFactory);  
  	}catch(Exception e){
  		e.printStackTrace();
//  		System.err.println("mongo conncect failed");
  	}
//  	System.out.println("+++++++++++++++++++++"+mongoTemplate.getDb().getName());
      return mongoTemplate;
  }  
    
//    @Bean  
////    @ConditionalOnMissingBean  
//    public MongoTemplate mongoTemplate(MongoDbFactory mongoDbFactory,  
//            MongoConverter converter) throws UnknownHostException {  
//    	MongoTemplate mongoTemplate = null;
//    	try{
//    		mongoTemplate = new MongoTemplate(mongoDbFactory, );  
//    	}catch(Exception e){
//    		e.printStackTrace();
//    	}
//        return mongoTemplate;
//    }  
  
//    @Bean  
////    @ConditionalOnMissingBean(MongoConverter.class)  
//    public MappingMongoConverter mappingMongoConverter(MongoDbFactory factory,  
//            MongoMappingContext context, BeanFactory beanFactory) {  
//        DbRefResolver dbRefResolver = new DefaultDbRefResolver(factory);  
//        MappingMongoConverter mappingConverter = new MappingMongoConverter(dbRefResolver,  
//                context);  
//        try {  
//            mappingConverter.setCustomConversions(beanFactory  
//                    .getBean(CustomConversions.class));  
//        }  
//        catch (NoSuchBeanDefinitionException ex) {  
//            // Ignore  
//        }  
//        return mappingConverter;  
//    }  
//  
//    @Bean  
////    @ConditionalOnMissingBean  
//    public MongoMappingContext mongoMappingContext(BeanFactory beanFactory)  
//            throws ClassNotFoundException {  
//        MongoMappingContext context = new MongoMappingContext();  
//        context.setInitialEntitySet(getInitialEntitySet(beanFactory));  
//        Class<? extends FieldNamingStrategy> strategyClass = this.properties  
//                .getFieldNamingStrategy();  
//        if (strategyClass != null) {  
//            context.setFieldNamingStrategy(BeanUtils.instantiate(strategyClass));  
//        }  
//        return context;  
//    }  
//  
//    private Set<Class<?>> getInitialEntitySet(BeanFactory beanFactory)  
//            throws ClassNotFoundException {  
//        Set<Class<?>> entitySet = new HashSet<Class<?>>();  
//        ClassPathScanningCandidateComponentProvider scanner = new ClassPathScanningCandidateComponentProvider(  
//                false);  
//        scanner.setEnvironment(this.environment);  
//        scanner.setResourceLoader(this.resourceLoader);  
//        scanner.addIncludeFilter(new AnnotationTypeFilter(Document.class));  
//        scanner.addIncludeFilter(new AnnotationTypeFilter(Persistent.class));  
//        for (String basePackage : getMappingBasePackages(beanFactory)) {  
//            if (StringUtils.hasText(basePackage)) {  
//                for (BeanDefinition candidate : scanner  
//                        .findCandidateComponents(basePackage)) {  
//                    entitySet.add(ClassUtils.forName(candidate.getBeanClassName(),  
//                            this.classLoader));  
//                }  
//            }  
//        }  
//        return entitySet;  
//    }  
//  
//    private static Collection<String> getMappingBasePackages(BeanFactory beanFactory) {  
//        try {  
//            return AutoConfigurationPackages.get(beanFactory);  
//        }  
//        catch (IllegalStateException ex) {  
//            // no auto-configuration package registered yet  
//            return Collections.emptyList();  
//        }  
//    }  
//  
//    @Bean  
////    @ConditionalOnMissingBean  
//    public GridFsTemplate gridFsTemplate(MongoDbFactory mongoDbFactory,  
//            MongoTemplate mongoTemplate) {  
//        return new GridFsTemplate(new GridFsMongoDbFactory(mongoDbFactory,  
//                this.properties), mongoTemplate.getConverter());  
//    }  
//  
//    /** 
//     * {@link MongoDbFactory} decorator to respect 
//     * {@link MongoProperties#getGridFsDatabase()} if set. 
//     */  
//    private static class GridFsMongoDbFactory implements MongoDbFactory {  
//  
//        private final MongoDbFactory mongoDbFactory;  
//  
//        private final MongoProperties properties;  
//  
//        public GridFsMongoDbFactory(MongoDbFactory mongoDbFactory,  
//                MongoProperties properties) {  
//            Assert.notNull(mongoDbFactory, "MongoDbFactory must not be null");  
//            Assert.notNull(properties, "Properties must not be null");  
//            this.mongoDbFactory = mongoDbFactory;  
//            this.properties = properties;  
//        }  
//  
//        public DB getDb() throws DataAccessException {  
//            String gridFsDatabase = this.properties.getGridFsDatabase();  
//            if (StringUtils.hasText(gridFsDatabase)) {  
//                return this.mongoDbFactory.getDb(gridFsDatabase);  
//            }  
//            return this.mongoDbFactory.getDb();  
//        }  
//  
//  
//        public DB getDb(String dbName) throws DataAccessException {  
//            return this.mongoDbFactory.getDb(dbName);  
//        }  
//  
//
//        public PersistenceExceptionTranslator getExceptionTranslator() {  
//            return this.mongoDbFactory.getExceptionTranslator();  
//        }  
//  
//    }  
  
}  
