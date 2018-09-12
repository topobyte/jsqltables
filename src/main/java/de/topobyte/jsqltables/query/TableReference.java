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

import java.util.ArrayList;
import java.util.List;

import de.topobyte.jsqltables.table.Table;

public class TableReference
{

	private Table table;
	private String alias;

	public TableReference(Table table, String alias)
	{
		this.table = table;
		this.alias = alias;
	}

	public Table getTable()
	{
		return table;
	}

	public String getAlias()
	{
		return alias;
	}

	public ColumnReference column(String name)
	{
		return new ColumnReference(this, name);
	}

	public List<ColumnReference> columns(String... names)
	{
		List<ColumnReference> columns = new ArrayList<>();
		for (String name : names) {
			columns.add(new ColumnReference(this, name));
		}
		return columns;
	}

}
