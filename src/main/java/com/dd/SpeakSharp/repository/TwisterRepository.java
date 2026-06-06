package com.dd.SpeakSharp.repository;

import com.dd.SpeakSharp.entity.Twister;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TwisterRepository extends JpaRepository<Twister, Long> {
}
