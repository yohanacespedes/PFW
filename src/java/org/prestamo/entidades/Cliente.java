/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.prestamo.entidades;

import java.io.Serializable;
import java.util.List;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author Aidee Ledezma
 */
@Entity
@Table(name = "cliente")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Cliente.findAll", query = "SELECT c FROM Cliente c"),
    @NamedQuery(name = "Cliente.findByIdCliente", query = "SELECT c FROM Cliente c WHERE c.idCliente = :idCliente"),
    @NamedQuery(name = "Cliente.findByIDEmpresa", query = "SELECT c FROM Cliente c WHERE c.iDEmpresa = :iDEmpresa"),
    @NamedQuery(name = "Cliente.findByNombre", query = "SELECT c FROM Cliente c WHERE c.nombre = :nombre"),
    @NamedQuery(name = "Cliente.findByApellidoPaterno", query = "SELECT c FROM Cliente c WHERE c.apellidoPaterno = :apellidoPaterno"),
    @NamedQuery(name = "Cliente.findByApellidoMaterno", query = "SELECT c FROM Cliente c WHERE c.apellidoMaterno = :apellidoMaterno"),
    @NamedQuery(name = "Cliente.findByCi", query = "SELECT c FROM Cliente c WHERE c.ci = :ci"),
    @NamedQuery(name = "Cliente.findByDireccion", query = "SELECT c FROM Cliente c WHERE c.direccion = :direccion"),
    @NamedQuery(name = "Cliente.findByTelefono", query = "SELECT c FROM Cliente c WHERE c.telefono = :telefono"),
    @NamedQuery(name = "Cliente.findByMz", query = "SELECT c FROM Cliente c WHERE c.mz = :mz"),
    @NamedQuery(name = "Cliente.findByUv", query = "SELECT c FROM Cliente c WHERE c.uv = :uv"),
    @NamedQuery(name = "Cliente.findByNit", query = "SELECT c FROM Cliente c WHERE c.nit = :nit")})
public class Cliente implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "IdCliente")
    private Integer idCliente;
    @Column(name = "IDEmpresa")
    private Integer iDEmpresa;
    @Size(max = 100)
    @Column(name = "Nombre")
    private String nombre;
    @Size(max = 100)
    @Column(name = "ApellidoPaterno")
    private String apellidoPaterno;
    @Size(max = 100)
    @Column(name = "ApellidoMaterno")
    private String apellidoMaterno;
    @Column(name = "Ci")
    private Integer ci;
    @Size(max = 100)
    @Column(name = "Direccion")
    private String direccion;
    @Column(name = "Telefono")
    private Integer telefono;
    @Column(name = "MZ")
    private Integer mz;
    @Column(name = "UV")
    private Integer uv;
    @Column(name = "Nit")
    private Integer nit;
    @OneToMany(mappedBy = "idCli")
    private List<Credito> creditoList;

    public Cliente() {
    }

    public Cliente(Integer idCliente) {
        this.idCliente = idCliente;
    }

    public Integer getIdCliente() {
        return idCliente;
    }

    public void setIdCliente(Integer idCliente) {
        this.idCliente = idCliente;
    }

    public Integer getIDEmpresa() {
        return iDEmpresa;
    }

    public void setIDEmpresa(Integer iDEmpresa) {
        this.iDEmpresa = iDEmpresa;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getApellidoPaterno() {
        return apellidoPaterno;
    }

    public void setApellidoPaterno(String apellidoPaterno) {
        this.apellidoPaterno = apellidoPaterno;
    }

    public String getApellidoMaterno() {
        return apellidoMaterno;
    }

    public void setApellidoMaterno(String apellidoMaterno) {
        this.apellidoMaterno = apellidoMaterno;
    }

    public Integer getCi() {
        return ci;
    }

    public void setCi(Integer ci) {
        this.ci = ci;
    }

    public String getDireccion() {
        return direccion;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    public Integer getTelefono() {
        return telefono;
    }

    public void setTelefono(Integer telefono) {
        this.telefono = telefono;
    }

    public Integer getMz() {
        return mz;
    }

    public void setMz(Integer mz) {
        this.mz = mz;
    }

    public Integer getUv() {
        return uv;
    }

    public void setUv(Integer uv) {
        this.uv = uv;
    }

    public Integer getNit() {
        return nit;
    }

    public void setNit(Integer nit) {
        this.nit = nit;
    }

    @XmlTransient
    public List<Credito> getCreditoList() {
        return creditoList;
    }

    public void setCreditoList(List<Credito> creditoList) {
        this.creditoList = creditoList;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idCliente != null ? idCliente.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Cliente)) {
            return false;
        }
        Cliente other = (Cliente) object;
        if ((this.idCliente == null && other.idCliente != null) || (this.idCliente != null && !this.idCliente.equals(other.idCliente))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "org.prestamo.entidades.Cliente[ idCliente=" + idCliente + " ]";
    }
    
}
