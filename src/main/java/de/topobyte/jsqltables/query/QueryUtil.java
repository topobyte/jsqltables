// Copyright 2019 Sebastian Kuerten
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

import java.util.List;

import de.topobyte.jsqltables.query.where.BooleanOperator;
import de.topobyte.jsqltables.query.where.CombinedCondition;
import de.topobyte.jsqltables.query.where.Condition;

public class QueryUtil
{

	public static Condition combine(List<Condition> conditions,
			BooleanOperator operator)
	{
		if (conditions.isEmpty()) {
			return null;
		}
		if (conditions.size() == 1) {
			return conditions.get(0);
		}
		CombinedCondition result = new CombinedCondition(operator,
				conditions.get(0), conditions.get(1));
		for (int i = 2; i < conditions.size(); i++) {
			result = new CombinedCondition(operator, result, conditions.get(i));
		}
		return result;
	}

}
