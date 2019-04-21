package ranjan.practice.practice_spark.hbase;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.apache.hadoop.hbase.Cell;
import org.apache.hadoop.hbase.CellUtil;
import org.apache.hadoop.hbase.HBaseConfiguration;
import org.apache.hadoop.hbase.TableName;
import org.apache.hadoop.hbase.client.Admin;
import org.apache.hadoop.hbase.client.Connection;
import org.apache.hadoop.hbase.client.ConnectionFactory;
import org.apache.hadoop.hbase.client.Result;
import org.apache.hadoop.hbase.client.ResultScanner;
import org.apache.hadoop.hbase.client.Scan;
import org.apache.hadoop.hbase.client.Table;

public class ScanTable {
	public static void main(String[] args) throws IOException {
		List records = new ArrayList();
		String tableName = args[0];
		TableName name = TableName.valueOf(tableName);
		Connection connection = null;
		connection = ConnectionFactory.createConnection(HBaseConfiguration.create());
		Admin admin = connection.getAdmin();
		if (admin.tableExists(name)) {
			Table table = connection.getTable(name);
			ResultScanner scanner = table.getScanner(new Scan());
			for (Result record = scanner.next(); record != null; record = scanner.next()) {
				List<Cell> cells = record.listCells();
				for(Cell cell:cells) {
					byte[] cellValue = CellUtil.cloneValue(cell);
					records.add(new String(cellValue));
				}
			}
		}
		System.out.println(records);
	}
}
