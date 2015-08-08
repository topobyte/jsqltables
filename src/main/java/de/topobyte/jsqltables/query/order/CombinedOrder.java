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

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;

public class CombinedOrder implements Order
{

	private List<Order> singles = new ArrayList<>();

	public CombinedOrder(Order... orders)
	{
		singles.addAll(Arrays.asList(orders));
	}

	@Override
	public void sql(StringBuilder b)
	{
		Iterator<Order> iterator = singles.iterator();
		while (iterator.hasNext()) {
			Order order = iterator.next();
			order.sql(b);
			if (iterator.hasNext()) {
				b.append(", ");
			}
		}
	}

}
