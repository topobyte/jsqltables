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

package de.topobyte.jsqltables.dialect;

import de.topobyte.jsqltables.table.ColumnClass;

public class DefaultDialect implements Dialect
{

	@Override
	public String getSqlKeyword(ColumnClass cc)
	{
		switch (cc) {
		case DOUBLE:
			return "double";
		case INT:
			return "integer";
		case LONG:
			return "bigint";
		case VARCHAR:
			return "varchar";
		case BLOB:
			return "blob";
		default:
			return null;
		}
	}

}
