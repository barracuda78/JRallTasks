package com.javarush.task.task33.task3309;

import org.w3c.dom.Document;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.*;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.StringWriter;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;

/* 
Комментарий внутри xml
*/

public class Solution {
    public static String toXmlWithComment(Object obj, String tagName, String comment) {
        String xmlData = null;
        String xmlDataResult = null;
        try {
            DocumentBuilderFactory dbf;
            DocumentBuilder        db ;
            Document doc;

            StringWriter writer = new StringWriter();
            JAXBContext jaxbContext = JAXBContext.newInstance(obj.getClass());
            Marshaller marshaller = jaxbContext.createMarshaller();
            marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, Boolean.TRUE);
            marshaller.marshal(obj, writer);

            xmlData = writer.toString();


            //1. Создайте DocumentBuilderFactory, который позволит получить анализатор, создающий DOM из xml, выставите setCoalescing(true) - это нужно, чтобы узлы CDATA преобразовались в текст и добавлялись к смежному текстовому узлу.
            //2. С его помощью создайте DocumentBuilder, который определяет интерфейс для получения DOM из xml.
            //3. С его помощью создайте Document.
            dbf = DocumentBuilderFactory.newInstance();
            dbf.setCoalescing(true);
            db  = dbf.newDocumentBuilder();
            //doc = db.parse(new File("data.xml"));
            doc = db.parse(new ByteArrayInputStream(xmlData.getBytes(StandardCharsets.UTF_8)));

            //5. Получите из обновлённого документа список искомых тегов (NodeList) при помощи метода getElementsByTagName(tagName).
            //6. Пройдитесь по полученному списку NodeList и обработайте каждый узел таким образом:
            NodeList children = doc.getElementsByTagName(tagName);
            for (int i = 0; i < children.getLength(); i++) {
                // дочерний узел
                Node node = children.item(i);
                Node parentNode = node.getParentNode();
                //Добавьте комментарий перед полученным узлом. Для этого надо получить родительский узел (getParentNode()), вызвать у него метод insertBefore, который позволит вставить что-то перед вашим узлом, в этот метод передайте комментарий, созданный при помощи документа (document.createComment(comment)) и ваш узел. Получается:
                parentNode.insertBefore(doc.createComment(comment), node);
                //6.2. Теперь после комментария надо добавить переход на новую строку. Для этого тоже используйте insertBefore, как выше, только первым параметром передайте созданный при помощи Document текстовый узел "\n". Вот так:
                parentNode.insertBefore(doc.createTextNode("\n"), node);
            }

            //7. Создайте TransformerFactory, который позволит вам создать Transformer.
            TransformerFactory tf = TransformerFactory.newInstance();
            //8. С его помощью создайте Transformer, который позволит получить из DOM результат (в нашем случае в виде текста).
            Transformer transformer = tf.newTransformer();
            transformer.setOutputProperty(OutputKeys.STANDALONE, "no");
            //8.1. Установите для Transformer setOutputProperty(OutputKeys.STANDALONE, "no"). Этот метод нужен для установки выходного свойства, которое будет действовать при преобразовании дерева. Константа OutputKeys.STANDALONE означает, будет ли выводиться отдельная декларация документа , мы ставит "no".
            transformer.setOutputProperty(OutputKeys.OMIT_XML_DECLARATION, "no");
            //9. Для трансформации документа вызовите у Transformer метод transform, который трансформирует xml в то, что нам нужно. Первый параметром передайте новый объект DOMSource (я так и не понял, для чего он нужен. Он имеет в себе исходный DOM для преобразования, но хз зачем), при создании которого используйте document, вторым параметром передайте StreamResult (содержит в себе результат преобразования), в конструктор которого передайте новый StringWriter. Всё это выглядит вот так:
            StringWriter writerResult = new StringWriter();
            transformer.transform(new DOMSource(doc), new StreamResult(writerResult));
            xmlDataResult = writerResult.toString();

        } catch (JAXBException e) {
            e.printStackTrace();
        } catch (ParserConfigurationException e) {
            e.printStackTrace();
        } catch (SAXException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (TransformerConfigurationException e) {
            e.printStackTrace();
        } catch (TransformerException e) {
            e.printStackTrace();
        }
        return xmlDataResult;
    }

//    public static void main(String[] args) throws JAXBException, ParserConfigurationException, TransformerException {
//        System.out.println(Solution.toXmlWithComment(new First(), "second2", "it's a comment"));
//    }

    public static void main(String[] args) {

    }
}

//@XmlRootElement(name = "first")
//class First {
//    @XmlElement(name = "second")
//    public String item1 = "some string";
//    @XmlElement(name = "second")
//    public String item2 = "need CDATA because of <second>";
//    @XmlElement(name = "second")
//    public String item3 = "";
//    @XmlElement(name = "third")
//    public String item4;
//    @XmlElement(name = "forth")
//    public Second[] third = new Second[]{new Second()};
//    @XmlElement(name = "fifth")
//    public String item5 = "need CDATA because of \"";
//}
//
//class Second {
//    @XmlElement(name = "second")
//    public String item1 = "some string";
//    @XmlElement(name = "second")
//    public String item2 = "need CDATA because of <second>";
//}
