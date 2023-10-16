package hcmute.Entities;

import java.io.Serializable;
import java.util.List;

import javax.persistence.*;

@Entity
@Table(name = "Category")
@NamedQuery(name = "Category.findAll", query = "SELECT c FROM CategoryEntity c")
public class CategoryEntity implements Serializable {
	private static final long serialVersionUID = 1L;
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int CategoryID;
	@Column(columnDefinition = "nvarchar(1000)")
	private String CategoryName;
	@Column(columnDefinition = "nvarchar(1000)")
	private String icon;

	public CategoryEntity() {
		super();
	}

	public int getCategoryID() {
		return CategoryID;
	}

	public void setCategoryID(int categoryID) {
		CategoryID = categoryID;
	}

	public String getCategoryName() {
		return CategoryName;
	}

	public void setCategoryName(String categoryName) {
		CategoryName = categoryName;
	}

	public String getIcon() {
		return icon;
	}

	public void setIcon(String icon) {
		this.icon = icon;
	}

//	@OneToMany(mappedBy = "category", fetch = FetchType.EAGER)
//	private List<ProductEntity> products;
//
//	public List<ProductEntity> getProducts() {
//		return products;
//	}
//
//	public void setProducts(List<ProductEntity> products) {
//		this.products = products;
//	}

}
