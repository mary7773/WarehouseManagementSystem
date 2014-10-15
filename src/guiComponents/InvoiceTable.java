package guiComponents;

import java.util.ArrayList;
import java.util.List;

import entity.*;

import javax.swing.table.AbstractTableModel;

@SuppressWarnings("serial")
public class InvoiceTable extends AbstractTableModel {

	List<Products> rowData = null;
	String[] columnNames = { "ID", "Product Name", "Qty", "Price", "Total" };

	public InvoiceTable() {
		super();
		rowData = new ArrayList<Products>();
	}

	@Override
	public int getColumnCount() {

		return columnNames.length;
	}

	public String getColumnName(int column) {
		return this.columnNames[column];
	}

	@Override
	public int getRowCount() {

		return rowData.size();
	}

	@Override
	public Object getValueAt(int row, int col) {

		Products in = rowData.get(row);
		

		switch (col) {
		case 0:
			return in.getProductId();
		case 1:
			return in.getName();
		case 2:
			return in.getQuantity();
		case 3:
			return in.getPrice();
		case 4:
			return in.getTotal();

		}
		return null;
	}
	
	public void updateRowInvoice(List<Products> pr) {
		this.rowData = pr;
	}

}
