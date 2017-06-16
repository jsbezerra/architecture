package br.com.inadimplente.quartz;

import org.quartz.Job;
import org.quartz.JobDetail;
import org.quartz.Scheduler;
import org.quartz.SchedulerException;
import org.quartz.spi.JobFactory;
import org.quartz.spi.TriggerFiredBundle;

import javax.enterprise.context.ApplicationScoped;
import javax.enterprise.context.spi.CreationalContext;
import javax.enterprise.inject.spi.Bean;
import javax.enterprise.inject.spi.BeanManager;
import javax.inject.Inject;

@ApplicationScoped
public class ConcreteJobFactory implements JobFactory {

    @Inject
    private BeanManager beanManager;

    @Override
    public Job newJob(TriggerFiredBundle bundle, Scheduler Scheduler) throws SchedulerException {
        JobDetail jobDetail = bundle.getJobDetail();
        Class<? extends Job> jobClazz = jobDetail.getJobClass();
        Bean<?> bean = beanManager.getBeans(jobClazz).iterator().next();
        CreationalContext<?> ctx = beanManager.createCreationalContext(bean);
        return (Job) beanManager.getReference(bean, jobClazz, ctx);
    }
}
