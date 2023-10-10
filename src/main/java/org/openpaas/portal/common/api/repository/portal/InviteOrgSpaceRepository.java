package org.openpaas.portal.common.api.repository.portal;

import org.openpaas.portal.common.api.entity.portal.InviteOrgSpace;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface InviteOrgSpaceRepository extends JpaRepository<InviteOrgSpace, Integer> {

}
