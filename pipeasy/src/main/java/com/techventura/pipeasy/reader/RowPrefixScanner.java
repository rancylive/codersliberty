package com.techventura.pipeasy.reader;

import com.limitedbrands.oms.utility.MD5Util;
import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.hbase.HBaseConfiguration;
import org.apache.hadoop.hbase.TableName;
import org.apache.hadoop.hbase.client.*;
import org.apache.hadoop.hbase.filter.CompareFilter;
import org.apache.hadoop.hbase.filter.FilterList;
import org.apache.hadoop.hbase.filter.SingleColumnValueFilter;
import org.apache.hadoop.hbase.util.Bytes;
import java.io.IOException;
import java.util.Map;

public class RowPrefixScanner {
	public static void main(String[] args) {
		Configuration hBaseConf = HBaseConfiguration.create();
		Connection con = null;
		try {
			con = ConnectionFactory.createConnection(hBaseConf);
			Table table = con
					.getTable(TableName.valueOf("/qa/stage1/level3/digital/ods/maprdb/microservice_error_log_maprdb"));
			Scan scan = new Scan();
			SingleColumnValueFilter singleColumnValueFilter1 = new SingleColumnValueFilter(Bytes.toBytes("error_log"),
					Bytes.toBytes("error_type"), CompareFilter.CompareOp.EQUAL, Bytes.toBytes("non_recoverable"));
			SingleColumnValueFilter singleColumnValueFilter2 = new SingleColumnValueFilter(Bytes.toBytes("error_log"),
					Bytes.toBytes("error_type"), CompareFilter.CompareOp.EQUAL, Bytes.toBytes("recoverable"));
			String serviceName = "demandcalc-micro-service";
			String rowKeyPrefix = MD5Util.md5SubStr7(serviceName);
			scan.setRowPrefixFilter(Bytes.toBytes(rowKeyPrefix + "\u0002" + serviceName + "201903"));
			//FilterList filterList = new FilterList(FilterList.Operator.MUST_PASS_ONE);
			//filterList.addFilter(singleColumnValueFilter1);
			//filterList.addFilter(singleColumnValueFilter2);
			//scan.setFilter(filterList);
			ResultScanner resultScanner = table.getScanner(scan);
			int exceptionCount = 0;
			for (Result rr = resultScanner.next(); rr != null; rr = resultScanner.next()) {

				Map<byte[], byte[]> tableResultMap = rr.getFamilyMap(Bytes.toBytes("error_log"));
//if (Bytes.toString(tableResultMap.get(Bytes.toBytes("error_type"))) == "error") {
				System.out.println("RowKey ::\n" + Bytes.toString(rr.getRow()));
				System.out
						.println("error_stack ::\n" + Bytes.toString(tableResultMap.get(Bytes.toBytes("error_stack"))));
				System.out.println(
						"json_message ::\n" + Bytes.toString(tableResultMap.get(Bytes.toBytes("json_message"))));
//}
				exceptionCount++;
			}

			System.out.println("total exception :: " + exceptionCount);
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			if (con != null) {
				try {
					con.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
	}
}
