package br.com.architecture.quartz;

import org.quartz.*;
import org.quartz.impl.StdSchedulerFactory;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import javax.ejb.Singleton;
import javax.ejb.Startup;
import javax.inject.Inject;

@Singleton
@Startup
public class ScheduleManager {

    private Scheduler scheduler;

    @Inject
    private ConcreteJobFactory jobFactory;

    @PostConstruct
    private void postConstruct() throws SchedulerException {
        try {
            scheduler = new StdSchedulerFactory().getScheduler();
            scheduler.setJobFactory(jobFactory);
            JobDetail novaVotacaoJob = JobBuilder.newJob(NovaVotacaoJob.class)
                    .withIdentity("novaVotacaoJob", "votacaoJobs").build();

            //CronScheduleBuilder.dailyAtHourAndMinute(22, 23))
            CronTrigger novaVotacaoTrigger = TriggerBuilder
                    .newTrigger()
                    .withIdentity("novaVotacaoTrigger", "votacaoTriggers")
                    .startNow()
                    .withSchedule(CronScheduleBuilder.dailyAtHourAndMinute(06, 00))
                    .build();

            JobDetail fechaVotacaoJob = JobBuilder.newJob(FechaVotacaoJob.class)
                    .withIdentity("fechaVotacaoJob", "votacaoJobs").build();

            CronTrigger fechaVotacaoTrigger = TriggerBuilder
                    .newTrigger()
                    .withIdentity("fechaVotacaoTrigger", "votacaoTriggers")
                    .withSchedule(CronScheduleBuilder.dailyAtHourAndMinute(11, 30))
                    .build();

            scheduler.scheduleJob(novaVotacaoJob, novaVotacaoTrigger);
            scheduler.scheduleJob(fechaVotacaoJob, fechaVotacaoTrigger);
            scheduler.start();
        } catch (SchedulerException e) {
            e.printStackTrace();
            throw e;
        }

    }

    @PreDestroy
    public void preDestroy() throws SchedulerException {
        try {
            if (scheduler != null && scheduler.isStarted()) {
                scheduler.shutdown(false);
            }
        } catch (SchedulerException e) {
            throw e;
        }
    }

}
