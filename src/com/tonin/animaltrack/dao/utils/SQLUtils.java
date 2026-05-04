package com.tonin.animaltrack.dao.utils;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

public class SQLUtils {

	public static void addClause(Object value, List<String> condiciones, String clause, List<Object> parametros, Object parameterValue) {
		if (value != null) {
			condiciones.add(clause);
			parametros.add(parameterValue);
		}
	}
	
	/**
	 * Obtencion del total de filas de un resultSet, sin repetir consulta.

	 * Metodo orientado a implementar paginación.

	 * No existe una solución en el API estandar de JDBC.

	 * Esta es un solución para todas las BD pero NO ES LA MEJOR EN RENDIMIENTO.

	 * Por ello en este caso es habitual usar soluciones propietarias

	 * de cada BD (rownum de Oracle, y similar en MySQL).

	 * (Lo de OFFSET y FETCH NEXT no es compatible con esto, pero 

	 *  esto permite sacar en una sola query tanto la página como el total)

	 */

	public static final int getTotalRows(ResultSet resultSet) throws SQLException {

		int totalRows = 0;

		if(resultSet.last()) {

			totalRows = resultSet.getRow();

		}

		resultSet.beforeFirst();

		return totalRows;

	}
	
	
	
}