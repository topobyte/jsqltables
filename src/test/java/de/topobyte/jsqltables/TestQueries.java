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

package de.topobyte.jsqltables;

import de.topobyte.jsqltables.model.Tables;
import de.topobyte.jsqltables.query.Delete;
import de.topobyte.jsqltables.query.Select;
import de.topobyte.jsqltables.query.TableReference;
import de.topobyte.jsqltables.query.Update;
import de.topobyte.jsqltables.query.group.CombinedGroupBy;
import de.topobyte.jsqltables.query.group.SingleGroupBy;
import de.topobyte.jsqltables.query.order.CombinedOrder;
import de.topobyte.jsqltables.query.order.OrderDirection;
import de.topobyte.jsqltables.query.order.SingleOrder;
import de.topobyte.jsqltables.query.select.CountAllColumn;
import de.topobyte.jsqltables.query.select.NormalColumn;
import de.topobyte.jsqltables.query.where.BooleanOperator;
import de.topobyte.jsqltables.query.where.CombinedCondition;
import de.topobyte.jsqltables.query.where.Comparison;
import de.topobyte.jsqltables.query.where.InCondition;
import de.topobyte.jsqltables.query.where.SingleCondition;

public class TestQueries
{

	public static void main(String[] args)
	{
		Select select1 = new Select(Tables.TABLE_STUDENTS);
		System.out.println(select1.sql());

		testJoin();

		testWhere();

		testUpdate();

		testDelete();

		testGroupBy();
	}

	private static void testJoin()
	{
		Select select1 = new Select(Tables.TABLE_STUDENTS);
		select1.join(Tables.TABLE_COURSES_STUDENTS, "id", "student");

		System.out.println(select1.sql());

		Select select2 = new Select(Tables.TABLE_STUDENTS);
		TableReference tcs = select2.join(Tables.TABLE_COURSES_STUDENTS, "id",
				"student");
		select2.join(tcs, Tables.TABLE_COURSES, "course", "id");

		System.out.println(select2.sql());
	}

	private static void testWhere()
	{
		Select select1 = new Select(Tables.TABLE_STUDENTS);
		select1.where(new SingleCondition(select1.getMainTable(), "first_name",
				Comparison.LIKE));
		System.out.println(select1.sql());

		Select select2 = new Select(Tables.TABLE_STUDENTS);
		select2.where(new CombinedCondition(BooleanOperator.AND,
				new SingleCondition(select2.getMainTable(), "first_name",
						Comparison.LIKE),
				new SingleCondition(select2.getMainTable(), "last_name",
						Comparison.EQUAL)));
		System.out.println(select2.sql());

		Select select3 = new Select(Tables.TABLE_STUDENTS);
		select3.where(new CombinedCondition(BooleanOperator.OR,
				new CombinedCondition(BooleanOperator.AND,
						new SingleCondition(select3.getMainTable(),
								"first_name", Comparison.LIKE),
						new SingleCondition(select3.getMainTable(), "last_name",
								Comparison.EQUAL)),
				new InCondition(select3.getMainTable(), "foo", 5)));
		System.out.println(select3.sql());

		Select select4 = new Select(Tables.TABLE_STUDENTS);
		select4.where(new CombinedCondition(BooleanOperator.AND,
				new SingleCondition(select4.getMainTable(), "first_name",
						Comparison.LIKE),
				new SingleCondition(select4.getMainTable(), "last_name",
						Comparison.EQUAL)));
		select4.order(new SingleOrder(select4.getMainTable(), "first_name",
				OrderDirection.DESC));
		System.out.println(select4.sql());

		Select select5 = new Select(Tables.TABLE_STUDENTS);
		select5.where(new CombinedCondition(BooleanOperator.AND,
				new SingleCondition(select5.getMainTable(), "first_name",
						Comparison.LIKE),
				new SingleCondition(select5.getMainTable(), "last_name",
						Comparison.EQUAL)));
		select5.order(new CombinedOrder(
				new SingleOrder(select5.getMainTable(), "last_name",
						OrderDirection.DESC),
				new SingleOrder(select5.getMainTable(), "first_name",
						OrderDirection.ASC)));
		System.out.println(select5.sql());
	}

	private static void testUpdate()
	{
		Update update1 = new Update(Tables.TABLE_STUDENTS);
		update1.addColum("first_name");
		System.out.println(update1.sql());

		Update update2 = new Update(Tables.TABLE_STUDENTS);
		update2.addColum("first_name");
		update2.where(new SingleCondition(null, "matrikel", Comparison.EQUAL));
		System.out.println(update2.sql());

		Update update3 = new Update(Tables.TABLE_STUDENTS);
		update3.addColum("first_name");
		update3.addColum("last_name");
		update3.where(new SingleCondition(null, "matrikel", Comparison.EQUAL));
		System.out.println(update3.sql());
	}

	private static void testDelete()
	{
		Delete delete1 = new Delete(Tables.TABLE_STUDENTS);
		System.out.println(delete1.sql());

		Delete delete2 = new Delete(Tables.TABLE_STUDENTS);
		delete2.where(new SingleCondition(null, "matrikel", Comparison.EQUAL));
		System.out.println(delete2.sql());
	}

	private static void testGroupBy()
	{
		Select group1 = new Select(Tables.TABLE_STUDENTS);
		group1.addSelectColumn(
				new NormalColumn(group1.getMainTable(), "first_name"));
		group1.addSelectColumn(new CountAllColumn("c"));
		group1.group(new SingleGroupBy(group1.getMainTable(), "first_name"));
		System.out.println(group1.sql());

		Select group2 = new Select(Tables.TABLE_STUDENTS);
		group2.addSelectColumn(
				new NormalColumn(group2.getMainTable(), "first_name"));
		group2.addSelectColumn(
				new NormalColumn(group2.getMainTable(), "last_name"));
		group2.addSelectColumn(new CountAllColumn("c"));
		group2.group(new CombinedGroupBy(
				group2.getMainTable().columns("first_name", "last_name")));
		System.out.println(group2.sql());
	}

}
