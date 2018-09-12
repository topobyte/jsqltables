// Copyright 2016 Sebastian Kuerten
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

import de.topobyte.jsqltables.query.TableReference;

public class TwoColumnsCondition implements Condition
{

	private TableReference firstTable;
	private TableReference secondTable;
	private String firstColumn;
	private String secondColumn;
	private Comparison comparison;

	public TwoColumnsCondition(TableReference firstTable,
			TableReference secondTable, String firstColumn, String secondColumn,
			Comparison comparison)
	{
		this.firstTable = firstTable;
		this.secondTable = secondTable;
		this.firstColumn = firstColumn;
		this.secondColumn = secondColumn;
		this.comparison = comparison;
	}

	public TableReference getFirstTable()
	{
		return firstTable;
	}

	public TableReference getSecondTable()
	{
		return secondTable;
	}

	public String getFirstColumn()
	{
		return firstColumn;
	}

	public String getSecondColumn()
	{
		return secondColumn;
	}

	public Comparison getComparison()
	{
		return comparison;
	}

	@Override
	public void sql(StringBuilder b)
	{
		if (firstTable != null) {
			b.append(firstTable.getAlias());
			b.append(".");
		}
		b.append(firstColumn);
		switch (comparison) {
		default:
		case EQUAL:
			b.append(" = ");
			break;
		case NOT_EQUAL:
			b.append(" != ");
			break;
		case LIKE:
			b.append(" LIKE ");
			break;
		case LESS_THAN:
			b.append(" < ");
			break;
		case LESS_THAN_OR_EQUAL:
			b.append(" <= ");
			break;
		case GREATER_THAN:
			b.append(" > ");
			break;
		case GREATER_THAN_OR_EQUAL:
			b.append(" >= ");
			break;
		}
		if (secondTable != null) {
			b.append(secondTable.getAlias());
			b.append(".");
		}
		b.append(secondColumn);
	}

}
