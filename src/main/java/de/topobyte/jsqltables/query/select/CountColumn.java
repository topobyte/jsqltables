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

package de.topobyte.jsqltables.query.select;

import de.topobyte.jsqltables.query.TableReference;

public class CountColumn extends AbstractColumn
{

	private boolean distinct;

	public CountColumn(TableReference table, String columnName, boolean distinct)
	{
		super(table, columnName);
		this.distinct = distinct;
	}

	@Override
	public void sql(StringBuilder b)
	{
		b.append("COUNT(");
		if (distinct) {
			b.append("DISTINCT ");
		}
		b.append(table.getAlias());
		b.append(".");
		b.append(columnName);
		b.append(")");
	}

}
