package com.dd.SpeakSharp.repository;

import com.dd.SpeakSharp.entity.UserTwister;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserTwisterRepository extends JpaRepository<UserTwister, Long> {
    List<UserTwister> findByUserId(Long id);

}
