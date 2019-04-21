package com.expertsoft.web.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.acls.domain.GrantedAuthoritySid;
import org.springframework.security.acls.domain.ObjectIdentityImpl;
import org.springframework.security.acls.domain.PrincipalSid;
import org.springframework.security.acls.model.MutableAcl;
import org.springframework.security.acls.model.MutableAclService;
import org.springframework.security.acls.model.NotFoundException;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.io.Serializable;

import static com.expertsoft.web.config.SecurityConfig.ROLE_ADMIN;
import static org.springframework.security.acls.domain.BasePermission.READ;
import static org.springframework.security.acls.domain.BasePermission.WRITE;

@Component
@Transactional
public class AclManager {

    private final MutableAclService aclService;

    @Autowired
    public AclManager(final MutableAclService aclService) {
        this.aclService = aclService;
    }

    public void addDefaultPermissions(Class<?> type, Serializable id, String username) {
        var oi = new ObjectIdentityImpl(type, id);
        var sid = new PrincipalSid(username);
        var adminSid = new GrantedAuthoritySid(ROLE_ADMIN);

        MutableAcl acl;
        try {
            acl = (MutableAcl) aclService.readAclById(oi);
        } catch (NotFoundException nfe) {
            acl = aclService.createAcl(oi);
        }

        acl.insertAce(acl.getEntries().size(), READ, sid, true);
        acl.insertAce(acl.getEntries().size(), READ, adminSid, true);
        acl.insertAce(acl.getEntries().size(), WRITE, adminSid, true);
        aclService.updateAcl(acl);
    }

    public void deletePermissions(Class<?> type, Serializable id) {
        aclService.deleteAcl(new ObjectIdentityImpl(type, id), true);
    }
}
