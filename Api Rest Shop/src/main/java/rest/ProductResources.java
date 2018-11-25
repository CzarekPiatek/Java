package rest;

import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;

import javax.persistence.PersistenceContext;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import domain.Comment;
import domain.Product;

@Path("/product")
@Stateless
public class ProductResources {

	@PersistenceContext
	EntityManager em;

	// dodaj produkt
	@POST
	@Consumes(MediaType.APPLICATION_JSON)
	public Response Add(Product product) {
		em.persist(product);
		return Response.ok(product.getId()).build();
	}

	// wypisz wszystkie produkty
	@GET
	@Path("/all")
	@Produces(MediaType.APPLICATION_JSON)
	public List<Product> getAll() {
		return em.createNamedQuery("product.all", Product.class).getResultList();
	}

	// wypisz produkt o id
	@GET
	@Path("/{id}")
	@Produces(MediaType.APPLICATION_JSON)
	public Response get(@PathParam("id") int id) {
		Product result = em.createNamedQuery("product.id", Product.class).setParameter("productId", id)
				.getSingleResult();
		if (result == null) {
			return Response.status(404).build();
		}
		return Response.ok(result).build();
	}

	// przedzial cenowy
	@GET
	@Path("/search/price/{lower}.{higher}")
	@Produces(MediaType.APPLICATION_JSON)
	public List<Product> getPrice(@PathParam("lower") float lower, @PathParam("higher") float higher) {
		return em.createNamedQuery("product.price", Product.class).setParameter("lower", lower)
				.setParameter("higher", higher).getResultList();

	}

	// szukaj po nazwie
	@GET
	@Path("/search/name/{name}")
	@Produces(MediaType.APPLICATION_JSON)
	public List<Product> getName(@PathParam("name") String name) {
		return em.createNamedQuery("product.name", Product.class).setParameter("name", name).getResultList();

	}

	// szukaj po kategorii
	@GET
	@Path("/search/category/{category}")
	@Produces(MediaType.APPLICATION_JSON)
	public List<Product> getCategory(@PathParam("category") String category) {
		return em.createNamedQuery("product.category", Product.class).setParameter("category", category)
				.getResultList();

	}

	// uaktualnij produkt
	@PUT
	@Path("/{id}")
	@Consumes(MediaType.APPLICATION_JSON)
	public Response update(@PathParam("id") int id, Product p) {
		Product result = em.createNamedQuery("product.id", Product.class).setParameter("productId", id)
				.getSingleResult();
		if (result == null) {
			return Response.status(404).build();
		}
		result.setName(p.getName());
		result.setCategory(p.getCategory());
		result.setPrice(p.getPrice());
		em.persist(result);
		return Response.ok().build();
	}

	// usun produkt
	@DELETE
	@Path("/{id}")
	public Response deleteProduct(@PathParam("id") int id) {
		Product result = em.createNamedQuery("product.id", Product.class).setParameter("productId", id)
				.getSingleResult();
		if (result == null) {
			return Response.status(404).build();
		}
		em.remove(result);
		return Response.ok().build();
	}

	// dodaj komentarz do produktu
	@POST
	@Path("/{id}/comment")
	@Consumes(MediaType.APPLICATION_JSON)
	public Response AddComment(@PathParam("id") int id, Comment comment) {
		Product result = em.createNamedQuery("product.id", Product.class).setParameter("productId", id)
				.getSingleResult();
		if (result == null) {
			return Response.status(404).build();
		}
		result.getComment().add(comment);
		comment.setProduct(result);
		em.persist(comment);
		return Response.ok().build();
	}

	// wypisz komentarz

	@GET
	@Path("/{id}/comment")
	@Produces(MediaType.APPLICATION_JSON)
	public Response getComment(@PathParam("id") int id) {
		Comment result = em.createNamedQuery("comment.id", Comment.class).setParameter("commentId", id)
				.getSingleResult();
		if (result == null) {
			return Response.status(404).build();
		}
		return Response.ok(result).build();
	}

	// wypisz wszystkie komentarze
	@GET
	@Path("/comment/all")
	@Produces(MediaType.APPLICATION_JSON)
	public List<Product> getAllComments() {
		return em.createNamedQuery("comment.all", Product.class).getResultList();
	}

	// usun komentarz
	@DELETE
	@Path("/{id}/comment")
	public Response deleteComment(@PathParam("id") int id) {
		Comment result = em.createNamedQuery("comment.id", Comment.class).setParameter("commentId", id)
				.getSingleResult();
		if (result == null) {
			return Response.status(404).build();
		}
		em.remove(result);
		return Response.ok().build();
	}

}
