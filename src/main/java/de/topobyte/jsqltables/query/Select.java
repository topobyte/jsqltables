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

import de.topobyte.jsqltables.query.group.GroupBy;
import de.topobyte.jsqltables.query.join.Join;
import de.topobyte.jsqltables.query.join.MultiColumnJoin;
import de.topobyte.jsqltables.query.join.Pair;
import de.topobyte.jsqltables.query.join.SingleColumnJoin;
import de.topobyte.jsqltables.query.order.Order;
import de.topobyte.jsqltables.query.select.SelectColumn;
import de.topobyte.jsqltables.query.values.IntegerValue;
import de.topobyte.jsqltables.query.values.LongValue;
import de.topobyte.jsqltables.query.values.Value;
import de.topobyte.jsqltables.query.values.Wildcard;
import de.topobyte.jsqltables.query.where.Condition;
import de.topobyte.jsqltables.table.Table;

public class Select implements Query
{

	private List<SelectColumn> selectColumns = new ArrayList<>();
	private TableReference mainTable;
	private List<TableReference> joinTables = new ArrayList<>();
	private List<Join> joins = new ArrayList<>();
	private boolean distinct = false;
	private Condition condition = null;
	private Order order;
	private GroupBy group;
	private Value limit = null;
	private Value offset = null;

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
		joins.add(new SingleColumnJoin(first, joinTable, firstColumn,
				secondColumn));
		return joinTable;
	}

	public TableReference join(Table table, List<Pair> joinColumns)
	{
		return join(mainTable, table, joinColumns);
	}

	public TableReference join(TableReference first, Table second,
			List<Pair> joinColumns)
	{
		TableReference joinTable = new TableReference(second,
				alias(joins.size() + 2));
		joinTables.add(joinTable);
		joins.add(new MultiColumnJoin(first, joinTable, joinColumns));
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

	public void limit()
	{
		this.limit = new Wildcard();
	}

	public void limit(int limit)
	{
		this.limit = new IntegerValue(limit);
	}

	public void limit(long limit)
	{
		this.limit = new LongValue(limit);
	}

	public void offset()
	{
		this.offset = new Wildcard();
	}

	public void offset(int offset)
	{
		this.offset = new IntegerValue(offset);
	}

	public void offset(long offset)
	{
		this.offset = new LongValue(offset);
	}

	private String alias(int n)
	{
		return "t" + Integer.toString(n);
	}

	@Override
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
			join.sql(b);
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
			limit.sql(b);
		}
		if (offset != null) {
			b.append(" OFFSET ");
			offset.sql(b);
		}
		return b.toString();
	}

}
