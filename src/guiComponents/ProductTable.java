package guiComponents;


import java.util.ArrayList;
import java.util.List;

import javax.swing.table.AbstractTableModel;

import entity.Products;


public class ProductTable
    extends AbstractTableModel
{

    /** field <code>serialVersionUID</code> */
    private static final long serialVersionUID = 1L;
    List<Products> rowData = null;
    String[] columnNames = {"ID", "Name", "Price", "Qty", "Category", "Brand"};


    public ProductTable()
    {
    	super();
    	rowData = new ArrayList<>();
        

    }


    @Override
    public int getColumnCount()
    {
        return this.columnNames.length;
    }


    public String getColumnName(int column)
    {
        return this.columnNames[column];
    }


    @Override
    public int getRowCount()
    {
        return this.rowData.size();
    }


    @Override
    public Object getValueAt(int row, int column)
    {
        switch (column)
        {
            case 0:
                return this.rowData.get(row).getProductId();
            case 1:
                return this.rowData.get(row).getName();
            case 2:
                return this.rowData.get(row).getPrice();
            case 3:
                return this.rowData.get(row).getQuantity();
            case 4:
                return this.rowData.get(row).getCategories().getName();
            case 5:
                return this.rowData.get(row).getBrands().getName();

        }
        return "Wrong value";
    }


    public void updateRow(List<Products> pr)
    {
        List<Products> newData = new ArrayList<Products>();
        for (Products product : pr)
        {
            newData.add(product);
        }
        rowData = newData;
    }


	public void removeRow(int row) {
		Products p = rowData.get(row); 
		
		if (rowData.contains(p)) {
			rowData.remove(p);
			fireTableDataChanged();
		}
		
		

	}

}
