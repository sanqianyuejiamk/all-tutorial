package com.mengka.springboot.config;

import com.mengka.springboot.util.TimeUtil;
import lombok.extern.log4j.Log4j2;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.StepContribution;
import org.springframework.batch.core.configuration.annotation.EnableBatchProcessing;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.batch.core.scope.context.ChunkContext;
import org.springframework.batch.core.step.tasklet.Tasklet;
import org.springframework.batch.repeat.RepeatStatus;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import java.util.Date;

/**
 * @author huangyy
 * @version cabbage-forward2.0,2018-05-29
 * @since cabbage-forward2.0
 */
@Log4j2
@Configuration
@EnableBatchProcessing
public class JobConfiguration {

    @Autowired
    private JobBuilderFactory jobBuilderFactory;

    @Autowired
    private StepBuilderFactory stepBuilderFactory;

    @Bean
    public Step step1() {
        return stepBuilderFactory.get("step1")
                .tasklet(new Tasklet() {
                    @Override
                    public RepeatStatus execute(StepContribution stepContribution, ChunkContext chunkContext) throws Exception {

                        log.info("---------, step1 execute..["+ TimeUtil.toDate(new Date(),TimeUtil.FORMAT_YYYY_MM_DD_HH_MM_SS));

                        return RepeatStatus.FINISHED;
                    }
                }).build();
    }

    public Step step2(){
        return stepBuilderFactory.get("step2")
                .tasklet(new Tasklet(){
                    @Override
                    public RepeatStatus execute(StepContribution stepContribution, ChunkContext chunkContext) throws Exception{

                        log.info("---------, step2 execute..["+ TimeUtil.toDate(new Date(),TimeUtil.FORMAT_YYYY_MM_DD_HH_MM_SS));

                        return RepeatStatus.FINISHED;
                    }
                }).build();
    }

    // our batch job
    @Bean
    public Job xmlToJsonToMongo() {
        return jobBuilderFactory.get("XML_Processor")
                .start(step1())
                .next(step2())
                .build();
    }
}
