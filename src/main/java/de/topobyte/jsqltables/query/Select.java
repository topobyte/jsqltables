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

import group.GroupBy;

import java.util.ArrayList;
import java.util.List;

import de.topobyte.jsqltables.query.order.Order;
import de.topobyte.jsqltables.query.select.SelectColumn;
import de.topobyte.jsqltables.query.where.Condition;
import de.topobyte.jsqltables.table.Table;

public class Select
{

	private List<SelectColumn> selectColumns = new ArrayList<>();
	private TableReference mainTable;
	private List<TableReference> joinTables = new ArrayList<>();
	private List<Join> joins = new ArrayList<>();
	private boolean distinct = false;
	private Condition condition = null;
	private Order order;
	private GroupBy group;
	private LimitOffset limit = null;

	public Select(Table table)
	{
		mainTable = new TableReference(table, alias(1));
	}

	public TableReference getMainTable()
	{
		return mainTable;
	}

	public void addSelectColumn(SelectColumn column)
	{
		selectColumns.add(column);
	}

	public TableReference join(Table table, String firstColumn,
			String secondColumn)
	{
		return join(mainTable, table, firstColumn, secondColumn);
	}

	public TableReference join(TableReference first, Table second,
			String firstColumn, String secondColumn)
	{
		TableReference joinTable = new TableReference(second,
				alias(joins.size() + 2));
		joinTables.add(joinTable);
		joins.add(new Join(first, joinTable, firstColumn, secondColumn));
		return joinTable;
	}

	public void distinct()
	{
		distinct = true;
	}

	public void where(Condition condition)
	{
		this.condition = condition;
	}

	public void group(GroupBy group)
	{
		this.group = group;
	}

	public void order(Order order)
	{
		this.order = order;
	}

	public void limit(LimitOffset limit)
	{
		this.limit = limit;
	}

	private String alias(int n)
	{
		return "t" + Integer.toString(n);
	}

	public String sql()
	{
		StringBuilder b = new StringBuilder();
		b.append("SELECT ");
		if (distinct) {
			b.append("DISTINCT ");
		}
		if (selectColumns.size() == 0) {
			b.append("*");
		} else {
			int n = selectColumns.size();
			for (int i = 0; i < n; i++) {
				SelectColumn column = selectColumns.get(i);
				column.sql(b);
				if (i < n - 1) {
					b.append(",");
				}
			}
		}
		b.append(" FROM ");
		b.append(mainTable.getTable().getName());
		b.append(" ");
		b.append(mainTable.getAlias());
		for (Join join : joins) {
			b.append(" JOIN ");
			b.append(join.getSecondTable().getTable().getName());
			b.append(" ");
			b.append(join.getSecondTable().getAlias());
			b.append(" ON ");
			b.append(join.getFirstTable().getAlias());
			b.append(".");
			b.append(join.getFirstColumn());
			b.append(" = ");
			b.append(join.getSecondTable().getAlias());
			b.append(".");
			b.append(join.getSecondColumn());
		}
		if (condition != null) {
			b.append(" WHERE ");
			condition.sql(b);
		}
		if (group != null) {
			b.append(" GROUP BY ");
			group.sql(b);
		}
		if (order != null) {
			b.append(" ORDER BY ");
			order.sql(b);
		}
		if (limit != null) {
			b.append(" LIMIT ");
			b.append(limit.getLimit());
			b.append(" OFFSET ");
			b.append(limit.getOffset());
		}
		return b.toString();
	}
}
