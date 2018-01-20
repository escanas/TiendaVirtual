/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.com.miempresa.entities;

import java.io.Serializable;
import java.util.Date;
import java.util.List;
import javax.persistence.Basic;
import javax.persistence.Cacheable;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Lob;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author Ccepeda
 */
@Entity
@Table(name = "tbl_producto")
@Cacheable(false)
@NamedQueries({
    @NamedQuery(name = "Producto.findAll", query = "SELECT p FROM Producto p")
    , @NamedQuery(name = "Producto.findByProductId", query = "SELECT p FROM Producto p WHERE p.productId = :productId")
    , @NamedQuery(name = "Producto.findByProductIdRepetidos", query = "SELECT p FROM Producto p WHERE p.productId = :productId order by p.productId desc")
    , @NamedQuery(name = "Producto.findByProductCode", query = "SELECT p FROM Producto p WHERE p.productCode = :productCode")
    , @NamedQuery(name = "Producto.findByProductDecription", query = "SELECT p FROM Producto p WHERE p.productDecription = :productDecription")
    , @NamedQuery(name = "Producto.findByProductPrecio", query = "SELECT p FROM Producto p WHERE p.productPrecio = :productPrecio")
    , @NamedQuery(name = "Producto.findByProductCantidad", query = "SELECT p FROM Producto p WHERE p.productCantidad = :productCantidad")
    , @NamedQuery(name = "Producto.findByProductEstado", query = "SELECT p FROM Producto p WHERE p.productEstado = :productEstado")
    , @NamedQuery(name = "Producto.findByProductUreg", query = "SELECT p FROM Producto p WHERE p.productUreg = :productUreg")
    , @NamedQuery(name = "Producto.findByProductFreg", query = "SELECT p FROM Producto p WHERE p.productFreg = :productFreg")
    , @NamedQuery(name = "Producto.findByProductUact", query = "SELECT p FROM Producto p WHERE p.productUact = :productUact")
    , @NamedQuery(name = "Producto.findByProductFact", query = "SELECT p FROM Producto p WHERE p.productFact = :productFact")})
public class Producto implements Serializable {

    private static final long serialVersionUID = 1L;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "product_id")
    private Double productId;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 80)
    @Column(name = "product_code")
    private String productCode;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 2500)
    @Column(name = "product_decription")
    private String productDecription;
    @Basic(optional = false)
    @NotNull
    @Column(name = "product_precio")
    private long productPrecio;
    @Lob
    @Column(name = "product_imagen")
    private byte[] productImagen;
    @Column(name = "product_cantidad")
    private Integer productCantidad;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 20)
    @Column(name = "product_estado")
    private String productEstado;
    @Basic(optional = false)
    @NotNull
    @Column(name = "product_ureg")
    private double productUreg;
    @Basic(optional = false)
    @NotNull
    @Column(name = "product_freg")
    @Temporal(TemporalType.TIMESTAMP)
    private Date productFreg;
    @Column(name = "product_uact")
    private Double productUact;
    @Column(name = "product_fact")
    @Temporal(TemporalType.TIMESTAMP)
    private Date productFact;
    
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "producto")
    private List<DetalleVenta> detalleVentaList;

    public Producto() {
    }

    public Producto(Double productId) {
        this.productId = productId;
    }

    public Producto(Double productId, String productCode, String productDecription, long productPrecio, String productEstado, double productUreg, Date productFreg) {
        this.productId = productId;
        this.productCode = productCode;
        this.productDecription = productDecription;
        this.productPrecio = productPrecio;
        this.productEstado = productEstado;
        this.productUreg = productUreg;
        this.productFreg = productFreg;
    }

    public Double getProductId() {
        return productId;
    }

    public void setProductId(Double productId) {
        this.productId = productId;
    }

    public String getProductCode() {
        return productCode;
    }

    public void setProductCode(String productCode) {
        this.productCode = productCode;
    }

    public String getProductDecription() {
        return productDecription;
    }

    public void setProductDecription(String productDecription) {
        this.productDecription = productDecription;
    }

    public long getProductPrecio() {
        return productPrecio;
    }

    public void setProductPrecio(long productPrecio) {
        this.productPrecio = productPrecio;
    }

    public byte[] getProductImagen() {
        return productImagen;
    }

    public void setProductImagen(byte[] productImagen) {
        this.productImagen = productImagen;
    }

    public Integer getProductCantidad() {
        return productCantidad;
    }

    public void setProductCantidad(Integer productCantidad) {
        this.productCantidad = productCantidad;
    }

    public String getProductEstado() {
        return productEstado;
    }

    public void setProductEstado(String productEstado) {
        this.productEstado = productEstado;
    }

    public double getProductUreg() {
        return productUreg;
    }

    public void setProductUreg(double productUreg) {
        this.productUreg = productUreg;
    }

    public Date getProductFreg() {
        return productFreg;
    }

    public void setProductFreg(Date productFreg) {
        this.productFreg = productFreg;
    }

    public Double getProductUact() {
        return productUact;
    }

    public void setProductUact(Double productUact) {
        this.productUact = productUact;
    }

    public Date getProductFact() {
        return productFact;
    }

    public void setProductFact(Date productFact) {
        this.productFact = productFact;
    }

    @XmlTransient
    public List<DetalleVenta> getDetalleVentaList() {
        return detalleVentaList;
    }

    public void setDetalleVentaList(List<DetalleVenta> detalleVentaList) {
        this.detalleVentaList = detalleVentaList;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (productId != null ? productId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Producto)) {
            return false;
        }
        Producto other = (Producto) object;
        if ((this.productId == null && other.productId != null) || (this.productId != null && !this.productId.equals(other.productId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "co.com.miempresa.entities.Producto[ productId=" + productId + " ]";
    }
    
}
