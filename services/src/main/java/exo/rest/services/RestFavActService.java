package exo.rest.services;

import io.swagger.annotations.ApiParam;
import org.exoplatform.addons.dao.JPA_dao;
import org.exoplatform.addons.entity.FavoriteActivityEntity;
import org.exoplatform.services.rest.resource.ResourceContainer;
import org.exoplatform.social.core.jpa.storage.entity.ActivityEntity;

import javax.annotation.security.RolesAllowed;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.Calendar;


@Path("/v1/favAct")
@Produces("application/json")
@Consumes("application/json")
public class RestFavActService implements ResourceContainer {

    private JPA_dao dao =new JPA_dao();


    public RestFavActService() {
    }


    @GET
    @Path("/hello/{name}")
    public String hello(@PathParam("name") String name) {
        return "Hello " + name;
    }

    @POST
    @Path("/add")
   // @RolesAllowed({"users"})
    public Response Add(FavoriteActivityEntity act)//RequestBody ??
     {
         dao.AddAct(act);
         return Response.ok().build();
    }

    @PUT
    @Path("/update")
    //@RolesAllowed({"users"})
    public Response Update(@ApiParam(value = "act", required = true) FavoriteActivityEntity act )//RequestBody ??
     {  //TODO convert to JSON
         FavoriteActivityEntity res = dao.Update(act);
        return Response.ok(res).build();
    }

    @GET
    @Path("/acts")
 //   @RolesAllowed({"users"})
    public Response GetAll()//RequestBody ??
    {  //TODO convert to JSON
        FavoriteActivityEntity res = (FavoriteActivityEntity) dao.FindAllActs();
        return Response.ok(res).build();
    }

    @GET
    @Path("/act/{id}")
   // @RolesAllowed({"users"})
    public Response GetById(@PathParam("id") Long id )
     {  //TODO convert to JSON
         FavoriteActivityEntity res = dao.FindActById(id);
        return Response.ok(res).build();
    }

    @DELETE
    @Path("/delete/{id}")
   // @RolesAllowed({"users"})
    public Response deleteById(@PathParam("id") Long id )
     {
         FavoriteActivityEntity res = (FavoriteActivityEntity) dao.deleteAct(id);
        return Response.ok(res).build();
    }

}
