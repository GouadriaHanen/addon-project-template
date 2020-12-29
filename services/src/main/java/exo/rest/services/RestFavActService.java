package exo.rest.services;

import io.swagger.annotations.ApiParam;
import org.exoplatform.addons.dao.JPA_dao;
import org.exoplatform.addons.entity.FavoriteActivityEntity;
import org.exoplatform.services.log.ExoLogger;
import org.exoplatform.services.log.Log;
import org.exoplatform.services.rest.resource.ResourceContainer;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import javax.annotation.security.RolesAllowed;
import javax.ws.rs.*;
import javax.ws.rs.core.Response;
import java.util.Calendar;
import java.util.List;


@Path("/v1/favAct")
@Produces("application/json")
@Consumes("application/json")
public class RestFavActService implements ResourceContainer {

    private JPA_dao dao =new JPA_dao();
    private static final Log LOG = ExoLogger.getExoLogger(RestFavActService.class);

    public RestFavActService() {
    }


    @GET
    @Path("/hello/{name}")
    @RolesAllowed("users")
    public String hello(@PathParam("name") String name) {
        LOG.info("hello API");
        return "Hello " + name ;
    }

    @POST
    @Path("/add")
    public Response Add(@ApiParam(value = "act", required = true) FavoriteActivityEntity act)
     {
         LOG.info("ADD API");
         act.setFavoriteDate(Calendar.getInstance());
         LOG.info("act to add :"+act.getFavoriteDate());
         dao.AddAct(act);
         return Response.ok().build();
    }

    @PUT
    @Path("/update")
    public Response Update(@ApiParam(value = "act", required = true) FavoriteActivityEntity act )//RequestBody ??
     {  //TODO convert to JSON
         FavoriteActivityEntity res = dao.Update(act);
        return Response.ok(res).build();
    }

    @GET
    @Path("/acts")
    public Response GetAll() throws JSONException {
        LOG.info("GetAll API");
        List<FavoriteActivityEntity> res = dao.FindAllActs();
        LOG.info("result    "+res);
        JSONArray list = new JSONArray();
        for (int i = 0; i<res.size(); i++) {

            JSONObject jsonObject = new JSONObject();
            jsonObject.put("ID", res.get(i).getID());
            jsonObject.put("activity_Title", res.get(i).getActivityTitle());
            jsonObject.put("target", res.get(i).getTargetActivity());
            jsonObject.put("Calender", res.get(i).getFavoriteDate());

            list.put(jsonObject);
        }
        return Response.ok(list.toString()).build();
    }

    @GET
    @Path("/act/{id}")
    public Response GetById(@PathParam("id") Long id ) throws JSONException {
        //TODO convert to JSON
         FavoriteActivityEntity res = dao.FindActById(id);
        LOG.info("result    "+res);
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("ID", res.getID());
        jsonObject.put("activity_Title", res.getActivityTitle());
        jsonObject.put("target", res.getTargetActivity());
        jsonObject.put("Calender", res.getFavoriteDate());
        return Response.ok(jsonObject.toString()).build();
    }

    @DELETE
    @Path("/delete/{id}")
    public Response deleteById(@PathParam("id") Long id ){
        dao.deleteAct(id);
        return Response.ok().build();
    }

    @DELETE
    @Path("/deleteAll")
    public Response deleteAll(){
        dao.deleteAll();
        return Response.ok().build();
    }

}
