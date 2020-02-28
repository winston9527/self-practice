package com.winston.practice.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "link_man")
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class LinkMan {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private String mobileNumber;
    @ManyToOne()
    @JoinColumn(name = "customer_id",referencedColumnName ="id" )
    @ToString.Exclude
    @EqualsAndHashCode.Exclude
    private Customer customer;
}
