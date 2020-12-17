package org.exoplatform.addons.services;

import org.exoplatform.container.ExoContainerContext;
import org.exoplatform.container.PortalContainer;
import org.exoplatform.services.log.*;
import org.picocontainer.Startable;

public class Hanen_StartableService implements Startable{
	 private static final Log LOG = ExoLogger.getExoLogger(Hanen_StartableService.class);
	
	@Override
	public void start() {
		// TODO Auto-generated method stub
		 LOG.info("loging in from Hanen_Startable Service !");
		 PortalContainer portalContainer = (PortalContainer) ExoContainerContext.getCurrentContainer();
		Hanen_Service myNewService = (Hanen_Service) portalContainer.getComponentInstance(Hanen_Service.class);
		    LOG.info(myNewService.call(" Calling function call of Hanen_Service !"));

	}
	@Override
	public void stop() {
		// TODO Auto-generated method stub
		 LOG.info("loging out from Hanen_Startable Service !");
		
	}
}
