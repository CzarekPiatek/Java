package domain;



import java.util.ArrayList;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.xml.bind.annotation.*;


@XmlRootElement
@Entity
@NamedQueries({
@NamedQuery(name="product.all",query="select p from Product p"),
@NamedQuery(name="product.id",query="from Product p where p.id=:productId"),
@NamedQuery(name="product.price",query="from Product p where p.price between :lower and :higher"),
@NamedQuery(name="product.name",query="from Product p where p.name like concat('%',:name,'%')"),
@NamedQuery(name="product.category",query="from Product p where p.category like concat('%',:category,'%')")
})
public class Product {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	private String name;
	private String category;
	private float price;
	@XmlTransient
	private List<Comment> comment = new ArrayList<Comment>();
	

	@XmlTransient
	@OneToMany(mappedBy="product")
	public List<Comment> getComment() {
		return comment;
	}
	public void setComment(List<Comment> comment) {
		this.comment = comment;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	
	public String getCategory() {
		return category;
	}
	public void setCategory(String category) {
		this.category = category;
	}
	public float getPrice() {
		return price;
	}
	public void setPrice(float price) {
		this.price = price;
	}
	
	

}

