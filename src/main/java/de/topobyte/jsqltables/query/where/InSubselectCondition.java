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

import java.util.Arrays;
import java.util.List;

import de.topobyte.jsqltables.query.Select;
import de.topobyte.jsqltables.query.TableReference;

public class InSubselectCondition implements Condition
{

	private TableReference table;
	private List<String> columns;
	private Select subselect;

	public InSubselectCondition(TableReference table, String column,
			Select subselect)
	{
		this(table, Arrays.asList(column), subselect);
	}

	public InSubselectCondition(TableReference table, List<String> columns,
			Select subselect)
	{
		this.table = table;
		this.columns = columns;
		this.subselect = subselect;
	}

	public TableReference getTable()
	{
		return table;
	}

	public List<String> getColumns()
	{
		return columns;
	}

	public Select getSubselect()
	{
		return subselect;
	}

	@Override
	public void sql(StringBuilder b)
	{
		if (columns.size() == 1) {
			column(b, table, columns.get(0));
		} else {
			b.append("(");
			for (int i = 0; i < columns.size(); i++) {
				column(b, table, columns.get(i));
				if (i < columns.size() - 1) {
					b.append(", ");
				}
			}
			b.append(")");
		}

		b.append(" IN (");
		b.append(subselect.sql());
		b.append(")");
	}

	private void column(StringBuilder b, TableReference table, String column)
	{
		if (table != null) {
			b.append(table.getAlias());
			b.append(".");
		}
		b.append(column);
	}

}
