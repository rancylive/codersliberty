package com.techventura.pipeasy.hbase;

import java.io.IOException;
import java.text.ParseException;
import java.util.Map;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.hbase.HBaseConfiguration;
import org.apache.hadoop.hbase.TableName;
import org.apache.hadoop.hbase.client.Connection;
import org.apache.hadoop.hbase.client.ConnectionFactory;
import org.apache.hadoop.hbase.client.Result;
import org.apache.hadoop.hbase.client.ResultScanner;
import org.apache.hadoop.hbase.client.Scan;
import org.apache.hadoop.hbase.client.Table;
import org.apache.hadoop.hbase.filter.CompareFilter;
import org.apache.hadoop.hbase.filter.FilterList;
import org.apache.hadoop.hbase.filter.SingleColumnValueFilter;
import org.apache.hadoop.hbase.io.ImmutableBytesWritable;
import org.apache.hadoop.hbase.mapreduce.TableInputFormat;
import org.apache.hadoop.hbase.util.Bytes;
import org.apache.log4j.Logger;
import org.apache.spark.api.java.JavaPairRDD;
import org.apache.spark.rdd.RDD;
import org.apache.spark.sql.Dataset;
import org.apache.spark.sql.SparkSession;
import org.apache.spark.sql.datasources.hbase.HBaseTableCatalog;
import org.slf4j.LoggerFactory;

import com.limitedbrands.oms.job.JobInfo;
import com.limitedbrands.oms.job.JobRunType;

import scala.Tuple2;

public class Reader<T> {

	static Logger LOGGER = (Logger) LoggerFactory.getLogger(Reader.class);
	
	public void read(String tableName, T targetType) {
		Configuration configuration=new Configuration();
		configuration.set(TableInputFormat.INPUT_TABLE, "/qa/stage1/level3/digital/analytics/maprdb/order_line_summary_maprdb");
		SparkSession session=SparkSession.builder().master("local").getOrCreate();
		RDD<Tuple2<ImmutableBytesWritable, Result>> data = session.sparkContext().newAPIHadoopRDD(configuration, TableInputFormat.class, ImmutableBytesWritable.class, Result.class);
		//Dataset input = session.read().option(HBaseTableCatalog.tableCatalog(), "/qa/stage1/level3/digital/analytics/maprdb/order_line_summary_maprdb").format("org.apache.spark.sql.execution.datasources.hbase").load();
		//input.take(2);
		long count = data.count();
		System.out.println("Count: "+count);
	}
	
	public static void main(String[] args) {
		new Reader<String>().read("", "");
	}
	
	private JobInfo getJobInfo(String jobName, String startRow, String stopRow, String tableName, JobRunType jobRunType) throws IOException, ParseException {
        JobInfo jobInfo = null;
        Scan scan = new Scan();
        scan = scan.setStartRow(Bytes.toBytes(startRow)).setStopRow(Bytes.toBytes(stopRow));

        SingleColumnValueFilter runTypeFilter = null;
        SingleColumnValueFilter lastRunStatusFilter = null;
        if(jobRunType.equals(JobRunType.ADHOC_RUN)) {
        	LOGGER.info("JobRunType.ADHOC_RUN :"+ JobRunType.ADHOC_RUN);
            runTypeFilter = new SingleColumnValueFilter(Bytes.toBytes("job_info_cf"), Bytes.toBytes("run_type"), CompareFilter.CompareOp.EQUAL, Bytes.toBytes("ac"));
            lastRunStatusFilter = new SingleColumnValueFilter(Bytes.toBytes("job_info_cf"), Bytes.toBytes("last_run"), CompareFilter.CompareOp.EQUAL, Bytes.toBytes("y"));
        } else {
        	LOGGER.info("Not JobRunType.ADHOC_RUN");
        	runTypeFilter = new SingleColumnValueFilter(Bytes.toBytes("job_info_cf"), Bytes.toBytes("run_type"), CompareFilter.CompareOp.EQUAL, Bytes.toBytes("bc"));
            lastRunStatusFilter = new SingleColumnValueFilter(Bytes.toBytes("job_info_cf"), Bytes.toBytes("last_run"), CompareFilter.CompareOp.EQUAL, Bytes.toBytes("y"));
        }

        FilterList filterList = new FilterList();
        filterList.addFilter(runTypeFilter);
        filterList.addFilter(lastRunStatusFilter);
        scan.setFilter(filterList);

        Connection con = ConnectionFactory.createConnection(HBaseConfiguration.create());
        Table table = con.getTable(TableName.valueOf(tableName));
        ResultScanner resultScanner = table.getScanner(scan);

        for (Result rr = resultScanner.next(); rr != null; rr = resultScanner.next()) {
            Map<byte[], byte[]> tableResultMap = rr.getFamilyMap(Bytes.toBytes("job_info_cf"));

            String currentRowKey = Bytes.toString(rr.getRow());

            byte[] startDateBytes = tableResultMap.get(Bytes.toBytes("start_date"));
            byte[] endDateBytes = tableResultMap.get(Bytes.toBytes("end_date"));
            byte[] lastRun = tableResultMap.get(Bytes.toBytes("last_run"));
            byte[] runType = tableResultMap.get(Bytes.toBytes("run_type"));
           
            LOGGER.info("startDate Job:" + Bytes.toString(startDateBytes));
            LOGGER.info("endDate Job:" + Bytes.toString(endDateBytes));
            LOGGER.info("lastRun job:" + Bytes.toString(lastRun));
            LOGGER.info("runType job:" + Bytes.toString(runType));
           
            //jobInfo = new JobInfo(Bytes.toString(startDateBytes), Bytes.toString(endDateBytes), Bytes.toString(runType), Bytes.toString(lastRun), tableName, jobName, currentRowKey);
            System.out.println(Bytes.toString(startDateBytes)+ Bytes.toString(endDateBytes)+ Bytes.toString(runType)+ Bytes.toString(lastRun)+ tableName+ jobName+ currentRowKey);
            break; // If for is invoked we will have only one row
        }

        resultScanner.close();
        table.close();
        con.close();

        return jobInfo;
    }
	
	
}
