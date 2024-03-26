package com.hanbaguni.project.domain.user.domain;

import com.hanbaguni.project.global.auth.Roles;
import jakarta.persistence.*;
import lombok.*;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;


/**
 * Member information entity class, saved in database.
 * <pre>
 *     {@code private Long id;}
 *     {@code private String username;}
 *     {@code private String password;}
 *     {@code private String memberName;}
 *     {@code private String memberNickname}
 *     {@code private List<String> roles;  //default : "USER"}
 * </pre>
 * also, implements {@code UserDetails} in spring security, <br>
 * there are override method which is
 * <pre>
 *     {@code public Collection<? extends GrantedAuthority> getAuthorities();}
 * </pre>
 * and isAccountNonExpired, isAccountNonLocked, isCredentialNonExpired, isEnable
 *
 * v
 */
@Entity
@Getter
@Setter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
@Builder
@EqualsAndHashCode(of = "id")
public class Member implements UserDetails {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="memberId", updatable = false, unique = true, nullable = false)
    private Long id;

    @OneToOne(mappedBy = "member", cascade = CascadeType.ALL)
    private Profile profile;

    @Column(nullable = false, unique = true, updatable = false)
    private String username;

    @Column(nullable = false)
    private String password;

    @Column(nullable = false)
    private String memberName;

    @Column(nullable = false)
    private String memberNickname;

    @ElementCollection(fetch=FetchType.EAGER)
    @Builder.Default
    private List<String> roles = new ArrayList<>(Arrays.asList("USER"));


    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return this.roles.stream()
                .map(SimpleGrantedAuthority::new)
                .collect(Collectors.toList());
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }
}
