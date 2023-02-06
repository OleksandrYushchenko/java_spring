package com.example.demo.security;

import com.example.demo.entities.Privilege;
import com.example.demo.entities.Role;
import com.example.demo.entities.User;
import com.example.demo.repositories.PrivilegeRepository;
import com.example.demo.repositories.RoleRepository;
import com.example.demo.repositories.UserRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import java.util.Arrays;
import java.util.Collection;

@Component
public class SetupDataLoader implements ApplicationListener<ContextRefreshedEvent> {
    boolean alreadySetup = false;
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private RoleRepository roleRepository;
    @Autowired
    private PrivilegeRepository privilegeRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Override
    @Transactional
    public void onApplicationEvent(ContextRefreshedEvent event) {
        if (alreadySetup) {
            return;
        }
        Privilege createPrivilege = createPrivilegeIfNotFound("CREATE_PRIVILEGE");
        Privilege readPrivilege = createPrivilegeIfNotFound("READ_PRIVILEGE");
        Privilege updatePrivilege = createPrivilegeIfNotFound("UPDATE_PRIVILEGE");
        Privilege deletePrivilege = createPrivilegeIfNotFound("DELETE_PRIVILEGE");


        Collection<Privilege> adminPrivileges = Arrays.asList(createPrivilege, readPrivilege, updatePrivilege, deletePrivilege);
        Collection<Privilege> userPrivileges = Arrays.asList(createPrivilege, readPrivilege, updatePrivilege);

        createRoleIfNotFound("ROLE_ADMIN", adminPrivileges);
        createRoleIfNotFound("ROLE_USER", userPrivileges);

        Role adminRole = roleRepository.findByName("ROLE_ADMIN");
        User admin = new User();
        admin.setName("root");
        admin.setPassword(passwordEncoder.encode("helloworld"));
        admin.setRoles(Arrays.asList(adminRole));
        userRepository.save(admin);
        alreadySetup = true;
        Role userRole = roleRepository.findByName("ROLE_USER");
        User user = new User();
        user.setName("user");
        user.setPassword(passwordEncoder.encode("helloworld"));
        user.setRoles(Arrays.asList(userRole));
        userRepository.save(user);
        alreadySetup = true;
    }
    @Transactional
    Privilege createPrivilegeIfNotFound(String name) {
        Privilege privilege = privilegeRepository.findByName(name);
        if (privilege == null) {
            privilege = new Privilege(name);
            privilegeRepository.save(privilege);
        }
        return privilege;
    }
    @Transactional
    Role createRoleIfNotFound(
            String name, Collection<Privilege> privileges) {

        Role role = roleRepository.findByName(name);
        if (role == null) {
            role = new Role(name);
            role.setPrivileges(privileges);
            roleRepository.save(role);
        }
        return role;
    }
}
