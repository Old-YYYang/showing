package com.qing.rbac.entity;

import lombok.Data;

import javax.persistence.*;
import java.util.Set;

@Entity
@Table(name = "rbac_user")
@Data
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, length = 36, unique = true)
    private String account;

    @Column(nullable = false, length = 128)
    private String password;

    private String userName;

    private String tel;

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(name = "rbac_user_role",
            joinColumns = {@JoinColumn(name = "user_id")},
            inverseJoinColumns = {@JoinColumn(name = "role_id")})
    private Set<Role> roles;
}
