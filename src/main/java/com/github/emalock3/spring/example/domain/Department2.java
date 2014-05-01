package com.github.emalock3.spring.example.domain;

import java.io.Serializable;
import java.util.Collection;
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
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author Shinobu Aoki
 */
@Entity
@Table(name = "DEPARTMENT2")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Department2.findAll", query = "SELECT d FROM Department2 d")})
public class Department2 implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "ID")
    private Long id;
    @Basic(optional = false)
    @Column(name = "NAME")
    private String name;
    @OneToMany(mappedBy = "deptId")
    private Collection<Employee2> employee2Collection;

    public Department2() {
    }

    public Department2(Long id) {
        this.id = id;
    }

    public Department2(Long id, String name) {
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

    @XmlTransient
    public Collection<Employee2> getEmployee2Collection() {
        return employee2Collection;
    }

    public void setEmployee2Collection(Collection<Employee2> employee2Collection) {
        this.employee2Collection = employee2Collection;
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
        if (!(object instanceof Department2)) {
            return false;
        }
        Department2 other = (Department2) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.github.emalock3.spring.example.domain.Department2[ id=" + id + " ]";
    }

}
