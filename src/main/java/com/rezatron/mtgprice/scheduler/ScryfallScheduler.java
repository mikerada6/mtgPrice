package com.rezatron.mtgprice.scheduler;

import com.rezatron.mtgprice.controller.ScryfallController;
import lombok.extern.slf4j.Slf4j;
import net.javacrumbs.shedlock.core.SchedulerLock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Component
@Slf4j
public
class ScryfallScheduler {

    @Autowired
    ScryfallController scryfallController;

    @Scheduled( cron = "0 0 * * * *" )
    @SchedulerLock( name = "ScryfallScheduler_bulkDataUpdate",
                    lockAtLeastForString = "PT5M",
                    lockAtMostForString = "PT59M" )
    public
    void bulkDataUpdate() {
        log.info( "Running schedule to get bulk data." );
        scryfallController.bulkDataUpdate( null );
        log.info( "scheduled task done." );
    }

    @Scheduled( cron = "* * * * * *" )
    @SchedulerLock( name = "ScryfallScheduler_schedulerLockTest",
                    lockAtLeastForString = "PT5M",
                    lockAtMostForString = "PT59M" )
    public
    void schedulerLockTest() {
        log.info( "schedulerLockTest." );
    }


    @Scheduled( cron = "0 15 1,13 * * *" )
    @SchedulerLock( name = "ScryfallScheduler_reloadFromBackUp",
                    lockAtLeastForString = "PT5M",
                    lockAtMostForString = "PT59M" )
    public
    void reloadFromBackUp() {
        log.info( "Running schedule reloadFromBackUp." );
        scryfallController.reloadFromBackUp();
        log.info( "scheduled task done." );
    }
}
