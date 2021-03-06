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

package de.topobyte.jsqltables.table;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

import de.topobyte.jsqltables.index.Indexes;

public class Table
{

	private String name;
	private List<TableColumn> columns = new ArrayList<>();
	private Map<String, Integer> nameToIndex = new TreeMap<>();

	/**
	 * Create a new table with the specified name.
	 * 
	 * @param name
	 *            the name of the table.
	 */
	public Table(String name)
	{
		this.name = name;
	}

	/**
	 * Get the name of this table.
	 * 
	 * @return the table's name.
	 */
	public String getName()
	{
		return name;
	}

	/**
	 * Add a new column to this table.
	 * 
	 * @param cc
	 *            the type of the column.
	 * @param name
	 *            the name of the column.
	 * @return the 1-based index of the new column
	 */
	public int addColumn(ColumnClass cc, String name)
	{
		return addColumn(cc, name, ColumnExtension.NONE);
	}

	/**
	 * Add a new column to this table.
	 * 
	 * @param cc
	 *            the type of the column.
	 * @param name
	 *            the name of the column.
	 * @param ce
	 *            a column extension to add.
	 * @return the 1-based index of the new column
	 */
	public int addColumn(ColumnClass cc, String name, ColumnExtension ce)
	{
		int index = columns.size() + 1;
		columns.add(new TableColumn(cc, name, ce));
		nameToIndex.put(name, index);
		return index;
	}

	/**
	 * Get the 1-based index of the specified column. This method throws a
	 * {@link NoSuchColumnException} if no column with the specified name
	 * exists.
	 * 
	 * @param name
	 *            the name of the column.
	 * @return the 1-based index within this table.
	 * @throws NoSuchColumnException
	 */
	public int getColumnIndex(String name) throws NoSuchColumnException
	{
		Integer index = nameToIndex.get(name);
		if (index == null) {
			throw new NoSuchColumnException("colum name: '" + name + "'");
		}
		return index;
	}

	/**
	 * Get the 1-based index of the specified column. This method returns 0 if
	 * no column with the specified name exists.
	 * 
	 * @param name
	 *            the name of the column.
	 * @return the 1-based index within this table.
	 * @throws NoSuchColumnException
	 */
	public int getColumnIndexSafe(String columnName)
	{
		try {
			return getColumnIndex(columnName);
		} catch (NoSuchColumnException e) {
			return 0;
		}
	}

	/**
	 * Get the number of columns of this table.
	 * 
	 * @return the number of columns.
	 */
	public int getNumberOfColumns()
	{
		return columns.size();
	}

	/**
	 * Get the i'th column of this table (1-based)
	 * 
	 * @param index
	 *            the index of the column.
	 * @return the column.
	 */
	public TableColumn getColumn(int index)
	{
		return columns.get(index - 1);
	}

	public String constructSelectSingleStatement(String column)
	{
		String alias = "t";

		StringBuilder builder = new StringBuilder();
		builder.append("SELECT * FROM ");
		builder.append(name);
		builder.append(" ");
		builder.append(alias);
		builder.append(" WHERE ");
		builder.append(column);
		builder.append(" = ?");
		return builder.toString();
	}

	public String constructDeleteStatement()
	{
		StringBuilder builder = new StringBuilder();
		builder.append("DELETE FROM ");
		builder.append(name);
		return builder.toString();
	}

	public String constructDeleteStatement(String column)
	{
		StringBuilder builder = new StringBuilder();
		builder.append("DELETE FROM ");
		builder.append(name);
		builder.append(" WHERE ");
		builder.append(column);
		builder.append(" = ?");
		return builder.toString();
	}

	public String constructDeleteStatement(String... columns)
	{
		StringBuilder builder = new StringBuilder();
		builder.append("DELETE FROM ");
		builder.append(name);
		builder.append(" WHERE ");
		for (int i = 0; i < columns.length - 1; i++) {
			builder.append(columns[i]);
			builder.append(" = ?");
			builder.append(" AND ");
		}
		builder.append(columns[columns.length - 1]);
		builder.append(" = ?");
		return builder.toString();
	}

	public String constructSelectAllStatement()
	{
		String alias = "t";

		StringBuilder builder = new StringBuilder();
		builder.append("SELECT * FROM ");
		builder.append(name);
		builder.append(" ");
		builder.append(alias);
		return builder.toString();
	}

	public String constructSelectAllStatementOrderBy(String columnName)
	{
		String alias = "t";

		StringBuilder builder = new StringBuilder();
		builder.append("SELECT * FROM ");
		builder.append(name);
		builder.append(" ");
		builder.append(alias);
		builder.append(" ORDER BY ");
		builder.append(columnName);
		return builder.toString();
	}

	public String constructSelectEqualStatementOrderBySame(String columnName)
	{
		return constructSelectEqualStatementOrderBy(columnName, columnName);
	}

	public String constructSelectEqualStatementOrderBy(String equalColumnName,
			String orderColumnName)
	{
		String alias = "t";

		StringBuilder builder = new StringBuilder();
		builder.append("SELECT * FROM ");
		builder.append(name);
		builder.append(" ");
		builder.append(alias);
		builder.append(" WHERE ");
		builder.append(equalColumnName);
		builder.append(" = ?");
		builder.append(" ORDER BY ");
		builder.append(orderColumnName);
		return builder.toString();
	}

	public String constructSelectLikeStatementOrderBySame(String columnName)
	{
		return constructSelectLikeStatementOrderBy(columnName, columnName);
	}

	public String constructSelectLikeStatementOrderBy(String likeColumnName,
			String orderColumnName)
	{
		String alias = "t";

		StringBuilder builder = new StringBuilder();
		builder.append("SELECT * FROM ");
		builder.append(name);
		builder.append(" ");
		builder.append(alias);
		builder.append(" WHERE ");
		builder.append(likeColumnName);
		builder.append(" LIKE ?");
		builder.append(" ORDER BY ");
		builder.append(orderColumnName);
		return builder.toString();
	}

	public String constructSelectEqualLikeStatementOrderBySame(
			String equalColumnName, String likeColumnName)
	{
		return constructSelectEqualLikeStatementOrderBy(equalColumnName,
				likeColumnName, likeColumnName);
	}

	public String constructSelectEqualLikeStatementOrderBy(
			String equalColumnName, String likeColumnName,
			String orderColumnName)
	{
		String alias = "t";

		StringBuilder builder = new StringBuilder();
		builder.append("SELECT * FROM ");
		builder.append(name);
		builder.append(" ");
		builder.append(alias);
		builder.append(" WHERE ");
		builder.append(equalColumnName);
		builder.append(" = ? AND ");
		builder.append(likeColumnName);
		builder.append(" LIKE ?");
		builder.append(" ORDER BY ");
		builder.append(orderColumnName);
		return builder.toString();
	}

	public String constructJoinStatementOrderBy(String otherTable,
			String myJoinColumnName, String otherJoinColumnName,
			String orderColumnName)
	{
		String alias1 = "t1";
		String alias2 = "t2";

		StringBuilder builder = new StringBuilder();
		builder.append("SELECT * FROM ");
		builder.append(name);
		builder.append(" ");
		builder.append(alias1);
		builder.append(" JOIN ");
		builder.append(otherTable);
		builder.append(" ");
		builder.append(alias2);
		builder.append(" ON ");
		builder.append(alias1);
		builder.append(".");
		builder.append(myJoinColumnName);
		builder.append(" = ");
		builder.append(alias2);
		builder.append(".");
		builder.append(otherJoinColumnName);
		builder.append(" ORDER BY ");
		builder.append(alias1);
		builder.append(".");
		builder.append(orderColumnName);
		return builder.toString();
	}

	public String constructJoinLikeStatementOrderBySame(String otherTable,
			String myJoinColumnName, String otherJoinColumnName,
			String likeColumnName)
	{
		return constructJoinLikeStatementOrderBy(otherTable, myJoinColumnName,
				otherJoinColumnName, likeColumnName, likeColumnName);
	}

	public StringBuilder constructJoinStatement(String alias1, String alias2,
			String otherTable, String myJoinColumnName,
			String otherJoinColumnName)
	{
		StringBuilder builder = new StringBuilder();
		builder.append("SELECT * FROM ");
		builder.append(name);
		builder.append(" ");
		builder.append(alias1);
		builder.append(" JOIN ");
		builder.append(otherTable);
		builder.append(" ");
		builder.append(alias2);
		builder.append(" ON ");
		builder.append(alias1);
		builder.append(".");
		builder.append(myJoinColumnName);
		builder.append(" = ");
		builder.append(alias2);
		builder.append(".");
		builder.append(otherJoinColumnName);

		return builder;
	}

	public String constructJoinLikeStatementOrderBy(String otherTable,
			String myJoinColumnName, String otherJoinColumnName,
			String likeColumnName, String orderColumnName)
	{
		String alias1 = "t1";
		String alias2 = "t2";

		StringBuilder builder = constructJoinStatement(alias1, alias2,
				otherTable, myJoinColumnName, otherJoinColumnName);

		builder.append(" WHERE ");
		builder.append(alias1);
		builder.append(".");
		builder.append(likeColumnName);
		builder.append(" LIKE ?");
		builder.append(" ORDER BY ");
		builder.append(alias1);
		builder.append(".");
		builder.append(orderColumnName);
		return builder.toString();
	}

	public String constructJoinEqualStatementOrderBy(String otherTable,
			String myJoinColumnName, String otherJoinColumnName,
			String equalColumnName, String orderColumnName)
	{
		String alias1 = "t1";
		String alias2 = "t2";

		StringBuilder builder = constructJoinStatement(alias1, alias2,
				otherTable, myJoinColumnName, otherJoinColumnName);

		builder.append(" WHERE ");
		builder.append(alias1);
		builder.append(".");
		builder.append(equalColumnName);
		builder.append(" = ?");
		builder.append(" ORDER BY ");
		builder.append(alias1);
		builder.append(".");
		builder.append(orderColumnName);
		return builder.toString();
	}

	public String constructCountStatement()
	{
		StringBuilder builder = new StringBuilder();
		builder.append("SELECT COUNT(*) FROM ");
		builder.append(name);
		return builder.toString();
	}

	public String constructCountStatement(String equalColumnName)
	{
		StringBuilder builder = new StringBuilder();
		builder.append("SELECT COUNT(*) FROM ");
		builder.append(name);
		builder.append(" WHERE ");
		builder.append(equalColumnName);
		builder.append(" = ?");
		return builder.toString();
	}

	public String constructColumnNameList(String alias)
	{
		StringBuilder builder = new StringBuilder();
		Iterator<TableColumn> iterator = columns.iterator();
		while (iterator.hasNext()) {
			builder.append(iterator.next().getName());
			if (iterator.hasNext()) {
				builder.append(", ");
			}
		}
		return builder.toString();
	}

	public String constructUpdateStatement(String column)
	{
		StringBuilder builder = new StringBuilder();
		builder.append("UPDATE ");
		builder.append(name);
		builder.append(" SET ");

		int index = nameToIndex.get(column) - 1;
		int num = columns.size() - 1;

		int k = 0;
		for (int i = 0; i < columns.size(); i++) {
			if (i == index) {
				continue;
			}
			k++;
			TableColumn tc = columns.get(i);
			builder.append(tc.getName());
			builder.append(" = ?");
			if (k < num) {
				builder.append(", ");
			}
		}

		builder.append(" WHERE ");
		builder.append(column);
		builder.append(" = ?");
		return builder.toString();
	}

	public String createIndex(String name, String... columns)
	{
		return Indexes.createStatement(this, name, columns);
	}

	public String createIndex(String name, boolean ignoreExisting,
			String... columns)
	{
		return Indexes.createStatement(this, name, ignoreExisting, columns);
	}

	public String createIndex(String name, List<String> columns)
	{
		return Indexes.createStatement(this, name, columns);
	}

	public String createIndex(String name, boolean ignoreExisting,
			List<String> columns)
	{
		return Indexes.createStatement(this, name, ignoreExisting, columns);
	}

}
