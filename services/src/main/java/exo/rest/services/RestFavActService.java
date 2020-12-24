package exo.rest.services;

import io.swagger.annotations.ApiParam;
import org.exoplatform.addons.dao.JPA_dao;
import org.exoplatform.addons.entity.FavoriteActivityEntity;
import org.exoplatform.services.rest.resource.ResourceContainer;

import javax.annotation.security.RolesAllowed;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;


@Path("/v1/Acts")
@Produces("application/json")
public class RestFavActService implements ResourceContainer {

    private JPA_dao dao ;

    public RestFavActService(JPA_dao dao) {
        this.dao = dao;
    }

    @GET
    @Path("/hello/{name}")
    @RolesAllowed({"users"})
    public String hello(@PathParam("name") String name) {
        return "Hello " + name;
    }

    @POST
    @Path("/addAct")
    @RolesAllowed({"users"})
    public Response Add(@ApiParam(value = "News", required = true) FavoriteActivityEntity act )//RequestBody ??
     {
         FavoriteActivityEntity res = dao.AddAct(act);
        return Response.ok(res).build();
    }

    @POST
    @Path("/update")
    @RolesAllowed({"users"})
    public Response Update(@ApiParam(value = "News", required = true) FavoriteActivityEntity act )//RequestBody ??
     {  //TODO convert to JSON
         FavoriteActivityEntity res = dao.Update(act);
        return Response.ok(res).build();
    }

    @GET
    @Path("/acts")
    @RolesAllowed({"users"})
    public Response GetAll()//RequestBody ??
    {  //TODO convert to JSON
        FavoriteActivityEntity res = (FavoriteActivityEntity) dao.FindAllActs();
        return Response.ok(res).build();
    }

    @GET
    @Path("/act/{id}")
    @RolesAllowed({"users"})
    public Response GetById(@PathParam("id") Long id )
     {  //TODO convert to JSON
         FavoriteActivityEntity res = dao.FindActById(id);
        return Response.ok(res).build();
    }

    @DELETE
    @Path("/delete/{id}")
    @RolesAllowed({"users"})
    public Response deleteById(@PathParam("id") Long id )
     {
         FavoriteActivityEntity res = (FavoriteActivityEntity) dao.deleteAct(id);
        return Response.ok(res).build();
    }

}
