package aduial.ithildin.config;

import org.hibernate.dialect.identity.IdentityColumnSupportImpl;

/**
 * Created by luthien on 18/02/2021.
 */
public class SQLiteIdentityColumnSupport extends IdentityColumnSupportImpl {

    @Override
    public boolean supportsIdentityColumns() {
        return true;
    }

    @Override
    public String getIdentitySelectString(String table, String column, int type) {
        return "select last_insert_rowid()";
    }

    @Override
    public String getIdentityColumnString(int type) {
        return "integer";
    }
}
