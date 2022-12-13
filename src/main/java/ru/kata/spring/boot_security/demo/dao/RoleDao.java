package ru.kata.spring.boot_security.demo.dao;

import ru.kata.spring.boot_security.demo.entities.Role;

import java.util.List;

public interface RoleDao {

    List<Role> index();

    Role getRole(Long id);

}