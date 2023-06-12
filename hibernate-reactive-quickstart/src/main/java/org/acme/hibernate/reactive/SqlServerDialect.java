package org.acme.hibernate.reactive;

import org.hibernate.dialect.DatabaseVersion;
import org.hibernate.dialect.SQLServerDialect;

public class SqlServerDialect extends SQLServerDialect {
	public SqlServerDialect() {
		super( DatabaseVersion.make( 15 ) );
	}
}
