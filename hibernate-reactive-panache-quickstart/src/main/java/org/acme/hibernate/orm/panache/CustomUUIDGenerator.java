package org.acme.hibernate.orm.panache;

import java.io.Serializable;

import org.hibernate.HibernateException;
import org.hibernate.engine.spi.SharedSessionContractImplementor;
import org.hibernate.id.UUIDGenerator;

import io.quarkus.logging.Log;

public class CustomUUIDGenerator extends UUIDGenerator {

    @Override
    public Serializable generate(SharedSessionContractImplementor session, Object object) throws HibernateException {
        Serializable result = super.generate( session, object);
//        Log.infof("Generated ID: %s", result);
        return result;
    }
}