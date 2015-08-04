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

public class InCondition implements Condition
{

	private TableReference table;
	private String column;
	private int numValues;

	public InCondition(TableReference table, String column, int numValues)
	{
		this.table = table;
		this.column = column;
		this.numValues = numValues;
	}

	public TableReference getTable()
	{
		return table;
	}

	public String getColumn()
	{
		return column;
	}

	public int getNumValues()
	{
		return numValues;
	}

	@Override
	public void sql(StringBuilder b)
	{
		b.append(table.getAlias());
		b.append(".");
		b.append(column);
		b.append(" IN (");
		for (int i = 0; i < numValues - 1; i++) {
			b.append("?,");
		}
		b.append("?)");
	}

}
