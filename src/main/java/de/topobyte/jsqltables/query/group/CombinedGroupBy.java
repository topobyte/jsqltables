// Copyright 2018 Sebastian Kuerten
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

package de.topobyte.jsqltables.query.group;

import java.util.Arrays;
import java.util.List;

import de.topobyte.jsqltables.query.ColumnReference;

public class CombinedGroupBy implements GroupBy
{

	private List<ColumnReference> columns;

	public CombinedGroupBy(ColumnReference... columns)
	{
		this.columns = Arrays.asList(columns);
	}

	public CombinedGroupBy(List<ColumnReference> columns)
	{
		this.columns = columns;
	}

	@Override
	public void sql(StringBuilder b)
	{
		int n = columns.size();
		for (int i = 0; i < n; i++) {
			ColumnReference column = columns.get(i);
			column.sql(b);
			if (i < n - 1) {
				b.append(", ");
			}
		}
	}

}
