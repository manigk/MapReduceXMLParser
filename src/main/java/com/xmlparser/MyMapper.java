package com.xmlparser;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
 


import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.NullWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Mapper;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
 
public class MyMapper extends Mapper<LongWritable, Text, Text, NullWritable> {
 
    private static final Log LOG = LogFactory.getLog(MyMapper.class);
 
    // Fprivate Text videoName = new Text();
 
    public void map(LongWritable key, Text value, Context context) throws IOException, InterruptedException {
 
        try {
 
            InputStream is = new ByteArrayInputStream(value.toString().getBytes());
            DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
            DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
            Document doc = dBuilder.parse(is);
 
            doc.getDocumentElement().normalize();
 
            NodeList nList = doc.getElementsByTagName("dataset");
 
            for (int temp = 0; temp < nList.getLength(); temp++) {
 
                Node nNode = nList.item(temp);
 
                if (nNode.getNodeType() == Node.ELEMENT_NODE) {
 
                    Element eElement = (Element) nNode;
 
                    String name = eElement.getElementsByTagName("name").item(0).getTextContent();
                    String metadata = eElement.getElementsByTagName("metadata-url").item(0).getTextContent();
                    String teaser = eElement.getElementsByTagName("teaser").item(0).getTextContent();
 
                    // System.out.println(id + "," + name + "," + gender);
                    context.write(new Text(name + "," + metadata + "," + teaser), NullWritable.get());
 
                }
            }
        } catch (Exception e) {
 //           LogWriter.getInstance().WriteLog(e.getMessage());
            System.out.println(e.getMessage().toString());

        }
 
    }
 
}