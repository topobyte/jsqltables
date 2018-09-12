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

package de.topobyte.jsqltables.query.where;

public class CombinedCondition implements Condition
{

	private BooleanOperator operator;
	private Condition c1;
	private Condition c2;

	public CombinedCondition(BooleanOperator operator, Condition c1,
			Condition c2)
	{
		this.operator = operator;
		this.c1 = c1;
		this.c2 = c2;
	}

	@Override
	public void sql(StringBuilder b)
	{
		b.append("(");
		c1.sql(b);
		switch (operator) {
		default:
		case AND:
			b.append(" AND ");
			break;
		case OR:
			b.append(" OR ");
			break;
		}
		c2.sql(b);
		b.append(")");
	}

}
