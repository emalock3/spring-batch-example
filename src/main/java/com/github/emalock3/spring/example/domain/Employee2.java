package com.github.emalock3.spring.example.domain;

import java.io.Serializable;
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
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author Shinobu Aoki
 */
@Entity
@Table(name = "EMPLOYEE2")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Employee2.findAll", query = "SELECT e FROM Employee2 e")})
public class Employee2 implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "ID")
    private Long id;
    @Basic(optional = false)
    @Column(name = "NAME")
    private String name;
    @JoinColumn(name = "DEPT_ID", referencedColumnName = "ID")
    @ManyToOne
    private Department2 deptId;

    public Employee2() {
    }

    public Employee2(Long id) {
        this.id = id;
    }

    public Employee2(Long id, String name) {
        this.id = id;
        this.name = name;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Department2 getDeptId() {
        return deptId;
    }

    public void setDeptId(Department2 deptId) {
        this.deptId = deptId;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (id != null ? id.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Employee2)) {
            return false;
        }
        Employee2 other = (Employee2) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.github.emalock3.spring.example.domain.Employee2[ id=" + id + " ]";
    }

}
