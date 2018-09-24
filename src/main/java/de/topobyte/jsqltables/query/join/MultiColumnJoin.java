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

package de.topobyte.jsqltables.query.join;

import java.util.List;

import de.topobyte.jsqltables.query.TableReference;

public class MultiColumnJoin implements Join
{

	private TableReference firstTable;
	private TableReference secondTable;

	private List<Pair> columnPairs;

	public MultiColumnJoin(TableReference firstTable,
			TableReference secondTable, List<Pair> columnPairs)
	{
		this.firstTable = firstTable;
		this.secondTable = secondTable;
		this.columnPairs = columnPairs;
	}

	public TableReference getFirstTable()
	{
		return firstTable;
	}

	public TableReference getSecondTable()
	{
		return secondTable;
	}

	@Override
	public void sql(StringBuilder b)
	{
		b.append(getSecondTable().getTable().getName());
		b.append(" ");
		b.append(getSecondTable().getAlias());
		b.append(" ON ");
		for (int i = 0; i < columnPairs.size(); i++) {
			Pair pair = columnPairs.get(i);
			b.append(getFirstTable().getAlias());
			b.append(".");
			b.append(pair.getFirst());
			b.append(" = ");
			b.append(getSecondTable().getAlias());
			b.append(".");
			b.append(pair.getSecond());

			if (i < columnPairs.size() - 1) {
				b.append(" AND ");
			}
		}
	}

}
