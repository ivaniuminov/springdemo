package com.iuminov.model;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "ROLES")
public class Role {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "ROLE_NAME")
    private String roleMane;
    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(name = "USER_TO_ROLE",
            joinColumns = {
                    @JoinColumn(name = "FK_ROLE_ID", nullable = false)
            },
            inverseJoinColumns = {
                    @JoinColumn(name = "FK_USER_ID", nullable = false)
            }
    )
    private List<User> users = new ArrayList<>();

    public Role() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getRoleMane() {
        return roleMane;
    }

    public void setRoleMane(String roleMane) {
        this.roleMane = roleMane;
    }

    public List<User> getUsers() {
        return users;
    }

    public void setUsers(List<User> users) {
        this.users = users;
    }
}
