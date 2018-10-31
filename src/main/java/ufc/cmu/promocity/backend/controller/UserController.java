package ufc.cmu.promocity.backend.controller;

import ufc.cmu.promocity.backend.model.User;
import ufc.cmu.promocity.backend.service.UserService;
import ufc.cmu.promocity.backend.service.interfaces.IUserService;

import java.net.URI;
import java.util.List;

import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Response;

import org.springframework.stereotype.Component;

/**
 * Users Controller
 * @author armandosoaressousa
 *
 */
@Component
@Path("/users")
public class UserController {
	private UserService userService;
	
	/**
	 * Contrutor of UserController
	 * @param userService
	 */
	public UserController(UserService userService) {
		this.userService = userService;
		this.userService.createTestUsers();
	}

	 /**
     * Retorna em um JSON todos os usuarios cadastrados
     * @return
     */
    @GET
    @Produces("application/json")
    public List<User> getAllUsers() {
        return userService.getAllUsers();
    }
    
    /**
     * Dado um id retorna o JSON dos dados do usuario
     * @param id
     * @return
     */
    @GET
    @Produces("application/json")
    @Path("/{id}")
    public User getUser(@PathParam("id") String id) {
    	return userService.getUser(Long.parseLong(id));
    }
    
    /**
     * Dados os dados de um livro adiciona um livro no repositorio
     * @param user
     * @return
     */
    @POST
    @Produces("application/json")
    @Consumes("application/json")
    public Response addUser(User user) {
        userService.addUser(user);
        URI uri = URI.create("/" + String.valueOf(user.getId()));
		return Response.created(uri).build();
    }
    
    /**
     * Dado um id e os dados do user faz sua atualizacao
     * @param id
     * @param user
     * @return
     */
    @PUT
    @Consumes("application/json")
    @Path("/{id}")
    public Response updateUser(@PathParam("id") String id, User user) {
        userService.updateUser(Long.parseLong(id), user);
        return Response.noContent().build();
    }
    
    /**
     * Dado um id de um livro faz sua remocao do repositorio
     * @param id
     * @return
     */
    @DELETE
    @Path("/{id}")
    public Response deleteUser(@PathParam("id") String id) {
        userService.deleteUser(Long.parseLong(id));
        return Response.ok().build();
    }
    
    /*
     * 	usuarios\idUsuario\cupons\add
		usuarios\idUsuario\cupons\idCupom
		usuarios\idUsuario\cupons\list
     */
}