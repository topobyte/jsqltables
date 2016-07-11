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

package de.topobyte.jsqltables.query.where;

import de.topobyte.jsqltables.query.Select;
import de.topobyte.jsqltables.query.TableReference;

public class InSubselectCondition implements Condition
{

	private TableReference table;
	private String column;
	private Select subselect;

	public InSubselectCondition(TableReference table, String column,
			Select subselect)
	{
		this.table = table;
		this.column = column;
		this.subselect = subselect;
	}

	public TableReference getTable()
	{
		return table;
	}

	public String getColumn()
	{
		return column;
	}

	public Select getSubselect()
	{
		return subselect;
	}

	@Override
	public void sql(StringBuilder b)
	{
		if (table != null) {
			b.append(table.getAlias());
			b.append(".");
		}
		b.append(column);
		b.append(" IN (");
		b.append(subselect.sql());
		b.append(")");
	}

}
