package ua.nure.gunko.rent.db;

import java.sql.ResultSet;

public interface EntityMapper<T> {
	T mapRow(ResultSet rs);
}
