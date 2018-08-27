package ru.eludia.base.model.phys;

import java.sql.JDBCType;
import java.sql.ResultSet;
import java.sql.SQLException;
import ru.eludia.base.model.abs.AbstractCol;

public final class PhysicalCol extends AbstractCol {
    
    JDBCType type;    
    String def;
    boolean virtual = false;

    public PhysicalCol (ResultSet rs) throws SQLException {
                
        super (rs.getString ("COLUMN_NAME"), rs.getInt ("COLUMN_SIZE"), rs.getInt ("DECIMAL_DIGITS"), rs.getString ("REMARKS"));

        type     = JDBCType.valueOf (rs.getInt ("DATA_TYPE"));
        nullable = rs.getInt    ("NULLABLE") == 1;
        def      = rs.getString ("COLUMN_DEF");
                
    }

    public void setVirtual (boolean virtual) {
        this.virtual = virtual;
    }

    public boolean isVirtual () {
        return virtual;
    }

    public String getDef () {
        return def;
    }

    public JDBCType getType () {
        return type;
    }

    public PhysicalCol (JDBCType type, String name, int length, int precision) {
        super (name, length, precision);
        this.type = type;
    }
    
    public PhysicalCol (JDBCType type, String name, int length) {
        this (type, name, length, 0);
    }

    public PhysicalCol (JDBCType type, String name) {
        this (type, name, 0);
    }

    public void setDef (String def) {
        this.def = def;
    }

    @Override
    public String toString () {
        return "[" + getName () + " " + type + "(" + length + "," + precision + ")=" + def + (isNullable () ? " " : " NOT") + " NULL #" + remark + "]";
    }
    
}