package ua.com.otpbank.domain;

import org.hibernate.annotations.Entity;
import org.hibernate.annotations.NamedQueries;
import org.hibernate.annotations.NamedQuery;

import javax.persistence.Column;
import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;

/**
 * Created by Igor on 09.05.2015.
 */

@NamedQueries({
        @NamedQuery(name = "getHead",
                query = "select name from vw_result_head"
        )
})
@Entity
@Table(name = "vw_result_head")
public class ResultHead implements Serializable {
    private static final long serialVersionUID = 8765016103450361311L;

    @Id
    @Column(name = "NAME")
    String name;
    @Column(name = "QNTY")
    Integer count;

    public void setName(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setCount(Integer count) {
        this.count = count;
    }

    public Integer getCount() {
        return count;
    }
}
