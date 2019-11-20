package com.winston.practice.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.util.Set;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedAttributeNode;
import javax.persistence.NamedEntityGraph;
import javax.persistence.NamedEntityGraphs;
import javax.persistence.NamedSubgraph;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Table(name = "user")
@NamedEntityGraphs(
  value =
    {@NamedEntityGraph(name = "user.accountEntities", attributeNodes = {
      @NamedAttributeNode(value = "accountEntities", subgraph = "accountEntities-subgraph")
    }, subgraphs = {
      @NamedSubgraph(name = "accountEntities-subgraph",
        attributeNodes = {
          @NamedAttributeNode("accountDetailEntities")
        })
    }
    ),
      @NamedEntityGraph(name = "user.accountEntities2", attributeNodes = {
        @NamedAttributeNode(value = "accountEntities")
      }
      )}
)
public class UserEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private int age;
    @ToString.Exclude
    @EqualsAndHashCode.Exclude
    @OneToMany(mappedBy = "userEntity", fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    private Set<AccountEntity> accountEntities;

    @ToString.Exclude
    @EqualsAndHashCode.Exclude
    @OneToOne(mappedBy = "userEntity", cascade = CascadeType.ALL)
    private PetEntity petEntities;

}
