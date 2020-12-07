package com.boot.commons.core.security;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.util.CollectionUtils;

import java.time.Duration;
import java.util.Collection;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

/**
 * JwtUser
 *
 * @author XINAN
 * @date 2019/8/2
 */

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class JwtUser implements UserDetails {

    @JsonIgnore
    private Long id;

    private String name;

    private UserType userType;

    @JsonIgnore
    private String pwd;

    private Boolean enabled = true;

    private Set<String> permissions;

    private Map<String, Object> extData;

    @JsonIgnore
    private String token;

    /**
     * token有效期，自动续期时长
     */
    @JsonIgnore
    private Duration expiration;

    /**
     * 调用接口自动续期token
     */
    @JsonIgnore
    private Boolean autoRenewal = true;

    /***
     * 权限重写
     */
    @JsonIgnore
    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        Set<GrantedAuthority> authorities = new HashSet<>();
        if (!CollectionUtils.isEmpty(permissions)) {
            permissions.forEach(per -> authorities.add(new SimpleGrantedAuthority(per)));
        }
        authorities.add(new SimpleGrantedAuthority("ROLE_" + userType.name()));
        return authorities;
    }

    @Override
    public String getPassword() {
        return this.pwd;
    }

    @Override
    public String getUsername() {
        return this.name;
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
        return this.id == 1 || this.enabled;
    }

}
