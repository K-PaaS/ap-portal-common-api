package org.openpaas.paasta.portal.common.api.domain.commonCode;

import org.openpaas.paasta.portal.common.api.entity.portal.CodeDetail;
import org.openpaas.paasta.portal.common.api.entity.portal.CodeGroup;
import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

import static org.slf4j.LoggerFactory.getLogger;

/**
 * Created by SEJI on 2018-03-06.
 */
@RestController
public class CommonCodeController {
    private static final Logger logger = getLogger(CommonCodeController.class);

    @Autowired
    private CommonCodeService commonCodeService;

    private final String V2_URL = "/v2";

    /**
     * 공통코드 목록을 조회한다.
     *
     * @param codeDetail CodeDetail(아이디)
     * @return Map(자바클래스)
     */
    @GetMapping(V2_URL+"/codeDetail")
    public Map<String, Object> getCodeDetail(@ModelAttribute CodeDetail codeDetail) {
        return commonCodeService.getCommonCodeDetail(codeDetail);
    }


    /**
     * 공통코드 목록을 조회한다.
     *
     * @param codeDetail CodeDetail(아이디)
     * @return Map(자바클래스)
     */
    @GetMapping(V2_URL + "/codeDetail/{key}")
    public Map<String, Object> getCodeDetail(@PathVariable("key") String key, @ModelAttribute CodeDetail codeDetail) {
        codeDetail.setKey(key);
        return commonCodeService.getCommonCodeDetail(codeDetail);
    }


    /**
     * 공통그룹 목록을 조회한다.
     *
     * @param codeGroup CodeGroup(아이디)
     * @return Map(자바클래스)
     */
    @GetMapping(V2_URL +"/groupDetail")
    public Map<String, Object> getGroupDetail(@ModelAttribute CodeGroup codeGroup) {
        return null;
    }


    /**
     * 공통그룹 목록을 조회한다.
     *
     * @param codeGroup CodeGroup(아이디)
     * @return Map(자바클래스)
     */
    @GetMapping(V2_URL +"/groupDetail/{id}")
    public Map<String, Object> getGroupDetail(@PathVariable("id") String key, @ModelAttribute CodeGroup codeGroup) {
        return null;
    }


    /**
     * 공통코드 및 그룹 목록을 조회한다.
     *
     * @param codeDetail,codeGroup CodeDetail,CodeGroup (모델클래스)
     * @return Map(자바클래스)
     */
    @GetMapping(V2_URL +"/commonCode")
    public Map<String, Object> getCommonCodeDetail(@ModelAttribute CodeDetail codeDetail, @ModelAttribute CodeGroup codeGroup) {
        return commonCodeService.getCommonCodeJoinGroup(codeDetail, codeGroup);
    }


    /**
     * 공통 코드 그룹을 등록한다.
     *
     * @param codeGroup CodeGroup (모델클래스)
     * @return Map(자바클래스)
     */
    @PostMapping(V2_URL +"/groupDetail")
    public Map<String, Object> insertDetailGroup(@RequestBody CodeGroup codeGroup) {
        return commonCodeService.insertDetailGroup(codeGroup);
    }


    /**
     * 공통 코드을 등록한다.
     *
     * @param codeDetail CodeDetail (모델클래스)
     * @return Map(자바클래스)
     */
    @PostMapping(V2_URL +"/codeDetail")
    public Map<String, Object> insertDetail(@RequestBody CodeDetail codeDetail) {
        return commonCodeService.insertDetail(codeDetail);
    }


    /**
     * 공통 코드 그룹을 수정한다.
     *
     * @param codeGroup CodeGroup (모델클래스)
     * @return Map(자바클래스)
     */
    @PutMapping(V2_URL +"/groupDetail/{id}")
    public Map<String, Object> updateCommonGroup(@PathVariable String id, @RequestBody CodeGroup codeGroup) {
        Map<String, Object> resultMap = new HashMap<>();
        String resultStr = commonCodeService.updateCommonGroup(id, codeGroup);
        resultMap.put("RESULT", resultStr);
        return resultMap;
    }


    /**
     * 공통 코드을 수정한다.
     *
     * @param codeDetail CodeDetail (모델클래스)
     * @return Map(자바클래스)
     */
    @PutMapping(V2_URL +"/codeDetail/{no}")
    public Map<String, Object> updateCommonDetail(@PathVariable int no, @RequestBody CodeDetail codeDetail) {
        Map<String, Object> resultMap = new HashMap<>();
        String resultStr = commonCodeService.updateCommonDetail(no, codeDetail);
        resultMap.put("RESULT", resultStr);
        return resultMap;
    }


    /**
     * 공통 코드 그룹을 삭제한다.
     *
     * @param id
     * * @return Map(자바클래스)
     */
    @DeleteMapping(V2_URL +"/groupDetail/{id}")
    public Map<String, Object> deleteCommonGroup(@PathVariable String id) {
        // TODO: 코드 그룹을 삭제할경우 그룹에 해당하는 CodeDetail 삭제하도록 개발하세요~
        return commonCodeService.deleteCommonGroup(id);
    }


    /**
     * 공통 코드을 삭제한다.
     *
     * @param no
     * @return Map(자바클래스)
     */
    @DeleteMapping(V2_URL +"/codeDetail/{no}")
    public Map<String, Object> deleteCommonDetail(@PathVariable int no) {
        return commonCodeService.deleteCommonDetail(no);
    }


}
