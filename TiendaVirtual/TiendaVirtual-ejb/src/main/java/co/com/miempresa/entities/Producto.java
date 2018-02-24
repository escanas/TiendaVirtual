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
import javax.persistence.JoinColumn;
import javax.persistence.Lob;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

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
    , @NamedQuery(name = "Producto.findByProductEstado", query = "SELECT p FROM Producto p WHERE p.productEstado = :productEstado")
    , @NamedQuery(name = "Producto.findByIdCategoria", query = "SELECT COUNT(p) FROM Producto p WHERE p.categoria.catId = :idcategoria")
})
public class Producto implements Serializable {

    private static final long serialVersionUID = 1L;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "product_id")
    private Long productId;
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

    @NotNull
    @ManyToOne
    @JoinColumn(name = "product_ureg")
    private User user;

    @ManyToOne
    @JoinColumn(name = "product_uact")
    private User userUpdate;

    @Basic(optional = false)
    @NotNull
    @Column(name = "product_freg")
    @Temporal(TemporalType.TIMESTAMP)
    private Date productFreg;

    @Column(name = "product_fact")
    @Temporal(TemporalType.TIMESTAMP)
    private Date productFact;

    @Column(name = "producto_desc")
    private Integer desc;

    @JoinColumn(name = "cat_id")
    @ManyToOne
    private Categoria categoria;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "producto")
    private List<DetalleVenta> detalleVentaList;

    public Long getProductId() {
        return productId;
    }

    public void setProductId(Long productId) {
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

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public User getUserUpdate() {
        return userUpdate;
    }

    public void setUserUpdate(User userUpdate) {
        this.userUpdate = userUpdate;
    }

    public Date getProductFreg() {
        return productFreg;
    }

    public void setProductFreg(Date productFreg) {
        this.productFreg = productFreg;
    }

    public Date getProductFact() {
        return productFact;
    }

    public void setProductFact(Date productFact) {
        this.productFact = productFact;
    }

    public List<DetalleVenta> getDetalleVentaList() {
        return detalleVentaList;
    }

    public void setDetalleVentaList(List<DetalleVenta> detalleVentaList) {
        this.detalleVentaList = detalleVentaList;
    }

    public Integer getDesc() {
        return desc;
    }

    public void setDesc(Integer desc) {
        this.desc = desc;
    }

    public Categoria getCategoria() {
        return categoria;
    }

    public void setCategoria(Categoria categoria) {
        this.categoria = categoria;
    }

}
