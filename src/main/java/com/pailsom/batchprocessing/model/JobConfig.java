package com.pailsom.batchprocessing.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.HashMap;
import java.util.Map;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class JobConfig {
    private String jobName;

    private Map<String,String> param = new HashMap<>();

    private boolean isForceRun = true;
}
