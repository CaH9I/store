package com.expertsoft.web.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.acls.domain.GrantedAuthoritySid;
import org.springframework.security.acls.domain.ObjectIdentityImpl;
import org.springframework.security.acls.domain.PrincipalSid;
import org.springframework.security.acls.model.MutableAcl;
import org.springframework.security.acls.model.MutableAclService;
import org.springframework.security.acls.model.NotFoundException;
import org.springframework.security.acls.model.ObjectIdentity;
import org.springframework.security.acls.model.Sid;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.io.Serializable;

import static com.expertsoft.web.security.SecurityConstants.ROLE_ADMIN;
import static org.springframework.security.acls.domain.BasePermission.READ;
import static org.springframework.security.acls.domain.BasePermission.WRITE;

@Component
public class AclManager {

    private final MutableAclService aclService;

    @Autowired
    public AclManager(final MutableAclService aclService) {
        this.aclService = aclService;
    }

    @Transactional
    public void addDefaultPermissions(Class<?> type, Serializable id, String username) {
        ObjectIdentity oi = new ObjectIdentityImpl(type, id);
        Sid sid = new PrincipalSid(username);
        Sid adminSid = new GrantedAuthoritySid(ROLE_ADMIN);

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
}
