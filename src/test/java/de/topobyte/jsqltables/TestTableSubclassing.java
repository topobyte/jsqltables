// Copyright 2020 Sebastian Kuerten
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

package de.topobyte.jsqltables;

import org.junit.Assert;
import org.junit.Test;

import de.topobyte.jsqltables.table.ColumnClass;
import de.topobyte.jsqltables.table.Table;

public class TestTableSubclassing
{

	private static class MyTable extends Table
	{

		public MyTable()
		{
			super("students");

			addColumn(ColumnClass.INT, "id");
			addColumn(ColumnClass.VARCHAR, "name");
		}

	}

	@Test
	public void tablesShouldBeExtendable()
	{
		Table table = new MyTable();
		Assert.assertEquals(2, table.getNumberOfColumns());
	}

}
