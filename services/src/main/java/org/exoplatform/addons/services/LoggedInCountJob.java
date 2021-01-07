package org.exoplatform.addons.services;

import org.exoplatform.addons.listener.LoggedUserListener;
import org.exoplatform.container.ExoContainer;
import org.exoplatform.container.ExoContainerContext;
import org.exoplatform.services.log.ExoLogger;
import org.exoplatform.services.log.Log;
import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;

public class LoggedInCountJob implements Job {

    private static final Log LOG = ExoLogger.getLogger(LoggedInCountJob.class);

    LoggedUserListener service ;
    public LoggedInCountJob() {
        this.service= new LoggedUserListener();
    }

    @Override
    public void execute(JobExecutionContext jobExecutionContext) throws JobExecutionException {
        LOG.info("log for my job");
        LOG.info("number of connected user :  "+service.ConnectedUserCount());
    }
}
