package org.exoplatform.addons.services;

import org.exoplatform.addons.entity.FavoriteAcitvity;
import org.exoplatform.container.ExoContainer;
import org.exoplatform.container.ExoContainerContext;
import org.exoplatform.services.jcr.RepositoryService;
import org.exoplatform.services.jcr.core.ManageableRepository;
import org.exoplatform.services.jcr.ext.common.SessionProvider;

import javax.jcr.*;
import javax.jcr.query.Query;
import javax.jcr.query.QueryManager;
import java.util.ArrayList;
import java.util.List;

public class JcrServices {
    private ExoContainer myContainer ;
    private RepositoryService repositoryService;
    private SessionProvider sessionProvider ;
    private ManageableRepository repository ;
    private Session session;

    public JcrServices() throws RepositoryException {
        //get container
        this.myContainer = ExoContainerContext.getCurrentContainer();
        //get repo service
        this.repositoryService = (RepositoryService) myContainer. getComponentInstance(RepositoryService.class);
        // *** == > Reposervice is null ! whyy ??
        //get current repo
        this.repository =   this.repositoryService.getCurrentRepository();
        //creating system session-provider
        this.sessionProvider = SessionProvider.createSystemProvider();
        //session
        this.session = sessionProvider.getSession(repository.getConfiguration().getDefaultWorkspaceName(), repository);
    }

    //Add Activity JCR
    public boolean AddActivity (FavoriteAcitvity act ) throws RepositoryException {
        try{
            //Create a node that represents the root node
            Node root = session.getRootNode();
            Node value= root.addNode("exo:FavoriteActivity");
            value.setProperty("Title",act.getTitle());
            value.setProperty("Link",act.getLink());
            value.setProperty("Publication Date",act.getPublication_Date().toString());
            // Save the session changes
            session.save();
        }
        catch(Exception e){
            e.printStackTrace();
        }
        return true ;
    }

    //get All activities JCR
    public List<FavoriteAcitvity> FindAll() throws RepositoryException {
        List <FavoriteAcitvity> acts = new ArrayList<>();
        try {
            QueryManager qm = session.getWorkspace().getQueryManager();
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

    public Boolean RemoveActivity (FavoriteAcitvity  act) throws RepositoryException  {
        try {
            QueryManager qm = session.getWorkspace().getQueryManager();
            String title = act.getTitle() ;
            String query = "delete from exo:FavoriteActivity where Title=="+act.getTitle() ;
            Query q = qm.createQuery(query,Query.SQL);
            q.execute() ;
            session.save();
        }
        catch(Exception e){
            e.printStackTrace();
        }
        return true ;
    }

}
