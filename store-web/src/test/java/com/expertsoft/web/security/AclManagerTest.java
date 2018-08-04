package com.expertsoft.web.security;

import com.expertsoft.web.test.WebApplicationTest;
import org.junit.Before;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.acls.domain.ObjectIdentityImpl;
import org.springframework.security.acls.model.MutableAclService;
import org.springframework.security.test.context.support.WithMockUser;

import static com.expertsoft.web.test.TestUtils.checkDefaultPermission;
import static org.springframework.security.test.web.servlet.setup.SecurityMockMvcConfigurers.springSecurity;
import static org.springframework.test.web.servlet.setup.MockMvcBuilders.webAppContextSetup;

@WithMockUser
public class AclManagerTest extends WebApplicationTest {

    @Autowired
    private AclManager aclManager;

    @Autowired
    private MutableAclService aclService;

    @Before
    public void init() {
        mockMvc = webAppContextSetup(context).apply(springSecurity()).build();
    }

    @Test
    public void addDefaultPermissions() {
        final var type = Object.class;
        final var id = 1L;
        final var principal = "manager";

        aclManager.addDefaultPermissions(type, id, principal);

        var acl = aclService.readAclById(new ObjectIdentityImpl(type, id));
        checkDefaultPermission(acl, principal);
    }
}
