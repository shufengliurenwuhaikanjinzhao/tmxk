package com.springcloud.tmxk.XStream;

import com.thoughtworks.xstream.XStream;
import junit.framework.TestCase;

/**
 * @ClassName AppTest
 * @Deacription TODO
 * @Author thl
 * @Date 2019/11/2 11:29
 * @Version 1.0
 **/
public class AppTest extends TestCase {
    public static void main(String[] args) {
        XStream xStream = new XStream();
        xStream.alias("User", User.class);
        //xStream.aliasAttribute(User.class,"files","files");
        xStream.alias("files", ListFile.class);
        xStream.addImplicitCollection(ListFile.class,"files","file", String.class);
        String xml = "<User>\n" +
                "  <userName>lanweihong</userName>\n" +
                "  <email>lwhhhp@gmail.com</email>\n" +
                "  <files>" +
                "    <file>xx</file>" +
                "    <file>yy</file>" +
                "    <file></file>" +
                "  </files> \n"+
                "</User>";
        //转对象
        User user = (User)xStream.fromXML(xml);
        System.out.println(user.getFiles().getFiles().size());
    }
}

