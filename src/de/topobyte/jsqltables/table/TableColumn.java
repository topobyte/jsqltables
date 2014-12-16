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

public class TableColumn
{

	private ColumnClass cc;
	private String name;
	private ColumnExtension ce;

	public TableColumn(ColumnClass cc, String name, ColumnExtension ce)
	{
		this.cc = cc;
		this.name = name;
		this.ce = ce;
	}

	public ColumnClass getColumnClass()
	{
		return cc;
	}

	public String getName()
	{
		return name;
	}

	public ColumnExtension getColumnExtension()
	{
		return ce;
	}

}
