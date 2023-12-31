package org.openpaas.portal.common.api.repository.cc;

import org.openpaas.portal.common.api.entity.cc.UsersCc;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UsersCcRepository extends JpaRepository<UsersCc, Integer> {
    UsersCc findAllByActive(boolean active);
}
