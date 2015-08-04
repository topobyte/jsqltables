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

package de.topobyte.jsqltables.model;

import de.topobyte.jsqltables.table.ColumnClass;
import de.topobyte.jsqltables.table.Table;

public class Tables
{

	public static Table TABLE_STUDENTS = new Table("students");
	public static Table TABLE_PROFESSORS = new Table("professors");
	public static Table TABLE_COURSES = new Table("courses");

	public static Table TABLE_COURSES_STUDENTS = new Table("courses_students");

	static {
		TABLE_STUDENTS.addColumn(ColumnClass.INT, "id");
		TABLE_STUDENTS.addColumn(ColumnClass.VARCHAR, "first_name");
		TABLE_STUDENTS.addColumn(ColumnClass.VARCHAR, "last_name");
		TABLE_STUDENTS.addColumn(ColumnClass.VARCHAR, "matrikel");

		TABLE_PROFESSORS.addColumn(ColumnClass.INT, "id");
		TABLE_PROFESSORS.addColumn(ColumnClass.VARCHAR, "first_name");
		TABLE_PROFESSORS.addColumn(ColumnClass.VARCHAR, "last_name");

		TABLE_COURSES.addColumn(ColumnClass.INT, "id");
		TABLE_COURSES.addColumn(ColumnClass.VARCHAR, "title");
		TABLE_COURSES.addColumn(ColumnClass.INT, "professor");

		TABLE_COURSES_STUDENTS.addColumn(ColumnClass.INT, "course");
		TABLE_COURSES_STUDENTS.addColumn(ColumnClass.INT, "student");
	}

}
