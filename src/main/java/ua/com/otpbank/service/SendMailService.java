package ua.com.otpbank.service;

import java.awt.*;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * Created by Igor on 05.05.2015.
 */
public class SendMailService {

    public SendMailService(){}

    private Map<String, String> mailList;
    private String subject;
    private String content;
    private Generator generator = Generator.getInstance();

    public Map<String, String> getMailList() {
        return mailList;
    }

    public void setMailList(Map<String, String> mailList) {
        this.mailList = mailList;
    }

    public String getSubject() {
        return subject;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }

    public String getContent() {
        return content;
    }

    public void setContent(List<String> participants) {
        this.content = arrayToString(participants);
    }

    public void composeNewMail() {

        List<String> sendTO = new ArrayList<String>();
        sendTO.addAll(getMailList().values());
        setContent(generator.getShuffledCoffeeLovers());
        try {
            mailTo(sendTO, getSubject(), getContent());
        } catch (Exception e1) {
            e1.printStackTrace();
        }
    }


    public void mailTo(List<String> recipients, List<String> carbon_copy, String subject,
                       String body) throws IOException, URISyntaxException {
        String uriStr = String.format("mailto:%s?CC=%s&subject=%s&body=%s",
                join(";", recipients), // use semicolon ";" for Outlook!
                join(";", carbon_copy), // use semicolon ";" for Outlook!
                urlEncode(subject),
                urlEncode(body));
        Desktop.getDesktop().browse(new URI(uriStr));
    }

    public void mailTo(List<String> recipients, String subject,
                       String body) throws IOException, URISyntaxException {
        String uriStr = String.format("mailto:%s?&subject=%s&body=%s",
                join(";", recipients), // use semicolon ";" for Outlook!
                urlEncode(subject),
                urlEncode(body));
        Desktop.getDesktop().browse(new URI(uriStr));
    }

    private String arrayToString(List<String> list) {
        StringBuilder resultString = new StringBuilder();
        int idx = 1;
        for (String item : list) {
            resultString = resultString.append(idx).append("-").append(item).append("; ");
            idx++;
        }

        return resultString.toString();
    }

    private final String urlEncode(String str) {
        try {
            return URLEncoder.encode(str, "UTF-8").replace("+", "%20");
        } catch (UnsupportedEncodingException e) {
            throw new RuntimeException(e);
        }
    }

    public final String join(String sep, Iterable<?> objs) {
        StringBuilder sb = new StringBuilder();
        for (Object obj : objs) {
            if (sb.length() > 0) sb.append(sep);
            sb.append(obj);
        }
        return sb.toString();
    }
}
