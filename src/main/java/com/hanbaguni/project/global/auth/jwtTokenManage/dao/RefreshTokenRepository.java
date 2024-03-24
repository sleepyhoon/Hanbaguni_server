package com.hanbaguni.project.global.auth.jwtTokenManage.dao;

import com.hanbaguni.project.global.auth.jwtTokenManage.domain.RefreshToken;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

/**
 * JWT refresh Token repository saved in MySql database.<br>
 * Two methods are
 * <pre>
 *     {@code Optional<RefreshToken> findByRefreshToken(String refreshToken);}
 *     {@code  void deleteByUsername(String username);}
 * </pre>
 */
@Repository
public interface RefreshTokenRepository extends JpaRepository<RefreshToken, String> {
    Optional<RefreshToken> findByRefreshToken(String refreshToken);
    void deleteByUsername(String username);
}
