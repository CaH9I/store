package com.expertsoft.core.dialect;

public class HSQLDialect extends org.hibernate.dialect.HSQLDialect {

    @Override
    public boolean dropConstraints() {
        return false;
    }
}
