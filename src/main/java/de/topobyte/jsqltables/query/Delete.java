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

package de.topobyte.jsqltables.query;

import de.topobyte.jsqltables.query.where.Condition;
import de.topobyte.jsqltables.table.Table;

public class Delete implements Query
{

	private Table table;
	private Condition condition = null;

	public Delete(Table table)
	{
		this.table = table;
	}

	public Table getTable()
	{
		return table;
	}

	public void where(Condition condition)
	{
		this.condition = condition;
	}

	@Override
	public String sql()
	{
		StringBuilder b = new StringBuilder();
		b.append("DELETE FROM ");
		b.append(table.getName());
		if (condition != null) {
			b.append(" WHERE ");
			condition.sql(b);
		}
		return b.toString();
	}

}
