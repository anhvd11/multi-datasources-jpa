package com.example.multipledb.multipledbjpa.domain.datasource1;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.io.Serializable;

@Getter
@Setter
@Entity
@Table(name="PARTNER_HOLIDAY")
public class PartnerHolidays1 implements Serializable {
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    @Column(name = "ID")
    private Integer id;

    @Column(name = "PARTNER_ID")
    private Integer partnerId;

    @Column(name = "BUSINESS_DATE")
    private String businessDate;

}
