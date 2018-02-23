package org.openpaas.paasta.portal.common.api.domain.user;

import com.sun.xml.internal.messaging.saaj.packaging.mime.MessagingException;
import org.openpaas.paasta.portal.common.api.entity.portal.UserDetail;
import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static org.slf4j.LoggerFactory.getLogger;


/**
 * Created by SEJI on 2018-02-20.
 */
@RestController
@Transactional
@RequestMapping(value = {"/user"})
public class UserController {

    private final Logger LOGGER = getLogger(this.getClass());

    @Autowired
    private UserService userService;

    /**
     * 사용자 총 명수
     *
     * @param
     * @return App user count
     */
    @RequestMapping(value = {"/getUserCount"}, method = RequestMethod.GET)
    public Map<String, Object> getUserCount() {

        int userCnt = userService.getUserCount();

        LOGGER.info("count : " + userCnt);

        Map<String, Object> resultMap = new HashMap<>();
        resultMap.put("count", userCnt);
//        UserDetail user = new UserDetail();
//        user.setCount(userCnt);

        return resultMap;
    }

    /**
     * Gets user.
     *
     * @param userId the user id
     * @return Map user
     */
    @RequestMapping(value = {"/getUser/{userId:.+}"}, method = RequestMethod.GET)
    public Map getUser(@PathVariable String userId) {
        LOGGER.info("> into getUser...");

        UserDetail user= userService.getUser(userId);

        Map<String, Object> result = new HashMap<>();

        result.put("User", user);

        return result;
    }


    /**
     * 이메일 인증 사용자 확인
     *
     * @param userDetail the user detail
     * @param response   the response
     * @return the map
     * @throws IOException        the io exception
     * @throws MessagingException the messaging exception
     */
    @RequestMapping(value = {"/confirmAuthUser"})
    public Map<String, Object> confirmAuthUser(@RequestBody UserDetail userDetail, HttpServletResponse response) throws IOException, MessagingException {
        HashMap body = new HashMap();
        Map<String, Object> resultMap = new HashMap();
        HashMap<String, Object> requestMap = new HashMap();

        body.put("userId", userDetail.getUserId());
        body.put("refreshToken", userDetail.getRefreshToken());
        body.put("status", userDetail.getStatus());

        List<Map<String, Object>> listUser = userService.getUserDetailInfo(body);
//        UserDetail userDetail1 = (UserDetail)listUser.get(0);
//        requestMap.put("userId",userDetail1.getUserId());
//        requestMap.put("authAccessCnt",userDetail1.getAuthAccessCnt());

//        userService.authAddAccessCnt(requestMap);
        resultMap.put("resultUser",listUser.size());
        resultMap.put("listResultUser",listUser);
        return resultMap;
    }

    /**
     * 계정등록
     *
     * @param userDetail the user detail
     * @param response   the response
     * @return map
     * @throws IOException        the io exception
     * @throws MessagingException the messaging exception
     */
    @RequestMapping(value = {"/authUser"},  method = RequestMethod.POST)
    public Map<String, Object> authUser(@RequestBody UserDetail userDetail, HttpServletResponse response) throws IOException, MessagingException {
        HashMap body = new HashMap();
        Map<String, Object> resultMap = new HashMap();

        body.put("userId", userDetail.getUserId());
//        body.put("password", userDetail.getPassword());
        body.put("refreshToken", userDetail.getRefreshToken());
//        body.put("password", userDetail.getPassword());
//        body.put("password", userDetail.getPassword());

        LOGGER.info("userId : " + userDetail.getUserId() + " : request : " + response.toString());
        List<Map<String, Object>> listUser = userService.getUserDetailInfo(body);

        resultMap.put("resultUser",listUser.size());
        return resultMap;
    }

    /**
     * Update user map.
     *
     * @param userId   the user id
     * @param body     the body
     * @param response the response
     * @return Map { "result": updateCount}
     * @throws Exception the exception
     */
    @RequestMapping(value = {"/updateUser/{userId:.+}"}, method = RequestMethod.PUT, consumes="application/json")
    public Map updateUser(@PathVariable String userId, @RequestBody Map<String, Object> body, HttpServletResponse response) throws Exception{

        LOGGER.info("> into updateUser...");

        UserDetail user = null;
        Map<String, Object> result = new HashMap<>();

        user = userService.getUser(userId);

        if( user == null ) {
            response.sendError(HttpServletResponse.SC_BAD_REQUEST, "User does not exist.");
        } else {

            if ( body.containsKey("userName") )         user.setUserName((String)body.get("userName"));
            if ( body.containsKey("status") )           user.setStatus((String) body.get("status"));
            if ( body.containsKey("addressDetail") )    user.setAddressDetail((String) body.get("addressDetail"));
            if ( body.containsKey("address") )          user.setAddress((String) body.get("address"));
            if ( body.containsKey("tellPhone") )        user.setTellPhone((String) body.get("tellPhone"));
            if ( body.containsKey("zipCode") )          user.setZipCode((String) body.get("zipCode"));
            if ( body.containsKey("adminYn") )          user.setAdminYn((String) body.get("adminYn"));
            if ( body.containsKey("imgPath") ) {
//                if (user.getImgPath() != null) glusterfsService.delete(user.getImgPath());
                user.setImgPath((String) body.get("imgPath"));
            }
            int cnt = userService.updateUser(userId, user);
            result.put("result", cnt);
        }
        return result;
    }


}
