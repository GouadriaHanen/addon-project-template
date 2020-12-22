package org.exoplatform.addons.services;


import org.exoplatform.services.log.ExoLogger;
import org.exoplatform.services.log.Log;


public class Hanen_Service {
	 private static final Log LOG = ExoLogger.getExoLogger(Hanen_Service.class);


	public Hanen_Service() {
		LOG.info("loged with Hanen_Service! ");

	}
	//for testing startable service
	 public String call(String msg) {
		if (msg.isEmpty()){
			return "Sorry ! empty msg";
		 }
		else return msg;
	}



}
