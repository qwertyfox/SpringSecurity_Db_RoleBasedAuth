package com.qwertyfox.thefox.dao;

import com.qwertyfox.thefox.model.FoxUsersRolesAndDetails;
import org.springframework.data.repository.Repository;
import org.springframework.stereotype.Component;

@Component
public interface AppUserRolesDao extends Repository<FoxUsersRolesAndDetails, Integer> {

    FoxUsersRolesAndDetails findByUserID (int id);




}
