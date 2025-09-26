package com.senac.games.entities;

import com.senac.games.enumerator.RoleName;
import jakarta.persistence.*;

import java.util.List;

@Entity
@Table(name = "role")
public class Role {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name ="role_id")
  private Integer id;

  @Column(name ="role_name")
  private String name;

  @Enumerated(EnumType.STRING)
  private RoleName roleName;

  public Integer getId() {
    return id;
  }

  public void setId(Integer id) {
    this.id = id;
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public RoleName getRoleName() {
    return roleName;
  }

  public void setRoleName(RoleName roleName) {
    this.roleName = roleName;
  }
}
