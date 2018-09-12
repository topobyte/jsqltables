// Copyright 2014 Sebastian Kuerten
//
// This file is part of jsqltables.
//
// jsqltables is free software: you can redistribute it and/or modify
// it under the terms of the GNU Lesser General Public License as published by
// the Free Software Foundation, either version 3 of the License, or
// (at your option) any later version.
//
// jsqltables is distributed in the hope that it will be useful,
// but WITHOUT ANY WARRANTY; without even the implied warranty of
// MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. See the
// GNU Lesser General Public License for more details.
//
// You should have received a copy of the GNU Lesser General Public License
// along with jsqltables. If not, see <http://www.gnu.org/licenses/>.

package de.topobyte.jsqltables.table;

import de.topobyte.jsqltables.dialect.Dialect;

public class QueryBuilder
{

	private Dialect dialect;

	public QueryBuilder(Dialect dialect)
	{
		this.dialect = dialect;
	}

	public String create(Table table)
	{
		return create(table, false);
	}

	public String create(Table table, boolean ignoreExisting)
	{
		StringBuilder builder = new StringBuilder();
		builder.append("CREATE TABLE ");
		if (ignoreExisting) {
			builder.append("IF NOT EXISTS ");
		}
		builder.append(table.getName());
		builder.append(" (");
		int n = table.getNumberOfColumns();
		for (int i = 0; i < n; i++) {
			TableColumn column = table.getColumn(i + 1);
			builder.append(column.getName());
			builder.append(" ");
			builder.append(dialect.getSqlKeyword(column.getColumnClass()));
			if (column
					.getColumnExtension() == ColumnExtension.PRIMARY_AUTO_INCREMENT) {
				builder.append(" PRIMARY KEY AUTOINCREMENT");
			} else if (column
					.getColumnExtension() == ColumnExtension.COLLATE_NOCASE) {
				builder.append(" COLLATE NOCASE");
			}
			if (i < n - 1) {
				builder.append(", ");
			}
		}
		builder.append(")");
		return builder.toString();
	}

	public String insert(Table table)
	{
		StringBuilder builder = new StringBuilder();
		builder.append("INSERT INTO ");
		builder.append(table.getName());
		builder.append(" VALUES (");
		int n = table.getNumberOfColumns();
		for (int i = 0; i < n - 1; i++) {
			builder.append("?, ");
		}
		builder.append("?)");
		return builder.toString();
	}

}
