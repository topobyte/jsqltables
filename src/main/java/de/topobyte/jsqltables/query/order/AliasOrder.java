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

package de.topobyte.jsqltables.query.order;


public class AliasOrder implements Order
{

	private String alias;
	private OrderDirection direction;

	public AliasOrder(String alias,
			OrderDirection direction)
	{
		this.alias = alias;
		this.direction = direction;
	}

	public String getAlias()
	{
		return alias;
	}

	public OrderDirection getDirection()
	{
		return direction;
	}

	@Override
	public void sql(StringBuilder b)
	{
		b.append(alias);
		switch (direction) {
		default:
		case ASC:
			b.append(" ASC");
			break;
		case DESC:
			b.append(" DESC");
			break;
		}
	}

}
