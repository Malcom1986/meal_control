package ru.maksimlitvinov.nutrition_control.model;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.Table;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import lombok.Getter;
import lombok.Setter;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.Set;

import static jakarta.persistence.GenerationType.AUTO;

@Entity
@Getter
@Setter
@Table(name = "users")
public class User implements UserDetails {
    @Id
    @GeneratedValue(strategy = AUTO)
    private Long id;
    private String name;
    @Email
    @Column(unique = true)
    private String email;

    private String passwordDigest;

    @ManyToMany(cascade = CascadeType.MERGE)
    @JoinTable(
            name = "user_role",
            joinColumns = @JoinColumn(name = "user_id"),
            inverseJoinColumns = @JoinColumn(name = "role_id")
    )
    private Set<Role> roles;

    @Min(18)
    @Max(120)
    private int age;
    @Min(20)
    @Max(300)
    private double weight;
    @Min(50)
    @Max(300)
    private double height;
    @Enumerated(EnumType.STRING)
    private Gender gender;

    @Enumerated(EnumType.STRING)
    private Goal goal;

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return getRoles()
                .stream()
                .map(role -> new SimpleGrantedAuthority(role.getRoleName()))
                .toList();
    }

    @Override
    public String getPassword() {
        return getPasswordDigest();
    }

    @Override
    public String getUsername() {
        return getEmail();
    }

    public enum Gender { MALE, FEMALE }
    public enum Goal { LOSE, MAINTAIN, GAIN }
}
