/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.com.miempresa.dto;

/**
 * Alt + Insert para generar getter y setters
 *
 * @author Ccepeda
 */
public class ProductoDTO {

    private Long id;
    private String code;
    private String decription;
    private long precio;
    private String imagen;
    private Integer cantidad;
    private String estado;

    private UsuarioDTO userreg;
    private UsuarioDTO useract;

    private String freg;
    private String fact;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getDecription() {
        return decription;
    }

    public void setDecription(String decription) {
        this.decription = decription;
    }

    public long getPrecio() {
        return precio;
    }

    public void setPrecio(long precio) {
        this.precio = precio;
    }

    public String getImagen() {
        return imagen;
    }

    public void setImagen(String imagen) {
        this.imagen = imagen;
    }

    public Integer getCantidad() {
        return cantidad;
    }

    public void setCantidad(Integer cantidad) {
        this.cantidad = cantidad;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    public UsuarioDTO getUserreg() {
        return userreg;
    }

    public void setUserreg(UsuarioDTO userreg) {
        this.userreg = userreg;
    }

    public UsuarioDTO getUseract() {
        return useract;
    }

    public void setUseract(UsuarioDTO useract) {
        this.useract = useract;
    }

    public String getFreg() {
        return freg;
    }

    public void setFreg(String freg) {
        this.freg = freg;
    }

    public String getFact() {
        return fact;
    }

    public void setFact(String fact) {
        this.fact = fact;
    }

}
