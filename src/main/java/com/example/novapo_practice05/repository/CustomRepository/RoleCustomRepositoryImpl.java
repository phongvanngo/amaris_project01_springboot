package com.example.novapo_practice05.repository.CustomRepository;

import com.example.novapo_practice05.domain.QRole;
import com.example.novapo_practice05.domain.QUsersRoles;
import com.example.novapo_practice05.domain.Role;
import com.example.novapo_practice05.service.dto.Item.ResponseItemDTO;
import com.querydsl.core.types.Projections;
import com.querydsl.jpa.impl.JPAQuery;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

public class RoleCustomRepositoryImpl implements RoleCustomRepository {

    @PersistenceContext
    EntityManager entityManager;

    @Override
    public List<Role> findRolesByUserID(Long id) {
        QUsersRoles qUsersRoles = QUsersRoles.usersRoles;
        QRole qRole = QRole.role;
        JPAQuery<Role> query = new JPAQuery<>(entityManager);

        List<Role> res =  query.select(Projections.fields(Role.class,qRole.name,qRole.id))
            .from(qUsersRoles)
            .leftJoin(qRole)
            .on(qRole.id.eq(qUsersRoles.roleID))
            .where(qUsersRoles.userID.eq(id))
            .fetch();

        return res;
    }
}
