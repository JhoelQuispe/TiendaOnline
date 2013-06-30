package pe.edu.ucsp.oms.domain;

import java.util.Date;

public class Purchase implements BaseEntity<Long> {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public static final String TABLE_NAME = "compra";

	private Long id;

	private Date date;

	private Offer offer;

	private Long ususuarioId;

	private Long productoId;

	private User owner;

	private Content product;

	private Double price;

	private Date downloadDate;

	/**
	 * Nota de 1 a 10 a los contenidos descargados.
	 */
	private Integer rate;

	@Override
	public Long getId() {
		return id;
	}

	@Override
	public void setId(Long id) {
		this.id = id;
	}

	public Offer getOffer() {
	  return offer;
  }

	public void setOffer(Offer offer) {
	  this.offer = offer;
  }

	public User getOwner() {
	  return owner;
  }

	public void setOwner(User owner) {
	  this.owner = owner;
  }

	public Content getProduct() {
	  return product;
  }

	public void setProduct(Content product) {
	  this.product = product;
  }

	public Date getDownloadDate() {
	  return downloadDate;
  }

	public void setDownloadDate(Date downloadDate) {
	  this.downloadDate = downloadDate;
  }

	public Integer getRate() {
	  return rate;
  }

	public void setRate(Integer rate) {
	  this.rate = rate;
  }

	public Double getPrice() {
	  return price;
  }

	public void setPrice(Double price) {
	  this.price = price;
  }

	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}

	public Long getUsusuarioId() {
		return ususuarioId;
	}

	public void setUsusuarioId(Long ususuarioId) {
		this.ususuarioId = ususuarioId;
	}

	public Long getProductoId() {
		return productoId;
	}

	public void setProductoId(Long productoId) {
		this.productoId = productoId;
	}
}
