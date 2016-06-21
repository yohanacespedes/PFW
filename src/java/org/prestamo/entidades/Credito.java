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
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
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
@Table(name = "credito")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Credito.findAll", query = "SELECT c FROM Credito c"),
    @NamedQuery(name = "Credito.findByIdCredito", query = "SELECT c FROM Credito c WHERE c.idCredito = :idCredito"),
    @NamedQuery(name = "Credito.findByIDEmpresa", query = "SELECT c FROM Credito c WHERE c.iDEmpresa = :iDEmpresa"),
    @NamedQuery(name = "Credito.findByCuotasPagadas", query = "SELECT c FROM Credito c WHERE c.cuotasPagadas = :cuotasPagadas"),
    @NamedQuery(name = "Credito.findByFecha", query = "SELECT c FROM Credito c WHERE c.fecha = :fecha"),
    @NamedQuery(name = "Credito.findByImporte", query = "SELECT c FROM Credito c WHERE c.importe = :importe"),
    @NamedQuery(name = "Credito.findByInteres", query = "SELECT c FROM Credito c WHERE c.interes = :interes"),
    @NamedQuery(name = "Credito.findBySaldo", query = "SELECT c FROM Credito c WHERE c.saldo = :saldo"),
    @NamedQuery(name = "Credito.findByEstado", query = "SELECT c FROM Credito c WHERE c.estado = :estado")})
public class Credito implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "IdCredito")
    private Integer idCredito;
    @Column(name = "IDEmpresa")
    private Integer iDEmpresa;
    @Column(name = "CuotasPagadas")
    private Integer cuotasPagadas;
    @Size(max = 50)
    @Column(name = "Fecha")
    private String fecha;
    @Column(name = "Importe")
    private Integer importe;
    @Column(name = "Interes")
    private Integer interes;
    @Column(name = "Saldo")
    private Integer saldo;
    @Size(max = 50)
    @Column(name = "Estado")
    private String estado;
    @JoinColumn(name = "idCli", referencedColumnName = "IdCliente")
    @ManyToOne
    private Cliente idCli;
    @JoinColumn(name = "idU", referencedColumnName = "IdUsuario")
    @ManyToOne
    private Usuario idU;
    @JoinColumn(name = "idPlanP", referencedColumnName = "IdPlan")
    @ManyToOne
    private Plandepago idPlanP;
    @OneToMany(mappedBy = "idCredi")
    private List<Pago> pagoList;

    public Credito() {
    }

    public Credito(Integer idCredito) {
        this.idCredito = idCredito;
    }

    public Integer getIdCredito() {
        return idCredito;
    }

    public void setIdCredito(Integer idCredito) {
        this.idCredito = idCredito;
    }

    public Integer getIDEmpresa() {
        return iDEmpresa;
    }

    public void setIDEmpresa(Integer iDEmpresa) {
        this.iDEmpresa = iDEmpresa;
    }

    public Integer getCuotasPagadas() {
        return cuotasPagadas;
    }

    public void setCuotasPagadas(Integer cuotasPagadas) {
        this.cuotasPagadas = cuotasPagadas;
    }

    public String getFecha() {
        return fecha;
    }

    public void setFecha(String fecha) {
        this.fecha = fecha;
    }

    public Integer getImporte() {
        return importe;
    }

    public void setImporte(Integer importe) {
        this.importe = importe;
    }

    public Integer getInteres() {
        return interes;
    }

    public void setInteres(Integer interes) {
        this.interes = interes;
    }

    public Integer getSaldo() {
        return saldo;
    }

    public void setSaldo(Integer saldo) {
        this.saldo = saldo;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    public Cliente getIdCli() {
        return idCli;
    }

    public void setIdCli(Cliente idCli) {
        this.idCli = idCli;
    }

    public Usuario getIdU() {
        return idU;
    }

    public void setIdU(Usuario idU) {
        this.idU = idU;
    }

    public Plandepago getIdPlanP() {
        return idPlanP;
    }

    public void setIdPlanP(Plandepago idPlanP) {
        this.idPlanP = idPlanP;
    }

    @XmlTransient
    public List<Pago> getPagoList() {
        return pagoList;
    }

    public void setPagoList(List<Pago> pagoList) {
        this.pagoList = pagoList;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idCredito != null ? idCredito.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Credito)) {
            return false;
        }
        Credito other = (Credito) object;
        if ((this.idCredito == null && other.idCredito != null) || (this.idCredito != null && !this.idCredito.equals(other.idCredito))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "org.prestamo.entidades.Credito[ idCredito=" + idCredito + " ]";
    }
    
}
