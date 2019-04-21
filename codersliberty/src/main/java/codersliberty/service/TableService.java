package codersliberty.service;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.apache.hadoop.hbase.Cell;
import org.apache.hadoop.hbase.CellUtil;
import org.apache.hadoop.hbase.HColumnDescriptor;
import org.apache.hadoop.hbase.HTableDescriptor;
import org.apache.hadoop.hbase.TableName;
import org.apache.hadoop.hbase.client.Admin;
import org.apache.hadoop.hbase.client.Connection;
import org.apache.hadoop.hbase.client.Put;
import org.apache.hadoop.hbase.client.Result;
import org.apache.hadoop.hbase.client.ResultScanner;
import org.apache.hadoop.hbase.client.Scan;
import org.apache.hadoop.hbase.client.Table;
import org.apache.hadoop.hbase.util.Bytes;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class TableService {
	@Autowired
	private HBConnection hbConnection;

	public void creatTable(String tableName) throws IOException {
		Connection connection = null;
		Table table = null;
		try {
			connection = hbConnection.getHbaseConnection();
			TableName tn = TableName.valueOf(tableName);
			Admin hBaseAdmin = connection.getAdmin();
			if (!hBaseAdmin.tableExists(TableName.valueOf(tableName))) {
				String columnFamily="name";
			    HTableDescriptor tableDescriptor = new HTableDescriptor(TableName.valueOf(tableName));
			    tableDescriptor.addFamily(new HColumnDescriptor(columnFamily));
			    tableDescriptor.addFamily(new HColumnDescriptor("address"));
			    hBaseAdmin.createTable(tableDescriptor);
			}
		} finally {
			if (table != null) {
				table.close();
			}
			if (connection != null) {
				connection.close();
			}
		}
	}
	
	public void updateTable(String tableName) throws IOException {
		Connection connection = null;
		Table table = null;
		try {
			connection = hbConnection.getHbaseConnection();
			TableName tn = TableName.valueOf(tableName);
			byte[] cf = Bytes.toBytes("name");
			table = connection.getTable(tn);

			Put put = new Put(Bytes.toBytes("rowKey"));
			put.addColumn(cf, Bytes.toBytes("fName"), Bytes.toBytes("Ranjan"));
			put.addColumn(cf, Bytes.toBytes("lName"), Bytes.toBytes("Choudhury"));
			put.addColumn(Bytes.toBytes("address"), Bytes.toBytes("City"), Bytes.toBytes("bangalore"));
			put.addColumn(Bytes.toBytes("address"), Bytes.toBytes("PIN"), Bytes.toBytes("560001"));
			table.put(put);
		} finally {
			if (table != null) {
				table.close();
			}
			if (connection != null) {
				connection.close();
			}
		}
	}
	
	public void deletetable(String tableName) throws IOException {
		TableName name=TableName.valueOf(tableName);
		Connection connection=null;
		try {
			connection=hbConnection.getHbaseConnection();
			Admin admin=connection.getAdmin();
			admin.disableTable(name);
			admin.deleteTable(name);
		} finally {
			if (connection != null) {
				connection.close();
			}
		}
	}
	

	public List<String> scanTable(String tableName) throws IOException {
		List<String> records=new ArrayList();
		Connection connection = null;
		Table table = null;
		try {
			connection = hbConnection.getHbaseConnection();
			TableName tn = TableName.valueOf(tableName);
			table = connection.getTable(tn);
			ResultScanner resultScanner=table.getScanner(new Scan());
			for(Result result=resultScanner.next();result!=null;result=resultScanner.next()) {
				List<Cell> cells = result.listCells();
				for(Cell cell:cells) {
					byte[] cellValue = CellUtil.cloneValue(cell);
					records.add(new String(cellValue));
				}
			}
		} finally {
			if (table != null) {
				table.close();
			}
			if (connection != null) {
				connection.close();
			}
		}
		return records;
	}
	
	public List<String> scanTable1(String tableName) throws IOException {
		List<String> datas=new ArrayList();
		Connection connection = hbConnection.getHbaseConnection();
		Table table = connection.getTable(TableName.valueOf(tableName));
		Scan scan=new Scan();
		ResultScanner scanner=table.getScanner(scan);
		for(Result result = scanner.next(); result!=null;result=scanner.next()) {
			byte[] row = result.value();
			datas.add(Bytes.toString(row));
		}
		return datas;
	}
}
