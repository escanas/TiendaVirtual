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
import javax.persistence.ManyToOne;
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
@Table(name = "tbl_venta")
@Cacheable(false)
@NamedQueries({
    @NamedQuery(name = "Venta.findAll", query = "SELECT v FROM Venta v")
    , @NamedQuery(name = "Venta.findByVentaId", query = "SELECT v FROM Venta v WHERE v.ventaId = :ventaId")
    , @NamedQuery(name = "Venta.findByVentaFecha", query = "SELECT v FROM Venta v WHERE v.ventaFecha = :ventaFecha")
    , @NamedQuery(name = "Venta.findByVentaTotal", query = "SELECT v FROM Venta v WHERE v.ventaTotal = :ventaTotal")
    , @NamedQuery(name = "Venta.findByVentaEstado", query = "SELECT v FROM Venta v WHERE v.ventaEstado = :ventaEstado")})
public class Venta implements Serializable {

    private static final long serialVersionUID = 1L;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "venta_id")
    private Double ventaId;
    @Basic(optional = false)
    @NotNull
    @Column(name = "venta_fecha")
    @Temporal(TemporalType.TIMESTAMP)
    private Date ventaFecha;
    @Basic(optional = false)
    @NotNull
    @Column(name = "venta_total")
    private long ventaTotal;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 20)
    @Column(name = "venta_estado")
    private String ventaEstado;
    
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "venta")
    private List<DetalleVenta> detalleVentaList;
    
    @JoinColumn(name = "user_id_vendedor", referencedColumnName = "user_id")
    @ManyToOne(optional = false)
    private User userIdVendedor;
    @JoinColumn(name = "user_id_cliente", referencedColumnName = "user_id")
    @ManyToOne(optional = false)
    private User userIdCliente;

    public Venta() {
    }

    public Venta(Double ventaId) {
        this.ventaId = ventaId;
    }

    public Venta(Double ventaId, Date ventaFecha, long ventaTotal, String ventaEstado) {
        this.ventaId = ventaId;
        this.ventaFecha = ventaFecha;
        this.ventaTotal = ventaTotal;
        this.ventaEstado = ventaEstado;
    }

    public Double getVentaId() {
        return ventaId;
    }

    public void setVentaId(Double ventaId) {
        this.ventaId = ventaId;
    }

    public Date getVentaFecha() {
        return ventaFecha;
    }

    public void setVentaFecha(Date ventaFecha) {
        this.ventaFecha = ventaFecha;
    }

    public long getVentaTotal() {
        return ventaTotal;
    }

    public void setVentaTotal(long ventaTotal) {
        this.ventaTotal = ventaTotal;
    }

    public String getVentaEstado() {
        return ventaEstado;
    }

    public void setVentaEstado(String ventaEstado) {
        this.ventaEstado = ventaEstado;
    }

    @XmlTransient
    public List<DetalleVenta> getDetalleVentaList() {
        return detalleVentaList;
    }

    public void setDetalleVentaList(List<DetalleVenta> detalleVentaList) {
        this.detalleVentaList = detalleVentaList;
    }

    public User getUserIdVendedor() {
        return userIdVendedor;
    }

    public void setUserIdVendedor(User userIdVendedor) {
        this.userIdVendedor = userIdVendedor;
    }

    public User getUserIdCliente() {
        return userIdCliente;
    }

    public void setUserIdCliente(User userIdCliente) {
        this.userIdCliente = userIdCliente;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (ventaId != null ? ventaId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Venta)) {
            return false;
        }
        Venta other = (Venta) object;
        if ((this.ventaId == null && other.ventaId != null) || (this.ventaId != null && !this.ventaId.equals(other.ventaId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "co.com.miempresa.entities.Venta[ ventaId=" + ventaId + " ]";
    }
    
}
