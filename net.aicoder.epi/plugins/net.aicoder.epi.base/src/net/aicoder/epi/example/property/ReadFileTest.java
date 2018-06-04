package net.aicoder.epi.example.property;

import net.aicoder.epi.base.BasePlugin;

public class ReadFileTest {
	
	public static void main(String[] args) {
		String fileName = "net/aicoder/epi/example/property/0_dpy_type_subtype.xml";
		String outStr = BasePlugin.readFile2String(fileName);
		System.out.print(outStr);
	}

}
