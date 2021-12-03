package com.pailsom.batchprocessing;

import com.pailsom.batchprocessing.batch.JobRunningService;
import com.pailsom.batchprocessing.model.JobConfig;
import com.pailsom.batchprocessing.model.Person;
import com.pailsom.batchprocessing.repository.PersonRepository;
import org.springframework.batch.core.JobParameter;
import org.springframework.batch.core.JobParametersBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.Assert;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.regex.Pattern;

import static org.apache.commons.lang3.math.NumberUtils.isParsable;

@RestController
@RequestMapping("/job")
public class ApplicationJobRunner  {
    private final Pattern jobParameterPattern = Pattern.compile("([^=]+)=([^=]+)");

    private final JobRunningService jobRunningService;

    private final PersonRepository personRepository;

    @Autowired
    public ApplicationJobRunner(JobRunningService jobRunningService, PersonRepository personRepository) {
        this.jobRunningService = jobRunningService;
        this.personRepository = personRepository;
    }

    @PostMapping(value = "/run",consumes = "application/json", produces = "application/json")
    public List<Person> run(@RequestBody JobConfig jobConfig) {
        Assert.notNull(jobConfig.getJobName(),"Job name required field");

        JobParametersBuilder jobParametersBuilder = new JobParametersBuilder();

        jobConfig.getParam().entrySet().forEach( entry-> {
                    jobParametersBuilder.addParameter(entry.getKey(), parseJobParameter(entry.getValue()));
                }
        );

        if (jobConfig.isForceRun()) {
            jobParametersBuilder.addLong("uniqueId", System.currentTimeMillis());
        }

        jobRunningService.runJob(jobConfig.getJobName(), jobParametersBuilder.toJobParameters());

        return personRepository.findAll();
    }

    private JobParameter parseJobParameter(String argument) {
        if (isParsable(argument)) {
            if (argument.contains(".")) {
                return new JobParameter(Double.parseDouble(argument));
            } else {
                return new JobParameter(Long.parseLong(argument));
            }
        }
        return new JobParameter(argument);
    }


}
