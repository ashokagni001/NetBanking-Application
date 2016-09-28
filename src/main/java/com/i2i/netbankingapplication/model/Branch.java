package com.i2i.netbankingapplication.model;

import javax.persistence.Column;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.search.annotations.DocumentId;
import org.hibernate.search.annotations.IndexedEmbedded;

/**
 * <p>
 *     Model class of Branch. 
 *     It have getter method, setter method, default constructor and parameter constructor.
 *     One to One mapping is established for Address model class.
 * </p>
 * 
 * @author TEAM-2
 * 
 * @created 2016-09-03
 */
@Entity
@Table(name = "branch_detail")
public class Branch {

    private Long id;

    @Column(name = "IFSCode")
    private String IFSCode;

    @Column(name = "email")
    private String emailId;

    @Embedded
    @IndexedEmbedded
    private Address address;

    /**
     * Default Constructor.
     * which create a instance of Branch.
     */
    public Branch() {}

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @DocumentId
    public Long getId() {
        return id;
    }

    public Address getAddress() {
        return address;
    }

    public String getEmailId() {
        return emailId;
    }

    public void setEmailId(String emailId) {
        this.emailId = emailId;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setAddress(Address address) {
        this.address = address;
    }

    public String getIFSCode() {
        return IFSCode;
    }

    public void setIFSCode(String IFSCode) {
        this.IFSCode = IFSCode;
    }

}
