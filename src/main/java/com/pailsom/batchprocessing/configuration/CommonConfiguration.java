package com.pailsom.batchprocessing.configuration;

import org.springframework.batch.core.configuration.JobRegistry;
import org.springframework.batch.core.configuration.support.JobRegistryBeanPostProcessor;
import org.springframework.batch.core.launch.support.ExitCodeMapper;
import org.springframework.batch.core.launch.support.SimpleJvmExitCodeMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.io.InputStream;
import java.util.HashMap;

import com.google.auth.oauth2.ServiceAccountCredentials;
import com.google.cloud.storage.BlobInfo;
import com.google.cloud.storage.Storage;
import com.google.cloud.storage.StorageOptions;

@Configuration
public class CommonConfiguration {

    @Bean
    public ExitCodeMapper exitCodeMapper() {
        final SimpleJvmExitCodeMapper exitCodeMapper = new SimpleJvmExitCodeMapper();
        HashMap map = new HashMap();
        map.put("NONE_PROCESSED", 3);
        exitCodeMapper.setMapping(map);
        return exitCodeMapper;
    }

    @Autowired
    JobRegistry jobRegistry;

    @Bean
    public JobRegistryBeanPostProcessor jobRegistryBeanPostProcessor() {
        JobRegistryBeanPostProcessor postProcessor = new JobRegistryBeanPostProcessor();
        postProcessor.setJobRegistry(jobRegistry);
        return postProcessor;
    }

    @Bean
    public static Storage storage() throws Exception {
        InputStream keyStream = Configuration.class.getClassLoader()
                .getResourceAsStream("absolute-vertex-328512-6a81b856ea60.json");

        // Define the Google cloud storage
        Storage storage = StorageOptions.newBuilder()
                .setCredentials(ServiceAccountCredentials.fromStream(keyStream))
                .build()
                .getService();

        return storage;
    }
}
