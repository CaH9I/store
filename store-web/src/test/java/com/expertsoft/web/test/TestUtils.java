package com.expertsoft.web.test;

import org.springframework.security.acls.domain.GrantedAuthoritySid;
import org.springframework.security.acls.domain.PrincipalSid;
import org.springframework.security.acls.model.Acl;
import org.springframework.security.acls.model.Sid;

import static com.expertsoft.web.util.Constants.ROLE_ADMIN;
import static org.hamcrest.Matchers.allOf;
import static org.hamcrest.Matchers.hasItem;
import static org.hamcrest.Matchers.hasProperty;
import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertThat;
import static org.springframework.security.acls.domain.BasePermission.READ;
import static org.springframework.security.acls.domain.BasePermission.WRITE;

public final class TestUtils {

    private TestUtils() {}

    public static void checkDefaultPermission(Acl acl, String principal) {
        Sid principalSid = new PrincipalSid(principal);
        Sid adminSid = new GrantedAuthoritySid(ROLE_ADMIN);

        assertThat(acl.getEntries(), allOf(
                hasItem(allOf(
                        hasProperty("permission", is(READ)),
                        hasProperty("granting", is(true)),
                        hasProperty("sid", is(principalSid))
                )),
                hasItem(allOf(
                        hasProperty("permission", is(READ)),
                        hasProperty("granting", is(true)),
                        hasProperty("sid", is(adminSid))
                )),
                hasItem(allOf(
                        hasProperty("permission", is(WRITE)),
                        hasProperty("granting", is(true)),
                        hasProperty("sid", is(adminSid))
                ))
        ));
    }
}
