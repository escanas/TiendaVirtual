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
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author Ccepeda
 */
@Entity
@Table(name = "tbl_rolemenu")
@Cacheable(false)
@NamedQueries({
    @NamedQuery(name = "RoleMenu.findAll", query = "SELECT r FROM RoleMenu r")
    , @NamedQuery(name = "RoleMenu.findByRolemenuId", query = "SELECT r FROM RoleMenu r WHERE r.rolemenuId = :rolemenuId")})
public class RoleMenu implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "rolemenu_id")
    private Integer rolemenuId;
    @JoinColumn(name = "menu_id", referencedColumnName = "menu_id")
    @ManyToOne(optional = false)
    private Menu menuId;
    @JoinColumn(name = "rol_id", referencedColumnName = "rol_id")
    @ManyToOne(optional = false)
    private Role rolId;

    public RoleMenu() {
    }

    public RoleMenu(Integer rolemenuId) {
        this.rolemenuId = rolemenuId;
    }

    public Integer getRolemenuId() {
        return rolemenuId;
    }

    public void setRolemenuId(Integer rolemenuId) {
        this.rolemenuId = rolemenuId;
    }

    public Menu getMenuId() {
        return menuId;
    }

    public void setMenuId(Menu menuId) {
        this.menuId = menuId;
    }

    public Role getRolId() {
        return rolId;
    }

    public void setRolId(Role rolId) {
        this.rolId = rolId;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (rolemenuId != null ? rolemenuId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof RoleMenu)) {
            return false;
        }
        RoleMenu other = (RoleMenu) object;
        if ((this.rolemenuId == null && other.rolemenuId != null) || (this.rolemenuId != null && !this.rolemenuId.equals(other.rolemenuId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "co.com.miempresa.entities.RoleMenu[ rolemenuId=" + rolemenuId + " ]";
    }
    
}
