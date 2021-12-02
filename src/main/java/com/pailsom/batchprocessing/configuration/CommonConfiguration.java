package com.pailsom.batchprocessing.configuration;

import org.springframework.batch.core.launch.support.ExitCodeMapper;
import org.springframework.batch.core.launch.support.SimpleJvmExitCodeMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.HashMap;

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
}
