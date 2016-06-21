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
@Table(name = "plandepago")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Plandepago.findAll", query = "SELECT p FROM Plandepago p"),
    @NamedQuery(name = "Plandepago.findByIdPlan", query = "SELECT p FROM Plandepago p WHERE p.idPlan = :idPlan"),
    @NamedQuery(name = "Plandepago.findByIDEmpresa", query = "SELECT p FROM Plandepago p WHERE p.iDEmpresa = :iDEmpresa"),
    @NamedQuery(name = "Plandepago.findByFechaPago", query = "SELECT p FROM Plandepago p WHERE p.fechaPago = :fechaPago"),
    @NamedQuery(name = "Plandepago.findByImporte", query = "SELECT p FROM Plandepago p WHERE p.importe = :importe"),
    @NamedQuery(name = "Plandepago.findByInteres", query = "SELECT p FROM Plandepago p WHERE p.interes = :interes"),
    @NamedQuery(name = "Plandepago.findByNroCuotas", query = "SELECT p FROM Plandepago p WHERE p.nroCuotas = :nroCuotas"),
    @NamedQuery(name = "Plandepago.findByTotal", query = "SELECT p FROM Plandepago p WHERE p.total = :total")})
public class Plandepago implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "IdPlan")
    private Integer idPlan;
    @Column(name = "IDEmpresa")
    private Integer iDEmpresa;
    @Size(max = 20)
    @Column(name = "FechaPago")
    private String fechaPago;
    @Column(name = "Importe")
    private Integer importe;
    @Column(name = "Interes")
    private Integer interes;
    @Column(name = "NroCuotas")
    private Integer nroCuotas;
    @Column(name = "Total")
    private Integer total;
    @OneToMany(mappedBy = "idPlanP")
    private List<Credito> creditoList;

    public Plandepago() {
    }

    public Plandepago(Integer idPlan) {
        this.idPlan = idPlan;
    }

    public Integer getIdPlan() {
        return idPlan;
    }

    public void setIdPlan(Integer idPlan) {
        this.idPlan = idPlan;
    }

    public Integer getIDEmpresa() {
        return iDEmpresa;
    }

    public void setIDEmpresa(Integer iDEmpresa) {
        this.iDEmpresa = iDEmpresa;
    }

    public String getFechaPago() {
        return fechaPago;
    }

    public void setFechaPago(String fechaPago) {
        this.fechaPago = fechaPago;
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

    public Integer getNroCuotas() {
        return nroCuotas;
    }

    public void setNroCuotas(Integer nroCuotas) {
        this.nroCuotas = nroCuotas;
    }

    public Integer getTotal() {
        return total;
    }

    public void setTotal(Integer total) {
        this.total = total;
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
        hash += (idPlan != null ? idPlan.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Plandepago)) {
            return false;
        }
        Plandepago other = (Plandepago) object;
        if ((this.idPlan == null && other.idPlan != null) || (this.idPlan != null && !this.idPlan.equals(other.idPlan))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "org.prestamo.entidades.Plandepago[ idPlan=" + idPlan + " ]";
    }
    
}
