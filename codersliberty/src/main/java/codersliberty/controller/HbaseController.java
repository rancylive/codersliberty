package codersliberty.controller;

import java.io.IOException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import codersliberty.service.HbaseService;

@Controller
@RequestMapping(value = "/")
public class HbaseController {
	@Autowired
	private HbaseService hbaseService;

	@RequestMapping(value = "hbase/data")
	public String scanTable(@RequestParam String tableName, @RequestParam String viewMode, Model model) throws IOException {
		List<String> datas = viewMode.equals("0") ? hbaseService.readtable(tableName) : hbaseService.readtable1(tableName);
		StringBuilder data = new StringBuilder();
		data.append("<table>");
		for (String r : datas) {
			data.append("<td>").append(r).append("</td>");
		}
		data.append("</table>");
		model.addAttribute("ack", "Reading " + tableName);
		model.addAttribute("data", data.toString());
		return "acknowledgement";
	}

	@RequestMapping(value = "hbase/tables")
	public String listTables(Model model) throws IOException {
		List<String> tables = hbaseService.listTables();
		StringBuilder data = new StringBuilder();
		data.append("<table>");
		for (String r : tables) {
			data.append("<td>").append(r).append("</td>");
		}
		data.append("</table>");
		model.addAttribute("ack", "List of tables");
		model.addAttribute("data", data.toString());
		return "acknowledgement";
	}

	@RequestMapping(value = "hbase/table/create")
	public String createTable(@RequestParam String tableName, Model model) throws IOException {
		model.addAttribute("ack", hbaseService.createtable(tableName));
		return "acknowledgement";
	}

	@RequestMapping(value = "hbase/table/update")
	public String updateTable(@RequestParam String tableName, Model model) throws IOException {
		model.addAttribute("ack", hbaseService.updateTable(tableName));
		return "acknowledgement";
	}

	@RequestMapping(value = "hbase/table/delete")
	public String udeleteTable(@RequestParam String tableName, Model model) throws IOException {
		model.addAttribute("ack", hbaseService.deleteTable(tableName));
		return "acknowledgement";
	}
}
