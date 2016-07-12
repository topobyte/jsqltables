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

package de.topobyte.jsqltables.index;

import java.util.Arrays;
import java.util.List;

import de.topobyte.jsqltables.table.Table;

public class Indexes
{

	public static String createStatement(Table table, String name,
			String... columns)
	{
		return createStatement(table, name, Arrays.asList(columns));
	}

	public static String createStatement(String tableName, String name,
			String... columns)
	{
		return createStatement(tableName, name, Arrays.asList(columns));
	}

	public static String createStatement(Table table, String name,
			List<String> columns)
	{
		return createStatement(table.getName(), name, columns);
	}

	public static String createStatement(String tableName, String name,
			List<String> columns)
	{
		StringBuilder builder = new StringBuilder();
		builder.append("CREATE INDEX ");
		builder.append(name);
		builder.append(" ON ");
		builder.append(tableName);
		builder.append("(");
		for (int i = 0; i < columns.size(); i++) {
			builder.append(columns.get(i));
			if (i < columns.size() - 1) {
				builder.append(", ");
			}
		}
		builder.append(")");
		return builder.toString();
	}

}
