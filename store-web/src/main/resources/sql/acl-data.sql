INSERT INTO acl_class(class) VALUES ('com.expertsoft.core.model.entity.Order');

INSERT INTO acl_sid(principal, sid) VALUES (TRUE, 'admin');
INSERT INTO acl_sid(principal, sid) VALUES (TRUE, 'user');
INSERT INTO acl_sid(principal, sid) VALUES (FALSE, 'ROLE_ADMIN');

INSERT INTO acl_object_identity(object_id_class, object_id_identity, owner_sid, entries_inheriting) VALUES (1, '1', 2, TRUE);
INSERT INTO acl_object_identity(object_id_class, object_id_identity, owner_sid, entries_inheriting) VALUES (1, '2', 1, TRUE);
INSERT INTO acl_object_identity(object_id_class, object_id_identity, owner_sid, entries_inheriting) VALUES (1, '3', 2, TRUE);

INSERT INTO acl_entry(acl_object_identity, ace_order, sid, mask, granting, audit_success, audit_failure) VALUES (1, 0, 2, 1, TRUE, FALSE, FALSE);
INSERT INTO acl_entry(acl_object_identity, ace_order, sid, mask, granting, audit_success, audit_failure) VALUES (1, 1, 3, 1, TRUE, FALSE, FALSE);
INSERT INTO acl_entry(acl_object_identity, ace_order, sid, mask, granting, audit_success, audit_failure) VALUES (1, 2, 3, 2, TRUE, FALSE, FALSE);
INSERT INTO acl_entry(acl_object_identity, ace_order, sid, mask, granting, audit_success, audit_failure) VALUES (2, 0, 1, 1, TRUE, FALSE, FALSE);
INSERT INTO acl_entry(acl_object_identity, ace_order, sid, mask, granting, audit_success, audit_failure) VALUES (2, 1, 3, 1, TRUE, FALSE, FALSE);
INSERT INTO acl_entry(acl_object_identity, ace_order, sid, mask, granting, audit_success, audit_failure) VALUES (2, 2, 3, 2, TRUE, FALSE, FALSE);
INSERT INTO acl_entry(acl_object_identity, ace_order, sid, mask, granting, audit_success, audit_failure) VALUES (3, 0, 2, 1, TRUE, FALSE, FALSE);
INSERT INTO acl_entry(acl_object_identity, ace_order, sid, mask, granting, audit_success, audit_failure) VALUES (3, 1, 3, 1, TRUE, FALSE, FALSE);
INSERT INTO acl_entry(acl_object_identity, ace_order, sid, mask, granting, audit_success, audit_failure) VALUES (3, 2, 3, 2, TRUE, FALSE, FALSE);