package org.openpaas.paasta.portal.common.api.domain.email;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;
import org.openpaas.paasta.portal.common.api.config.EmailConfig;
import org.openpaas.paasta.portal.common.api.domain.service.ServiceService;
import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import sun.misc.Launcher;

import java.io.File;
import java.net.URLClassLoader;
import java.util.HashMap;
import java.util.Map;

import static org.slf4j.LoggerFactory.getLogger;

@Service
public class EmailService {


    private static final Logger logger = getLogger(ServiceService.class);

    @Autowired
    EmailConfig emailConfig;

    public Map resetEmail(String userId, String refreshToken) {
        logger.info("resetEmail ::: " + userId);
        Map map = new HashMap();
        try {
            ClassLoader classLoader = getClass().getClassLoader();
            File file = new File(classLoader.getResource("loginpass.html").getFile());
            logger.debug("resetEmail ::: " + file.getAbsolutePath());
            Document doc = Jsoup.parse(file, "UTF-8");
            Elements elementAhref = doc.select("a[href]");
            Elements elementSpan = doc.select("span");
            if (elementAhref.size() != 0) {
                String link = emailConfig.getAuthUrl() + "/" + emailConfig.getExpiredUrl() + "?userId=" + userId + "&refreshToken=" + refreshToken;
                logger.debug("link : " + link);
                elementAhref.get(0).attr("href", link);
            }
            if (elementSpan.size() != 0) {
                elementSpan.get(0).childNode(0).attr("text", userId);
            }

            if (emailConfig.sendEmail(userId, doc.outerHtml())) {
                map.put("result", true);
                map.put("msg", "You have successfully completed the task.");
            } else {
                map.put("result", false);
                map.put("msg", "System error.");
            }

        } catch (Exception e) {
            e.printStackTrace();
            map.put("result", false);
            map.put("msg", e.getMessage());
        }
        return map;

    }


    public Map createEmail(String userId, String refreshToken) {
        logger.info("createEmail ::: " + userId);
        Map map = new HashMap();
        try {
            logger.info("createEmail ::: ");
            ClassLoader classLoader = URLClassLoader.getSystemClassLoader();
            File file = new File(classLoader.getResource("loginemail.html").getFile());
            logger.info("createEmail ::: " + file.getAbsolutePath());

            createEmail2(userId,refreshToken);
            createEmail3(userId,refreshToken);

            Document doc = Jsoup.parse(file, "UTF-8");

            Elements elementAhref = doc.select("a[href]");
            if (elementAhref.size() != 0) {
                String link = emailConfig.getAuthUrl() + "/" + emailConfig.getCreateUrl() + "?userId=" + userId + "&refreshToken=" + refreshToken;
                logger.info("link : " + link);
                elementAhref.get(0).attr("href", link);
            }
            logger.info(doc.outerHtml());
            if (emailConfig.sendEmail(userId, doc.outerHtml())) {
                map.put("result", true);
                map.put("msg", "You have successfully completed the task.");
            } else {
                map.put("result", false);
                map.put("msg", "System error.");
            }

        } catch (Exception e) {
            e.printStackTrace();
            map.put("result", false);
            map.put("msg", e.getMessage());
        }
        return map;

    }


    public Map createEmail2(String userId, String refreshToken) {
        logger.info("createEmail2 ::: " + userId);
        Map map = new HashMap();
        try {
            logger.info("createEmail2 ::: ");
            ClassLoader classLoader = getClass().getProtectionDomain().getClassLoader();
            File file = new File(classLoader.getResource("loginemail.html").getFile());
            logger.info("createEmail2 ::: " + file.getAbsolutePath());

            Document doc = Jsoup.parse(file, "UTF-8");

            Elements elementAhref = doc.select("a[href]");
            if (elementAhref.size() != 0) {
                String link = emailConfig.getAuthUrl() + "/" + emailConfig.getCreateUrl() + "?userId=" + userId + "&refreshToken=" + refreshToken;
                logger.info("link : " + link);
                elementAhref.get(0).attr("href", link);
            }
            logger.info(doc.outerHtml());
            if (emailConfig.sendEmail(userId, doc.outerHtml())) {
                map.put("result", true);
                map.put("msg", "You have successfully completed the task.");
            } else {
                map.put("result", false);
                map.put("msg", "System error.");
            }

        } catch (Exception e) {
            e.printStackTrace();
            map.put("result", false);
            map.put("msg", e.getMessage());
        }
        return map;

    }


    public Map createEmail3(String userId, String refreshToken) {
        logger.info("createEmail3 ::: " + userId);
        Map map = new HashMap();
        try {
            logger.info("createEmail3 ::: ");
            ClassLoader classLoader = Thread.currentThread().getContextClassLoader();
            File file = new File(classLoader.getResource("loginemail.html").getFile());
            logger.info("createEmail3 ::: " + file.getAbsolutePath());

            Document doc = Jsoup.parse(file, "UTF-8");

            Elements elementAhref = doc.select("a[href]");
            if (elementAhref.size() != 0) {
                String link = emailConfig.getAuthUrl() + "/" + emailConfig.getCreateUrl() + "?userId=" + userId + "&refreshToken=" + refreshToken;
                logger.info("link : " + link);
                elementAhref.get(0).attr("href", link);
            }
            logger.info(doc.outerHtml());
            if (emailConfig.sendEmail(userId, doc.outerHtml())) {
                map.put("result", true);
                map.put("msg", "You have successfully completed the task.");
            } else {
                map.put("result", false);
                map.put("msg", "System error.");
            }

        } catch (Exception e) {
            e.printStackTrace();
            map.put("result", false);
            map.put("msg", e.getMessage());
        }
        return map;

    }

    public Map createEmail4(String userId, String refreshToken) {
        logger.info("createEmail4 ::: " + userId);
        Map map = new HashMap();
        try {
            logger.info("createEmail4 ::: ");
            ClassLoader classLoader = Launcher.getLauncher().getClassLoader();
            File file = new File(classLoader.getResource("loginemail.html").getFile());
            logger.info("createEmail4 ::: " + file.getAbsolutePath());

            Document doc = Jsoup.parse(file, "UTF-8");

            Elements elementAhref = doc.select("a[href]");
            if (elementAhref.size() != 0) {
                String link = emailConfig.getAuthUrl() + "/" + emailConfig.getCreateUrl() + "?userId=" + userId + "&refreshToken=" + refreshToken;
                logger.info("link : " + link);
                elementAhref.get(0).attr("href", link);
            }
            logger.info(doc.outerHtml());
            if (emailConfig.sendEmail(userId, doc.outerHtml())) {
                map.put("result", true);
                map.put("msg", "You have successfully completed the task.");
            } else {
                map.put("result", false);
                map.put("msg", "System error.");
            }

        } catch (Exception e) {
            e.printStackTrace();
            map.put("result", false);
            map.put("msg", e.getMessage());
        }
        return map;

    }
}
