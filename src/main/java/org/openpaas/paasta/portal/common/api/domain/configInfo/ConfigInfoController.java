package org.openpaas.paasta.portal.common.api.domain.configInfo;

import org.openpaas.paasta.portal.common.api.entity.portal.ConfigInfo;
import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.ws.rs.DELETE;
import javax.ws.rs.PathParam;
import java.util.HashMap;
import java.util.Map;

import static org.slf4j.LoggerFactory.getLogger;

/**
 * Created by indra on 2018-02-22.
 */
@RestController
@RequestMapping("/configInfo")
public class ConfigInfoController {

    private static final Logger logger = getLogger(ConfigInfoController.class);

    @Autowired
    private ConfigInfoService configInfoService;


    /**
     * 설정 정보 값을 조회한다.
     *
     * @param name - - primary key, configInfo the config info
     * @return value value
     * @throws Exception the exception
     */
    @GetMapping("/{name:.+}")
    public Map<String, Object> getValue(@PathVariable String name, @RequestBody ConfigInfo configInfo) {
        return configInfoService.getValue(configInfo);
    }

    /**
     * 설정 정보 값을 생성한다..
     *
     * @param configInfo -
     * @return map map
     * @throws Exception the exception
     */
    @PostMapping()
    public Map<String, Object> insertValue(@RequestBody ConfigInfo configInfo) {
        return null;
    }


    /**
     * 설정 정보 값을 수정한다.
     *
     * @param name - primary key, configInfo the config info
     * @return map map
     * @throws Exception the exception
     */
    @PutMapping(value = {"/{name:.+}"})
    public Map<String, Object> updateValue(@PathVariable String name, @RequestBody ConfigInfo configInfo) {
        Map<String, Object> resultMap = new HashMap<>();
        String resultStr = configInfoService.updateValue(configInfo);
        resultMap.put("RESULT", resultStr);
        return resultMap;
    }

    /**
     * 설정 정보 값을 삭제한다.
     *
     * @param name - primary key
     * @return map map
     * @throws Exception the exception
     */
    @DeleteMapping(value = {"/{name:.+}"})
    public Map<String, Object> deleteValue(@PathVariable String name) {
        return null;
    }


}
