package com.zsp.zspoamember.repository;

import com.zsp.zspoamember.entity.Member;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MemberRepository extends JpaRepository<Member,Long> {

}
