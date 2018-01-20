/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.com.miempresa.entities;

import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.Cacheable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author Ccepeda
 */
@Entity
@Table(name = "tbl_detalle")
@Cacheable(false)
@NamedQueries({
    @NamedQuery(name = "DetalleVenta.findAll", query = "SELECT d FROM DetalleVenta d")
    , @NamedQuery(name = "DetalleVenta.findByDetalleId", query = "SELECT d FROM DetalleVenta d WHERE d.detalleId = :detalleId")
    , @NamedQuery(name = "DetalleVenta.findByDetalleCantidad", query = "SELECT d FROM DetalleVenta d WHERE d.detalleCantidad = :detalleCantidad")
    , @NamedQuery(name = "DetalleVenta.findByDetalleValor", query = "SELECT d FROM DetalleVenta d WHERE d.detalleValor = :detalleValor")
    , @NamedQuery(name = "DetalleVenta.findDetalleByVentaId", query = "SELECT d FROM DetalleVenta d WHERE d.venta.ventaId = :idventa")
    , @NamedQuery(name = "DetalleVenta.findByProductId", query = "SELECT d FROM DetalleVenta d WHERE d.producto.productId = :idproducto")
})
public class DetalleVenta implements Serializable {

    private static final long serialVersionUID = 1L;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "detalle_id")
    private Double detalleId;
    @Basic(optional = false)
    @NotNull
    @Column(name = "detalle_cantidad")
    private float detalleCantidad;

    @Basic(optional = false)
    @NotNull
    @Column(name = "detalle_valor")
    private Double detalleValor;

    @JoinColumn(name = "product_id", referencedColumnName = "product_id")
    @ManyToOne(optional = false)
    private Producto producto;

    @JoinColumn(name = "venta_id", referencedColumnName = "venta_id")
    @ManyToOne(optional = false)
    private Venta venta;

    public Double getDetalleId() {
        return detalleId;
    }

    public void setDetalleId(Double detalleId) {
        this.detalleId = detalleId;
    }

    public float getDetalleCantidad() {
        return detalleCantidad;
    }

    public void setDetalleCantidad(float detalleCantidad) {
        this.detalleCantidad = detalleCantidad;
    }

    public Double getDetalleValor() {
        return detalleValor;
    }

    public void setDetalleValor(Double detalleValor) {
        this.detalleValor = detalleValor;
    }

    public Producto getProducto() {
        return producto;
    }

    public void setProducto(Producto producto) {
        this.producto = producto;
    }

    public Venta getVenta() {
        return venta;
    }

    public void setVenta(Venta venta) {
        this.venta = venta;
    }

}
