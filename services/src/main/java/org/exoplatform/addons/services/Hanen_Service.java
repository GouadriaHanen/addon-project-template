package org.exoplatform.addons.services;

import org.exoplatform.addons.entity.FavoriteAcitvity;
import org.exoplatform.container.ExoContainer;
import org.exoplatform.container.ExoContainerContext;
import org.exoplatform.services.jcr.RepositoryService;
import org.exoplatform.services.jcr.core.ManageableRepository;
import org.exoplatform.services.jcr.ext.common.SessionProvider;
import org.exoplatform.services.log.ExoLogger;
import org.exoplatform.services.log.Log;

import javax.jcr.*;
import javax.jcr.query.Query;
import javax.jcr.query.QueryManager;
import java.util.ArrayList;
import java.util.List;

public class Hanen_Service {
	 private static final Log LOG = ExoLogger.getExoLogger(Hanen_Service.class);
	private ExoContainer MyContainer ;
	private RepositoryService RepositoryService;
	private SessionProvider SessionProvider ;
	private ManageableRepository Repository ;
	private Session Session ;

	public Hanen_Service() throws RepositoryException {
		LOG.info("loged with Hanen_Service! ");
		//get container
		this.MyContainer = ExoContainerContext.getCurrentContainer();
		//get repo service
		this.RepositoryService = (RepositoryService) MyContainer. getComponentInstance(RepositoryService.class);
		// *** == > Reposervice is null ! whyy ??
		//get current repo
		this.Repository =   RepositoryService.getCurrentRepository();
		//creating system session-provider
		this.SessionProvider = SessionProvider.createSystemProvider();
		//session
		this.Session = SessionProvider.getSession(Repository.getConfiguration().getDefaultWorkspaceName(), Repository);
	}
	//for testing startable service
	 public String call(String msg) {
		if (msg.isEmpty()){
			return "Sorry ! empty msg";
		 }
		else return msg;
	}

	//Add Activity JCR
	public void addActivity (FavoriteAcitvity act ) throws RepositoryException {
		try{
			//Create a node that represents the root node
			Node root = Session.getRootNode();
			Node value= root.addNode("exo:FavoriteActivity");
			value.setProperty("Title",act.getTitle());
			value.setProperty("Link",act.getLink());
			value.setProperty("Publication Date",act.getPublication_Date().toString());
			// Save the session changes
			Session.save();
		}
		 catch(Exception e){
			e.printStackTrace();
		}
	}

	//get All activities JCR
	public List<FavoriteAcitvity> FindAll() throws RepositoryException {
		List <FavoriteAcitvity> acts = new ArrayList<>();
		try {
		QueryManager qm = Session.getWorkspace().getQueryManager();
		Query q = qm.createQuery("select * from exo:FavoriteActivity", Query.SQL);
		NodeIterator ni = q.execute().getNodes();
		while (ni.hasNext()) {
			Node iterNode= ni.nextNode();
			FavoriteAcitvity act = new FavoriteAcitvity(iterNode.getProperty("Title").getString(),
					iterNode.getProperty("Link").getString(),
					iterNode.getProperty("Publication Date").getString());
			acts.add(act);
		}
		}
		catch(Exception e){
			e.printStackTrace();
		}
		return acts ;
	}

	public void RemoveActivity (FavoriteAcitvity  act) throws RepositoryException  {
		try {
			QueryManager qm = Session.getWorkspace().getQueryManager();
			String title = act.getTitle() ;
			String query = "delete from exo:FavoriteActivity where Title=="+act.getTitle() ;
			Query q = qm.createQuery(query,Query.SQL);
			q.execute() ;
		}
		catch(Exception e){
			e.printStackTrace();
		}
	}


}
