package fr.iutinfo.skeleton.api;

import static fr.iutinfo.skeleton.api.BDDFactory.getDbi;
import static fr.iutinfo.skeleton.api.BDDFactory.tableExist;

import java.sql.SQLException;
import java.util.List;
import java.util.stream.Collectors;

import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.NotFoundException;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.WebApplicationException;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.SecurityContext;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import fr.iutinfo.skeleton.common.dto.UserDto;

@Path("/user")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class UserResource {
	final static Logger logger = LoggerFactory.getLogger(UserResource.class);
	private static UserDao dao = getDbi().open(UserDao.class);

	public UserResource() throws SQLException {
		if (!tableExist("users")) {
			logger.debug("Create table users");
			dao.createUserTable();
			//dao.insert(new User(0, "Margaret Thatcher", "la Dame de fer"));
		}
	}

	@POST
	public Response createUser(UserDto dto, @Context SecurityContext context) {
		User u = (User) context.getUserPrincipal();
		if (u.getRole().equals("admin")) {
			User user = new User();
			user.initFromDto(dto);
			user.resetPasswordHash();
			int id = dao.insert(user);
			dto.setId(id);
			return Response.status(Response.Status.CREATED).build();
		}else
			return Response.status(Response.Status.FORBIDDEN).build();
	}

	@PUT
    @Path("/{id}")
    public Response modifyUser(@PathParam("id") Integer id, User user, @Context SecurityContext context){
    	if(dao.findById(id) == null){
    		return Response.status(Response.Status.NOT_FOUND).build();
    	}else{
    		dao.update(user);
    		return Response.status(Response.Status.NO_CONTENT).build();
    	}
    }

	@GET
	@Path("/{name}")
	public UserDto getUser(@PathParam("name") String name) {
		User user = dao.findByName(name);
		if (user == null) {
			throw new WebApplicationException(404);
		}
		return user.convertToDto();
	}

	@GET
	public List<UserDto> getAllUsers(@QueryParam("q") String query) {
		List<User> users;
		if (query == null) {
			users = dao.all();
		} else {
			logger.debug("Search users with query: " + query);
			users = dao.search("%" + query + "%");
		}
		return users.stream().map(User::convertToDto).collect(Collectors.toList());
	}

	@DELETE
	@Path("/{id}")
	public Response deleteUser(@PathParam("id") int id, @Context SecurityContext context) {
		if(((User)context.getUserPrincipal()).getRole().equals("admin")){
			dao.delete(id);
			return Response.status(Response.Status.NO_CONTENT).build();
		}else
			return Response.status(Response.Status.FORBIDDEN).build();
	}

}