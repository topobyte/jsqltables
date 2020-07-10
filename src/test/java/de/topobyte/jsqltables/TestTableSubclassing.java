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
