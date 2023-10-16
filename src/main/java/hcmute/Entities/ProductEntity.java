package hcmute.Entities;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

@Entity
@Table(name = "Product")
@NamedQuery(name = "Product.findAll", query = "SELECT p FROM ProductEntity p")
public class ProductEntity implements Serializable {

	private static final long serialVersionUID = 1L;
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int ProductID;
	@Column(columnDefinition = "nvarchar(1000)")
	private String ProductName;
	@Column(columnDefinition = "nvarchar(2000)")
	private String Description;
	private int Price;
	private String imageLink;
	@Column(columnDefinition = "nvarchar(1000)")
	private int CategoryID;
	private int SellerID;
	private int Amount;
	private int stoke;

	@ManyToOne
	@JoinColumn(name = "CategoryID")
	private CategoryEntity category;

	public ProductEntity() {
		super();
	}

	public int getProductID() {
		return ProductID;
	}

	public void setProductID(int productID) {
		ProductID = productID;
	}

	public String getProductName() {
		return ProductName;
	}

	public void setProductName(String productName) {
		ProductName = productName;
	}

	public String getDescription() {
		return Description;
	}

	public void setDescription(String description) {
		Description = description;
	}

	public int getPrice() {
		return Price;
	}

	public void setPrice(int price) {
		Price = price;
	}

	public String getImageLink() {
		return imageLink;
	}

	public void setImageLink(String imageLink) {
		this.imageLink = imageLink;
	}

	public int getCategoryID() {
		return CategoryID;
	}

	public void setCategoryID(int categoryID) {
		CategoryID = categoryID;
	}

	public int getSellerID() {
		return SellerID;
	}

	public void setSellerID(int sellerID) {
		SellerID = sellerID;
	}

	public int getAmount() {
		return Amount;
	}

	public void setAmount(int amount) {
		Amount = amount;
	}

	public int getStoke() {
		return stoke;
	}

	public void setStoke(int stoke) {
		this.stoke = stoke;
	}

	public CategoryEntity getCategory() {
		return category;
	}

	public void setCategory(CategoryEntity category) {
		this.category = category;
	}

}
