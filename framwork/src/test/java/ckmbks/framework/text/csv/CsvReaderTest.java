package ckmbks.framework.text.csv;

import ckmbks.framework.io.resource.ResourceUtil;
import ckmbks.framework.lang.Console;
import ckmbks.framework.util.CharsetUtil;
import org.junit.Test;

import java.util.List;

public class CsvReaderTest {
	@Test
	public void readTest() {
		CsvReader reader = new CsvReader();
		CsvData data = reader.read(ResourceUtil.getReader("test.csv", CharsetUtil.CHARSET_UTF_8));
		List<CsvRow> rows = data.getRows();
		for (CsvRow csvRow : rows) {
			Console.log(csvRow.get(2));
		}
	}
}
