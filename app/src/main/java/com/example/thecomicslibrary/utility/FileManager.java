package com.example.thecomicslibrary.utility;

import android.content.Context;

import org.jdom2.*;
import org.jdom2.input.SAXBuilder;
import org.jdom2.output.Format;
import org.jdom2.output.XMLOutputter;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class FileManager {
    private final String FILENAME = "mycomics.xml";
    private File file;

    //Add a comic, with the passed tag, to the file
    public void addComic(String tag,String text) {
        SAXBuilder builder = new SAXBuilder();
        try {
            Document doc = (Document) builder.build(file);
            Element rootElement = doc.getRootElement();
            Element typeNode = null;
            switch (tag) {
                case "bought":
                    typeNode = rootElement.getChild("bought");
                    break;
                case "read":
                    typeNode = rootElement.getChild("read");
                    break;
                case "desire":
                    typeNode = rootElement.getChild("desire");
                    break;
            }
            typeNode.addContent(new Element("comic").addContent(text));

            XMLOutputter outputter = new XMLOutputter();
            outputter.setFormat(Format.getPrettyFormat());
            outputter.output(doc,new FileWriter(file));
        }catch (IOException | JDOMException e) {
            e.printStackTrace();
        }
    }

    //Return true if a comic is in the list with the specific tag
    public boolean containsIssue(String tag,String linkAlbo) {
        SAXBuilder builder = new SAXBuilder();
        try {
            Document doc = (Document) builder.build(file);
            Element rootElement = doc.getRootElement();
            Element typeNode = null;
            switch (tag) {
                case "bought":
                    typeNode = rootElement.getChild("bought");
                    break;
                case "read":
                    typeNode = rootElement.getChild("read");
                    break;
                case "desire":
                    typeNode = rootElement.getChild("desire");
                    break;
            }

            if(!typeNode.getChildren().isEmpty()) {
                List<Element> contacts = new ArrayList<Element>(typeNode.getChildren());
                for (Element element : contacts) {
                    if(element.getText().equalsIgnoreCase(linkAlbo))
                        return true;
                }
            }
        }catch (IOException | JDOMException e) {
            e.printStackTrace();
        }
        return false;
    }

    //Remove a comic, with the passed tag, from the file
    public void deleteComic(String tag,String linkAlbo) {
        SAXBuilder builder = new SAXBuilder();
        try {
            Document doc = (Document) builder.build(file);
            Element rootElement = doc.getRootElement();
            Element typeNode = null;
            switch (tag) {
                case "bought":
                    typeNode = rootElement.getChild("bought");
                    break;
                case "read":
                    typeNode = rootElement.getChild("read");
                    break;
                case "desire":
                    typeNode = rootElement.getChild("desire");
                    break;
            }

            if(!typeNode.getChildren().isEmpty()) {
                List<Element> contacts = new ArrayList<Element>(typeNode.getChildren());
                for (Element element : contacts) {
                    if(element.getText().equalsIgnoreCase(linkAlbo))
                        element.removeContent();
                }
            }
            XMLOutputter outputter = new XMLOutputter();
            outputter.setFormat(Format.getPrettyFormat());
            outputter.output(doc,new FileWriter(file));
        }catch (IOException | JDOMException e) {
            e.printStackTrace();
        }
    }

    //Create a mycomics file if doesn't exists
    private void createFile() {
        if (!file.exists()) {
            try {
                file.createNewFile();
                initializeFile();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    //Add the basic xml's element to the mycomics file
    private void initializeFile() {
        try {
            Element rootElement = new Element("comics");
            Document doc = new Document();

            Element childBought = new Element("bought");
            rootElement.addContent(childBought);

            Element childRead = new Element("read");
            rootElement.addContent(childRead);

            Element childDesire = new Element("desire");
            rootElement.addContent(childDesire);

            doc.setRootElement(rootElement);

            XMLOutputter outputter = new XMLOutputter();
            outputter.setFormat(Format.getPrettyFormat());
            outputter.output(doc,new FileWriter(file));

        }catch (IOException e) {
            e.printStackTrace();
        }
    }

    public FileManager(Context context) {
        file = new File(context.getFilesDir(),FILENAME);
        createFile();
    }
}
