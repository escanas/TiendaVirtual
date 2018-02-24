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
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author CarlosJavier
 */
@Entity
@Table(name = "tbl_categoria")
@Cacheable(false)
@NamedQueries({
    @NamedQuery(name = "Categoria.findAll", query = "SELECT c FROM Categoria c WHERE  c.catEstado='ACTIVO'")
    ,@NamedQuery(name = "Categoria.findAllSinEstado", query = "SELECT c FROM Categoria c")
    , @NamedQuery(name = "Categoria.findByCatId", query = "SELECT c FROM Categoria c WHERE c.catId = :catId")
    , @NamedQuery(name = "Categoria.findByCatCod", query = "SELECT c FROM Categoria c WHERE c.catCod = :catCod")
    , @NamedQuery(name = "Categoria.findByCatDesc", query = "SELECT c FROM Categoria c WHERE c.catDesc = :catDesc")
    , @NamedQuery(name = "Categoria.findByCatEstado", query = "SELECT c FROM Categoria c WHERE c.catEstado = :catEstado")})
public class Categoria implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "cat_id")
    private Integer catId;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 10)
    @Column(name = "cat_cod")
    private String catCod;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 50)
    @Column(name = "cat_desc")
    private String catDesc;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 10)
    @Column(name = "cat_estado")
    private String catEstado;

    public Integer getCatId() {
        return catId;
    }

    public void setCatId(Integer catId) {
        this.catId = catId;
    }

    public String getCatCod() {
        return catCod;
    }

    public void setCatCod(String catCod) {
        this.catCod = catCod;
    }

    public String getCatDesc() {
        return catDesc;
    }

    public void setCatDesc(String catDesc) {
        this.catDesc = catDesc;
    }

    public String getCatEstado() {
        return catEstado;
    }

    public void setCatEstado(String catEstado) {
        this.catEstado = catEstado;
    }

}
