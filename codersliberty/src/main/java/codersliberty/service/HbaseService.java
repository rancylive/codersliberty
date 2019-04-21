package codersliberty.service;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.apache.hadoop.hbase.HTableDescriptor;
import org.apache.hadoop.hbase.client.Admin;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service
public class HbaseService {
	@Autowired
	private HBConnection hbConnection;
	
	@Autowired
	private TableService tableService;
	
	public List<String> readtable(String tableName) throws IOException {
		return tableService.scanTable(tableName);
	}
	
	public List<String> readtable1(String tableName) throws IOException {
		return tableService.scanTable1(tableName);
	}

	public List<String> listTables() throws IOException {
		List<String> result=new ArrayList<String>();
		Admin admin = hbConnection.getHbaseConnection().getAdmin();
		HTableDescriptor[] tables = admin.listTables();
		for(HTableDescriptor tableDescriptor:tables) {
			result.add(tableDescriptor.getNameAsString());
		}
		return result;
	}
	
	public String createtable(String tableName) throws IOException {
		tableService.creatTable(tableName);
		return "Created table "+tableName;
	}
	
	public String updateTable(String tableName) throws IOException {
		tableService.updateTable(tableName);
		return "Updated table "+tableName;
	}
	
	public String deleteTable(String tableName) throws IOException {
		tableService.deletetable(tableName);
		return "Deleted table "+tableName;
	}
}
