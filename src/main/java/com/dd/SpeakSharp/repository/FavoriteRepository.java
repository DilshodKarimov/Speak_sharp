package com.dd.SpeakSharp.repository;

import com.dd.SpeakSharp.dto.TopTwisterDTO;
import com.dd.SpeakSharp.entity.Favorite;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface FavoriteRepository extends JpaRepository<Favorite, Long> {
    List<Favorite> findByUserId(Long id);

    Optional<Favorite> findByUserIdAndTwisterId(Long userId, Long twisterId);

    Page<Favorite> findByUserId(Long userId, Pageable pageable);

    @Query("""
            SELECT new com.dd.SpeakSharp.dto.TopTwisterDTO(
                f.twister,
                COUNT(f)
            )
            FROM Favorite f
            GROUP BY f.twister
            ORDER BY COUNT(f) DESC
            """)
    List<TopTwisterDTO> getTopTwisters(Pageable pageable);
}
