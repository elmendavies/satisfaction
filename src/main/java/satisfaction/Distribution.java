package satisfaction;

import java.util.Arrays;
import java.util.Iterator;
import java.util.List;

public class Distribution {
	final PassengerId[][] assignments;

	public Distribution(PassengerId[][] assignments) {
		this.assignments = assignments;
	}

	public Cursor coordinatesOf(PassengerId passangerId) {
		int row = 0;
		while (row < assignments.length) {
			int column = 0;
			while (column < assignments[row].length) {
				
				if (passangerId.equals(assignments[row][column])) {
					return new Cursor(row, column);
				}
				
				column++;
			}
			row++;
		}
		return null;
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		for (PassengerId[] row : assignments) {
			for (PassengerId passenger : row) {
				if(passenger == row[0]) {
					if (row != assignments[0]) { 
						builder.append(",");
						builder.append("\n");
					}
				} else {
					builder.append(",");
					builder.append(" ");
				}
				builder.append(passenger);
			}
		}
		return builder.toString();
	}

	public static Distribution of(int cols, final List<PassengerId> passengerIds) throws ValidationException {
		// The followin line does this: 
		// rows = values.length / cols + (values.length % cols > 0 ? 1 : 0);
		int rows = (passengerIds.size() + cols - 1) / cols;
		
		PassengerId[][] assignments = new PassengerId[rows][cols];
		
		int i = 0;
		Iterator<PassengerId> it = passengerIds.iterator();
		while (it.hasNext()) {
			assignments[i / cols][i % cols] = it.next();
			i++;
		}
		return new Distribution(assignments);
	}
	
	public class Cursor {
		final int row;
		final int column;
		
		public Cursor(int row, int column) {
			super();
			this.row = row;
			this.column = column;
		}
		
		int row() { return row; }
		int column() { return column; }
	
		@Override
		public int hashCode() {
			final int prime = 31;
			int result = 1;
			result = prime * result + column;
			result = prime * result + row;
			return result;
		}
	
		@Override
		public boolean equals(Object obj) {			
			if (this == obj)
				return true;
			if (obj == null)
				return false;
			if (getClass() != obj.getClass())
				return false;
			Cursor other = (Cursor) obj;
			if (column != other.column)
				return false;
			if (row != other.row)
				return false;
			return true;
		}

		public boolean isInWindow() {
			int col = column();
			return col == 0 || col == assignments[row()].length - 1;
		}

	
		@Override
		public String toString() {
			return "(" + row + ", " + column + ")";
		}
	}

}
