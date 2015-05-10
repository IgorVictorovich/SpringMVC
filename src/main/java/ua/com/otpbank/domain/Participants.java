package ua.com.otpbank.domain;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

/**
 * Created by Igor on 07.05.2015.
 */
@Entity
@Table(name = "RESULTS")
public class Participants  implements Serializable {
    private static final long serialVersionUID=-5527566248002296042L;
    @Id
    @Column(name = "ID")
    @GeneratedValue
    private Integer id;

    @Column(name = "UUID")
    private String uuid;

    @Column(name = "ORDERNO")
    private Integer orderNo;

    @Column(name = "NAME")
    private String name;

    @Column(name = "TIMESTAMP")
    private Date timestamp;

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getId() {
        return id;
    }

    public String getUuid() {
        return uuid;
    }

    public void setUuid(String uuid) {
        this.uuid = uuid;
    }

    public Integer getOrderNo() {
        return orderNo;
    }

    public void setOrderNo(Integer orderNo) {
        this.orderNo = orderNo;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Date getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(Date timestamp) {
        this.timestamp = timestamp;
    }
}
